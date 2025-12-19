/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package hykyx.ds;

import javax.swing.JFrame;


/**
 *
 * @author Franklin Xam
 */
public class Dsle {
    public static void main(String[] args) {


        JFrame frame = new JFrame("Graph Visualization");
        GridPanel panel = new GridPanel();
//        // Create some Nodes with specific coordinates
//        Node n1 = new Node("A", 100, 100);
//        Node n2 = new Node("B", 300, 100);
//        Node n3 = new Node("C", 200, 250);
//        Node n4 = new Node("D", 400, 300);
//        Node n5 = new Node("E", 100, 300);
//
//        panel.addNode(n1);
//        panel.addNode(n2);
//        panel.addNode(n3);
//        panel.addNode(n4);
//        panel.addNode(n5);
//
//        // Connect them
//        panel.addEdge(n1, n2);
//        panel.addEdge(n2, n3);
//        panel.addEdge(n3, n1);
//        panel.addEdge(n3, n4);
//        panel.addEdge(n4, n5);
//        panel.addEdge(n5, n1);
//        panel.addEdge(n4, n2);

        frame.add(panel);
        frame.setSize(600, 500);
        panel.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

    }
}
