package com.company;

import java.util.ArrayList;

public class PolygonShape {
    private ArrayList<Coordinate> coords;
    private double area;

    public PolygonShape(ArrayList<Coordinate> coords) {
        this.coords = coords;
        calculateArea();
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
