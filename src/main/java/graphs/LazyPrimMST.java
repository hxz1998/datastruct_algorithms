package graphs;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;
import util.DataPathTemplate;

/**
 * 带延迟的Prim最小生成树实现
 */
public class LazyPrimMST {
    // 用来标记访问过的节点
    private boolean[] marked;
    // 用来对边进行排序
    private MinPQ<Edge> pq;
    // 最小生成树的表示
    private Queue<Edge> mst;

    /**
     * 在构造方法中对最小生成树进行实现
     */
    public LazyPrimMST(EdgeWeightedGraph graph) {
        pq = new MinPQ<>();
        mst = new Queue<>();
        marked = new boolean[graph.V()];

        // 首先对 0 作为根节点进行处理
        visit(graph, 0);
        // 如果最小优先队列不为空，那么就持续进行以下操作
        while (!pq.isEmpty()) {
            // 首先取出最小的边
            Edge edge = pq.delMin();

            // 取出最小边的两个端点
            int v = edge.either(), w = edge.other(v);
            // 判断这个边是否有效，关键在于两个顶点是不是都放问过了
            if (marked[v] && marked[w]) continue;
            // 将这个边添加进最小生成树中
            mst.enqueue(edge);
            // 如果v没有访问过，那么就先处理以v为开始顶点的元素，否则就先处理以w为顶点的元素
            if (!marked[v]) visit(graph, v);
            if (!marked[w]) visit(graph, w);
        }
    }

    /**
     * 私有实现方法，用来对某一个顶点进行访问，并且将其相邻的元素顶点对应的边放到优先级队列中
     */
    private void visit(EdgeWeightedGraph graph, int v) {
        marked[v] = true;
        for (Edge e : graph.adj(v))
            // 判断是否有效，就是说这条边和对方的那条边没有联通，否则会构成环
            if (!marked[e.other(v)])
                pq.insert(e);
    }

    public Iterable<Edge> edges() {
        return mst;
    }

    public double weight() {
        double weight = 0.0;
        for (Edge edge : mst)
            weight += edge.weight();
        return weight;
    }

    public static void main(String[] args) {
        EdgeWeightedGraph graph = new EdgeWeightedGraph(new In(DataPathTemplate.build("tinyEWG.txt")));
        LazyPrimMST mst = new LazyPrimMST(graph);
        for (Edge edge : mst.edges())
            StdOut.println(edge);
        System.out.println(String.format("%.2f", mst.weight()));
    }
}
