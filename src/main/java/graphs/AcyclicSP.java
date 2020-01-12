/**
 * Created by Xiaozhong on 2019/12/26.
 * Copyright (c) 2019/12/26 Xiaozhong. All rights reserved.
 */
package graphs;

import edu.princeton.cs.algs4.In;
import util.DataPathTemplate;

import java.util.Stack;

/**
 * 无环加权有向图的最短路径算法使用了拓扑排序
 * 该算法理论上有很好的优势，但是我没有将其正确实现。。。
 */
public class AcyclicSP {

    private DirectedEdge[] edgeTo;
    private double[] distTo;

    public AcyclicSP(EdgeWeightedDigraph graph, int s) {
        edgeTo = new DirectedEdge[graph.V()];
        distTo = new double[graph.V()];
        for (int i = 0; i < graph.V(); i++) {
            distTo[i] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0.0;

        Topological topological = new Topological(graph);

        for (int v : topological.order())
            relax(graph, v);

    }

    private void relax(EdgeWeightedDigraph graph, int v) {
        for (DirectedEdge edge : graph.adjForDirectedEdge(v)) {
            int w = edge.to();
            if (distTo[w] > distTo[v] + edge.weight()) {
                distTo[w] = distTo[v] + edge.weight();
                edgeTo[w] = edge;
            }
        }
    }

    public double distTo(int v) {
        return distTo[v];
    }

    public boolean hasPathTo(int v) {
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    public Iterable<DirectedEdge> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<DirectedEdge> path = new Stack<>();
        for (DirectedEdge edge = edgeTo[v]; edge != null; edge = edgeTo[edge.from()])
            path.push(edge);
        return path;
    }

    public static void main(String[] args) {
        AcyclicSP acyclicSP = new AcyclicSP(new EdgeWeightedDigraph(new In(DataPathTemplate.build("tinyEWDAG.txt"))), 5);
        if (acyclicSP.hasPathTo(6))
            for (DirectedEdge edge : acyclicSP.pathTo(6))
                System.out.println(edge);

    }

}
