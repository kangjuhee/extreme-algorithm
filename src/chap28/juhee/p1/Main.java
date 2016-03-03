package chap28.juhee.p1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;


/**
 * Created by Juhee on 2/18/16.
 */
public class Main {

    static int[][] adj;
    static LinkedList<String> words;
    static boolean [] visited;
    static LinkedList<Integer> res;

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));

        int testCase = Integer.parseInt(br.readLine().trim()); // 테스트 케이스 갯수
        for(int x = 0; x < testCase; x++){
            adj = new int[26][26];
            res = new LinkedList();
            words = new LinkedList();

            int total = Integer.parseInt(br.readLine());
            for (int y = 0; y < total; y++) {
                words.add(br.readLine());
            }
            int result = makeAdj();
/*
            System.out.println("a b c d e f g h i j k l m n o p q r s t u v w x y z");
            for(int i = 0; i < adj.length; i++) {
                for(int j =0; j < adj.length; j++) {
                    System.out.print(adj[i][j] + " ");
                }
                System.out.println();
            }
            //1.단어 받고 2. adj완성
*/
            if(result == -1) //실패일경우
                System.out.print("INVALID HYPOTHESIS");
            else
                dfsAll();

            Collections.reverse(res);
            for (int i = 0; i < res.size(); i++) {
                System.out.print((char) (res.get(i) + 97));
            }
            System.out.println();
        }
    }
    // 인접리스트 완성
    public static int makeAdj() {
        for (int i = 0; i < words.size() - 1; i++) {
            //   System.out.println(i +"th min is : " +words.get(i).length() + ", "+ words.get(i+1).length());
            int min = Math.min(words.get(i).length(), words.get(i + 1).length());
            int j = 0;
            while (j < min) {
                int a = words.get(i).charAt(j) - 'a';
                int b = words.get(i + 1).charAt(j) - 'a';

                if (a != b) { // adj에 입력 / (b,a)입력이 있나 확인부터
                    if (adj[b][a] != 1) // 없으면 입력가능
                        adj[a][b] = 1;
                    else return -1; //error 발생
                    break;
                } else if (a == b) {
                    j++;
                } // 다음 단어 비교
            } // end of while
        }// end of for
        return 1;
    }

    //시작한 탐색으로 깊이 탐색
    public static void dfs(int here) {
        visited[here] = true;
        for(int i = 0; i < 26; i ++) {
            if(adj[here][i] != 0) {
                int there = i;
                if (!visited[there]) {
                    dfs(there);
                    res.add(there);
                }
            }

        }
    }

    //초기화 & 탐색 시작
    public static void dfsAll(){
        visited = new boolean[26]; //초기값 false 로 설정됨
        Arrays.fill(visited, false);
        for(int i = 0; i < adj.length; i++) {
            if(!visited[i]) { //방문하지않았다면
                dfs(i);
                res.add(i);
            }
        }
    }

}