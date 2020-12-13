package unionfind;

public class UnionFindAlgorithm {
    static class QuickFind {
        private int[] id;
        private int count;

        QuickFind(int sz) {
            id = new int[sz];
            count = sz;
            for (int i = 0; i < sz; ++i) id[i] = i;
        }

        // 获取第 p 个节点的 identifier
        public int find(int p) {
            return id[p];
        }

        // 添加 p 和 q 之间的连接
        public void union(int p, int q) {
            if (connected(p, q)) return;
            int pid = id[p];
            for (int i = 0; i < id.length; ++i)
                if (id[i] == pid) id[i] = id[q];
            --count;
        }

        // 统计连通块的个数
        public int count() {
            return count;
        }

        // 检查 p 到 q 是否是可达的
        public boolean connected(int p, int q) {
            return find(p) == find(q);
        }
    }

    static class QuickUnion {
        private int[] id;
        private int count;

        public QuickUnion(int sz) {
            id = new int[sz];
            count = sz;
            for (int i = 0; i < sz; ++i)
                id[i] = i;
        }

        public void union(int p, int q) {
            int pRoot = find(p);
            int qRoot = find(q);
            if (pRoot == qRoot) return;
            id[pRoot] = qRoot;
            --count;
        }

        public int find(int p) {
            while (p != id[p]) p = id[p];
            return p;
        }

        public boolean connected(int p, int q) {
            return find(p) == find(q);
        }

        public int count() {
            return count;
        }
    }

    public static void main(String[] args) {
//        QuickFind uf = new QuickFind(10);
        QuickUnion uf = new QuickUnion(10);
        uf.union(4, 3);
        uf.union(3, 8);
        uf.union(6, 5);
        uf.union(9, 4);
        uf.union(2, 1);
        uf.union(8, 9);
        uf.union(5, 0);
        uf.union(7, 2);
        uf.union(6, 1);
        uf.union(1, 0);
        uf.union(6, 7);
        System.out.println("共计 " + uf.count() + " 个连通块");
        if (uf.connected(7, 8))
            System.out.println("7 和 8 相连~");
        else
            System.out.println("7 和 8 不相连~");
        if (uf.connected(7, 0))
            System.out.println("7 和 0 相连~");
        else
            System.out.println("7 和 0 不相连~");
    }
}
