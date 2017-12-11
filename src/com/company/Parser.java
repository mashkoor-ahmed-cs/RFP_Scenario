package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Parser {
    public static Problem parseString(String input) {
        String[] splitInput = input.split("#");
        String[] roomStr = splitInput[0].split(":");
        int problemNo = Integer.parseInt(roomStr[0]);

        Room room = createRoom(roomStr[1]);

        String[] shapes = splitInput[1].split(";");
        ArrayList<Furniture> furnitureList = createFurniture(shapes);

        return new Problem(problemNo, room, furnitureList);
    }

    private static ArrayList<Furniture> createFurniture(String[] furnitures) {
        ArrayList<Furniture> furnitureList = new ArrayList<Furniture>();
        for(String furniture : furnitures) {
            String[] splitStr = furniture.split(":");
            int cost = Integer.parseInt(splitStr[0]);
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
        Scanner sc = new Scanner(shapes);
        ArrayList<Coordinate> coords = new ArrayList<Coordinate>();
        while (sc.hasNextDouble()) {
            double x = sc.nextDouble();
            double y = sc.nextDouble();
            coords.add(new Coordinate(x, y));
        }
        return coords;
    }
}
