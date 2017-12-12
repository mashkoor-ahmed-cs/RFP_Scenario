package com.company;

import java.util.ArrayList;

public class Furniture extends PolygonShape {
    private int unitCost;
    private double realCost;

    public Furniture(int unitCost, ArrayList<Coordinate> coords)
    {
        super(coords);
        this.unitCost = unitCost;
        this.realCost = this.getArea() * unitCost;
    }

    public double getRealCost() { return realCost; }
}
