/*
 * Viewer.java
 *
 * Created on October 28, 2003, 10:14 PM
 */

package com.seekerr.simulator.robot.graphics.wall;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

/**
 *
 * @author  Wesley Kerr
 */
public class Viewer extends JFrame {
   
    /** Creates a new instance of Viewer */
    public Viewer() {
        addComponents();
        addListeners();
        show();
    }
    
    private void addComponents() {
        robotPanel = new RobotDisplayPanel();
        sonarPanel = new SonarDisplayPanel();
        buttonPanel = new ButtonPanel();
        
        getContentPane().setLayout(new GridBagLayout());
        
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(3, 3, 3, 3);
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(robotPanel, gridBagConstraints);
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(3, 3, 3, 3);
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        getContentPane().add(sonarPanel, gridBagConstraints);
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new Insets(3, 3, 3, 3);
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        getContentPane().add(buttonPanel, gridBagConstraints);
        
        setSize(1024, 768);
    }
    
    private void addListeners() {
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                Observer o = Observer.inst();
                synchronized (o.lock) {
                    o.exit = true;
                    o.lock.notifyAll();
                }
            }
        });
    }
    
    public void update() {
        robotPanel.repaint();
        sonarPanel.repaint();
    }
    
    private RobotDisplayPanel robotPanel;
    private SonarDisplayPanel sonarPanel;
    private ButtonPanel buttonPanel;
}
