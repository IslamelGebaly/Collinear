import edu.princeton.cs.algs4.StdDraw;

import java.util.Comparator;

public class Point implements Comparable<Point> {

    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Draws this point to standard draw.
     */
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    /**
     * Draws the line segment between this point and the specified point
     * to standard draw.
     *
     * @param that the other point
     */
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public int compareTo(Point that) {
        if ((this.y > that.y) || (this.y == that.y && this.x > that.x))
            return 1;
        if ((this.y < that.y) || (this.y == that.y && this.x < that.x))
            return -1;
        return 0;
    }

    public double slopeTo(Point that) {

        if (this.x == that.x && this.y == that.y)
            return Double.NEGATIVE_INFINITY;

        double nominator = this.y - that.y;
        double denominator = this.x - that.x;

        if (nominator == 0.0)
            return +0.0d;

        if (denominator == 0.0)
            return Double.POSITIVE_INFINITY;

        return nominator / denominator;

    }

    public Comparator<Point> slopeOrder() {

        return new Comparator<Point>() {
            public int compare(Point p1, Point p2) {
                return Double.compare(slopeTo(p1), slopeTo(p2));
            }
        };
    }


}

