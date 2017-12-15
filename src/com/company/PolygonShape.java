package com.company;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class PolygonShape {
    private ArrayList<Coordinate> coords;
    private double area;
    private Path2D.Double path;

    public PolygonShape(ArrayList<Coordinate> coords) {
        this.coords = coords;
        calculateArea();
        calculateAngles();
        path = new Path2D.Double();
        drawShape();
    }

    public double getAngle(int index) {
        return coords.get(index).getAngle();
    }

    private void calculateAngles() {
        for(int i = 0; i < coords.size(); i++) {
            coords.get(i).setAngle(angleAtPoint(i));
        }
    }

    private double angleAtPoint(int index) {
        Coordinate point = coords.get(index);
        Coordinate prevPoint;
        if(index == 0) {
            prevPoint = coords.get(coords.size()-1);
        }
        else {
            prevPoint = coords.get(index - 1);
        }
        Coordinate nextPoint;
        if(index == coords.size() - 1) {
            nextPoint = coords.get(0);
        }
        else {
            nextPoint = coords.get(index + 1);
        }
        double angle1 = angleFromNorth(point, nextPoint);
        double angle2 = angleFromNorth(point, prevPoint);
        double angle = angle1 - angle2;
        if(angle < 0) {
            coords.get(index).setClosestNorthAngle(angle2);
            return angle + 360;
        }
        coords.get(index).setClosestNorthAngle(angle1);
        return angle;
    }

    private double angleFromNorth(Coordinate c1, Coordinate c2) {
        return Math.toDegrees(Math.atan2(c1.getX() - c2.getX(), c1.getY() - c2.getY()));
    }

    public void translate(double x, double y) {
        AffineTransform transform = new AffineTransform();
        transform.translate(x, y);
        path.transform(transform);
        for(Coordinate c : getCoords()) {
            c.setX(c.getX() + x);
            c.setY(c.getY() + y);
        }
    }


    public void moveVertexTo(double startX, double startY, double destX, double destY) {
        AffineTransform transform = new AffineTransform();
        double tX = destX - startX;
        double tY = destY - startY;
        transform.translate(tX, tY);
        path.transform(transform);
        for(Coordinate c : getCoords()) {
            c.setX(c.getX() + tX);
            c.setY(c.getY() + tY);
        }
    }

    public void rotate(double degrees) {
        //untested
        double radians = Math.toRadians(degrees);
        AffineTransform transform = new AffineTransform();
        transform.rotate(radians);
        path.transform(transform);
    }

    public void rotateAroundPoint(double degrees, Coordinate c) {
        //untested
        double radians = Math.toRadians(degrees);
        AffineTransform transform = new AffineTransform();
        transform.rotate(radians, c.getX(), c.getY());
        path.transform(transform);
    }

    public void drawShape(){
        path.moveTo(coords.get(0).getX(), coords.get(0).getY());
        for (int i = 1; i < coords.size(); i++){
            path.lineTo(coords.get(i).getX(), coords.get(i).getY());
        }
        path.closePath();
    }

    public Path2D.Double getPath() {
        return path;
    }

    public ArrayList<Coordinate> getCoords() {
        return coords;
    }

    public Coordinate getCoord(int index) {
        return coords.get(index);
    }

    public double getArea() {return area;}

    //Checks if this shape intersects or contains another shape
    public boolean overlaps(PolygonShape shape) {
        Area thisShape = new Area(path);
        Area otherShape = new Area(shape.getPath());
        thisShape.intersect(otherShape);
        return !thisShape.isEmpty();
    }

    public boolean contains(Coordinate c) {
        Point2D p = new Point2D.Double(c.getX(), c.getY());
        return path.contains(p);
    }

    public double getMaxX() {
        return path.getBounds2D().getMaxX();
    }

    public double getMaxY() {
        return path.getBounds2D().getMaxY();
    }

    public double getMinX() {
        return path.getBounds2D().getMinX();
    }

    public double getMinY() {
        return path.getBounds2D().getMinY();
    }

    public double getHeight() {
        return path.getBounds2D().getHeight();
    }

    public double getWidth() {
        return path.getBounds2D().getWidth();
    }

    private void calculateArea() {
        double sum = 0.0;
        int n = coords.size() - 1;
        for (int i = 0; i < n; i++) {
            sum = sum + (coords.get(i).getX() * coords.get(i + 1).getY()) - (coords.get(i).getY() * coords.get(i + 1).getX());
        }
        sum = sum + (coords.get(n).getX() * coords.get(0).getY()) - (coords.get(n).getY() * coords.get(0).getX());
        area = Math.abs(0.5 * sum);
    }
}
