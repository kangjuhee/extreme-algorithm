package chap28.younghoo.p2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 끝말잇기
 *
 * Created by 01032286016 on 2016. 2. 26..
 */
public class Main {
    /**
     * 이 문제의 "핵심 포인트"는 시작점을 찾는 것이라고 생각한다.
     *
     *
     * 입력 차수와 출력 차수의 차가 0인 경우이 모두 짝수인 경우에는 오일러 순환이므로 시작점은 출력 차수 배열의 값이 0이 아닌 값인 지점 어느 곳에서나 출발이
     * 가능하지만 홀수인 경우에는 시작점을 찾아야 한다.
     *
     *
     * adj[26][26] : 행값은 노드의 출발점, 열값은 노드의 끝점을 의미한다. 예를 들어 adj[0][1]은 0번 노드에서 1번 노드로 갈 수 있다는 의미이다.
     * 다시 말해 0번 노드와 1번 노드는 0에서 1로 가는 간선으로 연결되어 있다는 의미이다.
     * in[26] : 입력 차수 배열
     * out[26] : 출력 차수 배열
     * words[26][26] :
     */
    static int[][] adj;
    static int[] in;
    static int[] out;
    static List<String>[][] words;
    static ArrayList<Integer> result;

    public static void makeGraph(String str) {
        words[str.charAt(0) - 97][str.charAt(str.length()-1) - 97].add(str);
        adj[str.charAt(0) - 97][str.charAt(str.length()-1) - 97]++;
        in[str.charAt(str.length()-1) - 97]++;
        out[str.charAt(0) - 97]++;
    }

    public static int findStartPoint() {

        int startPoint;
        int evenStartPoint = -1;
        int order;

        for (int i = 0; i < 26; i++) {

            order = in[i] - out[i];

            if (order == -1) {
                startPoint = i;
                return startPoint;
            }

            if (out[i] != 0) {
                evenStartPoint = i;
            }
        }
        return evenStartPoint;
    }

    public static void getEulerCircuit() {

        int startPoint = findStartPoint();

        findPath(startPoint);

        if (!hasPath()) {
            System.out.println("IMPOSSIBLE");
            return;
        } else {
            Collections.reverse(result);

            for (int i = 0; i < result.size() - 1; i++) {
                System.out.print(words[result.get(i)][result.get(i+1)].get(words[result.get(i)][result.get(i+1)].size() - 1) + " ");
                words[result.get(i)][result.get(i+1)].remove(words[result.get(i)][result.get(i+1)].size() - 1);
            }
            System.out.println();
        }
    }

    public static boolean hasPath() {
        for (int i = 0; i < adj.length; i++) {
            for (int j = 0; j < adj.length; j++) {
                if (adj[i][j] != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void findPath(int here) {

        for (int there = 0; there < adj.length; there++) {
            while (adj[here][there] > 0) {
                adj[here][there]--;
                findPath(there);
            }
        }
        result.add(here);
    }

    public static void main(String[] args) throws IOException {

        String filePath = Main.class.getResource("").getPath();
        BufferedReader br = new BufferedReader(new FileReader(filePath + "/input.txt"));
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test = Integer.parseInt(br.readLine().trim());

        for (int t = 0; t < test; t++) {
            int numWords = Integer.parseInt(br.readLine());

            // 초기화
            words = new List[26][26];
            adj = new int[26][26];
            result = new ArrayList<>();
            for (int i = 0; i < words.length; i++) {
                for (int j = 0; j < words.length; j++) {
                    words[i][j] = new ArrayList<>();
                }
            }
            in = new int[26];
            out = new int[26];

            for (int s = 0; s < numWords; s++) {
                makeGraph(br.readLine());
            }
            getEulerCircuit();
        }
    }
}
