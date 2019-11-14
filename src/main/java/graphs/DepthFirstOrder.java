package graphs;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

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
