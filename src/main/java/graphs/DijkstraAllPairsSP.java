/**
 * Created by Xiaozhong on 2019/12/27.
 * Copyright (c) 2019/12/27 Xiaozhong. All rights reserved.
 */
package graphs;

/**
 * 该类将会把一个图中所有节点对的最短距离和最短路径都求出来
 */
public class DijkstraAllPairsSP {
    DijkstraSP[] all;

    public DijkstraAllPairsSP(EdgeWeightedDigraph digraph) {
        all = new DijkstraSP[digraph.V()];
        for (int i = 0; i < digraph.V(); i++)
            all[i] = new DijkstraSP(digraph, i);
    }

    Iterable<DirectedEdge> path(int s, int v) {
        return all[s].pathTo(v);
    }

    public double dist(int s, int v) {
        return all[s].distTo(v);
    }
}
