package chap28.juhee.ex2;

import java.io.LineNumberReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by Juhee on 3/3/16.
 */
//  그래프 너비 우선 탐색
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

//        adj = new LinkedList[6][6]; //
//        for(int i = 0; i < 6; i++) {
//            for(int j = 0; j < 6; j++) {
//                adj[i][j] = new LinkedList<>();
//            }
//        }
//        adj[0][1].add(1);
//        adj[1][0].add(1);
//
//        adj[0][4].add(1);
//        adj[4][0].add(1);
//
//        adj[0][5].add(1);
//        adj[5][0].add(1);
//
//        adj[0][9].add(1);
//        adj[9][0].add(1);
//
//        adj[5][6].add(1);
//        adj[6][5].add(1);
//
//        adj[1][3].add(1);
//        adj[3][1].add(1);
//
//        adj[1][4].add(1);
//        adj[4][1].add(1);
//
//        adj[6][3].add(1);
//        adj[3][6].add(1);
//
//        adj[3][7].add(1);
//        adj[7][3].add(1);
//
//        adj[4][7].add(1);
//        adj[7][4].add(1);
//
//        adj[7][8].add(1);
//        adj[8][7].add(1);
        // 인접행렬 완성
        bfs(0);

    }

    // start 에서 시작해 그래프를 너비우선 탐색하고 각 정점의 방문 순서를 반환
    public static void bfs(int start) {
        boolean[] discovered = new boolean[adj.length]; // 방문여부 --> 초기값 false
        Queue<Integer> q = new LinkedList<>();// 방문할 정점 목록을 유지하는 큐
        List<Integer> order = new ArrayList<>();//정점의 방문순서


        discovered[start] = true;
        q.add(start);
        while(!q.isEmpty()) {
            int here = q.peek();
            q.remove();
            order.add(here); // 방문후
            for(int i =0; i < adj[here].length; i++) {
                if (adj[here][i] != 0) {
                    int there = i;
                    if(!discovered[there]) { //here-> there 경로중 there 을 방문하지않았다면
                        q.add(there);
                        discovered[there] = true;
                    } // end of if
                }
            } //  end of for

        }
        for(int i = 0; i < order.size(); i++)
            System.out.print(order.get(i) + " ");
    }


}
