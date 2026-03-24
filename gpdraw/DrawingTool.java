package gpdraw;

public class DrawingTool {
    private SketchPad pad;
    private double currentX;
    private double currentY;
    private boolean penDown;

    public DrawingTool(SketchPad pad) {
        this.pad = pad;
        this.currentX = 0;
        this.currentY = 0;
        this.penDown = true;
    }

    public void up() {
        penDown = false;
    }

    public void down() {
        penDown = true;
    }

    public void move(double x, double y) {
        currentX = x;
        currentY = y;
    }

    public void drawTo(double x, double y) {
        if (penDown) {
            pad.addLine((int) currentX, (int) currentY, (int) x, (int) y);
        }
        currentX = x;
        currentY = y;
    }
}
