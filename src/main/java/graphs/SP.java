/**
 * Created by Xiaozhong on 2019/12/22.
 * Copyright (c) 2019/12/22 Xiaozhong. All rights reserved.
 */
package graphs;

import edu.princeton.cs.algs4.StdOut;

import java.util.Stack;

public class SP {

    private double[] distTo;
    private DirectedEdge[] edgeTo;
    private final int s;

    public SP(EdgeWeightedDigraph graph, int s) {
        this.s = s;
        distTo = new double[graph.V()];

        // 这个怎么初始化？
        edgeTo = new DirectedEdge[graph.V()];

        for (int i = 0; i < distTo.length; i++)
            distTo[i] = Double.POSITIVE_INFINITY;
        distTo[s] = 0;
    }

    public double distTo(int v) {
        return distTo[v];
    }

    public Iterable<DirectedEdge> pathTo(int v) {
        Stack<DirectedEdge> path = new Stack<DirectedEdge>();
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()])
            path.push(e);
        return path;
    }

    /**
     * 边的松弛
     */
    private void relax(DirectedEdge e) {
        int v = e.from(), w = e.to();
        if (distTo[w] > distTo[v] + e.weight()) {
            distTo[w] = distTo[v] + e.weight();
            edgeTo[w] = e;
        }
    }

    /**
     * 顶点的松弛
     */
    private void relax(EdgeWeightedDigraph graph, int v) {

    }

    public static void main(String[] args) {
        EdgeWeightedDigraph graph = new EdgeWeightedDigraph(10);
        int s = 0;
        SP sp = new SP(graph, 0);
        for (int i = 0; i < graph.V(); i++) {
            StdOut.printf("%d to %d (%0.2f): ", s, i, sp.distTo(i));
            for (DirectedEdge e : sp.pathTo(i))
                StdOut.print(e + " ");
            StdOut.println();
        }

    }
}
