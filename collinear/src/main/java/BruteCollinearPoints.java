import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints {

    private List<LineSegment> segments = new ArrayList<>();

    public BruteCollinearPoints(Point[] points) {

        if (points == null) {
            throw new IllegalArgumentException("Points should not be null");
        }
        for (Point point : points) {
            if (point == null) {
                throw new IllegalArgumentException("Points should not contains null");
            }
        }
        Point[] copy = Arrays.copyOf(points, points.length);
        for (Point point : copy) {
            if (point == null) {
                throw new IllegalArgumentException("Points should not contains null");
            }
        }
        Arrays.sort(copy);
        for (int i = 1; i < copy.length; i++) {
            if (copy[i - 1].compareTo(copy[i]) == 0) {
                throw new IllegalArgumentException("Points should not contains duplicates");
            }
        }
        for (int i1 = 0; i1 < copy.length - 3; i1++) {
            for (int i2 = i1 + 1; i2 < copy.length - 2; i2++) {
                for (int i3 = i2 + 1; i3 < copy.length - 1; i3++) {
                    for (int i4 = i3 + 1; i4 < copy.length; i4++) {
                        if (isPointsSlope(copy[i1], copy[i2], copy[i3], copy[i4])) {
                            segments.add(new LineSegment(copy[i1], copy[i4]));
                        }
                    }
                }
            }
        }
    }

    private boolean isPointsSlope(Point p1, Point p2, Point p3, Point p4) {

        return p1.slopeTo(p2) == p2.slopeTo(p3) && p3.slopeTo(p4) == p4.slopeTo(p1);
    }

    public int numberOfSegments() {

        return this.segments.size();
    }

    public LineSegment[] segments() {

        return this.segments.toArray(new LineSegment[0]);
    }

    public static void main(String[] args) {

        // read the n points from a file
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
        StdDraw.setPenRadius(0.01);
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
