package chap29.younghoo.bfs;

import java.util.*;

public class Main {

    static int[][] adj = {
            {0, 1, 1, 1, 1, 0, 0, 0, 0},
            {1, 0, 0, 0, 0, 0, 0, 0, 0},
            {1, 0, 0, 0, 0, 1, 0, 0, 0},
            {1, 0, 0, 0, 1, 0, 1, 0, 0},
            {1, 0, 0, 1, 0, 0, 0, 0, 0},
            {0, 0, 1, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 1, 0, 1, 0, 1, 0},
            {0, 0, 0, 0, 0, 0, 1, 0, 1},
            {0, 0, 0, 0, 0, 0, 0, 1, 0}
    }; // 인접행렬
    static Queue<Integer> q; // 방문할 노드들의 순서를 저장하는 큐
    static boolean[] discovered;
    static List<Integer> result; // 결과리스트

    public static void bfs(int start) {

        discovered[start] = true;
        q.add(start); // queue에 초기 시작점이 저장됨
        int there = -1;

        while (!q.isEmpty()) {

            int here = q.poll();
            result.add(here);

            for (int i = 0; i < adj.length; i++) { // 자신과 인접한 노드(하나의 간선으로 이어진)들을 방문한다.

                if (adj[here][i] != 0) {
                    there = i;
                    if (!discovered[there]) { // 방문하지 않은 노드라면 큐에 추가시킨다.
                        q.add(there);
                        discovered[there] = true;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        // 변수 초기화
        q = new LinkedList<>();
        discovered = new boolean[adj.length];
        Arrays.fill(discovered, false);
        result = new ArrayList<>();

        bfs(0);
        result.stream().forEach(System.out::println);
        for (int i : result) {
            System.out.println(i);
        }
    }
}
