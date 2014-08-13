/*
 * DisplayPanel.java
 *
 * Created on October 28, 2003, 10:12 PM
 */

package com.seekerr.simulator.robot.graphics.wallCollision;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
/**
 *
 * @author  Wesley Kerr
 */
public class IdealPanel extends JPanel {
    
    
    /** Creates a new instance of DisplayPanel */
    public IdealPanel() {
        super(true);
        init();
    }
    
    public void init() {
        setBackground(Color.white);
        setForeground(Color.white);
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D f = (Graphics2D) g;
        f.setColor(Color.white);
        f.fillRect(0, 0, getWidth(), getHeight());
        
        Observer o = Observer.inst();
        int scaleX = getWidth() / o.width;
        int scaleY = getHeight() / o.height;
        
        int scale = Math.min(scaleX, scaleY);
        
        //paintGrid(f, scale);
        paintRobots(f, scale, o.getCalculator());
    }
    
    public void paintGrid(Graphics2D g, int size) {
        g.setColor(Color.black);
        for (int i = 0; i <= Observer.inst().width; ++i) {
            g.drawLine(i*size, 0, i*size, Observer.inst().height*size);
        }
        
        for (int i = 0; i <= Observer.inst().height; ++i) {
            g.drawLine(0, i*size, Observer.inst().width*size, i*size);
        }
    }

    public void paintRobots(Graphics2D g, int size, Calculator c) {
        
        if (c == null) {
            System.out.println("Calculator is null");
            return;
        }
        
        int centerx = Observer.inst().width / 2;
        int centery = Observer.inst().height / 2;
        
        g.setColor(Color.green);
        double finalx = 10*Math.cos(Math.toRadians(c.globalInitialBearing));
        double finaly = 10*Math.sin(Math.toRadians(c.globalInitialBearing));
        g.drawLine(centerx*size, centery*size, (int) (centerx+finalx)*size, (int) (centery+finaly)*size);
        
        g.setColor(Color.blue);
        g.drawLine(centerx*size, centery*size, (int) ((centerx+c.idealVx)*size), centery*size);
        g.drawLine(centerx*size, centery*size, centerx*size, (int) ((centery+c.idealVy)*size));
        
        g.setColor(Color.red);
        g.drawLine(centerx*size, centery*size, (int) ((centerx+c.idealVx)*size), (int) ((centery+c.idealVy)*size));
    }
}