/**
 * Created by Xiaozhong on 2019/11/8.
 * Copyright (c) 2019/11/8 Xiaozhong. All rights reserved.
 */
package graphs;


import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import util.DataPathTemplate;

/**
 * 广度优先路径算法的实现
 * <p>
 * 在程序大致结构上和深度优先一致，但是使用了队列作为核心的数据结构对广度优先算法进行了实现
 */
public class BreadthFirstPaths {
    private boolean[] marked;
    private int[] edgeTo;
    private final int s;

    /**
     * 根据图和起点进行广度优先路径构建
     */
    public BreadthFirstPaths(Graph graph, int s) {
        this.s = s;
        marked = new boolean[graph.V()];
        edgeTo = new int[graph.V()];
        bfs(graph, s);
    }

    /**
     * 广度优先算法核心部分
     * 使用了非递归的结构
     */
    public void bfs(Graph graph, int s) {
        // 队列用于存放和当前起点s相连接的所有子节点
        Queue<Integer> queue = new Queue<>();
        // 将起点标记为可访问的
        marked[s] = true;
        // 将起点入栈
        queue.enqueue(s);
        while (!queue.isEmpty()) {
            // 首先取出来最先访问到的节点，对其进行处理
            int v = queue.dequeue();
            // 挨个访问与取出来的节点相连接的子节点
            for (int w : graph.adj(v)) {
                // 如果没有标记过，那么进行访问处理
                if (!marked[w]) {
                    edgeTo[w] = v;
                    marked[w] = true;
                    // 入队
                    queue.enqueue(w);
                }
            }
        }
    }

    /**
     * 检查是否有到达某个节点的路径
     */
    public boolean hashPathTo(int v) {
        return marked[v];
    }

    /**
     * 获取相应的路径，其基本思路和深度优先算法一致
     */
    public Iterable<Integer> pathTo(int v) {
        if (!hashPathTo(v)) return null;
        Stack<Integer> path = new Stack<>();
        for (int i = v; i != s; i = edgeTo[i])
            path.push(i);
        path.push(s);
        return path;
    }


    public static void main(String[] args) {
        Graph graph = new Graph(new In(DataPathTemplate.build("tinyG.txt")));
        DepthFirstPaths depthFirstPaths = new DepthFirstPaths(graph, 0);
        for (Integer i : depthFirstPaths.pathTo(4))
            System.out.print(i + " ");
    }
}
