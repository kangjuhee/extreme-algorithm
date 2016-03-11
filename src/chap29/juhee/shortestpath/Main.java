package chap29.juhee.shortestpath;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 너비 우선 탐색을 통한 최단 경로 찾기
 * Created by Juhee on 3/4/16.
 */
public class Main {

    static int[][] adj; // 인접행렬

    public static void main(String[] args) {
        adj = new int[9][9];
        adj[0][1] = 1;
        adj[0][4] = 1;
        adj[0][5] = 1;
        adj[0][2] = 1;

        adj[1][0] = 1;
        adj[1][3] = 1;
        adj[1][4] = 1;

        adj[2][0] = 1;

        adj[3][1] = 1;
        adj[3][6] = 1;
        adj[3][7] = 1;

        adj[4][0] = 1;
        adj[4][1] = 1;
        adj[4][7] = 1;

        adj[5][0] = 1;
        adj[5][6] = 1;

        adj[6][3] = 1;
        adj[6][5] = 1;

        adj[7][3] = 1;
        adj[7][4] = 1;
        adj[7][8] = 1;

        adj[8][7] = 1;

        // 인접행렬 완성
        bfs(0);
    }

    // start 에서 시작해 그래프를 너비우선 탐색하고 각 정점의 방문 순서를 반환
    //
    public static void bfs(int start) {
        int here;
        int[] distance = new int[9]; // distance
        int[] parent = new int[9];
        Queue<Integer> q = new LinkedList<>();

        for(int i = 0; i < distance.length; i++)
            distance[i] = -1;

        q.add(start);
        distance[start] = 0;
        parent[start] = start;

        while(!q.isEmpty()) {
//            here = q.peek();
//            if(here != 0) {
//                parent[here] = here;
//                distance[here] = distance[parent[here]] + 1;
//            }
            here = q.poll();

            for(int i = 0; i < adj[here].length; i++) {
                if(adj[here][i] == 1) {
                    int there = i;
                    if (distance[there] == -1) {
                        q.add(i);
                        distance[there] = distance[here] + 1;
                        parent[there] = here;
                    }
                }
            }
           // System.out.println("D");
            //bfs(q.peek());
        }
        for (int i = 0; i < distance.length; i++) {
            System.out.print(distance[i] + " ");
        }
        System.out.println();
        int parentIndex;

      //  parentIndex = parent[7];
        parentIndex = 3;
        while(parentIndex != -1) {
            if(parentIndex == parent[parentIndex]) {
                System.out.print(parentIndex + "");
                break;
            }
            System.out.print(parentIndex + " --> ");
            parentIndex = parent[parentIndex];
        }
    }
}
