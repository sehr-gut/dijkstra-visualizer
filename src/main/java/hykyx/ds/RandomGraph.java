/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hykyx.ds;

import java.util.List;
import java.util.Random;

/**
 *
 * @author Franklin Xam
 */
public class RandomGraph {
    private final List<Node> nodes;
    private final List<Edge> edges;
    private final Random rnd;
    private final int NODE_RADIUS = 25;
    private final int CELL_SIZE = 5;
    
    public RandomGraph(Builder builder){
        this.nodes = builder.nodes;
        this.edges = builder.edges;
        this.rnd = builder.rnd;
    }
    
    public void generate(int n, int width, int height) {
        // 1. Clear existing data
        nodes.clear();
        edges.clear();
        
        // Safety check to prevent crashing if window is not visible yet
        if (width <= 0 || height <= 0) return;

        // 2. Generate Nodes
        generateNodes(n, width, height);

        // 3. Generate Edges
        generateEdges();
    }
    
    private void generateNodes(int n, int width, int height) {
        int maxAttempts = 2000; // Safety break to prevent infinite loops
        int created = 0;

        while (created < n && maxAttempts > 0) {
            maxAttempts--;
            
            // Generate random coordinates with padding (keep away from edges)
            int padding = 50;
            int x = rnd.nextInt(width - (padding * 2)) + padding;
            int y = rnd.nextInt(height - (padding * 2)) + padding;

            // Snap to grid
            x = (x / CELL_SIZE) * CELL_SIZE;
            y = (y / CELL_SIZE) * CELL_SIZE;

            // Check for overlap
            if (!isTooClose(x, y)) {
                nodes.add(new Node(getNextAvailableId(), x, y));
                created++;
            }
        }
    }
    
    private void generateEdges() {
        // Strategy: Ensure every node connects to at least one other node
        // plus some random extra connections.
        for (Node u : nodes) {
            int connections = rnd.nextInt(2) + 1; // Connect to 1 or 2 neighbors

            for (int i = 0; i < connections; i++) {
                if (nodes.isEmpty()) break;
                
                // Pick a random neighbor
                Node v = nodes.get(rnd.nextInt(nodes.size()));

                // Don't connect to self, and don't duplicate existing edges
                if (u != v && !edgeExists(u, v)) {
                    int weight = rnd.nextInt(20) + 1; // Random weight 1-20
                    edges.add(new Edge(u, v, weight));
                }
            }
        }
    }
    private boolean isTooClose(int x, int y) {
        int safeDistance = NODE_RADIUS * 3; // Ensure clear gap between nodes
        for (Node n : nodes) {
            // Euclidean distance check
            double dist = Math.sqrt(Math.pow(x - n.x, 2) + Math.pow(y - n.y, 2));
            if (dist < safeDistance) return true;
        }
        return false;
    }

    private boolean edgeExists(Node u, Node v) {
        for (Edge e : edges) {
            // Check both directions
            if ((e.source == u && e.dest == v) || (e.source == v && e.dest == u)) {
                return true;
            }
        }
        return false;
    }
    private String getNextAvailableId() {
        // Simple incremental ID generation based on current list size
        // Since we clear the list before generating, this usually resets to 0, 1, 2...
        return String.valueOf(nodes.size());
    }
    public static class Builder {
        private List<Node> nodes;
        private List<Edge> edges;
        private Random rnd;
        
        public Builder nodes(List<Node> nodes) {
            this.nodes = nodes;
            return this;
        }
        public Builder edges(List<Edge> edges) {
            this.edges = edges;
            return this;
        }
        public Builder rnd(Random rnd) {
            this.rnd = rnd;
            return this;
        }
        public RandomGraph build() {
            return new RandomGraph(this);
        }
    }
}
