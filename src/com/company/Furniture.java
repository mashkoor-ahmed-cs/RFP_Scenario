package com.company;

import java.util.ArrayList;

public class Furniture extends PolygonShape {
    private int unitCost;
    private int realCost;

    public Furniture(int unitCost, ArrayList<Coordinate> coords)
    {
        super(coords);
        this.unitCost = unitCost;
    }
}
