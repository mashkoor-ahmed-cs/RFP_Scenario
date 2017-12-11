package com.company;

import java.util.ArrayList;

public class Room implements Shape {
    public String problemNo;
    public ArrayList<Coordinate> coords;

    public Room(String problemNo, ArrayList<Coordinate> coords) {
        this.problemNo = problemNo;
        this.coords = coords;
    }
}
