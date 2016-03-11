package chap29.younghoo.ex1;

import java.util.*;

/**
 * Shortest path 연습
 */
public class Main {
    static List<Integer> shortestPath;
    static int[][] adj = {
            {0, 1, 1, 1, 1, 0, 0, 0, 0},
            {1, 0, 0, 0, 0, 0, 0, 0, 0},
            {1, 0, 0, 0, 0, 1, 0, 0, 0},
            {1, 0, 0, 0, 1, 0, 1, 0, 0},
            {1, 0, 0, 1, 0, 0, 0, 1, 0},
            {0, 0, 1, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 1, 0, 1, 0, 1, 0},
            {0, 0, 0, 0, 1, 0, 1, 0, 1},
            {0, 0, 0, 0, 0, 0, 0, 1, 0}
    }; // 인접행렬

    /**
     *
     * @param start
     * @return
     */
    public static int[] bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        int[] distance = new int[adj[0].length];
        int[] parent = new int[adj[0].length];
        Arrays.fill(distance, -1);
        q.offer(start);
        distance[start] = 0;
        parent[start] = start;

        while (!q.isEmpty()) {
            int here = q.poll();

            for (int i = 0; i < adj[here].length; i++) {
                if (adj[here][i] == 1) {
                    int there = i;
                    if (distance[there] == -1) { // 아직 방문하지 않은 정점
                        distance[there] = distance[here] + 1;
                        parent[there] = here;
                        q.offer(there);
                    }
                }
            }
        }

        return parent;
    }

    /**
     *
     * @param vertex
     * @param parent
     * @return
     */
    public static List<Integer> getShortestPath(int vertex, int[] parent) {
        shortestPath = new ArrayList<>();
        shortestPath.add(vertex);
        while (parent[vertex] != vertex) {
            vertex = parent[vertex];
            shortestPath.add(vertex);
        }
        Collections.reverse(shortestPath);
        return shortestPath;
    }

    public static void main(String[] args) {
        getShortestPath(8, bfs(0)).forEach(System.out::println);
    }
}
