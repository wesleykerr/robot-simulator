package com.seekerr.simulator.robot.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;

import com.seekerr.simulator.robot.env.Environment;
import com.seekerr.simulator.robot.graphics.ui.DisplayPanel;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
/**
 *
 * @author  Wesley Kerr
 */
public class ImageSaver extends DisplayPanel {
    private static ImageSaver image = null;
    
    /** Creates a new instance of ImageWriter */
    private ImageSaver() {
    }
    
    public static ImageSaver inst() {
        if (image == null) {
            image = new ImageSaver();
        }
        return image;
    }

    public void saveImage(BufferedImage bi, String fileName) {
        try {
            FileOutputStream os = new FileOutputStream(fileName);
            JPEGImageEncoder ie = JPEGCodec.createJPEGEncoder(os);
            ie.encode(bi, JPEGCodec.getDefaultJPEGEncodeParam(bi));
            os.close();
        }
        catch (Exception e) {
            System.out.println("Error - " + e.getMessage());
        }
    }

    public BufferedImage getPlainImage(int width, int height) {
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = (Graphics2D) bi.getGraphics();
        
        g.setColor(Color.white);
        g.fillRect(0, 0, width, height);
        
        Font f1 = g.getFont();
        Font f2 = f1.deriveFont(9.0f);
        g.setFont(f2);

        Environment e = Environment.inst();
       
        double scaleX = (double) width / (double) e.getWidth();
        double scaleY = (double) height / (double) e.getHeight();
        
        double scale = Math.min(scaleX, scaleY);
        
        paintLowerElements(g, scale);
        paintRobots(g, scale);
        return bi;
    }
}
