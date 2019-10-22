package sort;

import edu.princeton.cs.algs4.StdRandom;

public class Quick3Way {

    /**
     * 对外提供的统一接口
     *
     * @param a 待排序的数组对象
     */
    public static void sort(Comparable[] a) {
        // 首先打乱原有顺序，很重要，关系到算法的性能优劣
        StdRandom.shuffle(a);
        // 对数组进行排序算法调用
        sort(a, 0, a.length - 1);
    }

    /**
     * 对内实现的排序算法
     *
     * @param a  待排序的对象
     * @param lo 子序列起始位置
     * @param hi 子序列终止位置
     */
    private static void sort(Comparable[] a, int lo, int hi) {
        if (lo >= hi) return;

        /* 三个重要的指针
        [lo..lt] 小于v的元素
        [lt..i] 等于v的元素
        [i..gt] 未审查的元素
        [gt..hi] 大于v的元素
        */
        int lt = lo, gt = hi, i = lo + 1;
        Comparable v = a[lo];
        while (true) {
            if (i > gt) break;
            int cmp = a[i].compareTo(v);
            if (cmp < 0) exch(a, i++, lt++);
            else if (cmp > 0) exch(a, i, gt--);
            else i++;
        }
        sort(a, lo, lt - 1);
        sort(a, lt + 1, hi);
    }

    /**
     * 交换数组中的两个值
     *
     * @param a 待处理的数组
     * @param i 第一个下标
     * @param j 第二个下标
     */
    public static void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    /**
     * 切分数组，并且获取到切分对象所在的下标
     *
     * @param a  待切分的数组
     * @param lo 起始位置
     * @param hi 终止位置
     * @return 切分之后的中间元素下标
     */
    private static int partition(Comparable[] a, int lo, int hi) {
        /* 记录所用到的下标值。
        其中，lo用来的是给选择基准点的，hi + 1是为了矫正--j在循环中造成的问题。
         */
        int i = lo, j = hi + 1;
        Comparable v = a[lo];   //该元素就是用来切分的基准点
        while (true) {
            // 循环，直到两个下标指针相遇
            while (less(a[++i], v)) if (i == hi) break; // 对基准点左侧元素进行检查
            while (less(v, a[--j])) if (j == lo) break; // 对基准点右侧元素进行检查
            if (i >= j) break;  // 如果相遇，那么退出
            exch(a, i, j);  // 单次循环交换两个下标指向的对象
        }
        exch(a, j, lo); // 最后交换基准元素和 j 指向的元素。
        return j;
    }

    public static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    public static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
    }

    public static void main(String[] args) {
        String[] strings = new String[]{"K", "R", "A", "T", "E", "L", "E", "P", "U", "I", "M", "Q", "C", "X", "O", "S"};
        sort(strings);
        show(strings);
    }
}
