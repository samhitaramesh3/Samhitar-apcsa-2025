package polygon;

import java.awt.geom.*; // for Point2D.Double
import java.util.ArrayList; // for ArrayList
import gpdraw.*; // for DrawingTool

public class IrregularPolygon {
    private ArrayList<Point2D.Double> myPolygon = new ArrayList<Point2D.Double>();

    // constructor
    public IrregularPolygon() {}

    // public methods
    public void add(Point2D.Double aPoint)
    {
        // Add a point to the polygon list; order matters for perimeter/area calculations
        if (aPoint != null) {
            myPolygon.add(aPoint);
        }
    }

    public double perimeter() {
        // Calculate the sum of the distances between successive points, including
        // the segment closing the polygon (last -> first).
        double perim = 0.0;
        int n = myPolygon.size();
        if (n < 2) {
            return 0.0;
        }
        for (int i = 0; i < n; i++) {
            Point2D.Double p1 = myPolygon.get(i);
            Point2D.Double p2 = myPolygon.get((i + 1) % n); // wraps around
            double dx = p2.x - p1.x;
            double dy = p2.y - p1.y;
            perim += Math.hypot(dx, dy);
        }
        return perim;
    }

    public double area() {
        // Use shoelace formula. 0 if fewer than 3 points.
        int n = myPolygon.size();
        if (n < 3) {
            return 0.0;
        }
        double sum = 0.0;
        for (int i = 0; i < n; i++) {
            Point2D.Double p1 = myPolygon.get(i);
            Point2D.Double p2 = myPolygon.get((i + 1) % n);
            sum += p1.x * p2.y;
            sum -= p2.x * p1.y;
        }
        double area = Math.abs(sum) / 2.0;
        return area;
    }

    public void draw()
    {
        // Wrap the DrawingTool in a try/catch to allow development without need for graphics.
        try {
            if (myPolygon.isEmpty()) {
                return; // nothing to draw
            }
            DrawingTool pen = new DrawingTool(new SketchPad(500, 500));
            // move to first point without drawing
            Point2D.Double first = myPolygon.get(0);
            pen.up();
            pen.move(first.x, first.y);
            pen.down();

            // draw lines to subsequent points
            for (int i = 1; i < myPolygon.size(); i++) {
                Point2D.Double p = myPolygon.get(i);
                pen.drawTo(p.x, p.y);
            }
            // close back to the start if there are at least two points
            if (myPolygon.size() > 1) {
                pen.drawTo(first.x, first.y);
            }
        } catch (java.awt.HeadlessException e) {
            System.out.println("Exception: No graphics support available.");
        }
    }

}
