/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hykyx.priority_queue;

import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author Franklin Xam
 */
public class Heap<T>{
    private ArrayList<T> heap;
    private Comparator<T> comp;
    public Heap(Comparator<T> b) {
        /*There are many ways to implement a Minimum Heap or a Priority Queue
          However, the most effective and Efficient implementataion is using 
          a array/arraylist that uses the properties of a complete binary to 
          perform operations 
        */
        comp = b;
        heap = new ArrayList<>();
    }
    
    private int getParent(int i) {
        // the parent of a child in a complete binary tree is located
        // in (i -1) / 2 
        return (i - 1) / 2;
    }
    
    private int leftChild(int i) {
        // the left child of a node in a complete binary tree 
        //is located in 2 * n + 1
        return 2 * i + 1;       
    }
    private int rightChild(int i) {
        // The right child of a node in a complete binary tree
        // is located in 2 * n + 2
        return 2 * i + 2;
    }
    
    private void swap(int i, int j) {
        // essential method for the bubble up (or bubble down) during insertions
        T temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);      
    }
    
    public void insert(T val) {
        heap.add(val);
        int curr = heap.size() - 1;
        upShift(curr);
    }
    public int getIndex(T target) {
        if(!search(target)) return -1;
        return heap.indexOf(target);
    }
    public boolean search(T target) {
        return search(0, target);
    }
    public boolean search(int i, T target) {
        // simulation of a pre-order traversal
        if(i >= heap.size()) return false;
        if(heap.get(i).equals(target)) return true;
        return search(i * 2 + 1, target) || search(i * 2 + 2, target);
    }
    
    public T pop() {
        if(heap.isEmpty()) {
            throw new RuntimeException("The heap is empty!");
        }
        T top = heap.get(0);
        int lastInd = heap.size() - 1;
        T last = heap.get(lastInd);
        if(lastInd > 0) {
            heap.set(0, last); 
            heap.remove(lastInd);
            downShift(0);
        } else {
            heap.remove(0);
        }
        return top;        
    }
    public T remove(T target) {
        // find the index of the element to be removed
        int index = getIndex(target);
        if(index == -1) return null; // if not in heap
        
        T element = heap.get(index); 
        int lastInd = heap.size() - 1; 
        
        if(index == lastInd) return heap.remove(index); 
        // if the element is in the end of the list
       
        
        // if there is > 1 element in the heap

        T lastNode = heap.get(lastInd);
        heap.set(index, lastNode);
        heap.remove(lastInd);
        T parent = heap.get(getParent(index));
        
        // If only one element just pop it
        // if more than one, shift it down until heap invariance is achieved
        
        if(index > 0 && comp.compare(heap.get(index), parent) >= 0) {
            upShift(index); 
            // if the element is smaller / bigger (min max heap)
        } else {
            // else just move it down
            downShift(index);
        }
        return element;
    }
    public void upShift(int curr) {
        while(curr > 0 && 
               comp.compare(heap.get(curr),
                       heap.get(this.getParent(curr))) >= 0 )
        {
            // Important for holding up the heap Invariance.
            // bubbling up (keeping min/max on the top)
            
            
            swap(curr, this.getParent(curr));
            curr = this.getParent(curr);
        }
    } 
    public void downShift(int curr) {
         while(true) {
            // To restore the Heap Invariance as the root 
            // may not be the smallest / biggest in the Priority Queue
            int left = this.leftChild(curr);
            int right = this.rightChild(curr);

            int target = curr;
            if(left < heap.size() && 
                    comp.compare(heap.get(left),
                            heap.get(target)) >= 0)
            {
                target = left;
            }
            if(right < heap.size() 
                    && comp.compare(heap.get(right),
                            heap.get(target)) >= 0)
            {

                target = right;
            }
            if(target  == curr) break; 
            // when the heap invariance have been
            // restored after popping


            this.swap(curr, target);
            curr = target;
        }
    }
    public boolean isEmpty() {
        return heap.isEmpty();
    }
    public String toString() {
        return heap.toString();
    }
}
