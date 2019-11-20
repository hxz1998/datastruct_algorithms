package graphs;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;
import util.DataPathTemplate;

public class LazyPrimMST {
    private boolean[] marked;
    private MinPQ<Edge> pq;
    private Queue<Edge> mst;

    public LazyPrimMST(EdgeWeightedGraph graph) {
        pq = new MinPQ<>();
        mst = new Queue<>();
        marked = new boolean[graph.V()];

        visit(graph, 0);
        while (!pq.isEmpty()) {
            Edge edge = pq.delMin();

            int v = edge.either(), w = edge.other(v);
            if (marked[v] && marked[w]) continue;
            mst.enqueue(edge);
            if (!marked[v]) visit(graph, v);
            if (!marked[w]) visit(graph, w);
        }
    }

    private void visit(EdgeWeightedGraph graph, int v) {
        marked[v] = true;
        for (Edge e : graph.adj(v))
            if (!marked[e.other(v)])
                pq.insert(e);
    }

    public Iterable<Edge> edges() {
        return mst;
    }

    public double weight() {
        return 0.0;
    }

    public static void main(String[] args) {
        EdgeWeightedGraph graph = new EdgeWeightedGraph(new In(DataPathTemplate.build("tinyEWG.txt")));
        LazyPrimMST mst = new LazyPrimMST(graph);
        for (Edge edge : mst.edges())
            StdOut.println(edge);
    }
}
