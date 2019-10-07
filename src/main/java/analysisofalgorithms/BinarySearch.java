package analysisofalgorithms;

import edu.princeton.cs.algs4.StdOut;

public class BinarySearch {
    public static void main(String[] args) {
        int[] array = {0, 1, 2, 3, 4, 5, 6, 7, 8};
        for (int i = 0; i < 9; i++) {
            StdOut.printf("查找 %d 的时候找到了 %d\n\n", i, binarySearch(array, i));
        }
    }

    public static int binarySearch(int[] array, int key) {
        int lo = 0, hi = array.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (key > array[mid]) lo = mid + 1;
            else if (key < array[mid]) hi = mid - 1;
            else return mid;
        }
        return -1;
    }
}
