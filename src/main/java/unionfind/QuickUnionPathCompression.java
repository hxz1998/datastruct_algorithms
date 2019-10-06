package unionfind;

/**
 * 该程序为查并集算法中的快速并集算法，使用了路径压缩算法实现，理论上优化程度较高
 */
public class QuickUnionPathCompression {
    private int[] id;

    public QuickUnionPathCompression(int N) {
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }

    /**
     * 优化核心部分，将每一个子节点上移一个层级。
     */
    public int root(int i) {
        while (i != id[i]) {
            //上移层级
            id[i] = id[id[i]];
            i = id[i];
        }
        return i;
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    /**
     * 将前者的父节点设置为后者
     * @param p 子节点
     * @param q 父节点
     */
    public void union(int p, int q) {
        int i = root(p);
        int j = root(q);
        if (i != j) {
            if (i > j) {
                id[j] = i;
            } else {
                id[i] = j;
            }
        }
    }

    public static void main(String[] args) {
        QuickUnionPathCompression uf = new QuickUnionPathCompression(8);
        System.out.println(uf.connected(1, 2));
        uf.union(1, 2);
        System.out.println(uf.connected(1, 2));
    }
}
