package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;
import java.util.ArrayList;

public class ShapeDrawer extends JPanel{
    public ShapeDrawer(){

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // come back to this 
        Graphics2D g2d = (Graphics2D) g.create();
        //g2d.draw(path);
        g2d.dispose();

    }

}
