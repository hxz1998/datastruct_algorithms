package graphs;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;

public class KruskalMST {
    private Queue<Edge> mst = new Queue<>();

    public KruskalMST(EdgeWeightedGraph graph) {
        MinPQ<Edge> pq = new MinPQ<>();
        for (Edge e:graph.edges()) {

        }
    }

    public Iterable<Edge> edges() {
        return mst;
    }
}
