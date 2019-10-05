package unionfind;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.UF;
import util.DataPathTemplate;


/**
 * 该程序是一个查并集简单的示例程序，使用的数据文件为tinyUF.txt
 * 每一次输出的是新建立连接后产生的连接对，如果已经在一个components里面了，则不会建立连接。
 */
public class UnionFind {
    public static void main(String[] args) {
        int N = StdIn.readInt();
        UF uf = new UF(N);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (!uf.connected(p, q)) {
                uf.union(p, q);
                StdOut.println(p + " " + q);
            }
        }
    }
}
