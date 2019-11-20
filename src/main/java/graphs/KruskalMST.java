package graphs;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.UF;
import util.DataPathTemplate;

/**
 * 最小生成树的Kruskal算法实现
 */
public class KruskalMST {
    private Queue<Edge> mst = new Queue<>();

    /**
     * 使用Kruskal算法对最小生成树进行处理
     */
    public KruskalMST(EdgeWeightedGraph graph) {
        // 将边按照升序排列
        MinPQ<Edge> pq = new MinPQ<>();
        for (Edge e:graph.edges())
            pq.insert(e);

        // 使用union-find对边的连接关系进行处理
        UF uf = new UF(graph.V());
        // 如果最小优先队列还有元素，而且最小生成树还没有满，那么进行迭代
        while (!pq.isEmpty() && mst.size() < graph.V() - 1) {
            // 取出来最小元素
            Edge edge = pq.delMin();
            // 取出来两个顶点
            int v = edge.either(), w = edge.other(v);
            // 如果图没有连通上去，那么就将其连接，而且最小生成树中添加进来对应的边
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
