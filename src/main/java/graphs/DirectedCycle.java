package graphs;

import edu.princeton.cs.algs4.Stack;

public class DirectedCycle {
    private int[] edgeTo;
    private boolean[] marked;
    private boolean[] onStack;      // 递归调用的栈上所有顶点
    private Stack<Integer> cycle;   // 有向环中的所有顶点（如果存在）

    public DirectedCycle(Digraph digraph) {
        onStack = new boolean[digraph.V()];
        marked = new boolean[digraph.V()];
        edgeTo = new int[digraph.V()];
        for (int v = 0; v < digraph.V(); v++)
            if (!marked[v]) dfs(digraph, v);
    }

    private void dfs(Digraph g, int v) {
        onStack[v] = true;
        marked[v] = true;
        for (int w : g.adj(v))
            if (this.hasCycle()) return;
            else if (onStack[w]) {
                cycle = new Stack<>();
                for (int x = v; x != w; x = edgeTo[x])
                    cycle.push(x);
                cycle.push(w);
                cycle.push(v);
            }
        onStack[v] = false;
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<Integer> cycle() {
        return cycle;
    }
}
