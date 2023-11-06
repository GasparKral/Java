package lib;

public class Point {
    private double x;
    private double y;

    public Point point(double x, double y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}