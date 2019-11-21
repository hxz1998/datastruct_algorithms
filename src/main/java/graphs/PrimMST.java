/**
 * Created by Xiaozhong on 2019/11/20.
 * Copyright (c) 2019/11/20 Xiaozhong. All rights reserved.
 */
package graphs;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;
import util.DataPathTemplate;

/**
 * 最小生成树的Prim即时算法实现
 */
public class PrimMST {
    private Edge[] edgeTo;
    private double[] distTo;
    private boolean[] marked;
    private IndexMinPQ<Double> pq;

    public PrimMST(EdgeWeightedGraph graph) {
        edgeTo = new Edge[graph.V()];
        distTo = new double[graph.V()];
        marked = new boolean[graph.V()];
        for (int v = 0; v < graph.V(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;
        pq = new IndexMinPQ<>(graph.V());

        distTo[0] = 0.0;
        pq.insert(0, 0.0);
        while (!pq.isEmpty())
            visit(graph, pq.delMin());
    }

    private void visit(EdgeWeightedGraph graph, int v) {
        marked[v] = true;

        for (Edge e : graph.adj(v)) {
            int w = e.other(v);
            if (marked[w]) continue;
            if (e.weight() < distTo[w]) {
                edgeTo[w] = e;

                distTo[w] = e.weight();
                if (pq.contains(w)) pq.changeKey(w, distTo[w]);
                else pq.insert(w, distTo[w]);
            }
        }
    }

    public Iterable<Edge> edges() {
        Queue<Edge> edges = new Queue<>();
        for (int i = 0; i < edgeTo.length; i++) {
            if (edgeTo[i] != null)
                edges.enqueue(edgeTo[i]);
        }
        return edges;
    }

    public double weight() {
        double weight = 0.0;
        for (Edge edge : edges())
            weight += edge.weight();
        return weight;
    }

    public static void main(String[] args) {
        EdgeWeightedGraph graph = new EdgeWeightedGraph(new In(DataPathTemplate.build("tinyEWG.txt")));
        PrimMST mst = new PrimMST(graph);
        for (Edge edge : mst.edges())
            StdOut.println(edge);
        System.out.println(String.format("%.2f", mst.weight()));
    }
}
