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
//            Server[] servers = {new Server("192.168.101.1")
//            , new Server("192.168.101.2")
//            , new Server("192.168.101.3")};
//            
//           Heap minHeap = new Heap((Server a, Server b) -> {
//               return b.connections - a.connections;
//           });
//        System.out.println("Hello World!");
//        Heap<Integer> minHeap = new Heap((a, b) -> {
//            return Integer.compare((Integer)b, (Integer)a);
//        });
//        
//        minHeap.insert(10);
//        minHeap.insert(5);
//        minHeap.insert(15);
//        minHeap.insert(20);
//        minHeap.insert(25);
//        System.out.println(minHeap);
//        System.out.println(minHeap.search(10));
//        System.out.println(minHeap.pop());       
//        System.out.println(minHeap.pop());
//        int V = 9;
//        Graph g = new Graph(V);
//
//        // Adding edges to create the graph
//        g.addEdge(0, 1, 4);
//        g.addEdge(0, 7, 8);
//        g.addEdge(1, 2, 8);
//        g.addEdge(1, 7, 11);
//        g.addEdge(2, 3, 7);
//        g.addEdge(2, 8, 2);
//        g.addEdge(2, 5, 4);
//        g.addEdge(3, 4, 9);
//        g.addEdge(3, 5, 14);
//        g.addEdge(4, 5, 10);
//        g.addEdge(5, 6, 2);
//        g.addEdge(6, 7, 1);
//        g.addEdge(6, 8, 6);
//        g.addEdge(7, 8, 7);
//
//        // Finding and printing the shortest paths from
//        // source vertex 0
//        g.dijsktra(0);

        JFrame frame = new JFrame("Graph Visualization");
        GridPanel panel = new GridPanel();
//
        // Create some Nodes with specific coordinates
        Node n1 = new Node("A", 100, 100);
        Node n2 = new Node("B", 300, 100);
        Node n3 = new Node("C", 200, 250);
        Node n4 = new Node("D", 400, 300);
        Node n5 = new Node("E", 100, 300);

        panel.addNode(n1);
        panel.addNode(n2);
        panel.addNode(n3);
        panel.addNode(n4);
        panel.addNode(n5);

        // Connect them
        panel.addEdge(n1, n2);
        panel.addEdge(n2, n3);
        panel.addEdge(n3, n1);
        panel.addEdge(n3, n4);
        panel.addEdge(n4, n5);
        panel.addEdge(n5, n1);
        panel.addEdge(n4, n2);

        frame.add(panel);
        frame.setSize(600, 500);
        panel.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }
}
