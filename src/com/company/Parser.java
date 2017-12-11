package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Parser {
    public static Problem parseString(String input) {
        String[] splitInput = input.split("#");
        String[] roomStr = splitInput[0].split(":");
        int problemNo = Integer.parseInt(roomStr[0].trim());

        Room room = createRoom(roomStr[1]);

        String[] shapes = splitInput[1].split(";");
        ArrayList<Furniture> furnitureList = createFurniture(shapes);

        return new Problem(problemNo, room, furnitureList);
    }

    private static ArrayList<Furniture> createFurniture(String[] furnitures) {
        ArrayList<Furniture> furnitureList = new ArrayList<Furniture>();
        for(String furniture : furnitures) {
            String[] splitStr = furniture.split(":");
            int cost = Integer.parseInt(splitStr[0].trim());
            ArrayList<Coordinate> coords = getCoords(splitStr[1]);
            furnitureList.add(new Furniture(cost, coords));
        }
        return furnitureList;
    }

    private static Room createRoom(String s) {
        ArrayList<Coordinate> coords = getCoords(s);

        return new Room(coords);
    }

    private static ArrayList<Coordinate> getCoords(String shapes) {
        // (0, 0), (10, 0), (10, 10), (0, 10)
        String[] splitStr = shapes.split("\\),");
        ArrayList<Coordinate> coords = new ArrayList<Coordinate>();

        for(String str : splitStr)
        {
            str = str.replace("(", "").replace(")", "");
            String[] strCoords = str.split(",");
            double x = Double.parseDouble(strCoords[0].trim());
            double y = Double.parseDouble(strCoords[1].trim());
            coords.add(new Coordinate(x, y));
        }

        return coords;
    }
}
