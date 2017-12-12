package com.company;

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
        return problemNo + ": " + room.areaCovered();
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
