/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hykyx.ds;

import hykyx.priority_queue.Heap;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Franklin Xam
 */
public class Dijkstra {
    Set<Edge> paths;
    List<Node> nodes;
    List<Edge> edges;
    Node u;
    TableFrame f = new TableFrame();
    boolean isRunning = false;
    
    JPanel parent;
    public Dijkstra(List<Node> nodes, 
            List<Edge> edges, 
            Set<Edge> paths,
            JPanel jp) {
        this.nodes = nodes;
        this.edges = edges;
        this.paths = paths;
        this.parent = jp;
    }
    public void dijsktra(Node start, Node end) { // continuous mode
        
        /* Reset sequence start */
        reset();
        /* Reset sequence end */
        isRunning = true;
        start.minDist = 0; // starting node has distance 0
        Heap<Node> pq = new Heap<Node>((a, b) -> {
            // min heap implementation
            return b.minDist - a.minDist;
        });

        pq.insert(start); // insert the initial node
        while (!pq.isEmpty()) {
            step(pq, end);
        }
        f.setVisible(true);
    }

    public void stepByStep(Node start, Node end, Runnable onFinish) {
        // step by step mode
        // reset config
        reset();
        
        // show table 
        f.setVisible(true);
        isRunning = true;
        start.minDist = 0; // starting node has distance 0
        Heap<Node> pq = new Heap<Node>((a, b) -> {
            // min heap implementation
            return b.minDist - a.minDist;
        });

        pq.insert(start); // insert the initial node
        Timer t = new Timer(600, null);
        t.addActionListener(e -> {
            if (pq.isEmpty()) { // handle unreachable nodes (not connected)
                t.stop();
                isRunning = false;
                return;
            }
            step(pq, end); // call the step function per 600 ms
            f.updateTable(nodes, u); // display table updates
            parent.repaint(); // repaint the display (visualization purposes)
            if(!isRunning) { // handle if the taask is finished
                t.stop();
                if(onFinish != null) onFinish.run();
            }
        });
        t.start();

    }
    
    
    
    public void reset() {
        for(Node n: nodes) {
            n.state = NodeState.UNVISITED;
            n.minDist = Integer.MAX_VALUE;
            n.previous = null;
        }
        parent.repaint();
        paths.clear();
    }
    // separate step from the loop for individual use in the future
    private void step(Heap<Node> pq, Node end) {
       u = pq.pop(); // current node
       u.state = NodeState.CLOSED;
        
        if(u == end) {
            // return if the destination is reached
            isRunning = false;
            reconstructPath(end);  
            return;
        }
        for(Edge e : outboundEdge(u)) {
            Node v = e.dest; // check the neighbors of the current node
            if(v == u) v = e.source;
            // if the destination is the source
            if(v.state == NodeState.CLOSED) continue;
            // calculating the cost when using this node to traverse
            if (u.minDist == Integer.MAX_VALUE) continue;
            int distPassingU = u.minDist + e.weight;


            // if the cost is less than the current minimum distance
            // use the path for the traversal
            if(distPassingU < v.minDist) {
                pq.remove(v);
                v.minDist = distPassingU;                       
                v.previous = u;
                v.state = NodeState.OPEN;
                pq.insert(v);
            }
            
        }
    }
    private List<Edge> outboundEdge(Node n) {
        // method for finding all the incident edges
        
        
        List<Edge> result = new ArrayList<>();
        for (Edge e: edges ) {
            if (e.source == n || e.dest == n) result.add(e); 
            // getting the outbound edges of a graph
        }
        return result;
    }
    public void reconstructPath(Node target) {
        // return the minimum path to get to the distination
        Node current = target;
        for (Node n : nodes) {
            if (n.state == NodeState.OPEN) {
                n.state = NodeState.CLOSED; // or NodeState.UNVISITED to make them red
            }
        }
        while(current.previous != null) {
            current.state = NodeState.PATH;
            Edge pathEdge = getEdge(current, current.previous);
            // path traversal
            if(pathEdge != null) {
                paths.add(pathEdge);
            }
            current = current.previous;  
        }
        current.state = NodeState.PATH;
        parent.repaint();
        f.updateTable(nodes, current);
    }
    private Edge getEdge(Node src, Node dest) {
        for (Edge e : edges) {
            if ((e.source == src || e.dest == src) 
                    && (e.dest == dest || e.source == dest)) return e;
            // getting the edge of the current node where the destination
            // and the source match
        }
        return null;
    }
}
