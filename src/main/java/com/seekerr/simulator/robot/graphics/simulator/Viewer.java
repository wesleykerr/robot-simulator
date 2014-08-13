/*
 * Viewer.java
 *
 * Created on October 28, 2003, 10:14 PM
 */

package com.seekerr.simulator.robot.graphics.simulator;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.seekerr.simulator.robot.env.Environment;
import com.seekerr.simulator.robot.graphics.simulator.sliders.DefaultSliderPanel;
import com.seekerr.simulator.robot.graphics.simulator.sliders.KTSliderPanel;
import com.seekerr.simulator.robot.graphics.ui.DisplayPanel;

/**
 *
 * @author  Wesley Kerr
 */
public class Viewer extends JFrame {
   
    /** Creates a new instance of Viewer */
    public Viewer(int width, int height) {
        addComponents(width, height);
        addListeners();
        show();
    }
    
    private void addComponents(int width, int height) {
        menuBar        = new ProgramMenuBar();
        displayPanel   = new DisplayPanel();
        
        displayPanel.setPreferredSize(new Dimension(width/2, height/2));
        scroll = new JScrollPane(displayPanel);
        scroll.setPreferredSize(new Dimension(width/2, height/2));
        scroll.getViewport().setPreferredSize(new Dimension(width/2, height/2));
        //displayPanel.setPreferredSize(new Dimension(500, 500));
        //scroll = new JScrollPane(displayPanel);
        //scroll.setPreferredSize(new Dimension(500, 500));
        //scroll.getViewport().setPreferredSize(new Dimension(500, 500));
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        getContentPane().setLayout(new GridBagLayout());
        
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.insets = new Insets(3, 3, 3, 3);
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(scroll, gridBagConstraints);
        
        createSliderPanel();
        addSliderPanel();
        
        addFillerPanel();

        setJMenuBar(menuBar);
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
    
    private void removeSliderPanel() {
        getContentPane().remove(sliderPanel);
    }

    private void addSliderPanel() {
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(3, 3, 3, 3);
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.0;
        getContentPane().add(sliderPanel, gridBagConstraints);

        validate();
        repaint();
    }
    
    private void addFillerPanel() {
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(3, 3, 3, 3);
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(new JPanel(), gridBagConstraints);
    }

    private void createSliderPanel() {
        String robotType = Environment.inst().getRobotType();
        if (robotType.equals("robot.KTRobot")) {
            sliderPanel = new KTSliderPanel();
        } else {
            sliderPanel = new DefaultSliderPanel();
        }
    }

    public void displayId(boolean displayId) {
        displayPanel.displayId(displayId);
        update();
    }
    
    public void update() {
        displayPanel.repaint();
        sliderPanel.updateSliders();
    }
    
    public void showErrorMessage(String error) {
        JOptionPane.showMessageDialog(this, error);
    }
    
    private JScrollPane        scroll;
    private ProgramMenuBar     menuBar;
    private DisplayPanel       displayPanel;
    private DefaultSliderPanel sliderPanel;
}
