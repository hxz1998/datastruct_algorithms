package graphs;

import edu.princeton.cs.algs4.Graph;

/**
 * 深度优先算法实现，使用了marked标记数组，同时使用count记录连接数
 */
public class DepthFirstSearch {
    private boolean[] marked;
    private int count;

    /**
     * 使用图对象 g 和起点 s 构建一个深度优先算法实现，调用了 DFS 函数
     */
    public DepthFirstSearch(Graph g, int s) {
        marked = new boolean[g.V()];
        dfs(g, s);
    }

    /**
     * 深度优先算法（DFS）的实现
     * 根据传入的图对象，和相对的起点s进行遍历，标记没有遍历过的与之相连的结点。
     */
    private void dfs(Graph g, int s) {
        marked[s] = true;
        count++;
        for (int i : g.adj(s))
            if (!marked[i])
                dfs(g, i);
    }

    /**
     * 返回连接节点数目
     */
    public int count() {
        return count;
    }

    /**
     * 判断是否和起点s相连接
     */
    public boolean marked(int i) {
        return marked[i];
    }
}
