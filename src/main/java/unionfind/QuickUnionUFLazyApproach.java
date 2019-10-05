package unionfind;

/**
 * 快速并集算法，相对于快速查找算法而言，更加有利于进行合并操作。
 */
public class QuickUnionUFLazyApproach {
    private int id[];

    public QuickUnionUFLazyApproach(int N) {
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }

    public int root(int i) {
        while (i != id[i]) {
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
        // 下面的方法是基本的Quick-Union方法，改进后的方法为Weight
        id[i] = j;
    }

    public static void main(String[] args) {
        QuickUnionUFLazyApproach uf = new QuickUnionUFLazyApproach(3);
        System.out.println(uf.connected(1, 2));
        uf.union(1, 2);
        System.out.println(uf.connected(1, 2));
    }
}
