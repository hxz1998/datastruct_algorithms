package sort;

import edu.princeton.cs.algs4.Insertion;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

public class Experiment {
    public static void main(String[] args) {
        int N = StdIn.readInt();
        Double[] a = new Double[N];
        for (int i = 0; i < N; i++) {
            a[i] = StdRandom.uniform();
        }
        Insertion.sort(a);
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }
}
