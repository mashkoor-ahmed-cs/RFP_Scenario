package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;
import java.util.ArrayList;

public class ShapeDrawer extends JPanel{
    private Path2D path = new Path2D.Double();
    private ArrayList<Coordinate> points;

    public ShapeDrawer(PolygonShape polygonShape){
        this.points = polygonShape.getCoords();
    }

    public void drawShape(){

        path.moveTo(points.get(0).getX(), points.get(0).getY());
        for (int i = 1; i < points.size(); i++){
            path.lineTo(points.get(i).getX(), points.get(i).getY());
        }
        path.closePath();

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // come back to this 
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.draw(path);
        g2d.dispose();

    }

}
