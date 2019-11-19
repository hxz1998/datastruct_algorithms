package graphs;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;

public class EdgeWeightedGraph {
    private final int V;
    private int E;
    private final Bag<Edge>[] adj;

    public EdgeWeightedGraph(int V) {
        this.V = V;
        adj = (Bag<Edge>[]) new Bag[V];
        for (int i = 0; i < V; i++)
            adj[i] = new Bag<Edge>();
    }

    public EdgeWeightedGraph(In in) {
        this(in.readInt());
        int E = in.readInt();
        for (int i = 0; i < E; i++)
            addEdge(new Edge(in.readInt(), in.readInt(), in.readDouble()));
    }

    public void addEdge(Edge edge) {
        int v = edge.either(), w = edge.other(v);
        adj[v].add(edge);
        adj[w].add(edge);
        E++;
    }

    public Iterable<Edge> adj(int v) {
        return adj[v];
    }

    public int V() {
        return V;
    }

    public Iterable<Edge> edges() {
        MinPQ<Edge> edges = new MinPQ<>();
        for (int i = 0; i < adj.length; i++) {
            for (Edge edge : adj[i]) {
                edges.insert(edge);
            }
        }
        return edges;
    }
}
