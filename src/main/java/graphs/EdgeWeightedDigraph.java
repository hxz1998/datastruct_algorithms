/**
 * Created by Xiaozhong on 2019/12/19.
 * Copyright (c) 2019/12/19 Xiaozhong. All rights reserved.
 */
package graphs;

import edu.princeton.cs.algs4.Bag;

/**
 * 有向图的边值加权图
 */
public class EdgeWeightedDigraph {

    // 定义整个图中的节点数目
    private final int V;
    private int E;
    // 邻接表
    private Bag<DirectedEdge>[] adj;

    public EdgeWeightedDigraph(int V) {
        this.V = V;
        this.E = 0;
        adj = (Bag<DirectedEdge>[]) new Bag[V];
        for (int v = 0; v < V; v++)
            adj[v] = new Bag<DirectedEdge>();
    }

    /**
     * 添加一条边，首先取出来这条边的起点作为邻接表的索引，再往上添加
     */
    public void addEdge(DirectedEdge e) {
        int v = e.from();
        adj[v].add(e);
        E++;
    }

    public int V() {
        return V;
    }

    /**
     * 获取某一个顶点所能到达的所有边
     */
    public Iterable<DirectedEdge> adj(int v) {
        return adj[v];
    }

    public Iterable<DirectedEdge> edges() {
        Bag<DirectedEdge> bag = new Bag<>();
        for (int v = 0; v < V; v++)
            for (DirectedEdge e :adj[v])
                bag.add(e);
        return bag;
    }

}
