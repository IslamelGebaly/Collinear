import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class FastCollinearPoints {
    private ArrayList<LineSegment> lineSegments;

    public FastCollinearPoints(Point[] points) {

        if (points == null)
            throw new IllegalArgumentException();

        if (checkNull(points))
            throw new IllegalArgumentException();

        if (checkDuplicates(points))
            throw new IllegalArgumentException();

        lineSegments = new ArrayList<LineSegment>();
        LineSegment temp;
        ArrayList<Point> set;
        int n_points;

        for (int i = 0; i < points.length - 3; i++) {
            Arrays.sort(points);

            Arrays.sort(points, points[i].slopeOrder());

            for (int j = i + 1; j < points.length; j++) {
                set = new ArrayList<Point>();
                n_points = j + 1;
                set.addAll(Arrays.asList(points[i], points[j]));

                while (n_points < points.length && Double.compare(set.get(0).slopeTo(set.get(1)),
                                                                  set.get(0)
                                                                     .slopeTo(points[n_points]))
                        == 0) {
                    set.add(points[n_points]);
                    n_points++;
                }

                if (set.size() < 4)
                    continue;

                Collections.sort(set);
                temp = new LineSegment(set.get(0), set.get(set.size() - 1));
                if (!lineSegments.contains(temp))
                    lineSegments.add(temp);
                j += set.size() - 1;
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
