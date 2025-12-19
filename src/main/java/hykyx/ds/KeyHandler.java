/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hykyx.ds;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;

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
            case KeyEvent.VK_Q:
                gp.changeMode(Mode.STEP);
                break;
            case KeyEvent.VK_G:
                String input = JOptionPane.showInputDialog(null, "How many nodes?");
                if (input != null && !input.isEmpty()) {
                    try {
                        int n = Integer.parseInt(input);
                        gp.createRandomGraph(n);
                    } catch (NumberFormatException ex) {
                        System.out.println("Invalid number");
                    }
                }
                break;
         }
        
    };  
}
