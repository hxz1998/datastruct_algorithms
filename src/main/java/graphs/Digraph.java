/**
 * Created by Xiaozhong on 2019/11/12.
 * Copyright (c) 2019/11/12 Xiaozhong. All rights reserved.
 */
package graphs;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

public class Digraph {
    private Bag<Integer>[] adj;
    private final int V;

    public Digraph(int V) {
        this.V = V;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new Bag<>();
        }
    }

    public Digraph(In in) {
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

    public void addEdge(int w, int v) {
        adj[w].add(v);
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }
}
