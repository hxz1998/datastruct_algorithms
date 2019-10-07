package analysisofalgorithms;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;
import util.DataPathTemplate;

public class ThreeSum {
    public static void main(String[] args) {

        int[] a = {30, -40, -20, -10, 40, 0, 10, 5};

        long start = System.currentTimeMillis();
        System.out.println(count(a));
        long end = System.currentTimeMillis();
        System.out.printf("计算 %d 个数据用了 %d Millis\n\n", a.length, end - start);


        a = In.readInts(DataPathTemplate.build("1KInts.txt"));
        start = System.currentTimeMillis();
        System.out.println(count(a));
        end = System.currentTimeMillis();
        System.out.printf("计算 %d 个数据用了 %d Millis\n\n", a.length, end - start);

        a = In.readInts(DataPathTemplate.build("2KInts.txt"));
        start = System.currentTimeMillis();
        System.out.println(count(a));
        end = System.currentTimeMillis();
        System.out.printf("计算 %d 个数据用了 %d Millis\n\n", a.length, end - start);

        a = In.readInts(DataPathTemplate.build("4KInts.txt"));
        start = System.currentTimeMillis();
        System.out.println(count(a));
        end = System.currentTimeMillis();
        System.out.printf("计算 %d 个数据用了 %d Millis\n\n", a.length, end - start);


        Stopwatch stopwatch = new Stopwatch();
        a = In.readInts(DataPathTemplate.build("4KInts.txt"));
        StdOut.println(count(a));
        System.out.printf("计算 %d 个数据用了 %f s\n\n", a.length, stopwatch.elapsedTime());
    }

    public static int count(int[] array) {
        int N = array.length;
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                for (int k = j + 1; k < N; k++) {
                    if (array[i] + array[j] + array[k] == 0) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

}
