package chap29.juhee.dijkstra;

import java.util.Comparator;

/**
 * Created by Juhee on 3/8/16.
 */
public class VertexMap implements Comparable<VertexMap> {
    int vertex;
    int distance;

    public VertexMap(int vertex, int distance) {
        this.vertex = vertex;
        this.distance = distance;
    }

    public int getVertex() {
        return vertex;
    }

    public int getDistance() {
        return distance;
    }

    @Override
    public int compareTo(VertexMap o) {
        return distance - o.getDistance();
    }
}
