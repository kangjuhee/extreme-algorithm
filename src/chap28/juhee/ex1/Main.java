package chap28.juhee.ex1;
//위상정렬

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Juhee on 2/20/16.
 */
public class Main {
    static LinkedList<Integer> adj[];
    static boolean [] visited; //방문여부

    public static void main(String[] args) {
        adj = new LinkedList[6];
        for(int i = 0; i < adj.length; i++)
            adj[i] = new LinkedList();

        adj[0].add(1);
        adj[1].add(5);
        adj[1].add(3);
        adj[1].add(2);
        adj[2].add(4);
        dfsAll();
    }
    //시작한 탐색으로 깊이 탐색
    public static void dfs(int here) {
        System.out.println("dfs visits (index) " + here);
        visited[here] = true;
        for(int i = 0; i < adj[here].size(); i ++) {
//            System.out.println("adj[here].length : " + adj[here].length);
            if(adj[here].get(i) != 0) {
                int there = adj[here].get(i);
                if (!visited[there])
                    dfs(there);
            }
        }
    }
    //초기화 & 탐색 시작
    public static void dfsAll(){
        visited = new boolean[6]; //초기값 false 로 설정됨

        for(int i = 0; i < adj.length; i++) {
            if(!visited[i]) //방문하지않았다면
                dfs(i);
        }
    }
}

/** recursive function
 * */
    /*
    public static void main(String[] args) {
        int a = 4;
        System.out.println(fac(a));
    }
    private static int fac(int a) {
        if ( a == 1)    return a;
        else return fac(a-1) * a;
    }
    */