/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HeapTest;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 *
 * @author Franklin Xam
 */
public class DijkstraMain {
    public static void main(String[] args) {
        int numEdges = 1_000_000;
        int numVertices = 1_000_000;
        int maxWeight = 10_000;
        Graph randGraph = generate(numEdges, numVertices, maxWeight);
        System.out.println("Running Dijkstra from node 0");
        randGraph.dijsktra(0);
    }
    
    public static Graph generate(int n, int numEdges, int maxWeight) {
        // Validation: Max edges in undirected graph is n*(n-1)/2
        long maxPossibleEdges = (long) n * (n - 1) / 2;
        if (numEdges > maxPossibleEdges) {
            System.out.println("Warning: numEdges exceeds maximum possible. Capping at " + maxPossibleEdges);
            numEdges = (int) maxPossibleEdges;
        }

        Graph graph = new Graph(n);
        Random random = new Random();
        
        // Track existing edges to avoid duplicates (e.g., 0-1 is same as 1-0)
        Set<String> existingEdges = new HashSet<>();
        int edgesCount = 0;

        while (edgesCount < numEdges) {
            int u = random.nextInt(n);
            int v = random.nextInt(n);

            // 1. No self-loops allowed in this implementation
            if (u == v) continue;

            // 2. Create a unique key for the edge (always min-max)
            // This ensures 1-0 is treated the same as 0-1
            int min = Math.min(u, v);
            int max = Math.max(u, v);
            String edgeKey = min + "-" + max;

            // 3. Add edge if it doesn't exist yet
            if (!existingEdges.contains(edgeKey)) {
                int weight = random.nextInt(maxWeight) + 1; // Weights 1 to maxWeight
                graph.addEdge(u, v, weight);
                
                existingEdges.add(edgeKey);
                edgesCount++;
            }
        }
        
        System.out.println("Generated Graph with " + n + " nodes and " + edgesCount + " edges.");
        return graph;
    }
}
