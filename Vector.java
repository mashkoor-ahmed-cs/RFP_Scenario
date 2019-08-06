package com.company;
import java.util.*;

public class Vector {
    public Coordinate Origin;
    public ArrayList<Coordinate> Vectors;

    public void setOrigin(Coordinate origin) {
        Origin = origin;
    }

    public void calculateArrayList(ArrayList<Coordinate> coordinates){
        ArrayList<Coordinate> newVectors = new ArrayList<Coordinate>();
        Coordinate currentCoordinate = Origin;
        Coordinate nextCoordinate;
        for (Iterator<Coordinate> i = coordinates.iterator(); i.hasNext();)
        {
            nextCoordinate = currentCoordinate;
            currentCoordinate = i.next();
            newVectors.add(calculateVector(nextCoordinate, currentCoordinate));
        }
    }

    public Coordinate calculateVector(Coordinate vector1, Coordinate vector2)
    {
        return new Coordinate((vector2.x - vector1.x),(vector2.y - vector1.y));
    }

}
