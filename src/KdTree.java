import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.util.TreeSet;

public class KdTree {
    private Point2D nearestPoint;
    private double nearestDistance;
    private Node node;
    private int size;
    private final RectHV DEFAULTRECT = new RectHV(0, 0, 1, 1);

    public KdTree() {
        this.node = null;
        this.size = 0;
    }                            // construct an empty set of points

    public boolean isEmpty() {
        return size == 0;
    }                    // is the set empty?

    public int size() {
        return size;
    }                     // number of points in the set

   /* public void insert(Point2D p) {
//        //corner case
//        if (p == null)
//            throw new IllegalArgumentException();
//        //recursive call
//        node = insert(node, p, true);
        if(p == null)
            throw new IllegalArgumentException();

        node = insert(node, p, true);

    }         // add the point to the set (if it is not already in the set)
    private Node insert(Node node, Point2D pointToInsert, boolean verticalorient)
    {
        if(node == null)
        {
            size++;
            return new Node(pointToInsert, verticalorient);
        }

        if(node.point2D.equals(pointToInsert))
            return node;

        double coordinatecompare = 0;

        if(verticalorient)
        {
            coordinatecompare =(pointToInsert.x()) - (node.point2D.x());
            verticalorient = (!verticalorient);

            if(coordinatecompare < 0)
                node.lb = insert(node.lb, pointToInsert, verticalorient);

            else
                node.rt = insert(node.rt, pointToInsert, verticalorient);
        }

        else
        {
            coordinatecompare =(pointToInsert.y()) - (node.point2D.y());
            verticalorient = (!verticalorient);

            if(coordinatecompare < 0)
                node.lb = insert(node.lb, pointToInsert, verticalorient);

            else
                node.rt = insert(node.rt, pointToInsert, verticalorient);
        }

        return node;
    }*/

    public void insert(Point2D p) // add the point to the set (if it is not already in the set)
    {
        if (p == null)
            throw new IllegalArgumentException();

        node = insert(node, p, true);

    }

    private Node insert(Node node, Point2D pointToInsert, boolean verticalorient) {
        if (node == null)
        {
            size++;
            return new Node(pointToInsert, verticalorient);
        }

        if (node.point2D.equals(pointToInsert))
            return node;

        double coordinatecompare = 0;

        if (verticalorient)
        {
            coordinatecompare = (pointToInsert.x()) - (node.point2D.x());
            verticalorient = (!verticalorient);

            if (coordinatecompare < 0)
                node.lb = insert(node.lb, pointToInsert, verticalorient);

            else
                node.rt = insert(node.rt, pointToInsert, verticalorient);
        }

        else
        {
            coordinatecompare = (pointToInsert.y()) - (node.point2D.y());
            verticalorient = (!verticalorient);

            if (coordinatecompare < 0)
                node.lb = insert(node.lb, pointToInsert, verticalorient);

            else
                node.rt = insert(node.rt, pointToInsert, verticalorient);
        }

        return node;
    }

    public boolean contains(Point2D p) {
        if (p == null)
            throw new IllegalArgumentException();
        return contains(node, p, true);
    }       // does the set contain point p?

    private boolean contains(Node node, Point2D point, boolean verticalorient)
    {
        if(node == null)
            return false;

        if(node.point2D.equals(point))
            return true;

        double compare = 0;

        if(verticalorient)
            compare = (point.x()) - (node.point2D.x());

        else
            compare = (point.y()) - (node.point2D.y());

        if(compare < 0)
            return contains(node.lb, point, !verticalorient);

        else
            return contains(node.rt, point, !verticalorient);

    }


    public void draw() {
        extradraw(node, DEFAULTRECT);
    }               // draw all points to standard draw

    private void extradraw (Node node, RectHV rect)
    {

        if(node!=null)
        {
            //nODE is not null at this point

            StdDraw.setPenColor(StdDraw.BLACK) ;
            StdDraw.setPenRadius(0.05);
            node.point2D.draw();

            if(node.isVerticalOrientation)
            {
                StdDraw.setPenColor(StdDraw.RED) ;
                new Point2D(node.point2D.x(),0).drawTo(new Point2D(node.point2D.x(),1));
            }

            else
            {
                StdDraw.setPenColor(StdDraw.BLUE) ;
                new Point2D(0,node.point2D.y()).drawTo(new Point2D(1,node.point2D.y()));
            }
            extradraw(node.lb, getLeftRect(node));
            extradraw(node.rt, getRightRect(node));

        }
    }

    private void draw(Node node, RectHV rect) {
        if (node != null) {
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.setPenRadius(0.02);
            node.point2D.draw();
            StdDraw.setPenRadius();

            if (node.isVerticalOrientation) {
                StdDraw.setPenColor(StdDraw.RED);
                new Point2D(node.point2D.x(), rect.ymin()).drawTo(new Point2D(node.point2D.x(), rect.ymax()));
            } else {
                StdDraw.setPenColor(StdDraw.BLUE);
                new Point2D(rect.xmin(), node.point2D.y()).drawTo(new Point2D(rect.xmax(), node.point2D.y()));

            }

            draw(node.lb, getLeftRect(node));
            draw(node.rt, getRightRect(node));
        }
    }

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null)
            throw new IllegalArgumentException();
        TreeSet<Point2D> point2DTreeSet = new TreeSet<Point2D>();
        range(node, rect, DEFAULTRECT, point2DTreeSet);

        return point2DTreeSet;
    }         // all points that are inside the rectangle (or on the boundary)

    private void range(Node node, RectHV rect, RectHV defaultrect, TreeSet<Point2D> point2DTreeSet) {
        if (node == null)
            return;
        //check if query rectangle intersects the splitting line segment
        if (rect.intersects(defaultrect)) {
            Point2D currentNodePoint = node.point2D;
            if (rect.contains(currentNodePoint))
                point2DTreeSet.add(currentNodePoint);

            //search both right and left subtrees
            range(node.lb, rect, getLeftRect(node), point2DTreeSet);
            range(node.rt, rect, getRightRect(node), point2DTreeSet);
        }
    }

//    private RectHV getLeftRect(Node node) {
//        if (node.isVerticalOrientation)
//            return new RectHV(0, 0, node.point2D.x(), 1);
//        else
//            return new RectHV(0, 0, 1, node.point2D.y());
//    }
//
//    private RectHV getRightRect(Node node) {
//        if (node.isVerticalOrientation)
//            return new RectHV(node.point2D.x(), DEFAULTRECT.ymin(), DEFAULTRECT.xmax(), DEFAULTRECT.ymax());
//        else
//            return new RectHV(DEFAULTRECT.xmin(), node.point2D.y(), DEFAULTRECT.xmax(), DEFAULTRECT.ymax());
//    }

    private RectHV getLeftRect(Node node) {
        if (node.isVerticalOrientation)
            return new RectHV(0, 0, node.point2D.x(), 1);
        else
            return new RectHV(0, 0, 1, node.point2D.y());
    }
    private RectHV getRightRect(Node node) {
        if (node.isVerticalOrientation)
            return new RectHV(node.point2D.x(), 0, 1, 1);
        else
            return new RectHV(0, node.point2D.y(), 1, 1);
    }

    public Point2D nearest(Point2D givenPoint) {

        if (givenPoint == null)
            throw new java.lang.NullPointerException();
        if (isEmpty())
            return null;
        nearestPoint = node.point2D;
        nearestDistance = nearestPoint.distanceTo(givenPoint);
        nearest(node, givenPoint);
        return nearestPoint;
    } // a nearest neighbor in the set to point p; null if the set is empty

    private void nearest(Node node, Point2D givenPoint) {
        if (node == null)
            return;
        if (nearestDistance > givenPoint.distanceTo(node.point2D)) {
            nearestPoint = node.point2D;
            nearestDistance = givenPoint.distanceTo(node.point2D);
        }
        nearest(node.lb, givenPoint);
        nearest(node.rt, givenPoint);
    }


    public static void main(String[] args) {
        KdTree kdTree = new KdTree();
        kdTree.insert(new Point2D(0.7, 0.2));
        kdTree.insert(new Point2D(0.5, 0.4));
        //kdTree.insert(new Point2D(0.2, 0.3));
        kdTree.draw();
       // System.out.println(kdTree.contains(new Point2D(0.7, 0.2))); // returns true
       // System.out.println(kdTree.contains(new Point2D(3, 2))); // returns false

    }

    private static class Node {
        private Point2D point2D;
        private RectHV rectHV;
        private Node lb;
        private Node rt;
        private boolean isVerticalOrientation;

        Node(Point2D point2D, boolean isVerticalOrientation) {
            this.point2D = point2D;
            this.rectHV = null;
            this.lb = null;
            this.rt = null;
            this.isVerticalOrientation = isVerticalOrientation;
        }
    }
}
