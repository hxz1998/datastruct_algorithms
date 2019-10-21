package sort;

public class Merge {


    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {

        assert isSorted(a, lo, mid);
        assert isSorted(a, mid + 1, hi);

        // 拷贝原有数组内容到辅助数组中，注意，需要拷贝的范围是[lo, hi]，而不是[lo, hi)!!!
        for (int i = lo; i <= hi; i++) aux[i] = a[i];

        // 指定三个指针i， j， k，同样注意的是操作的数组范围。
        int i = lo, k = lo, j = mid + 1;
        for (; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (less(aux[i], aux[j])) a[k] = aux[i++];
            else a[k] = aux[j++];
        }

        assert isSorted(a, lo, hi);
    }

    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        // 首先检查lo是否小于hi，如果等于的话说明只有一个元素了，没必要进行以下操作
        if (lo >= hi) return;

        // 构造下一个分治的范围
        int mid = lo + (hi - lo) / 2;

        // 排序左半部分
        sort(a, aux, lo, mid);

        // 排序右半部分
        sort(a, aux, mid + 1, hi);

        // 合并起来两边
        merge(a, aux, lo, mid, hi);
    }

    /**
     * 对外部提供一个简单易操作的方法
     * @param a 要排序的对象
     */
    public static void sort(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];
        sort(a, aux, 0, a.length - 1);
    }

    public static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i < hi; i++) {
            if (less(a[i], a[i - 1])) return false;
        }
        return true;
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }


    public static void main(String[] args) {
        String[] a = new String[]{"M", "E", "R", "G", "E", "S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};
        sort(a);
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
    }
}
