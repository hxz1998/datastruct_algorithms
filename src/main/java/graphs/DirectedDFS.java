package graphs;

public class DirectedDFS {
    private boolean[] marked;

    public DirectedDFS(Graph graph, int s) {
        marked = new boolean[graph.V()];
        dfs(graph, s);
    }

    private void dfs(Graph graph, int v) {
        marked[v] = true;
        for (int i : graph.adj(v)) {
            if (!marked[i])
                dfs(graph, i);
        }
    }

    public boolean marked(int v) {
        return marked[v];
    }
}
