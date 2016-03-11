package chap29.younghoo.p1;

public class VertexMap implements Comparable<VertexMap> {
    private int vertex;
    private double distance;

    public VertexMap(int vertex, double distance) {
        this.vertex = vertex;
        this.distance = distance;
    }

    public int getVertex() {
        return vertex;
    }

    public double getDistance() {
        return distance;
    }

    @Override
    public int compareTo(VertexMap o) {
        return (int) (distance - o.getDistance());
        // return distance > o.getDistance() ? 1 : (distance < o.getDistance()) ? -1 : 0;
    }
}
