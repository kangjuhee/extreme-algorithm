package chap23.younghoo.p1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 답안에 나온 대로 풀이를 시도해보았다.
 * 입력되는 수열을 maxHeap과 minHeap에 나눠서 add하고
 * 1. maxHeap의 크기는 minHeap보다 1만큼 더 크거나 같다.
 * 2. maxHeap의 root는 minHeap의 root보다 작다.
 * 이 두가지 조건을 만족시킨 뒤, maxHeap의 root를 꺼내면
 * 이것이 입력된 수열의 중간값이 된다.
 */
public class Main {

    public static long getMedium(int seriesSize, int a, int b) {

        long ret = 0;
        int heapSize = (seriesSize / 2) + 1;
        GenerateInput generateInput = new GenerateInput(a, b);

        PriorityQueue<Integer> minHeap = new PriorityQueue<>(heapSize, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(heapSize, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        for (int i = 0; i < seriesSize; i++) { // 수열 갯수만큼 반복

            int element = generateInput.next();

            if (maxHeap.size() == minHeap.size()) {
                maxHeap.offer(element);
            } else {
                minHeap.offer(element);
            }

            // 두 번째 조건을 충족시키지 않았을 경우 maxHeap의 root(maximum value of maxHeap)와 minHeap(minimum value of minHeap)의 root를 바꿈
            if (!maxHeap.isEmpty() && !minHeap.isEmpty() && maxHeap.peek() > minHeap.peek()) {
                int x = maxHeap.poll();
                int y = minHeap.poll();
                maxHeap.add(y);
                minHeap.add(x);
            }
            // ret = (ret + maxHeap.peek()) % 20090711; // 왜 이렇게 하는거지?
            ret += maxHeap.peek();
        }
        return ret % 20090711;
    }

    public static void main(String[] args) throws IOException {

        // test input
        // 3
        // 10 1 0
        // 10 1 1
        // 10000 1273 4936
        //
        // test output
        // 19830
        // 19850
        // 2448920
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new FileReader("input.txt"));

        int testCase = Integer.parseInt(br.readLine().trim()); // 테스트 케이스 갯수

        for (int t = 0; t < testCase; t++) {

            // System.out.println("TEST CASE #" + (t + 1));
            StringTokenizer st = new StringTokenizer(br.readLine());
            int seriesSize = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            System.out.println(getMedium(seriesSize, a, b));

        }
    }
}
