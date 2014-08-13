/*
 * APSliderPanel.java
 *
 * Created on July 13, 2004, 4:02 PM
 */

package com.seekerr.simulator.robot.graphics.simulator.sliders;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.seekerr.simulator.robot.graphics.ui.DoubleLabel;
import com.seekerr.simulator.robot.robot.KTRobot;
/**
 *
 * @author  Wesley Kerr
 */
public class KTSliderPanel extends DefaultSliderPanel {
    
    /** Creates a new instance of APSliderPanel */
    public KTSliderPanel() {
        super();
        addComponents();
        addListeners();
        
        updateSliders();
    }
    
    private void addComponents() {
        setLayout(new GridBagLayout());
        
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(3, 3, 3, 3);
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        add(wallVelocityLabel, gridBagConstraints);
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(3, 3, 3, 3);
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.8;
        add(wallVelocitySlider, gridBagConstraints);
        
        wallVelocityLabelValue.setHorizontalAlignment(SwingConstants.RIGHT);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(3, 3, 3, 3);
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        add(wallVelocityLabelValue, gridBagConstraints);
    }
    
    private void addListeners() {
        wallVelocitySlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                float value = (float) wallVelocitySlider.getValue() / 10.0f;
                wallVelocityLabelValue.setText(value);
                KTRobot.wall = value;
            }
        });
    }
    
    public void updateSliders() {
        wallVelocityLabelValue.setText(KTRobot.wall + "");
    }
    
    private JLabel wallVelocityLabel = new JLabel("Wall Velocity:");
    private JSlider wallVelocitySlider = new JSlider(0, 200, 10);
    private DoubleLabel wallVelocityLabelValue = new DoubleLabel(10);
}
