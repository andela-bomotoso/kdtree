import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

import java.util.ArrayList;
import java.util.TreeSet;

public class PointSET {
    private TreeSet<Point2D> pointSets;

    public PointSET() {
        this.pointSets = new TreeSet<Point2D>();
    }                             // construct an empty set of points

    public boolean isEmpty() {
        return pointSets.isEmpty();
    }                   // is the set empty?

    public int size() {
        return pointSets.size();
    }                     // number of points in the set

    public void insert(Point2D p) {
        if (p == null)
            throw new java.lang.IllegalArgumentException();
        pointSets.add(p);
    }      // add the point to the set (if it is not already in the set)

    public boolean contains(Point2D p) {
        if (p == null)
            throw new java.lang.IllegalArgumentException();
        return pointSets.contains(p);
    }          // does the set contain point p?

    public void draw() {
        for (Point2D point2D : pointSets)
            point2D.draw();
    }                      // draw all points to standard draw

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null)
            throw new java.lang.IllegalArgumentException();
        TreeSet<Point2D> pointsInRect = new TreeSet<Point2D>();
        for (Point2D point2D : pointSets) {
            if(rect.contains(point2D))
             pointsInRect.add(point2D);
        }
        return pointsInRect;
    }          // all points that are inside the rectangle (or on the boundary)


    public Point2D nearest(Point2D p) {
        if (p == null)
            throw new java.lang.IllegalArgumentException();
        if (pointSets.isEmpty())
            return null;
        double nearest = Integer.MAX_VALUE;
        Point2D nearestPoint2D = new Point2D(0, 0);
        for (Point2D point2D : pointSets) {
            double distance = point2D.distanceSquaredTo(p);
            if (Math.abs(distance) < nearest) {
                nearest = distance;
                nearestPoint2D = point2D;
            }
        }
        return nearestPoint2D;
    }      // a nearest neighbor in the set to point p; null if the set is empty

    public static void main(String[] args) {
        ArrayList<Point2D> pointSets = new ArrayList<Point2D>();

        Point2D point1 = new Point2D(0.8, 0.6);
        Point2D point2 = new Point2D(0.5, 0.9);
        pointSets.add(point1);
        pointSets.add(point2);
        Point2D point3 = new Point2D(0.5, 0.9);
        Point2D point4 = new Point2D(0.5, 0.8);

        System.out.println(pointSets.contains(point3));
        System.out.println(pointSets.contains(point4));
//        System.out.println(point1.equals(point2));
//        System.out.println(point2.equals(point3));

    }
}
