package chap29.younghoo.dijkstra;

import java.util.Comparator;

public class VertexMap implements Comparator<VertexMap>, Comparable<VertexMap> {

    private int distance;
    private int vertex;

    public VertexMap(int distance, int vertex) {
        this.distance = distance;
        this.vertex = vertex;
    }

    public int getDistance() {
        return distance;
    }

    public int getVertex() {
        return vertex;
    }

    @Override
    public int compare(VertexMap o1, VertexMap o2) {
        return o1.getDistance() > o2.getDistance() ? 1 : (o1.getDistance() == o2.getDistance() ? 0 : -1);
    }

    @Override
    public int compareTo(VertexMap o) {
        return getDistance() > o.getDistance() ? 1 : (getDistance() == o.getDistance() ? 0 : -1);
    }
}
