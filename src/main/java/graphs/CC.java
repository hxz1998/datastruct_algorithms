/**
 * Created by Xiaozhong on 2019/11/12.
 * Copyright (c) 2019/11/12 Xiaozhong. All rights reserved.
 */
package graphs;

import edu.princeton.cs.algs4.In;
import util.DataPathTemplate;

/**
 * 使用深度优先搜索来查找图中的所有连通分量
 */
public class CC {
    private int[] id;
    private boolean[] marked;
    private int count;

    public CC(Graph graph) {
        marked = new boolean[graph.V()];
        id = new int[graph.V()];
        count = 0;
        for (int s = 0; s < graph.V(); s++)
            if (!marked[s]) {
                dfs(graph, s);
                count++;
            }
    }

    public void dfs(Graph graph, int s) {
        marked[s] = true;
        id[s] = count;
        for (int v : graph.adj(s)) {
            if (!marked[v])
                dfs(graph, v);
        }
    }

    public boolean connected(int w, int v) {
        return id[w] == id[v];
    }

    public int id(int v) {
        return id[v];
    }

    public int count() {
        return count;
    }

    public static void main(String[] args) {
        Graph graph = new Graph(new In(DataPathTemplate.build("tinyG.txt")));
        CC cc = new CC(graph);
        System.out.printf("共有%d个连通分量\n", cc.count);
        for (int i = 0; i < cc.count(); i++) {
            for (int j = 0; j < graph.V(); j++) {
                if (cc.id(j) == i)
                    System.out.print(j + " ");
            }
            System.out.println();
        }
    }
}
