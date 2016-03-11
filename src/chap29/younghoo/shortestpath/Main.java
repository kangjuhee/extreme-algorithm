package chap29.younghoo.shortestpath;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * 너비 우선 탐색을 이용한 최단 경로 찾기
 */
public class Main {

    static ArrayList<Integer>[] adj; // 인접 행렬
    static int[] distance; // 시작점부터의 거리
    static int[] parent; // 너비 우선 탐색 스패닝 트리에서 해당 정점의 부모의 번호. 루트인 경우 자신의 번호
    static LinkedList<Integer> shortestPath;
    static Queue<Integer> q; // 방문할 정점 목록을 유지하는 큐

    public static void bfs(int start) {
        distance = new int[adj.length];
        parent = new int[adj.length];
        Arrays.fill(distance, -1);
        distance[start] = 0;
        parent[start] = start;
        q = new LinkedList<>();
        q.offer(start);

        while (!q.isEmpty()) {
            int here = q.poll();
            for (int i = 0; i < adj[here].size(); i++) {
                int there = adj[here].get(i);
                if (distance[there] == -1) { // 방문하지 않은 정점이라면 queue에 추가한다.
                    q.offer(there);
                    distance[there] = distance[here] + 1;
                    parent[there] = here;
                }
            }
        }
    }

    /**
     * 너비 우선 탐색 스패닝 트리에서 시작점부터 정점 v까지의 최단 경로를 찾는다.
     * @param v
     * @return
     */
    public static List<Integer> getShortestPath(int v) {
        shortestPath = new LinkedList<>();
        shortestPath.offer(v);

        while (parent[v] != v) { // 시작 정점에 도달할 때까지 v를 계속해서 업데이트하며 shortestPath 리스트에 추가시킨다.
            v = parent[v]; // update v
            shortestPath.offer(v); // add vertex to shortest path list
        }

        Collections.reverse(shortestPath);
        return shortestPath;
    }

    public static void main(String[] args) throws IOException {

        String filePath = Main.class.getResource("").getPath();
        BufferedReader br = new BufferedReader(new FileReader(filePath + "/input.txt"));

        int numVertex = Integer.parseInt(br.readLine().trim());
        adj = new ArrayList[numVertex];
        for (int i = 0; i < adj.length; i++) {
            adj[i] = new ArrayList<>();
        }

        String input;
        while ((input = br.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(input);
            adj[Integer.parseInt(st.nextToken())].add(Integer.parseInt(st.nextToken()));
        }

        bfs(0);
        getShortestPath(8).forEach(System.out::println);
    }
}
