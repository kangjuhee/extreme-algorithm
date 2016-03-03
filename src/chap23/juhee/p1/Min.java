package chap23.juhee.p1;

/**
 * Created by Juhee on 2/17/16.
 */
public class Min implements Comparable<Min> {

    private long num;

    public Min(long num){
        this.num = num;
    }

    public long getNum(){
        return num;
    }

    @Override // Comparable 인터페이스의 compareTo 메서드 구현
    public int compareTo(Min o) {
        return num <= o.num ? -1 : 1;
    }

}
