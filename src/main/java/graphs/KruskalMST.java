package graphs;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.UF;
import util.DataPathTemplate;

public class KruskalMST {
    private Queue<Edge> mst = new Queue<>();

    public KruskalMST(EdgeWeightedGraph graph) {
        // 将边按照升序排列
        MinPQ<Edge> pq = new MinPQ<>();
        for (Edge e:graph.edges())
            pq.insert(e);

        UF uf = new UF(graph.V());
        while (!pq.isEmpty() && mst.size() < graph.V() - 1) {
            Edge edge = pq.delMin();
            int v = edge.either(), w = edge.other(v);
            if (!uf.connected(v, w)) {
                uf.connected(v, w);
                mst.enqueue(edge);
            }
        }
    }

    public Iterable<Edge> edges() {
        return mst;
    }

    public static void main(String[] args) {
        EdgeWeightedGraph edgeWeightedGraph = new EdgeWeightedGraph(new In(DataPathTemplate.build("tinyEWG.txt")));
        KruskalMST kruskalMST = new KruskalMST(edgeWeightedGraph);
        for (Edge edge : kruskalMST.edges())
            System.out.println(edge);
    }
}
