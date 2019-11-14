package graphs;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import util.DataPathTemplate;

public class DirectBFSPaths {
    private boolean[] marked;
    private final int s;
    private int[] edgeTo;

    public DirectBFSPaths(Digraph digraph, int s) {
        this.s = s;
        this.marked = new boolean[digraph.V()];
        this.edgeTo = new int[digraph.V()];
        bfs(digraph, s);
    }

    private void bfs(Digraph digraph, int s) {
        Queue<Integer> queue = new Queue<>();
        queue.enqueue(s);
        marked[s] = true;
        while (!queue.isEmpty()) {
            int v = queue.dequeue();
            for (int i : digraph.adj(v))
                if (!marked[i]) {
                    edgeTo[i] = v;
                    marked[i] = true;
                    queue.enqueue(i);
                }
        }
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<Integer> paths = new Stack<>();
        for (int i = v; i != s; i = edgeTo[i])
            paths.push(i);
        paths.push(s);
        return paths;
    }


    public static void main(String[] args) {
        Digraph digraph = new Digraph(new In(DataPathTemplate.build("tinyG.txt")));
        DirectBFSPaths paths = new DirectBFSPaths(digraph, 0);
        for (int i : paths.pathTo(6)) {
            System.out.print(i + " ");
        }
    }
}
