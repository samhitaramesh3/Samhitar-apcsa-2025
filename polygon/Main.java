package polygon;

import java.awt.geom.Point2D;

public class Main {
    public static void main(String[] args)
    {
        IrregularPolygon myPolygon = new IrregularPolygon();
        double[][] pentagonPoints = { {350, 250}, {281, 345}, {169, 309}, {169, 191}, {281, 155} };
        for (double[] p : pentagonPoints) {
            Point2D.Double point = new Point2D.Double(p[0], p[1]);
            myPolygon.add(point);
        }
        // Add points to the polygon.
        myPolygon.draw();
        // Keep the window open for viewing
        try {
            Thread.sleep(10000); // 10 seconds to view the shape
        } catch (InterruptedException e) {
            // ignore
        }
    }
    
}
