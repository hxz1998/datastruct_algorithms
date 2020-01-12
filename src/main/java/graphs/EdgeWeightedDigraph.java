/**
 * Created by Xiaozhong on 2019/12/19.
 * Copyright (c) 2019/12/19 Xiaozhong. All rights reserved.
 */
package graphs;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import util.DataPathTemplate;

/**
 * 有向图的边值加权图
 */
public class EdgeWeightedDigraph extends Digraph {

    // 定义整个图中的节点数目
    private final int V;
    private int E;
    // 邻接表
    private Bag<DirectedEdge>[] adj;

    public EdgeWeightedDigraph(int V) {
        super(V);
        this.V = V;
        this.E = 0;
        adj = (Bag<DirectedEdge>[]) new Bag[V];
        for (int v = 0; v < V; v++)
            adj[v] = new Bag<DirectedEdge>();
    }

    /**
     * 根据输入流来创建一个加权有向无环图
     */
    public EdgeWeightedDigraph(In in) {
        this(in.readInt());
        int E = in.readInt();
        for (int i = 0; i < E;i++)
            addEdge(new DirectedEdge(in.readInt(), in.readInt(), in.readDouble()));
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

    public int E() {
        return E;
    }

    /**
     * 获取某一个顶点所能到达的所有边
     */
    public Iterable<DirectedEdge> adjForDirectedEdge(int v) {
        return adj[v];
    }

    public Iterable<DirectedEdge> edges() {
        Bag<DirectedEdge> bag = new Bag<>();
        for (int v = 0; v < V; v++)
            for (DirectedEdge e :adj[v])
                bag.add(e);
        return bag;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (DirectedEdge edge : edges())
            builder.append("\n").append(edge);
        return builder.toString();
    }

    public static void main(String[] args) {
        EdgeWeightedDigraph digraph = new EdgeWeightedDigraph(new In(DataPathTemplate.build("tinyEWDAG.txt")));
        System.out.println(digraph);
    }
}
