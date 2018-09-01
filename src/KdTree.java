import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;

public class KdTree {

    private Node node;
    private int size;
    private boolean isVerticalOrientation;

    public KdTree() {
        this.node = null;
        this.size = 0;
        isVerticalOrientation = true;
    }                            // construct an empty set of points

    public boolean isEmpty() {
        return size == 0;
    }                    // is the set empty?

    public int size() {
        return size;
    }                     // number of points in the set

    public void insert(Point2D p) {
        if (p == null)
            throw new IllegalArgumentException();
        node = insert(node, p, true);

    }         // add the point to the set (if it is not already in the set)


    private Node insert(Node node, Point2D point2D, boolean isVerticalOrientation) {
        // if it is not in the set, create new node
        if (node == null) {
            size++;
            return new Node(point2D, isVerticalOrientation);
        }

        // check if point exist
        if (node.point2D.equals(point2D))
            return node;

        // insert point if it does not exist
        if (node.isVerticalOrientation && point2D.x() < node.point2D.x() || !node.isVerticalOrientation && point2D.y() < node.point2D.y()) {
            node.lb = insert(node.lb, point2D, !node.isVerticalOrientation);
        } else {
            node.rt = insert(node.rt, point2D, !node.isVerticalOrientation);
        }
        return node;
    }

    public boolean contains(Point2D p) {
        if (p == null)
            throw new IllegalArgumentException();
        return contains(node, p, true);
    }       // does the set contain point p?


    private boolean contains(Node node, Point2D point2D, boolean orientation) {
        if (node == null) {
            return false;
        }

        if (node.point2D.equals(point2D)) {
            return true;
        }

        double cmp;
        if (orientation == isVerticalOrientation) {
            cmp = point2D.x() - node.point2D.x();
        } else {
            cmp = point2D.y() - node.point2D.y();
        }


        if (cmp < 0) {
            return contains(node.lb, point2D, !orientation);
        } else {
            return contains(node.rt, point2D, !orientation);
        }
    }


    public void draw() {

    }               // draw all points to standard draw

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null)
            throw new IllegalArgumentException();
        Stack<Point2D> point2DStack = new Stack<Point2D>();
        range(node, rect, point2DStack);

        return point2DStack;
    }         // all points that are inside the rectangle (or on the boundary)

    private void range(Node node, RectHV rectHV, Stack<Point2D> point2DStack) {

    }

    public Point2D nearest(Point2D p) {
        return null;
    }           // a nearest neighbor in the set to point p; null if the set is empty

    public static void main(String[] args) {
        KdTree kdTree = new KdTree();

        kdTree.insert(new Point2D(0.5, 0.875));
        kdTree.insert(new Point2D(1.0, 0.125));
        kdTree.insert(new Point2D(0.6875, 0.4375));
        kdTree.insert(new Point2D(0.5625, 0.75));
        kdTree.insert(new Point2D(0.6875, 0.8125));
        kdTree.insert(new Point2D(1.0, 0.375));
        kdTree.insert(new Point2D(0.5625, 0.4375
        ));
        kdTree.insert(new Point2D(0.5625, 0.4375));

        System.out.println(kdTree.size());

        kdTree.insert(new Point2D(0.75, 0.375));
        kdTree.insert(new Point2D(0.1875, 0.75));
        kdTree.insert(new Point2D(0.375, 0.0));
        kdTree.insert(new Point2D(0.625, 0.25));
        kdTree.insert(new Point2D(0.4375, 0.0625));
        kdTree.insert(new Point2D(0.125, 0.3125
        ));
        kdTree.insert(new Point2D(0.1875, 0.25));
        kdTree.insert(new Point2D(0.25, 0.625));
        kdTree.insert(new Point2D(1.0, 0.4375));
        kdTree.insert(new Point2D(0.875, 0.1875));
        kdTree.insert(new Point2D(0.375, 0.75));
        kdTree.insert(new Point2D(0.0625, 0.5));
        kdTree.insert(new Point2D(0.9375, 0.0625));
        kdTree.insert(new Point2D(0.875, 0.0625
        ));
        kdTree.insert(new Point2D(1.0, 0.8125));
        kdTree.insert(new Point2D(0.1875, 1.0));
        kdTree.insert(new Point2D(0.9375, 0.9375));
        kdTree.insert(new Point2D(0.75, 0.375));
        System.out.println(kdTree.contains(new Point2D(0.1875, 0.75)));
        System.out.println(kdTree.size());


    }

    private static class Node {
        private Point2D point2D;
        private RectHV rectHV;
        private Node lb;
        private Node rt;
        private boolean isVerticalOrientation;

        Node(Point2D point2D, boolean isVerticalOrientation) {
            this.point2D = point2D;
            this.rectHV = rectHV;
            this.lb = null;
            this.rt = null;
            this.isVerticalOrientation = isVerticalOrientation;
        }
    }
}