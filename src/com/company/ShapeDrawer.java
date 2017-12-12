package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Path2D;
import java.util.ArrayList;

public class ShapeDrawer extends JComponent{
    private Coordinate lastcoordinates;
    private ArrayList<Furniture> listFurnitures;
    private Room room;

    public ShapeDrawer(Problem p){
        this.listFurnitures = p.getFurnitureList();
        this.room = p.getRoom();
    }


    @Override
    public void paint(Graphics g) {
        drawRoom((Graphics2D) g);
    }

    private Coordinate drawRoom(Graphics2D g){
        Graphics2D g2 = g;
        g2.setStroke(new BasicStroke(.10f));
        g2.setPaint(Color.BLUE);

        ArrayList<Coordinate> roomCoordinates = room.getCoords();

        double xPoints [] = new double[roomCoordinates.size()];
        double yPoints [] = new double[roomCoordinates.size()];

        int index = 0;
        for (Coordinate point : roomCoordinates){
            xPoints[index] = point.getX();
            yPoints[index] = point.getY();
            index++;
        }

        Path2D.Double path = new Path2D.Double(Path2D.WIND_EVEN_ODD, xPoints.length);

        //set origin
        path.moveTo(xPoints[0], yPoints[0]);

        // define drawing path
        for (int i = 0; i< xPoints.length; i++){
            path.lineTo(xPoints[i], yPoints[i]);
        }
        path.closePath();
        g2.draw(path);
        
        return roomCoordinates.get(roomCoordinates.size() - 1);
    }

}
