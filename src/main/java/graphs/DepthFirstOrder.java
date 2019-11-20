package graphs;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

/**
 * 使用到了dfs正好只会访问每个节点一次的特性
 * 将dfs中的顶点参数进行保存，就可以得到前序、后序和逆后序的排序结果
 */
public class DepthFirstOrder {
    private boolean[] marked;
    private Queue<Integer> pre;         // 所有顶点前序排列
    private Queue<Integer> post;        // 后序排列
    private Stack<Integer> reversePost; // 逆后序排列

    public DepthFirstOrder(Digraph digraph) {
        pre = new Queue<>();
        post = new Queue<>();
        reversePost = new Stack<>();
        marked = new boolean[digraph.V()];

        for (int i = 0; i < digraph.V(); i++)
            if (!marked[i])
                dfs(digraph, i);
    }

    /**
     * 深度优先排序，在方法中加入前序、后序和逆后序排序所用到的数据结构
     * 当访问所有元素完成时，三个排序也有了结果
     */
    private void dfs(Digraph digraph, int v) {
        pre.enqueue(v);
        marked[v] = true;
        for (int w : digraph.adj(v))
            if (!marked[w])
                dfs(digraph, w);
        post.enqueue(v);
        reversePost.push(v);
    }

    public Iterable<Integer> pre() {
        return pre;
    }

    public Iterable<Integer> post() {
        return post;
    }

    public Iterable<Integer> reversePost() {
        return reversePost;
    }

}
