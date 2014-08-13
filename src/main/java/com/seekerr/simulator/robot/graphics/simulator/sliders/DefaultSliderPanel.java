/*
 * DefaultSliderPanel.java
 *
 * Created on July 14, 2004, 3:45 PM
 */

package com.seekerr.simulator.robot.graphics.simulator.sliders;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
/**
 *
 * @author  Wesley Kerr
 */
public class DefaultSliderPanel extends JPanel {
    
    /** Creates a new instance of DefaultSliderPanel */
    public DefaultSliderPanel() {
        this.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
    }
    
    public void updateSliders() {
    }
}
