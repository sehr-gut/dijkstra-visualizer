/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.HeapTest to edit this template
 */
package HeapTest;

import hykyx.priority_queue.Heap;
import java.util.Random;
/**
 *
 * @author Franklin Xam
 */
public class HeapTest {
    public static void main(String[] args) {
        Heap<Integer> heap = new Heap<Integer>((a, b) -> {
            return Integer.compare(a, b);
        });
        
        Random rnd = new Random();
        int n = 100000000;
        int i = n;

        for(; i > 0; i--) {
            heap.insert(rnd.nextInt(n + (int)(n * .2)));
        }
        
//        heap.search(10);
//        heap.search(-1);
//        
//        while(!heap.isEmpty()) {
//            heap.pop();
//        }
    }
}
