package unionfind;

/**
 * 该程序为基本的查并集快速查找算法，旨在完成快速查找，但是并集操作比较费时间。
 */
public class QuickFindUFEagerApproach {
    private int[] id;

    public QuickFindUFEagerApproach(int N) {
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }

    public boolean connected(int p, int q) {
        return id[p] == id[q];
    }

    public void union(int p, int q) {
        int pid = id[p];
        int qid = id[q];
        for (int i = 0; i < id.length; i++) {
            if (id[i] == pid) {
                id[i] = qid;
            }
        }
    }

    public static void main(String[] args) {
        QuickFindUFEagerApproach uf = new QuickFindUFEagerApproach(3);
        System.out.println(uf.connected(1, 2));
        uf.union(1, 2);
        System.out.println(uf.connected(1, 2));
    }
}
