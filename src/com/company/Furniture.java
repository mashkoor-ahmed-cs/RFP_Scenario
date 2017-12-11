package com.company;

import java.util.ArrayList;

public class Furniture implements Shape {
    int unitCost;
    ArrayList<Coordinate> coords = new ArrayList<Coordinate>();

    public Furniture(int unitCost, ArrayList<Coordinate> coords)
    {
        this.coords = coords;
        this.unitCost = unitCost;
    }
}
