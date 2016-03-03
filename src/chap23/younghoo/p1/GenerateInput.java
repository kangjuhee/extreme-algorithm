package chap23.younghoo.p1;

public class GenerateInput {

    long A = 1983;
    int a, b;
    int next;

    public GenerateInput(int a, int b) {
        this.a = a;
        this.b = b;
        this.next = 0;
    }

    public int next() {
        // int next = (A * a + b) % 20090711;
        next = (int)A; // insert previous A
        A = (A * a + b) % 20090711; // update A
        return next;
    }
}
