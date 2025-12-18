/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hykyx.ds;

/**
 *
 * @author Franklin Xam
 */

// class for vertex visualization
public class Node {
    int x, y;
    String id;
    int minDist = Integer.MAX_VALUE; // Dijsktra helper
    Node previous = null; // path reconstruction helper
    boolean selected = false;
    public Node( String id, int x, int y) {
        this.x = x;
        this.y = y;
        this.id = id;
    }
    
}
