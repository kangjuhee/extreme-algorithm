package chap28.younghoo.p1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {

    static boolean[] visited = new boolean[26];
    static int[][] adj;
    static List<Character> result;

    public static void topologicalSort() {
        dfsAll();
    }

    public static void dfsAll() {
        for (int i = 0; i < 26; i++) {
            if (!visited[i]) {
                dfs(i);
                result.add((char)(i + 97));
            }
        }
    }

    public static void dfs(int here) {

        visited[here] = true;

        for (int i = 0; i < adj[here].length; i++) {
            if (adj[here][i] == 1) {

                int there = i;

                if (!visited[there]) {
                    dfs(there);
                    result.add((char)(i + 97));
                }
            }
        }
    }

    public static int[][] makeGraph(List<String> list) {
        for (int i = 0; i < list.size() - 1; i++) { // 입력된 총 word 갯수만큼 반복
            // 해당 index의 단어 길이와 바로 다음 index 단어의 길이 중 짧은 단어만큼 반복을 수행하며 비교한다.
            int min = Math.min(list.get(i).length(), list.get(i + 1).length());
            for (int j = 0; j < min; j++) {
                int a = list.get(i).charAt(j) - 'a';
                int b = list.get(i + 1).charAt(j) - 'a';
                if (a == b) {
                    continue;
                } else {
                    if (adj[b][a] == 1) {
                        System.out.println("INVALID HYPOTHESIS");
                        return null;
                    } else {
                        adj[a][b] = 1;
                        break;
                    }
                }
            }
        }
        return adj;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new FileReader("input.txt"));

        int test = Integer.parseInt(br.readLine());

        for (int t = 0; t < test; t++) {
            List<String> list = new ArrayList<>();
            Arrays.fill(visited, false);
            adj = new int[26][26];
            result = new ArrayList<>();

            int total = Integer.parseInt(br.readLine());

            for (int s = 0; s < total; s++) {
                list.add(br.readLine());
            }

            if (makeGraph(list) == null) {
                continue;
            } else {
                topologicalSort();
                Collections.reverse(result);
                for (char i : result) {
                    System.out.print(i);
                }
                System.out.println();
            }
        }
    }
}