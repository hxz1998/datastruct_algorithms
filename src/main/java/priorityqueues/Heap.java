package priorityqueues;

public class Heap {

    public static void sort(Comparable[] a) {
        int N = a.length;
        for (int i = N / 2; i >= 1; i--)
            sink(a, i, N);
        while (N > 1) {
            exch(a, 1, N);
            sink(a, 1, --N);
        }
    }

    private static void sink(Comparable[] a, int k, int N) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && less(a, j, j + 1)) j++;
            if (less(a, j, k)) break;
            exch(a, j, k);
            k = j;
        }
    }

    private static boolean less(Comparable[] a, int i, int j) {
        i--;j--;
        return a[i].compareTo(a[j]) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        i--;j--;
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void main(String[] args) {
//        String[] a = new String[]{"A", "C", "D", "B", "Z", "E"};
        String[] a = new String[]{"S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};
        Heap.sort(a);
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
    }
}
