package com.company;

import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Path2D;
import java.util.ArrayList;

public class PolygonShape {
    private ArrayList<Coordinate> coords;
    private double area;
    private Path2D.Double path;

    public PolygonShape(ArrayList<Coordinate> coords) {
        this.coords = coords;
        calculateArea();
        path = new Path2D.Double();
        drawShape();
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

    public void translate(double x, double y) {
        AffineTransform transform = new AffineTransform();
        transform.translate(x, y);
        path.transform(transform);
    }

    public void rotate(int degrees) {
        //untested
        AffineTransform transform = new AffineTransform();
        transform.rotate(degrees);
        path.transform(transform);
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
