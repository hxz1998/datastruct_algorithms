package graphs;

//import edu.princeton.cs.algs4.Graph;

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

    /**
     * 使用图和起点构造相应的路径
     * 构造得到的结果为与该起点相连接的所有边
     */
    public DepthFirstPaths(Graph g, int s) {
        this.s = s;
        marked = new boolean[g.V()];
        edgeTo = new int[g.V()];
        dfs(g, s);
    }

    /**
     * 深度优先搜索实现
     */
    private void dfs(Graph g, int s) {
        // 首先标记起点为可到达的
        marked[s] = true;
        for (int w : g.adj(s)) {
            // 对当前起点所能到达的所有子节点进行挨个访问
            if (!marked[w]) {// 如果没有访问过，那么进行访问并标记
                // ！！！！！！最重要的一个操作，该处记录的是子节点的父节点，也就是来时的路径
                edgeTo[w] = s;
                // 进行递归搜索
                dfs(g, w);
            }
        }
    }

    /**
     * 判断是否有到达某个节点的相应路径
     */
    public boolean hasPathTo(int v) {
        return marked[v];
    }

    /**
     * 获取到达某个节点的详细路径
     * 使用了栈作为基本的数据结构进行存储路径
     */
    public Iterable<Integer> pathTo(int v) {
        // 如果没有相应的路径，那么直接返回空
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<>();
        // 从终点，按照edgeTo中标记的父节点位置，进行路径回溯，并且压入路径栈中
        for (int i = v; i != s; i = edgeTo[i])
            path.push(i);
        // 最后将起点也压进去
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
