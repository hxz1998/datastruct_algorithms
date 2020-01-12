/**
 * Created by Xiaozhong on 2019/12/25.
 * Copyright (c) 2019/12/25 Xiaozhong. All rights reserved.
 */
package graphs;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.Stack;
import util.DataPathTemplate;

/**
 * 首先将distTo数组中数据全部初始化为正无穷
 * 然后将distTo[s]内容赋值为0
 * 然后将distTo[]最小的非树顶点放松（relax）并加入树中，如此这般
 * 直到所有的顶点都在树中或者所有的非树顶点的distTo[]值均为无限大
 *
 * Dijkstra算法可以解决边权重非负的加权有向图的单起点最短路径问题
 */
public class DijkstraSP {
    private DirectedEdge[] edgeTo;
    private double[] distTo;
    private IndexMinPQ<Double> pq;

    public DijkstraSP(EdgeWeightedDigraph graph, int s) {

        // 构造新的数组
        edgeTo = new DirectedEdge[graph.V()];
        distTo = new double[graph.V()];
        // 创建低优先级索引优先级队列
        pq = new IndexMinPQ<>(graph.V());

        // 将所有距离视为不可达（无限大距离）
        for (int v = 0; v < graph.V(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;
        // 更改起点为0.0
        distTo[s] = 0.0;

        // 将起点放入低优先级队列中
        pq.insert(s, 0.0);

//        while (!pq.isEmpty()) {
//            int v = pq.delMin();
//            for (DirectedEdge e : graph.adj(v))
//                relax(e);
//        }

        // 如果队列不为空，那么就如此反复
        while (!pq.isEmpty())
            // 放松掉最短的那个点
            relax(graph, pq.delMin());
    }

    private void relax(DirectedEdge e) {
        int v = e.from(), w = e.to();
        if (distTo[w] > distTo[v] + e.weight()) {
            distTo[w] = distTo[v] + e.weight();
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
        // 放松掉目标点，并且将与之相连接的点全部放到优先级队列中去
        for (DirectedEdge e : graph.adjForDirectedEdge(v)) {
            // 找到与v相邻的边的另一端点
            int w = e.to();
            /*
            如果当前直接到w的距离比从v绕一下到w的距离长，那么就需要从v绕道过去，并且更新距离，然后更新最后一条到目的地的边
            如果e的to得到的顶点不在优先级队列中，那就用insert把它加入进来
            如果在，那么就将其的优先级更新或降低
             */
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

    public static void main(String[] args) {
        DijkstraSP sp = new DijkstraSP(new EdgeWeightedDigraph(new In(DataPathTemplate.build("tinyEWDAG.txt"))), 5);
        System.out.println(sp.distTo(6));
        if (sp.hasPathTo(6)) {
            Iterable<DirectedEdge> path = sp.pathTo(6);
            for (DirectedEdge edge : path)
                System.out.println(edge);
        }
    }
}
