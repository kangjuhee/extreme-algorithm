package chap29.younghoo.p1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 신호 라우팅
 */
public class Main {

    static ArrayList<VertexMap>[] adj;
    static double[] distance;
    static VertexMap makeVertexMap(int vertex, double distance) {
        return new VertexMap(vertex, distance);
    }

    public static double[] findPath(int src) {
        PriorityQueue<VertexMap> pq = new PriorityQueue<>();
        distance[src] = 1;
        pq.offer(makeVertexMap(src, 0));

        while (!pq.isEmpty()) {
            int here = pq.peek().getVertex();
            double cost = pq.peek().getDistance();
            pq.poll();

            if (distance[here] < cost) {
                continue;
            }

            for (int i = 0; i < adj[here].size(); i++) {

                int there = adj[here].get(i).getVertex();
                double dist = distance[here] * adj[here].get(i).getDistance();

                if (distance[there] > dist) {
                    distance[there] = dist;
                    pq.add(makeVertexMap(there, dist));
                }
            }
        }
        return distance;
    }

    public static void main(String[] args) throws IOException {

        // String path = Main.class.getResource("").getPath();
        // BufferedReader br = new BufferedReader(new FileReader(path + "input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int test = Integer.parseInt(br.readLine());

        for (int t = 0; t < test; t++) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            int numVertex = Integer.parseInt(st.nextToken());
            int numEdge = Integer.parseInt(st.nextToken());

            adj = new ArrayList[numVertex];
            for (int i = 0; i < adj.length; i++) {
                adj[i] = new ArrayList<>();
            }

            distance = new double[numVertex];
            // Arrays.fill(distance, Double.MAX_VALUE);
            for (int i = 0; i < distance.length; i++) {
                distance[i] = Double.MAX_VALUE;
            }

            while (numEdge-- > 0) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                double weight = Double.parseDouble(st.nextToken());
                adj[from].add(makeVertexMap(to, weight));
                adj[to].add(makeVertexMap(from, weight));
            }

            double[] result = findPath(0);
            System.out.format("%.10f", result[numVertex-1]);
            System.out.println();
        }
    }
}
