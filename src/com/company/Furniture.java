package com.company;

import java.util.ArrayList;

public class Furniture implements Shape {
    ArrayList<Coordinate> coords = new ArrayList<Coordinate>();

    public Furniture(ArrayList<Coordinate> coords)
    {
        this.coords = coords;
    }

}
