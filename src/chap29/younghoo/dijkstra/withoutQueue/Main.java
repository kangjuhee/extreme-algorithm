package chap29.younghoo.dijkstra.withoutQueue;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Priority Queue를 사용하지 않는 Dijkstra algorithm
 */
public class Main {

    static ArrayList<VertexMap>[] adj;
    static int[] distance;
    static boolean[] visited;
    static Integer NUM_OF_VERTEX = 4;
    static VertexMap makeVertexMap(int distance, int vertex) {
        return new VertexMap(distance, vertex);
    }

    public static int[] dijkstra(int src) {
        makeGraph();
        distance = new int[NUM_OF_VERTEX];
        visited = new boolean[NUM_OF_VERTEX];

        Arrays.fill(distance, Integer.MAX_VALUE);
        Arrays.fill(visited, false);

        distance[src] = 0;

        while (true) {

            int closest = Integer.MAX_VALUE;
            int here = -1;

            for (int i = 0; i < 4; i++) {
                if (distance[i] < closest && !visited[i]) {
                    here = i;
                    closest = distance[i];
                }
            }

            if (closest == Integer.MAX_VALUE) {
                break;
            }

            visited[here] = true;

            for (int i = 0; i < adj[here].size(); i++) {
                int there = adj[here].get(i).getVertex();

                if (visited[there]) {
                    continue;
                }

                int dist = distance[here] + adj[here].get(i).getDistance();
                distance[there] = Math.min(distance[there], dist);
            }
        }
        return distance;
    }

    public static ArrayList<VertexMap>[] makeGraph() {
        adj = new ArrayList[NUM_OF_VERTEX];
        for (int i = 0; i < NUM_OF_VERTEX; i++) {
            adj[i] = new ArrayList<>();
        }
        adj[0].add(makeVertexMap(2, 1));
        adj[0].add(makeVertexMap(12, 2));
        adj[1].add(makeVertexMap(2, 1));
        adj[1].add(makeVertexMap(4, 3));
        adj[2].add(makeVertexMap(12, 0));
        adj[2].add(makeVertexMap(3, 3));
        adj[3].add(makeVertexMap(4, 1));
        adj[3].add(makeVertexMap(3, 2));

        return adj;
    }

    public static void main(String[] args) {
        int[] result = dijkstra(0);
        Arrays.stream(result).forEach(System.out::println);
    }
}
