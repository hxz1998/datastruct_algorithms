import java.util.ArrayList;

public class Board {
    private static final int BLANK = 0;
    private final int size;
    private int[][] blocks;

    /**
     * 根据提供的数组来构造初始的矩阵
     *
     * @param tiles 待复制的数组
     */
    public Board(int[][] tiles) {
        size = tiles.length;
        blocks = new int[size][size];
        //使用拷贝函数进行元素拷贝
        for (int row = 0; row < size; row++)
            for (int col = 0; col < size; col++)
                this.blocks[row][col] = tiles[row][col];
    }

    /**
     * 获取现有的矩阵维度
     *
     * @return 矩阵维度
     */
    public int dimension() {
        return size;
    }

    /**
     * 获取值 value 在目标矩阵中的行数
     *
     * @param value 待计算的值
     * @return 目标矩阵中的行数
     */
    private int getRow(int value) {
        return (value - 1) / size;
    }

    /**
     * 获取值 value 在目标矩阵中的列数
     *
     * @param value 待计算的值
     * @return 目标矩阵中的列数
     */
    private int getCol(int value) {
        return (value - 1) % size;
    }

    /**
     * 计算指定下标所对应的值
     *
     * @param row 行标
     * @param col 列标
     * @return 应该放置的数值
     */
    private int getValue(int row, int col) {
        return row * size + col + 1;
    }

    /**
     * 计算汉明距离
     *
     * @return 汉明距离
     */
    public int hamming() {
        // number of blocks out of place
        int hamming = 0;
        for (int row = 0; row < size; row++)
            for (int col = 0; col < size; col++)
                // 累积计算不符合要求的点的个数
                if (blocks[row][col] != BLANK && blocks[row][col] != getValue(row, col))
                    hamming++;
        return hamming;
    }

    /**
     * 计算曼哈顿距离
     *
     * @return 返回曼哈顿距离
     */
    public int manhattan() {
        // sum of Manhattan distances between blocks and goal
        int manhattan = 0;
        for (int row = 0; row < size; row++)
            for (int col = 0; col < size; col++)
                // 如果不在应该在的地方，那么进行计算
                if (blocks[row][col] != BLANK && blocks[row][col] != getValue(row, col))
                    // 应该在的行数减去现在在的行数 + 应该在的列数减去现在在的列数，再进行累积求和
                    manhattan += Math.abs(getRow(blocks[row][col]) - row) + Math.abs(getCol(blocks[row][col]) - col);
        return manhattan;
    }

    /**
     * 判断是否已经达到了目标矩阵
     *
     * @return 是否达到了目标矩阵
     */
    public boolean isGoal() {
        // is this board the goal board?
        for (int row = 0; row < size; row++)
            for (int col = 0; col < size; col++)
                if (blocks[row][col] != BLANK && blocks[row][col] != getValue(row, col))
                    return false;
        return true;
    }

    public Board twin() {
        // a board that is obtained by exchanging any pair of blocks
        Board twinBoard = new Board(blocks);
        int firRow = 0;
        int firCol = 0;
        if (blocks[firRow][firCol] == BLANK)
            firCol++;
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (blocks[row][col] != blocks[firRow][firCol] && blocks[row][col] != BLANK) {
                    twinBoard.swap(firRow, firCol, row, col);
                    return twinBoard;
                }
            }
        }
        return twinBoard;
    }

    private void swap(int vRow, int vCol, int wRow, int wCol) {
        int t = blocks[vRow][vCol];
        blocks[vRow][vCol] = blocks[wRow][wCol];
        blocks[wRow][wCol] = t;
    }

    public boolean equals(Object y) {
        // does this board equal y?
        if (y == null) return false;
        if (y == this) return true;
        if (y.getClass().isInstance(this)) {
            Board yb = (Board) y;
            if (yb.size != this.size) return false;
            else {
                for (int row = 0; row < size; row++)
                    for (int col = 0; col < size; col++)
                        if (yb.blocks[row][col] != blocks[row][col])
                            return false;
                return true;
            }
        } else return false;
    }

    public Iterable<Board> neighbors() {
        // all neighboring boards
        ArrayList<Board> neighbors = new ArrayList<Board>();
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (blocks[row][col] == BLANK) {
                    // 空白的位置分别与上下左右的元素交换一次位置就得到一个邻居board
                    // 与上方元素互换
                    if (row > 0) {
                        Board neighborT = new Board(blocks);
                        neighborT.swap(row, col, row - 1, col);
                        neighbors.add(neighborT);
                    }
                    // 与下方元素互换
                    if (row < size - 1) {
                        Board neighborB = new Board(blocks);
                        neighborB.swap(row, col, row + 1, col);
                        neighbors.add(neighborB);
                    }
                    // 与左边元素互换
                    if (col > 0) {
                        Board neighborL = new Board(blocks);
                        neighborL.swap(row, col, row, col - 1);
                        neighbors.add(neighborL);
                    }
                    // 与右边元素互换
                    if (col < size - 1) {
                        Board neighborR = new Board(blocks);
                        neighborR.swap(row, col, row, col + 1);
                        neighbors.add(neighborR);
                    }
                }
            }
        }
        return neighbors;
    }

    public String toString() {
        // string representation of this board (in the output format specified
        // below)
        StringBuilder sb = new StringBuilder();
        sb.append(size + "\n");
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                //本来考虑到n<128时元素可能会很大，设置的是%6d，但是提交时不满足校验规则
                //校验规则要求必须是%2d，很奇怪的校验
                sb.append(String.format("%2d ", blocks[row][col]));
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
//         unit tests (not graded)
        int[][] test = {{0, 1}, {2, 3}};
        Board b = new Board(test);
        System.out.println(b);
        System.out.println(b.hamming());
        System.out.println(b.manhattan());
        System.out.println(b.toString());
    }
}