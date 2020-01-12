/**
 * Created by Xiaozhong on 2019/12/19.
 * Copyright (c) 2019/12/19 Xiaozhong. All rights reserved.
 */
package graphs;

public class DirectedEdge {
    private final int v, w;
    private final double weight;

    public DirectedEdge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public int to() {
        return w;
    }

    public int from() {
        return v;
    }

    public double weight() {
        return weight;
    }

    @Override
    public String toString() {
        return String.format("%d - %d, weight is %.3f", v, w, weight);
    }
}
