package chap29.younghoo.dijkstra.ex1;

public class Main {
    public static void main(String[] args) {
        int i = 10;
        int a = 15;
        while (i-- > 0) {
            a--;
            if (a == 12) {
                continue;
            }
            System.out.println(a);
        }
    }
}