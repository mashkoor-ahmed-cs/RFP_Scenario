package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.Random;

public class ShapeDrawer extends JComponent{
    private Coordinate lastcoordinates;
    private ArrayList<Furniture> listFurnitures;
    private Room room;
    static  double width =0;
    static  double height =0;
    static  double maxHeight =0;


    public ShapeDrawer(Problem p){
        this.listFurnitures = p.getFurnitureList();
        this.room = p.getRoom();
    }


    @Override
    public void paint(Graphics g) {
        drawRoom((Graphics2D) g);


    }

    private void drawFurniture(Graphics2D g, Furniture furnitureItem) {
        Graphics2D g2 = g;
        g2.setStroke(new BasicStroke(.02f));
        g2.setColor(new Color((int) (Math.random()*0x1000000)));


        g2.draw(furnitureItem.getPath());
        g2.fill(furnitureItem.getPath());


    }

    private void drawRoom(Graphics2D g){
        Graphics2D g2 = g;
        g2.setStroke(new BasicStroke(.10f));



        double mostnegativeX=room.getMinX();
        double mostnegativeY=room.getMinY();


        if(room.getArea() <=100000) {
            g2.scale(50, 50);
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