package unionfind;

/**
 * 快速并集算法的加权优化版本，使用了每个节点的层级作为加权系数进行合并，尽量在深度和宽度上平衡。
 */
public class QuickUnionWeighting {
    private int[] id;
    private int[] sz;

    public QuickUnionWeighting(int N) {
        id = new int[N];
        sz = new int[N];
        for(int i = 0; i < N; i++) {
            id[i] = i;
            sz[i] = 1;
        }
    }

    public int root(int i) {
        while(i != id[i]) {
            i = id[i];
        }
        return i;
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    public void union(int p, int q) {
        int i = root(p);
        int j = root(q);
        if (i != j) {
            if (sz[i] > sz[j]) {
                id[j] = i;
                sz[i] += sz[j];
            } else {
                id[i] = j;
                sz[j] += sz[i];
            }
        }
    }

    public static void main(String[] args) {
        QuickUnionWeighting uf = new QuickUnionWeighting(3);
        System.out.println(uf.connected(1, 2));
        uf.union(1, 2);
        System.out.println(uf.connected(1, 2));
    }
}
