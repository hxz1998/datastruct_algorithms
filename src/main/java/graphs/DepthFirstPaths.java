package graphs;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import util.DataPathTemplate;

/**
 * 深度优先算法路径实现
 */
public class DepthFirstPaths {

    private boolean marked[];   // 记录每一个访问到的节点是否和起始点相连接
    private final int s;        // 记录起点
    private int[] edgeTo;       // 与起点相连的节点的上一个节点

    public DepthFirstPaths(Graph g, int s) {
        this.s = s;
        marked = new boolean[g.V()];
        edgeTo = new int[g.V()];
        dfs(g, s);
    }

    private void dfs(Graph g, int s) {
        marked[s] = true;
        for (int w : g.adj(s)) {
            if (!marked[w]) {
                edgeTo[w] = s;
                dfs(g, w);
            }
        }
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<>();
        for (int i = v; i != s; i = edgeTo[i])
            path.push(i);
        path.push(s);
        return path;
    }

    public static void main(String[] args) {
        Graph graph = new Graph(new In(DataPathTemplate.build("tinyG.txt")));
        DepthFirstPaths depthFirstPaths = new DepthFirstPaths(graph, 0);
        for (Integer i : depthFirstPaths.pathTo(5))
            System.out.print(i + " ");
    }
}
