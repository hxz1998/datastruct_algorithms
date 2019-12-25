/**
 * Created by Xiaozhong on 2019/12/25.
 * Copyright (c) 2019/12/25 Xiaozhong. All rights reserved.
 */
package graphs;

import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.Stack;

public class DijkstraSP {
    private DirectedEdge[] edgeTo;
    private double[] distTo;
    private IndexMinPQ<Double> pq;

    public DijkstraSP(EdgeWeightedDigraph graph, int s) {
        edgeTo = new DirectedEdge[graph.V()];
        distTo = new double[graph.V()];
        pq = new IndexMinPQ<>(graph.V());

        for (int v = 0; v < graph.V(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;
        distTo[s] = 0.0;

        pq.insert(s, 0.0);
//        while (!pq.isEmpty()) {
//            int v = pq.delMin();
//            for (DirectedEdge e : graph.adj(v))
//                relax(e);
//        }

        while (!pq.isEmpty())
            relax(graph, pq.delMin());
    }

    private void relax(DirectedEdge e) {
        int v = e.from(), w = e.to();
        if (distTo[w] >distTo[v] + e.weight()) {
            distTo[w] =distTo[v] + e.weight();
            edgeTo[w] = e;
            // 更新最小值索引队列
            if (pq.contains(w)) pq.decreaseKey(w, distTo[w]);
            else pq.insert(w, distTo[w]);
        }
    }

    /**
     * 放松一个顶点，并且更新最小值索引队列
     */
    private void relax(EdgeWeightedDigraph graph, int v) {
        for (DirectedEdge e : graph.adj(v)) {
            int w = e.to();
            if (distTo[w] > distTo[v] + e.weight()) {
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;
                if (pq.contains(w)) pq.changeKey(w, distTo[w]);
                else pq.insert(w, distTo[w]);
            }
        }
    }

    /**
     * 获取从起点到目的地点v的最短路径为多少
     */
    public double distTo(int v) {
        return distTo[v];
    }

    /**
     * 检查是否有到目的地点v的连接
     */
    public boolean hasPathTo(int v) {
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    /**
     * 返回从源点s到目的地点v的最短路径上的每一条边
     */
    public Iterable<DirectedEdge> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<DirectedEdge> path = new Stack<>();
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()])
            path.push(e);
        return path;
    }
}
