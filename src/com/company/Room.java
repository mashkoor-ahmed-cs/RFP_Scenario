package com.company;

import java.awt.geom.Area;
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


    //Check if furniture intersects with room
    public boolean intersects(Furniture f) {
        //untested
        //check if it overlaps but doesnt contain f
        return (overlaps(f) && !contains(f));
    }

    public boolean contains(Furniture f) {
        //untested
        Area thisShape = new Area(this.getPath());
        Area otherShape = new Area(f.getPath());
        thisShape.intersect(otherShape);
        return thisShape.equals(otherShape);
    }

    private boolean furnitureFits(Furniture f) {
        if(f.getArea() > this.getArea()) {
            return false;
        }
        //find position to fit furniture if possible
        //currently not dealing with rotation

        //move to first vertex of room
        f.transform(getCoord(0).getX(), getCoord(0).getY());
        //move around until fits in room

        return false;
    }
}
