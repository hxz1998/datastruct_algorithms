import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.RectHV;

/**
 * Created by Xiaozhong on 2019/11/3.
 * Copyright (c) 2019/11/3 Xiaozhong. All rights reserved.
 */

public class PointSET {
    private SET<Point2D> points;

    public PointSET() {     // construct an empty set of points
        points = new SET<>();
    }

    public boolean isEmpty() {  // is the set empty?
        return points.isEmpty();
    }

    public int size() {     // number of points in the set
        return points.size();
    }

    public void insert(Point2D p) { // add the point to the set (if it is not already in the set)
        if (p == null) throw new IllegalArgumentException("Illegal Args!");
        points.add(p);
    }

    public boolean contains(Point2D p) {    // does the set contain point p?
        if (p == null) throw new IllegalArgumentException("Illegal Args!");
        return points.contains(p);
    }

    public void draw() {    // draw all points to standard draw
        for (Point2D p : points)
            StdDraw.point(p.x(), p.y());
    }

    public Iterable<Point2D> range(RectHV rect) {   // all points that are inside the rectangle (or on the boundary)
        if (rect == null) throw new IllegalArgumentException("Rect is null");
        Queue<Point2D> queue = new Queue<>();
        for (Point2D p : points) {
            if (rect.contains(p)) queue.enqueue(p);
        }
        return queue;
    }

    public Point2D nearest(Point2D p) { // a nearest neighbor in the set to point p; null if the set is empty
        if (p == null) throw new IllegalArgumentException("Illegal args");
        double minDis = Double.MAX_VALUE;
        Point2D result = null;
        for (Point2D x : points) {
            double distance = p.distanceSquaredTo(x);
            if (distance < minDis) {
                result = x;
                minDis = distance;
            }
        }
        return result;
    }

    public static void main(String[] args) {    // unit testing of the methods (optional)
    }
}
