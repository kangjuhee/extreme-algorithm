package chap29.younghoo.dijkstra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {
    /**
     * Dijkstra algorithm
     *
     * Dijkstra algorithm에서는 bfs와 달리 Priority queue를 사용한다.
     * {@code Map<K, V>}
     * K: vertex index,
     * V: 시작점으로부터 해당 vertex까지의 거리
     */
    static ArrayList<VertexMap>[] adj;
    static PriorityQueue<VertexMap> pq;
    static int[] distance;
    static int[] parent; // implement later...
    static Integer NUM_OF_VERTEX = 4;

    static VertexMap makeVertexMap(int distance, int vertex) {
        return new VertexMap(distance, vertex);
    }

    public static void dijkstra(int start) {
        // 초기화(priority queue, distance)
        pq = new PriorityQueue<>();
        distance = new int[NUM_OF_VERTEX];
        Arrays.fill(distance, Integer.MAX_VALUE);

        distance[start] = 0;
        pq.offer(makeVertexMap(0, start));

        while (!pq.isEmpty()) {
            int cost = pq.peek().getDistance();
            int here = pq.peek().getVertex();
            pq.poll();

            if (distance[here] < cost) {
                continue;
            }

            /**
             * {@code dist = cost + adj[here].get(i).getDistance()}
             * {@code dist}: 시작점부터 there까지의 거리
             * {@code cost}: 시작점부터 here까지의 거리
             * {@code adj[here].get(i).getDistance()}: here부터 there까지의 거리
             */
            for (int i = 0; i < adj[here].size(); i++) {
                int there = adj[here].get(i).getVertex();
                int dist = cost + adj[here].get(i).getDistance();
                if (distance[there] > dist) {
                    distance[there] = dist;
                    pq.add(makeVertexMap(dist, there));
                }
            }
        }
    }

    public static void main(String[] args) {

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

        dijkstra(0);
    }
}
