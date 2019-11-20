package graphs;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;

/**
 * 加权图的实现，使用到了Edge边的抽象实现
 */
public class EdgeWeightedGraph {
    // 记录有多少个顶点
    private final int V;
    // 记录有多少个边
    private int E;
    // 邻接表来表示每一个起点之后的边
    private final Bag<Edge>[] adj;

    /**
     * 根据顶点个数来对加权图进行构建
     */
    public EdgeWeightedGraph(int V) {
        this.V = V;
        adj = (Bag<Edge>[]) new Bag[V];
        for (int i = 0; i < V; i++)
            adj[i] = new Bag<Edge>();
    }

    /**
     * 使用输入流对加权图进行构建
     */
    public EdgeWeightedGraph(In in) {
        this(in.readInt());
        int E = in.readInt();
        for (int i = 0; i < E; i++)
            addEdge(new Edge(in.readInt(), in.readInt(), in.readDouble()));
    }

    /**
     * 添加一个边
     */
    public void addEdge(Edge edge) {
        int v = edge.either(), w = edge.other(v);
        adj[v].add(edge);
        adj[w].add(edge);
        E++;
    }

    /**
     * 获取与指定顶点相连接的顶点们
     */
    public Iterable<Edge> adj(int v) {
        return adj[v];
    }

    /**
     * 获取顶点数目
     */
    public int V() {
        return V;
    }

    /**
     * 获取所有的边
     * 在获取的过程中，需要排除掉已经出现的边，也就是只添加
     */
    public Iterable<Edge> edges() {
        Bag<Edge> edges = new Bag<>();
        for (int i = 0; i < V; i++) {
            for (Edge e : adj[i]) if (e.other(i) > i) edges.add(e);
        }
        return edges;
    }
}
