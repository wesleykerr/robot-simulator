/*
 * Obstacle.java
 *
 * Created on August 24, 2004, 11:00 AM
 */

package com.seekerr.simulator.robot.obstacle;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Hashtable;
/**
 *
 * @author  wkerr
 */
public interface Obstacle {
    public float intersect(float px1, float py1, float px2, float py2);
    public void draw(Graphics2D g, Color c, double scale, boolean fill);
    public void write(BufferedWriter out) throws IOException;
    public void fillAreaHashtable(Hashtable area, double[] ratio, double[] cellDim2);
    public void fillBehindHashtable(Hashtable area, Hashtable behind, double[] ratio, double[] cellDim2);
    public boolean inside(double rx, double ry);
}
