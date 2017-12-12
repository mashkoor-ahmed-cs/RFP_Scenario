package com.company;

import java.awt.geom.Path2D;
import java.util.ArrayList;

public class PolygonShape {
    private ArrayList<Coordinate> coords;
    private double area;
    private Path2D.Double path;

    public PolygonShape(ArrayList<Coordinate> coords) {
        this.coords = coords;
        calculateArea();
    }

    public void drawShape(){
        path.moveTo(coords.get(0).getX(), coords.get(0).getY());
        for (int i = 1; i < coords.size(); i++){
            path.lineTo(coords.get(i).getX(), coords.get(i).getY());
        }
        path.closePath();

    }
    public ArrayList<Coordinate> getCoords() {
        return coords;
    }

    public double getArea() {return area;}

    private void calculateArea() {
        double sum = 0.0;
        int n = coords.size() - 1;
        for (int i = 0; i < n; i++) {
            sum = sum + (coords.get(i).getX() * coords.get(i+1).getY()) - (coords.get(i).getY() * coords.get(i+1).getX());
        }
        sum = sum + (coords.get(n).getX() * coords.get(0).getY()) - (coords.get(n).getY() * coords.get(0).getX());
        area = Math.abs(0.5 * sum);
    }
}
