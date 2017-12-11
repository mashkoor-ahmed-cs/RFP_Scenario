package com.company;

import java.awt.geom.Path2D;
import java.util.ArrayList;

public class ShapeDrawer {
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

}
