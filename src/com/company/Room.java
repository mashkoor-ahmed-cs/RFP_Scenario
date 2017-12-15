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

    public boolean insertFurnitureByAngle(Furniture f) {
        if(fitByAngle(f)) {
            furnitureList.add(f);
            return true;
        }
        return false;
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

    private boolean fitByAngle(Furniture f) {
        double diff = 10;
        double minAngle = 90;
        for(Coordinate roomC : this.getCoords()) {
            double thisAngle = roomC.getAngle();

            for(Coordinate furnC : f.getCoords()) {
                if (furnC.getAngle() <= minAngle && Math.abs(thisAngle - furnC.getAngle()) < diff) {

                    f.moveVertexTo(furnC.getX(), furnC.getY(), roomC.getX(), roomC.getY());

                    double rotateDegrees = roomC.getClosestNorthAngle() - furnC.getClosestNorthAngle();
                    f.rotateAroundPoint(rotateDegrees, roomC);
                    if (contains(f) && !overlapsAny(f)) {
                        return true;
                    }
                    f.rotateAroundPoint(-rotateDegrees, roomC);
                }
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

    private boolean moveByIntersection(Furniture f, double rotateDegrees) {
        int i = 0;
        double rotations = 360/rotateDegrees;
        Area furnitureArea = new Area(f.getPath());
        Area roomArea = new Area(this.getPath());
        roomArea.intersect(furnitureArea);
        if(!roomArea.isEmpty()) {
            furnitureArea.subtract(roomArea);
            f.translate(furnitureArea.getBounds2D().getWidth(), 0);
            if (contains(f) && !overlapsAny(f)) {
                return true;
            }
            f.translate(0, furnitureArea.getBounds2D().getHeight());
            if (contains(f) && !overlapsAny(f)) {
                return true;
            }
            while(i < rotations) {
                f.rotate(rotateDegrees);
                if(contains(f) && !overlapsAny(f)) {
                    return true;
                }
                i++;
            }
            i = 0;
            f.translate(-furnitureArea.getBounds2D().getWidth(), -furnitureArea.getBounds2D().getHeight());
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
        double rotateDegrees = 30;
        double rotations = 360/rotateDegrees;
        int i = 0;

        while(f.getMinY() < this.getMaxY()) {
            //move around until fits in room
            while ((f.getMinX() < this.getMaxX())) {
                if(moveByIntersection(f, rotateDegrees)) {
                    return true;
                }
                f.translate(f.getWidth(), 0);
                if(contains(f) && !overlapsAny(f)) {
                    return true;
                }
                while(i < rotations) {
                    f.rotateAroundPoint(rotateDegrees, f.getCoord(0));
                    if(contains(f) && !overlapsAny(f)) {
                        return true;
                    }
                    i++;
                }
                i = 0;
            }
            //move back to leftmost point
            f.translate(getMinX()-f.getCoord(0).getX(), 0);
            f.translate(0, f.getHeight());
        }

        return false;
    }
}
