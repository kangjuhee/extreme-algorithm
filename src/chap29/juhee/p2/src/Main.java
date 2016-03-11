package chap29.juhee.p2.src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * Created by Juhee on 3/8/16.
 */
public class Main {
    // maxint를 double 형으로 할 것 안그럼 예외사항이 발생
   // static int n,m;
    static int computer, edge;
    static double MAXINT = Double.MAX_VALUE;
    public static ArrayList<VertexMap>[] list;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("input30.txt"));

        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // StringTokenizer st = new StringTokenizer(br.readLine());
        int testCase = Integer.parseInt(br.readLine()); // 테스트 케이스 갯수
        while(testCase --> 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            computer = Integer.parseInt(st.nextToken());
            edge = Integer.parseInt(st.nextToken());
            int a, b;
            double c;

            list = new ArrayList[computer];
            for(int j = 0; j < list.length; j++) {
                list[j] = new ArrayList<>();
            } //1. 컴퓨터 갯수만큼 리스트 객체화

            //2. edge 갯수만큼 입력
            for(int j = 0; j < edge; j++) {
                st = new StringTokenizer(br.readLine());
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());
                c = Double.parseDouble(st.nextToken());
                list[a].add(new VertexMap(b, c));
                list[b].add(new VertexMap(a, c));
            }
            dijkstra(0);
        }
    } // end of main

    public static void dijkstra(int src) {
        PriorityQueue<VertexMap> pq = new PriorityQueue<>();
        double[] dist = new double[computer];
        for(int i = 0; i < dist.length; i++) {
            dist[i] = MAXINT;
          //  System.out.println("dust[i] : " +  dist[i]);
        }
//        System.out.println("------ " + MAXINT);
//        System.out.println("====== " + Double.MAX_VALUE);
        dist[src] = 1;
        pq.add(new VertexMap(src, dist[src]));
        while(!pq.isEmpty()) {
            double cost = pq.peek().getDistance();
            int here = pq.peek().getVertex();
            pq.poll();

            if(dist[here] < cost) continue;

            for(int i = 0; i < list[here].size(); i++) {
                int there = list[here].get(i).getVertex();
                double nextDist =  dist[here] * list[here].get(i).getDistance();

                if(dist[there] > nextDist) {
                    dist[there] = nextDist;
                    pq.add(new VertexMap(there, nextDist));
                }
            }
        }
        System.out.format("%.10f",dist[computer-1]);
        System.out.println();
    }
}
