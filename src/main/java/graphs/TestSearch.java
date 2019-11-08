package graphs;

//import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import util.DataPathTemplate;

public class TestSearch {
    public static void main(String[] args) {
        Graph graph = new Graph(new In(DataPathTemplate.build(args[0])));
        int start = Integer.parseInt(args[1]);
        DepthFirstSearch search = new DepthFirstSearch(graph, start);
        for (int i = 0; i < graph.V(); i++) {
            if (search.marked(i))
                StdOut.print(i + " ");
        }

        if (search.count() != graph.V())
            StdOut.print("NOT ");
        StdOut.print("connected");
    }
}
