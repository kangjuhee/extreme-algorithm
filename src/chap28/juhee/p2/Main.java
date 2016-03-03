package chap28.juhee.p2;
/**
 *   에러가 발생할 수 있는 경우
 *  1.차수가 홀수 인 것이 2개가 1개 또는 3개 이상일 경우
 *  2. 차수가 짝수 인데 연결이 안되는 경우 ( ab, ba, cd, dc)
 *  3. 차수가 2개일떄 in > out 인것을 시작점으로 설정해야된다
 *  4. 차수가 짝수일떄 in=out이어야 한다
 * */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;

import java.util.Arrays;
import java.util.Collections;
/**
 * Created by Juhee on 2/26/16.
 */
public class Main {

    static ArrayList<String> [][] adj; // 인접행렬
    static ArrayList<String> track; // 지난온거리
    static ArrayList<String> words; // 입력파일에서 받은 단어리스트

    public static void main(String[] args) throws IOException {
        //BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine().trim()); // 테스트 케이스 갯수
        int result, wordCount; // 인접행렬 결과를 받게 된다
        String ex;
        for (int x = 0; x < testCase; x++) {
            //매번 시작할 때 객체 먼저 초기화;
            adj = new ArrayList[26][26];
            words = new ArrayList<>();
            track = new ArrayList<>();
            ex = "";
            wordCount = Integer.parseInt(br.readLine()); //한 게임당 입력받을 단어의 갯수

            //단어 입력 받기
            for(int y = 0; y < wordCount; y++)
                words.add(br.readLine());

            for(int i = 0; i < 26; i++) {
                for(int j = 0; j < 26; j++) {
                    adj[i][j] = new ArrayList<>();
                }
            }

            //인접행렬 초기화
            result = makeAdj(words);
            if(result != -2 ) {
                dfs(result); //오일러 서킷 또는 트레일
                for(int i = 0; i < 26; i++) {
                    for(int j = 0; j < 26; j++) {
                        if (!adj[i][j].isEmpty()) { // 오일러트레일로 다 dfs하고 다돌지않은 path가 잇을 경우
                            ex = adj[i][j].get(0);
                        }
                    }
                }

                if (ex == "") {// 다돌았다면
                    Collections.reverse(track);
                    for (int i = 0; i < track.size(); i++)
                        System.out.print(track.get(i) + " ");
                    System.out.println();
                } else { // for의 if문에 걸렷다면
                    result = -2;
                }
            }
            if(result == -2)
                System.out.println("IMPOSSIBLE");
        }
    } //end of main

    public static void dfs(int here) {
        String adjVal = "";
        for (int there = 0; there < adj[here].length; there++) {
            while (!adj[here][there].isEmpty()) {
                adjVal = adj[here][there].get(0);
                adj[here][there].remove(0);
                dfs(there);
            }
        } // end of for
        if(!adjVal.equals(""))
            track.add(adjVal);
    }

    public static int makeAdj(ArrayList<String> words) {
        int []from;
        int []to;
        ArrayList<Integer> valuable = new ArrayList<>();  //찻수가 홀수
        from = new int[26];
        to = new int[26];
        int a, b;

        //2. 인접행렬 완성
        for (String word : words) {
            a = (int) word.charAt(0) - 97;
            b = (int) word.charAt(word.length() - 1) - 97;
            from[a]++;
            to[b]++;
            adj[a][b].add(word); //단어를 인접행렬에 넣음
        } // end of for

        for (int i = 0; i < 26; i++) {
            if(from[i] != to[i] && ((from[i]+ to[i]) % 2 == 0))
                return -2;
            else if ((from[i]+ to[i]) % 2 != 0) // 진입차수가 홀수인 것 찾기
                valuable.add(i);
        }

        if (valuable.size() == 2) { //진입차수가 홀수인게 2개일경우
            a = from[valuable.get(0)] - to[valuable.get(0)];
            b = from [valuable.get(1)] - to[valuable.get(1)];
            if (a > b)
                return valuable.get(0);
            else
                return valuable.get(1);
        } // 오일러 트레일
        else if (valuable.size() > 2 || valuable.size() == 1)
            return -2; //  에러 오일러 트레일은 홀수인 정점이 2개여야만 함
        else
            return words.get(0).charAt(0) - 97;
        // 오일러 서킷
    } // end of makeAdj
}