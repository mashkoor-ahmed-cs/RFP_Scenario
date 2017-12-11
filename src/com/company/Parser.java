package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Parser {
    public static Problem parseString(String input) {
        String[] splitInput = input.split("#");
        createRoom(splitInput[0]);

        String[] shapes = splitInput[1].split(";");
        createFurniture(shapes);
        return new Problem(room, furnitureList);
    }

    private static ArrayList<Shape> createFurniture(String[] furnitures) {
        ArrayList<Shape> furnitureList = new ArrayList<Shape>();
        for(String furniture : furnitures) {
            ArrayList<Coordinate> coords = getCoords(furniture);
            furnitureList.add(new Furniture(coords));
        }
        return furnitureList;
    }

    private static Room createRoom(String s) {
        String[] splitString = s.split(":");
        String problemNo = splitString[0];

        ArrayList<Coordinate> coords = getCoords(splitString[1]);

        return new Room(problemNo, coords);
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
