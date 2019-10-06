import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private boolean[] table;
    private int count;
    private final WeightedQuickUnionUF uf;
    private final WeightedQuickUnionUF backwashUF;
    private final int size;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Illegal args!");
        }
        size = n;
        table = new boolean[n * n + 2];
        count = 0;
        uf = new WeightedQuickUnionUF(n * n + 2);
        backwashUF = new WeightedQuickUnionUF(n * n + 1);
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        valid(row, col);
        if (!isOpen(row, col)) {
            int pos = positionConvert(row, col);
            table[pos] = true;
            count++;
            int rowPrev = positionConvert(row - 1, col), rowNext = positionConvert(row + 1, col);
            int colPrev = positionConvert(row, col - 1), colNext = positionConvert(row, col + 1);
            if (row == 1) {
                // 如果开放的是顶部节点，那么将其连接到虚拟顶部节点。
                uf.union(0, pos);
                backwashUF.union(0, pos);
            } else if (isOpen(row - 1, col)) {
                //如果上面的邻接点是开放的，那么相连
                uf.union(rowPrev, pos);
                backwashUF.union(rowPrev, pos);
            }
            if (row == size) {
                // 如果开放的是底部节点，那么将其连接到虚拟末端节点
                uf.union(pos, size * size + 1);
            } else if (isOpen(row + 1, col)) {
                //如果下面的邻接点是开放的，那么连接
                uf.union(rowNext, pos);
                backwashUF.union(rowNext, pos);
            }

            if (col != 1 && isOpen(row, col - 1)) {
                // 如果左边的邻接点是开放的，那么连接
                uf.union(colPrev, pos);
                backwashUF.union(colPrev, pos);
            }
            if (col != size && isOpen(row, col + 1)) {
                // 如果右边的邻接点是开放的，那么连接
                uf.union(colNext, pos);
                backwashUF.union(colNext, pos);
            }

        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        valid(row, col);
        return table[positionConvert(row, col)];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        valid(row, col);
        return backwashUF.connected(0, positionConvert(row, col));
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return count;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.connected(0, size * size + 1);
    }

    private int positionConvert(int row, int col) {
        return (row - 1) * size + col;
    }

    private void valid(int row, int col) {
        if (row < 1 || col < 1 || col > size || row > size) {
            throw new IllegalArgumentException("Illegal args!");
        }
    }

    // test client (optional)
    public static void main(String[] args) {
        Percolation percolation = new Percolation(4);
        System.out.println(percolation.percolates());
        percolation.open(1, 3);
        percolation.open(2, 3);
        percolation.open(2, 2);
        percolation.open(3, 2);
        percolation.open(4, 2);
        System.out.println(percolation.percolates());
    }
}
