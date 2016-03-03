package chap28.younghoo.ex2;

/**
 * Topological Sorting
 */
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    static List<Integer>[] adj = new ArrayList[10];
    static boolean[] visited = new boolean[10];

    static {
        for (int i = 0; i < 10; i++) {
            adj[i] = new ArrayList<>();
        }
        Arrays.fill(visited, false);
    }

    public static void dfsAll() {
        for(int i = 0; i < adj.length; i++) {
            if (!visited[i]) {
                dfs(i);
                System.out.println(i);
            }
        }
    }

    public static void dfs(int here) {

        visited[here] = true;

        for (int i = 0; i < adj[here].size(); i++) {
            int there = adj[here].get(i);
            if (!visited[there]) {
                dfs(there);
                System.out.println(there);
            }
        }
    }

    public static void main(String[] args) throws IOException {

        adj[0].add(1);
        adj[0].add(2);
        adj[0].add(3);
        adj[1].add(4);
        adj[4].add(5);
        adj[2].add(5);
        adj[3].add(8);
        adj[5].add(7);
        adj[6].add(7);
        adj[7].add(8);
        adj[8].add(9);

        System.out.println("탐색 시작");
        dfsAll();
    }
}