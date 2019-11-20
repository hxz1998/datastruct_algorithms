package graphs;

public class Edge implements Comparable<Edge> {

    // v 和 w 用来记录两个端点
    private final int v, w;
    // weight用来记录权重
    private final double weight;

    public Edge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    /**
     * 获取其中一个端点
     */
    public int either() {
        return v;
    }

    /**
     * 获取另一个端点
     */
    public int other(int vertex) {
        if (vertex == v) return w;
        return v;
    }

    /**
     * 定义比较规则，只看权重，大则1小则-1，否则0
     */
    @Override
    public int compareTo(Edge that) {
        if (this.weight < that.weight) return -1;
        else if (this.weight > that.weight) return 1;
        else return 0;
    }

    @Override
    public String toString() {
        return "[" + v + " " + w + " " + weight + "]";
    }
}
