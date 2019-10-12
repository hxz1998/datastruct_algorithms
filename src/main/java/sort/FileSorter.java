package sort;

import edu.princeton.cs.algs4.Insertion;

import java.io.File;

public class FileSorter {
    public static void main(String[] args) {
        File dir = new File(".");
        File[] files = dir.listFiles();
        Insertion.sort(files);
        for (int i = 0; i < files.length; i++) {
            System.out.println(files[i]);
        }
    }
}
