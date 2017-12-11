package com.company;

import java.util.ArrayList;

public class Problem {
    int problemNo;
    Room room;
    ArrayList<Furniture> furnitureList;

    public Problem(int problemNo, Room room, ArrayList<Furniture> furnitureList)
    {
        this.room = room;
        this.furnitureList = furnitureList;
        this.problemNo = problemNo;
    }

    public Room getRoom() {
        return room;
    }

    public ArrayList<Furniture> getFurnitureList() {
        return furnitureList;
    }

}
