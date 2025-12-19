/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HeapTest;

import hykyx.priority_queue.Heap;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Franklin Xam
 */
public class Graph { 
    private int n; // # of vertices
    private List<int[]> [] adjacencyList;  // list of nodes adjacent 
                                           // to the vertex
    public Graph(int n) {
        this.n = n;
        adjacencyList = new ArrayList[n];
        for(int i = 0; i < n; i++) 
            adjacencyList[i] = new ArrayList<>();
    }
    
    public void addEdge(int u, int v, int w) {
        adjacencyList[u].add(new int[] {v, w});
        adjacencyList[v].add(new int[] {u, w});
    }
    
    public void dijsktra(int sourceNode) {
        Heap<GraphNode> pq = new Heap<GraphNode>((a, b) -> {
            return b.weight - a.weight;
        });
        
        int[] distance = new int[n];
        Arrays.fill(distance, Integer.MAX_VALUE);
        pq.insert(new GraphNode(sourceNode, 0));
        distance[sourceNode] = 0;
        while(!pq.isEmpty()) {
            int u = pq.pop().vertex;
            
            for(int [] neighbor : adjacencyList[u]) {
                int v = neighbor[0];
                int weight = neighbor[1];
                if(distance[v] > distance[u] + weight) {
                    distance[v] = distance[u] + weight;
                    pq.insert(new GraphNode(v, distance[v]));
                }
            }
        }
        System.out.println("Distance from source");
        for(int i = 0 ; i < n; i++) {
            try{
                writeToCSV(""+i, ""+distance[i]);
            } catch (Exception e) {
                
            }
        }
    }
    public void writeToCSV(String node, String minCost) throws IOException {
        FileWriter fw  = new FileWriter(new File("dijkstra.csv"), true);
        fw.write(node + "," + minCost + "\n");
        fw.close();
    }
}
