package com.company;

import java.awt.geom.PathIterator;
import java.util.ArrayList;
import java.util.Comparator;

public class Problem {
    int problemNo;
    Room room;
    ArrayList<Furniture> furnitureList;

    public Problem(int problemNo, Room room, ArrayList<Furniture> furnitureList) {
        this.room = room;
        this.furnitureList = furnitureList;
        this.problemNo = problemNo;
        sortFurnitureList();
    }

    public Room getRoom() {
        return room;
    }

    public ArrayList<Furniture> getFurnitureList() {
        return furnitureList;
    }

    public String solveProblem() {
        for(Furniture f : furnitureList) {
            room.insertFurniture(f);
        }
        return formatSolution();
    }

    public String formatSolution() {
        String s = "";
        s = s + problemNo + ": ";
        ArrayList<String> stringList = new ArrayList<>();
        for(Furniture f : room.getFurnitureList()) {
            //TODO: Fix coordinates to either get points on path or change coordinates when fitting in room
            for(Coordinate c : f.getCoords()) {
                s = s + "(" + c.getX() + ",";
                s = s + c.getY() + "), ";
            }
            s = s.substring(0, s.length() - 2);
            s = s + "; ";
        }
        if(s.length() > 3) {
            s = s.substring(0, s.length() - 2);
        }
        return s;
    }

    //Sort furnitureList in order of descending realCost
    private void sortFurnitureList() {
        furnitureList.sort(new Comparator<Furniture>() {
            @Override
            public int compare(Furniture o1, Furniture o2) {
                if(o1.getRealCost() < o2.getRealCost()) { return -1; }
                if(o1.getRealCost() > o2.getRealCost()) { return 1; }
                return 0;
            }
        }.reversed());
    }
}
