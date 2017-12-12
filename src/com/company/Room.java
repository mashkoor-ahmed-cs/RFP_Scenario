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
        return (overlaps(f) && !contains(f));
    }


    public boolean contains(Furniture f) {
        Area thisShape = new Area(this.getPath());
        Area otherShape = new Area(f.getPath());
        thisShape.intersect(otherShape);
        return thisShape.equals(otherShape);
    }

    //area covered by furniture in room
    public double areaCovered() {
        double areaSum = 0;
        for(Furniture f : furnitureList) {
            areaSum = areaSum + f.getArea();
        }
        return areaSum/this.getArea() * 100;
    }

    private boolean furnitureFits(Furniture f) {
        if(f.getArea() > this.getArea()) {
            return false;
        }
        //find position to fit furniture if possible
        //currently not dealing with rotation

        //move to first vertex of room
        f.translate(getCoord(0).getX(), getCoord(0).getY());
        if(contains(f)) {
            return true;
        }
        int direction = 1;

        /*while() {
            //move around until fits in room
            while (intersects(f)) {
                f.translate(f.getWidth() * direction, 0);
            }
            if (contains(f)) {
                return true;
            }
            f.translate(0, f.getHeight());
            direction = direction * -1;
        }*/

        return false;
    }
}
