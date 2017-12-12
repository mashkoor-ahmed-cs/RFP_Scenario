package com.company;

import java.util.ArrayList;

public class Room extends PolygonShape {
    private ArrayList<Furniture> furnitureList;

    public Room(ArrayList<Coordinate> coords) {
        super(coords);
        furnitureList = new ArrayList<Furniture>();
    }

    public boolean insertFurniture(Furniture f) {
        if(furnitureFits(f)) {
            furnitureList.add(f);
            return true;
        }
        return false;
    }

    public ArrayList<Furniture> getFurnitureList() {
        return furnitureList;
    }

    private boolean furnitureFits(Furniture f) {
        if(f.getArea() > this.getArea()) {
            return false;
        }
        //find position to fit furniture if possible

        return false;
    }
}
