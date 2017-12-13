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
        ArrayList<Furniture> fittedFurniture = new ArrayList<>();
        for(Furniture f : furnitureList) {
            if (room.insertFurniture(f)) {
                fittedFurniture.add(f);
            }
        }
        furnitureList.removeAll(fittedFurniture);
        fittedFurniture.clear();
        /*if(room.areaCovered() > 30) {
            return formatSolution();
        }
        for(Furniture f : furnitureList) {
            if (room.insertFurnitureAtVertex(f)) {
                fittedFurniture.add(f);
            }
        }
        furnitureList.removeAll(fittedFurniture);
        fittedFurniture.clear();*/
        return formatSolution();
    }


    public String formatSolution() {
        String s = "";
        s = s + problemNo + ": ";
        ArrayList<String> stringList = new ArrayList<>();
        for(Furniture f : room.getFurnitureList()) {
            PathIterator pathIterator = f.getPath().getPathIterator(null);
            double[] coords = new double[6];

            while (!pathIterator.isDone()) {
                int type = pathIterator.currentSegment(coords);
                if(type != PathIterator.SEG_CLOSE) {
                    s = s + "(" + coords[0] + ",";
                    s = s + coords[1] + "), ";
                }
                pathIterator.next();
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
