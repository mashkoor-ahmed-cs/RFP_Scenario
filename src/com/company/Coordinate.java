package com.company;

public class Coordinate {
    private double x;
    private double y;
    private double angle;
    private double closestNorthAngle;

    public Coordinate(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public double getAngle() {
        return angle;
    }

    public void setClosestNorthAngle(double closestNorthAngle) {
        this.closestNorthAngle = closestNorthAngle;
    }

    public double getClosestNorthAngle() {
        return closestNorthAngle;
    }



    public double getX() { return x; }

    public double getY() { return y; }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
}
