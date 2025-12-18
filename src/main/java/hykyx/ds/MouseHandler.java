/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hykyx.ds;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingUtilities;

/**
 *
 * @author Franklin Xam
 */
public class MouseHandler extends MouseAdapter {
    private GridPanel gp;
    public MouseHandler(GridPanel gp) {
        this.gp = gp;
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        if(SwingUtilities.isLeftMouseButton(e)) {
            gp.handleLeftClick(e); // handle all the left click logic
        }
        if(SwingUtilities.isRightMouseButton(e)) {
            gp.handleRightClick(e); // handle all the right click logic
        }
    }
}
