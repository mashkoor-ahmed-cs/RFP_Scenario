package com.company;

import java.util.ArrayList;

public class Room implements Shape {
    public ArrayList<Coordinate> coords;

    public Room(ArrayList<Coordinate> coords) {
        this.coords = coords;
    }
}
