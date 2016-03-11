package chap29.younghoo.floyd;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Floyd algorithm
 */
public class Main {
    // MAX_VALUE을 사용하는 경우 덧셈 연산 시 정수 범위를 벗어나 마이너스 값이 반환되어 대소비교 문제가 발생한다.
    static Integer MAX = 100000;

//    static int[][] adj = {
//            {0, 2, MAX, 12},
//            {2, 0, 3, MAX},
//            {MAX, 3, 0, 4},
//            {12, MAX, 4, 0}
//    };

    static double[][] adj = {
            {0, 3, 1, MAX},
            {3, 0, 0.3, 2},
            {1, 0.3, 0, 1},
            {MAX, 2, 1, 0}
    };

    static int[][] transit;
    static ArrayList<Integer> path;

    public static void floyd() {
        int V = adj[0].length;

        for (int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    // System.out.println("adj["+i+"]"+"["+j+"]"+" : " + adj[i][j]);
                    // System.out.println("adj["+i+"]"+"["+k+"]"+"+"+"adj["+k+"]"+"["+j+"]"+" : " + (adj[i][k] + adj[k][j]));
                    if (adj[i][j] > (adj[i][k] + adj[k][j])) {
                        transit[i][j] = k;
                        adj[i][j] = adj[i][k] + adj[k][j];
                    }
                }
            }
        }
    }

    public static void findPath(int s, int t, ArrayList<Integer> path) {
        if (transit[s][t] == -1) {
            path.add(s);
            if (s != t) {
                path.add(t);
            }
        } else {
            int u = transit[s][t]; // s에서 t로 갈 때 경유하는 점
            findPath(s, u, path);
            // path.remove(u);
            path.remove(path.size()-1);
            findPath(u, t, path);
        }
    }

    public static void printGraph() {
        System.out.println("Adjoint Matrix");
        for (double[] i : adj) {
            for (double j : i) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }

    public static void printTransit() {
        System.out.println("Transit");
        for (int[] i : transit) {
            for (int j : i) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }

    public static void printPath() {
        System.out.println("Shortest Path");
        path.stream().forEach((i) -> System.out.print(i + " "));
        System.out.println();
    }

    public static void main(String[] args) {

        transit = new int[4][4];
        for (int i = 0; i < 4; i++) {
            Arrays.fill(transit[i], -1);
        }
        path = new ArrayList<>();

        floyd();
        printGraph();
        printTransit();
        findPath(0, 3, path);
        printPath();
    }
}