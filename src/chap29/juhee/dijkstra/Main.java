package chap29.juhee.dijkstra;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * Created by Juhee on 3/8/16.
 *  다익스트라
 */
public class Main {
    static int V = 4; // 정점의 갯수
    static int intMax = Integer.MAX_VALUE;
    public static ArrayList<VertexMap>[] list;
/*
    public static VertexMap makeVertexMap(int vertex, int distance) {
        return new VertexMap(vertex, distance);
    }
*/
    public static void main(String[] args) {

        list = new ArrayList[4];
        for (int i = 0; i < list.length; i++) {
            list[i] = new ArrayList<>();
        }
        list[0].add(new VertexMap(1, 4));
        list[0].add(new VertexMap(0, 3));

        list[1].add(new VertexMap(0, 4));
        list[1].add(new VertexMap(2, 3));

        list[2].add(new VertexMap(1, 3));
        list[2].add(new VertexMap(3, 12));

        list[3].add(new VertexMap(0, 2));
        list[3].add(new VertexMap(2, 12));

        //Queue를 이용한 다익익스트라
        //dijkstra_queue(3);

        //For문을 이용한 다익스트라
        dijkstra_for(3);


    }

    public static void dijkstra_for(int src) {
        int[] dist = new int[V];
        boolean[] visited = new boolean[V];

        for(int i = 0; i < dist.length; i++) {
            dist[i] = intMax;
            visited[i] = false;
        }

        dist[src] = 0;
        // visited[src] = true;

        while(true) {
            int closest = intMax;
            int here = src;

            for(int i = 0; i < V; i++) {
              //  System.out.println("dist : " + dist[i] +" closet : " + closest);
                if ((dist[i] < closest) && !visited[i]) { //방문하지않은것
                    here = i;
                    closest = dist[i];
                }
               // System.out.println("--- here : closet : " + here +" / " +  closest);
            }

            if(closest == intMax) break;

            visited[here] = true;
            for(int i = 0; i < list[here].size(); i++) {
                int there = list[here].get(i).getVertex();
                if(visited[there]) continue;
                int nextDist = dist[here] + list[here].get(i).getDistance();
                dist[there] = Integer.min(dist[there], nextDist);
            }

          }
        for(int i = 0; i < dist.length; i++)
            System.out.println(dist[i]);

    }
    public static void dijkstra_queue(int src) {
        PriorityQueue<VertexMap> pq = new PriorityQueue<>();
        int[] dist = new int[4];
        dist[0] = 99;
        dist[1] = 99;
        dist[2] = 99;
        dist[3] = 99;

        dist[src] = 0;        //pq.add(new VertexMap(0, 5));
        pq.add(new VertexMap(src, dist[src]));
        while(!pq.isEmpty()) {
            int cost = pq.peek().getDistance();
            int here = pq.peek().getVertex();
           // System.out.println("HERE here : " +  here + " FIRST COST : " + cost );
            pq.remove();

            if(dist[here] < cost) continue;   // 이럴 경우가???

            for(int i = 0; i < list[here].size(); i++) {
                int there = list[here].get(i).getVertex();
                int nextDist = dist[here] + list[here].get(i).getDistance();

              //  System.out.println(i+" th, here : " + here + " nextDIst : " +  nextDist);
                if(dist[there] > nextDist) { //원래 경로보다 업뎃 된 경로가 빠를 경우
                    dist[there]  = nextDist;
                    pq.add(new VertexMap(there, nextDist));
                }
            }  // END OF FOR

        }
        for(int i = 0; i < dist.length; i++) {
            System.out.println(dist[i]);
        }
    }
}
