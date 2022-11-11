import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
    private ArrayList<LineSegment> lineSegments;

    public BruteCollinearPoints(Point[] points) {

        if (points == null)
            throw new IllegalArgumentException();
        if (checkNull(points))
            throw new IllegalArgumentException();
        if (checkDuplicates(points))
            throw new IllegalArgumentException();

        Point p, q, r, s;
        lineSegments = new ArrayList<LineSegment>();
        LineSegment line;

        Point[] copy = points.clone();
        Arrays.sort(copy);

        for (int i = 0; i < points.length - 3; i++) {
            p = copy[i];
            for (int j = i + 1; j < points.length - 2; j++) {
                q = copy[j];
                for (int k = j + 1; k < points.length - 1; k++) {
                    r = copy[k];
                    for (int L = k + 1; L < points.length; L++) {
                        s = copy[L];
                        if (Double.compare(p.slopeTo(q), p.slopeTo(r)) == 0
                                && Double.compare(p.slopeTo(r), p.slopeTo(s)) == 0) {
                            line = new LineSegment(p, s);
                            if (!lineSegments.contains(line))
                                lineSegments.add(line);
                        }
                    }
                }
            }
        }
    }

    public int numberOfSegments() {
        return lineSegments.size();
    }

    public LineSegment[] segments() {
        return lineSegments.toArray(new LineSegment[lineSegments.size()]);
    }

    private boolean checkDuplicates(Point[] points) {
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                if (points[i].compareTo(points[j]) == 0)
                    return true;
            }
        }
        return false;
    }

    private boolean checkNull(Point[] points) {
        for (Point p : points) {
            if (p == null)
                return true;
        }

        return false;
    }
}
