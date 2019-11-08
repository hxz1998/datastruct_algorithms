/**
 * Created by Xiaozhong on 2019/11/5.
 * Copyright (c) 2019/11/5 Xiaozhong. All rights reserved.
 */
package graphs;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import util.DataPathTemplate;

/**
 * 图的实现，使用Adjacent-List实现相关的算法
 * 也可以使用二维矩阵来实现
 */
public class Graph {
    private final int V;
    private Bag<Integer>[] adj;
    private int E;

    /**
     * 使用固定数量的点构建一棵树
     * @param V
     */
    public Graph(int V) {
        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int i = 0; i < V; i++)
            adj[i] = new Bag<Integer>();
    }

    public Graph(In in) {
        this(in.readInt());
        int E = in.readInt();
        for (int i = 0; i < E; i++) {
            int v = in.readInt();
            int w = in.readInt();
            addEdge(v, w);
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
        E++;
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    /**
     * Common methods.
     */
    public static int degree(Graph g, int v) {
        int degree = 0;
        for (int w : g.adj(v)) degree++;
        return degree;
    }

    public static int maxDegree(Graph g) {
        int maxDegree = 0;
        for (int v = 0; v < g.V(); v++)
            if (degree(g, v) > maxDegree) maxDegree = degree(g, v);
        return maxDegree;
    }

    public static double numberOfSelfLoops(Graph g) {
        int count = 0;
        for (int v = 0; v < g.V(); v++)
            for (int w : g.adj(v))
                if (v == w) count++;
        return count / 2;
    }

    public String toString() {
        String s = V + " 个顶点， " + E + " 条边。\n";
        for (int i = 0; i < V; i++) {
            s += i + ": ";
            for (int w : this.adj(i))
                s += w + " ";
            s += "\n";
        }
        return s;
    }

    public static void main(String[] args) {
        In in = new In(DataPathTemplate.build("tinyG.txt"));
        Graph g = new Graph(in);
        System.out.println(g);
    }
}
