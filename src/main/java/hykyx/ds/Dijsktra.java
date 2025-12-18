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
            Heap<Node> pq = new Heap<Node>((a, b) -> { // min heap implementation
                return b.minDist - a.minDist;
            });

            pq.insert(start);
            while (!pq.isEmpty()) {
                Node u = pq.pop();
                System.out.println(u.id);
                if(u == end) break;
                for(Edge e : outboundEdge(u)) {
                    Node v = e.dest;
                    if(v == u) v = e.source;

                    int distPassingU = u.minDist + e.weight;
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
        List<Edge> result = new ArrayList<>();
        for (Edge e: edges ) {
            if (e.source == n || e.dest == n) result.add(e); 
            // getting the outbound edges of a graph
        }
        return result;
    }
    public void reconstructPath(Node target) {
        Node current = target;
        while(current.previous != null) {
            Edge pathEdge = getEdge(current, current.previous);
            if(pathEdge != null) {
                paths.add(pathEdge);
            }
            current = current.previous;        
        }
    }
    private Edge getEdge(Node src, Node dest) {
        for (Edge e : edges) {
            if ((e.source == src || e.dest == src) && (e.dest == dest || e.source == dest)) return e;
        }
        return null;
    }
}
