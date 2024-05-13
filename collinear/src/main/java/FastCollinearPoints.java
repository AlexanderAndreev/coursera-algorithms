import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {


    private List<LineSegment> segments = new ArrayList<>();


    public FastCollinearPoints(Point[] points) {

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
        for (int i = 0; i < copy.length - 3; i++) {
            Arrays.sort(copy);
            Arrays.sort(copy, copy[i].slopeOrder());
            for (int p = 0, first = 1, last = 2; last < points.length; last++) {
                while (last < points.length && Double.compare(copy[p].slopeTo(points[first]),
                        copy[p].slopeTo(points[last])) == 0) {
                    last++;
                }
                if (last - first >= 3 && copy[p].compareTo(points[first]) < 0) {
                    segments.add(new LineSegment(copy[p], points[last - 1]));
                }
                first = last;
            }
        }
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
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
