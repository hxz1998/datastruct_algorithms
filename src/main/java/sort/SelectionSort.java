/**
 * Created by Xiaozhong on 2019/4/24.
 * Copyright (c) 2019/4/24 Xiaozhong. All rights reserved.
 */
package sort;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import util.DataPathTemplate;

/**
 * 该算法为选择排序算法示例，排序数据文件为tiny.txt
 */
public class SelectionSort {

    /**
     * 比较两个元素的大小值，需要实现Comparable接口，如果第
     * @param v 第一个元素
     * @param w 第二个元素
     * @return 第一个元素是否小于第二个元素，如果是那么返回 true
     */
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    /**
     * 交换数组中的两个元素
     * @param a 待处理的元素数组
     * @param i 待交换的数组下标
     * @param j 待交换的另一个数组下标
     */
    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    /**
     * 将一个数组进行挨个输出，并输出一个换行符
     * @param a 待输出的数组
     */
    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.print(a[i] + " ");
        }
        StdOut.println();
    }

    /**
     * 判断一个数组是否从小到大有序排列
     * @param a 待判断的数组
     * @return 如果数组无序则返回false
     */
    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i - 1])) return false;
        }
        return true;
    }

    /**
     * 排序算法的核心部分
     * @param a 待处理的数组
     */
    public static void sort(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            int min = i;
            for (int j = i; j < a.length; j++) {
                if (less(a[j], a[min])) min = j;
                exch(a, min, i);
            }
        }
    }

    public static void main(String[] args) {
        String[] a = new In(DataPathTemplate.build("tiny.txt")).readAllStrings();
        sort(a);
        assert isSorted(a);
        show(a);
    }
}
