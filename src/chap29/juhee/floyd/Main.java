package chap29.juhee.floyd;

/**
 * Created by Juhee on 3/9/16.
 */

import java.util.ArrayList;

/**
 * Floyd-warshall Algorhitm
 * 모든 쌍의 최단거리 알고리즘
 * */

public class Main {
    static int V = 7;

    //adj[u][v]:u에서 v로 가는 간선의 가중치 간선이 없으면 아주 큰 값을 넣는다
    static int[][] adj;
    static int[][] via;
    static ArrayList<Integer> path;

    public static void main(String[] args)
    {
        adj = new int [V][V];
        via = new int [V][V];
        path = new ArrayList<>();

        adj[0][1] = 5;
        adj[1][0] = 5;

        adj[1][5] = 3;
        adj[5][1] = 3;

        adj[1][6] = 3;
        adj[6][1] = 3;

        adj[0][2] = 1;
        adj[2][0] = 1;

        adj[2][3] = 2;
        adj[3][2] = 2;

        adj[3][1] = 1;
        adj[1][3] = 1;

        adj[3][4] = 5;
        adj[4][3] = 5;

        adj[3][6] = 3;
        adj[6][3] = 3;

        adj[5][6] =2;
        adj[6][5] =2;

/*
        //1. u->v 로 가는 정점에 가중치, 없으면 큰값을 넣음
        adj[0][1] =5;
        adj[1][0] =5;

        adj[0][2] = 1;
        adj[2][0] = 1;

        adj[1][2] = 2;
        adj[2][1] = 2;
*/
        for(int i = 0; i < V; i++) {
            for(int j = 0; j < V; j++) {
                if ((adj[i][j] == 0) && (i != j))
                    adj[i][j] = 100;

            }
        }
        /*
        for(int i = 0; i < V; i++) {
            for(int j = 0; j < V; j++) {
                System.out.print(adj[i][j] +"  ");
            }
            System.out.println();
        }
        System.out.println("----------------------");
        */
        floyd2();
        /*
        for(int i = 0; i < V; i++) {
            for(int j = 0; j < V; j++) {
                System.out.print(adj[i][j] +"  ");
            }
            System.out.println();
        }
        System.out.println("----------------------");

        for(int i = 0; i < V; i++) {
            for(int j = 0; j < V; j++) {
                System.out.print(via[i][j] +" ");
            }
            System.out.println();
        }
        */

       reconstruct(0, 4, path);

        for(int i = 0; i < path.size(); i++) System.out.print(path.get(i) + " -> ");
     }

    static void floyd2(){
        for(int i = 0; i < V; i++) adj[i][i] = 0; //  u->u 는 0

        for(int i = 0; i < V; i++)
            for(int j  =0; j < V; j++)
                via[i][j] = -1;

        for(int k = 0; k < V; k++)
            for(int i = 0; i < V; i++)
                for(int j = 0; j < V; j++)
                    if(adj[i][j] > adj[i][k] + adj[k][j]) { // 1. 돌아가는 것이 빠르면
                        via[i][j] = k;
                        adj[i][j] = adj[i][k] + adj[k][j];
                    }
    } // end of the method

    static void reconstruct(int u, int v, ArrayList<Integer> path) {
        if(via[u][v] == -1) {
            path.add(u);
            if(u != v) path.add(v);
        } else {
            int w = via[u][v];
            reconstruct(u, w, path);
            path.remove(path.size()-1);
            reconstruct(w,v, path);
        }
    }// end of method
}
