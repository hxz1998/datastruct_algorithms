package sort;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Insertion;
import util.DataPathTemplate;

public class StringSorter {
    public static void main(String[] args) {
        String[] a = In.readStrings(DataPathTemplate.build("word3.txt"));
        Insertion.sort(a);
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }
}
