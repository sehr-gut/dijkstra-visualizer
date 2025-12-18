/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hykyx.ds;

import hykyx.priority_queue.Heap;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Franklin Xam
 */
public class Dijsktra {
    Set<Edge> paths;
    List<Node> nodes;
    List<Edge> edges;
    public Dijsktra(List<Node> nodes, List<Edge> edges, Set<Edge> paths) {
        this.nodes = nodes;
        this.edges = edges;
        this.paths = paths;
    }
    public void dijsktra(Node start, Node end) {

            /* Reset sequence start */
            for(Node n: nodes) {
                n.minDist = Integer.MAX_VALUE;
                n.previous = null;
            }

            paths.clear();
            /* Reset sequence end */


            start.minDist = 0; // starting node has distance 0
            Heap<Node> pq = new Heap<Node>((a, b) -> {
                // min heap implementation
                return b.minDist - a.minDist;
            });

            pq.insert(start); // insert the initial node
            while (!pq.isEmpty()) {
                Node u = pq.pop();
                if(u == end) break; // return if the destination is reached
                for(Edge e : outboundEdge(u)) {
                    Node v = e.dest; // check the neighbors of the current node
                    if(v == u) v = e.source;
                    // if the destination is the source
                    
                    // calculating the cost when using this node to traverse
                    int distPassingU = u.minDist + e.weight;
                    
                    
                    // if the cost is less than the current minimum distance
                    // use the path for the traversal
                    if(distPassingU < v.minDist) {
                        pq.remove(v);
                        v.minDist = distPassingU;                       
                        v.previous = u;
                        pq.insert(v);
                    }

                }
            }
        reconstructPath(end);   
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
        while(current.previous != null) {
            Edge pathEdge = getEdge(current, current.previous);
            // path traversal
            
            if(pathEdge != null) {
                paths.add(pathEdge);
            }
            current = current.previous;        
        }
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
