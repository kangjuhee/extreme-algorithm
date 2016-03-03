package chap28.younghoo.ex3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Euler circuit
 */
public class Main {

    public static int[][] adj;
    public static List<Integer> circuit;

    public static void getEulerCircuit(int here) {

        for (int there = 0; there < adj.length; there++) {
            while (adj[here][there] > 0) {
                adj[here][there]--;
                adj[there][here]--;
                getEulerCircuit(there);
            }
        }
        circuit.add(here);
    }

    public static void main(String[] args) {

        adj = new int[7][7];
//        adj[0][1] = 1;
//        adj[1][0] = 1;
//
//        adj[1][2] = 1;
//        adj[2][1] = 1;
//
//        adj[2][3] = 1;
//        adj[3][2] = 1;
//
//        adj[3][4] = 1;
//        adj[4][3] = 1;
//
//        adj[4][5] = 1;
//        adj[5][4] = 1;
//
//        adj[5][6] = 1;
//        adj[6][5] = 1;
//
//        adj[6][3] = 1;
//        adj[3][6] = 1;
//
//        adj[3][7] = 1;
//        adj[7][3] = 1;
//
//        adj[7][0] = 1;
//        adj[0][7] = 1;

        adj[0][1] = 1;
        adj[1][0] = 1;

        adj[1][2] = 1;
        adj[2][1] = 1;

        adj[2][3] = 3;
        adj[3][2] = 3;

        adj[0][3] = 1;
        adj[3][0] = 1;

        adj[2][4] = 1;
        adj[4][2] = 1;

        adj[4][5] = 1;
        adj[5][4] = 1;

        adj[5][6] = 1;
        adj[6][5] = 1;

        adj[2][6] = 1;
        adj[6][2] = 1;

        circuit = new ArrayList<>();
        getEulerCircuit(0);

        Collections.reverse(circuit);
        circuit.stream().forEach((i) -> System.out.println(i));
    }
}
