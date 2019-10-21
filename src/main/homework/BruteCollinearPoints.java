import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class BruteCollinearPoints {

    private int count;
    private Node tail;

    public BruteCollinearPoints(Point[] points) {
        // finds all line segments containing 4 points
        if (points == null) throw new IllegalArgumentException("Empty!");

        // 初始化变量
        int num = points.length;

        Point[] copy = new Point[num];
        count = 0;

        // 检查非空和相同元素，进行元素拷贝
        for (int i = 0; i < num; i++) {
            if (points[i] == null) throw new IllegalArgumentException("Null!");
            for (int j = i + 1; j < num; j++)
                if (points[i].compareTo(points[j]) == 0) throw new IllegalArgumentException("Same!");
            copy[i] = points[i];
        }

        Arrays.sort(copy);

        for (int i = 0; i < num; i++) {
            for (int j = i + 1; j < num; j++) {
                for (int k = j + 1; k < num; k++) {
                    for (int l = k + 1; l < num; l++) {
                        double p0p1 = copy[i].slopeTo(copy[j]);
                        double p0p2 = copy[i].slopeTo(copy[k]);
                        double p0p3 = copy[i].slopeTo(copy[l]);
                        if (p0p1 == p0p2 && p0p1 == p0p3) {
                            if (tail != null) {
                                Node node = new Node();
                                node.value = new LineSegment(copy[i], copy[l]);
                                node.prev = tail;
                                tail = node;
                            } else {
                                tail = new Node();
                                tail.value = new LineSegment(copy[i], copy[l]);
                                tail.prev = null;
                            }
                            count++;
                        }
                    }
                }
            }
        }
    }

    private class Node {
        LineSegment value;
        Node prev;
    }

    public int numberOfSegments() {        // the number of line segments
        return count;
    }

    public LineSegment[] segments() {                // the line segments
        LineSegment[] lineSegments = new LineSegment[count];
        Node current = tail;
        for (int i = 0; i < count; i++) {
            lineSegments[i] = current.value;
            current = current.prev;
        }
        return lineSegments;
    }

    public static void main(String[] args) {
        // read the n points from a file
        /*int[] data = In.readInts(DataPathTemplate.build("input8.txt"));
        Point[] points = new Point[data[0]];
        int index = 0;
        for (int i = 1; i < data.length;) {
            points[index++] = new Point(data[i++], data[i++]);
        }*/

        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);

        for (Point p : points) {
            p.draw();
        }

        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);

        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }

        StdDraw.show();
    }
}
