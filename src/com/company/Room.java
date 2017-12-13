package com.company;

import java.awt.geom.Area;
import java.util.ArrayList;

public class Room extends PolygonShape {
    private ArrayList<Furniture> furnitureList;

    public Room(ArrayList<Coordinate> coords) {
        super(coords);
        furnitureList = new ArrayList<Furniture>();
    }

    public ArrayList<Furniture> getFurnitureList() {
        return furnitureList;
    }

    //Check if furniture intersects with room
    public boolean intersects(Furniture f) {
        return (overlaps(f) && !contains(f));
    }

    //check if furniture is in room with no intersection
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

    public boolean insertFurnitureAtVertex(Furniture f) {
        if(fitAtVertices(f)) {
            furnitureList.add(f);
            return true;
        }
        return false;
    }

    public boolean insertFurniture(Furniture f) {
        if(furnitureFits(f)) {
            furnitureList.add(f);
            return true;
        }
        return false;
    }

    private boolean overlapsAny(Furniture f) {
        for(Furniture furniture : furnitureList) {
            if(f.overlaps(furniture)) {
                return true;
            }
        }
        return false;
    }

    private boolean fitAtVertices(Furniture f) {
        int rotateDegrees = 20;
        int rotations = 360/rotateDegrees;
        int i = 1;
        for(Coordinate c : this.getCoords()) {
            f.translate(c.getX()-f.getCoord(0).getX(), c.getY()-f.getCoord(0).getY());
            while(i < rotations) {
                f.rotate(rotateDegrees);
                if(contains(f) && !overlapsAny(f)) {
                    return true;
                }
                i++;
            }
            i = 0;
        }
        return false;
    }

    private boolean furnitureFits(Furniture f) {
        if(f.getArea() > this.getArea()) {
            return false;
        }
        //find position to fit furniture if possible
        //currently not dealing with rotation

        //move to bottom-leftmost vertex of room
        f.translate(getMinX()-f.getCoord(0).getX(), getMinY()-f.getCoord(0).getY());
        int rotateDegrees = 20;
        int rotations = 360/rotateDegrees;
        int i = 1;
        while(f.getMinY() < this.getMaxY()) {
            //move around until fits in room
            while ((f.getMinX() < this.getMaxX()) && !contains(f)) {
                f.translate(f.getWidth(), 0);

                //Rotation
                /*while(i < rotations) {
                    f.rotate(rotateDegrees);
                    if(contains(f) && !overlapsAny(f)) {
                        return true;
                    }
                    i++;
                }*/
            }
            if (contains(f) && !overlapsAny(f)) {
                return true;
            }
            //move back to leftmost point
            f.translate(getMinX()-f.getCoord(0).getX(), 0);
            f.translate(0, f.getHeight());
        }

        return false;
    }
}
