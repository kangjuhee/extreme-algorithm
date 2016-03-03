package chap23.juhee.p1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
//test
/**
 * 1.자료형 int->long  범위를 잘 고려해서 작성할 것
 * 2. priorityQueue library
 */
public class Main {
    public static void main(String[] args) throws IOException{
        int FIXED_VAL = 20090711;

        ArrayList<Integer> x = new ArrayList<Integer>();
        ArrayList<Integer> y = new ArrayList<Integer>();
        Queue<Integer> queue = new LinkedList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCase = Integer.parseInt(br.readLine().trim()); // 테스트 케이스 갯수
//        ArrayList<Integer> total;
//        ArrayList<Integer> val1;
//        ArrayList<Integer> val2;

        for (int s = 0; s < testCase; s++) {
            PriorityQueue<Max> maxHeap = new PriorityQueue<Max>();
            PriorityQueue<Min> minHeap = new PriorityQueue<Min>();

//            total = new ArrayList<Integer>();
//            val1 = new ArrayList<Integer>();
//            val2 = new ArrayList<Integer>();

            StringTokenizer st = new StringTokenizer(br.readLine());
//            total.add(Integer.parseInt(st.nextToken()));
//            val1.add(Integer.parseInt(st.nextToken())); // 32라는 숫자가 val1에 대입된다.
//            val2.add(Integer.parseInt(st.nextToken()));

            int total = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            long input = 1983;
            long mid;

            mid = input;
//            a = val1.get(s);
//            b = val2.get(s);

            //1. 최대 힙의 크기는 최소희 힙의 크기와 같거나 하나 더 크다.
            for (int j = 0; j < total; j++) {
                if (maxHeap.size() == minHeap.size())
                    maxHeap.add(new Max(input));
                else
                    minHeap.add(new Min(input));
                input = (input * (long) a + (long) b) % (long) FIXED_VAL;

                //2. 최대 힙의 최대원소는 최소의 힙의 최소 원소보다 작거나 같다.
                //   System.out.println("maxheap의 max : " + maxHeap.peek().getNum());
                //   System.out.println("minheap의 min :" + minHeap.peek().getNum());
                long maxTop = maxHeap.peek().getNum();
                long minTop;

                // if문을 두번으로 나눈이유 : 조건을 다같이 쓰면 minheap에서 걸린다
                if (!minHeap.isEmpty() && !maxHeap.isEmpty()) {
                    minTop = minHeap.peek().getNum();
                    if (maxTop > minTop) {
                        long p = maxHeap.poll().getNum();
                        long q = minHeap.poll().getNum();
                        maxHeap.add(new Max(q));
                        minHeap.add(new Min(p));
                    }

                    maxTop = maxHeap.peek().getNum();

                    mid = (mid + maxTop) % FIXED_VAL;
                } // End of if
            } // end of for
            // System.out.println("max " + maxHeap.size() + " , min : " + minHeap.size());
            // System.out.println("final answer is : " + mid);
            System.out.println(mid);
        }// end of for
    } // end of main
}
