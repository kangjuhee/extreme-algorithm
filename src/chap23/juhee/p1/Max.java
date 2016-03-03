package chap23.juhee.p1;
/**
 * Created by Juhee on 2/17/16.
 */
public class Max implements Comparable<Max>{

    private long num;

    public Max(long num){
        this.num = num;
    }

    public long getNum(){
        return num;
    }

    @Override // Comparable 인터페이스의 compareTo 메서드 구현
    public int compareTo(Max o) {
        return num > o.num ? -1 : 1;
    }
}
