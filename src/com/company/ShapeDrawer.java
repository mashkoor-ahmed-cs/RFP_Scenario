package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class ShapeDrawer extends JComponent{
    private Coordinate lastcoordinates;
    private ArrayList<Furniture> listFurnitures;
    private Room room;
    static  double height =0;
    static  double maxHeight =0;
    int scaleX = 30;
    int scaleY = scaleX;
    int width = 1000;
    double minCost;
    double maxCost;

    public ShapeDrawer(Problem p){
        this.listFurnitures = p.getFurnitureList();
        this.room = p.getRoom();
        setup();
    }

    private void setup() {
        double maxRoomFurn = room.getFurnitureList().get(0).getRealCost();
        double maxOutsideFurn = listFurnitures.get(0).getRealCost();
        double minRoomFurn = room.getFurnitureList().get(room.getFurnitureList().size()-1).getRealCost();
        double minOutsideFurn = listFurnitures.get(listFurnitures.size()-1).getRealCost();
        this.maxCost = Math.max(maxRoomFurn, maxOutsideFurn);
        this.minCost = Math.min(minRoomFurn, minOutsideFurn);

        double currX = room.getMaxX() + 1;
        double currY = 0;
        double maxHeight = 0;
        for(Furniture f : listFurnitures) {
            f.translate(currX - f.getMinX(), currY - f.getMinY());
            currX = currX + f.getWidth() + 1;
            if (f.getHeight() > maxHeight) {
                maxHeight = f.getHeight();
            }
            if (currX > width/scaleX) {
                currX = room.getMaxX() + 1;
                currY = currY + maxHeight + 1;
                maxHeight = 0;
            }
        }
    }


    @Override
    public void paint(Graphics g) {
        drawRoom((Graphics2D) g);
        for(Furniture f : listFurnitures) {
            drawFurniture((Graphics2D) g, f);
        }
    }

    private void drawFurniture(Graphics2D g, Furniture furnitureItem) {
        Graphics2D g2 = g;
        g2.setStroke(new BasicStroke(.02f));
        Color c = getColor(furnitureItem);
        g2.setColor(c);


        g2.draw(furnitureItem.getPath());
        g2.fill(furnitureItem.getPath());


    }

    private Color getColor(Furniture furniture) {
        float shade =  (float)((furniture.getRealCost()-minCost)/(maxCost-minCost));
        float blue = shade;
        float red = (1-shade);
        return new Color(red, 0, blue);
    }

    private void drawRoom(Graphics2D g){
        Graphics2D g2 = g;
        g2.setStroke(new BasicStroke(.03f));

        double mostnegativeX=room.getMinX();
        double mostnegativeY=room.getMinY();


        if(room.getArea() <=100000) {
            g2.scale(scaleX, scaleY);
        }

        if(mostnegativeX<0) {
            g.translate((double) -1*mostnegativeX, 0);
        }
        if(mostnegativeX<0) {
            g.translate(0, (double) -1*mostnegativeY);
        }


        for(Furniture f : room.getFurnitureList()) {
            drawFurniture(g2, f);
        }

        g2.setPaint(Color.BLUE);
        g2.draw(room.getPath());



    }

}