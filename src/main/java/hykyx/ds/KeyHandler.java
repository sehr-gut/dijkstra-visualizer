/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hykyx.ds;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author Franklin Xam
 */
public class KeyHandler extends KeyAdapter {
    GridPanel gp;
    public KeyHandler(GridPanel gp) {
        this.gp = gp;
    }
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_W: // insert mode (insert/delete nodes)
                gp.changeMode(Mode.INSERT);
                break;
            case KeyEvent.VK_E:// edge mode (adding edges)
                gp.changeMode(Mode.EDGE);
                break;
            case KeyEvent.VK_R:// running the program
                gp.changeMode(Mode.VIEW);
                break;
         }
        gp.getLabel().setText("" + gp.opMode);
    };  
}
