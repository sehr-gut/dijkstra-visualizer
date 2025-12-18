/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hykyx.ds;

/**
 *
 * @author Franklin Xam
 */

// class handling the edges and how to visualize them
public class Edge {
    Node source;
    Node dest;
    int weight; 
    public Edge(Node source, 
            Node destination, int weight) {
        this.source = source;
        this.dest = destination;
        this.weight = weight;
    }
}
