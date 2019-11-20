/**
 * Created by Xiaozhong on 2019/11/20.
 * Copyright (c) 2019/11/20 Xiaozhong. All rights reserved.
 */
package graphs;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.SymbolDigraph;
import util.DataPathTemplate;

public class Topological {
    private Iterable<Integer> order;

    public Topological(Digraph digraph) {
        DirectedCycle directedCycle = new DirectedCycle(digraph);
        if (!directedCycle.hasCycle()) {
            DepthFirstOrder dfs = new DepthFirstOrder(digraph);
            order = dfs.reversePost();
        }
    }

    public boolean isDAG() {
        return order != null;
    }

    public Iterable<Integer> order() {
        return order;
    }

    public static void main(String[] args) {
        SymbolDigraph symbolDigraph = new SymbolDigraph(DataPathTemplate.build("jobs.txt"), "/");
        edu.princeton.cs.algs4.Topological topological = new edu.princeton.cs.algs4.Topological(symbolDigraph.digraph());
        for (int v : topological.order())
            System.out.println(symbolDigraph.nameOf(v));
    }
}
