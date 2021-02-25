import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;


public class MinHeap {
    List<Integer> heap;
    
    MinHeap() {
        heap = new ArrayList<Integer>();
        heap.add(Integer.MIN_VALUE);
    }
    
    boolean isEmpty() {
        return heap.size() == 1;
    }
    
    void getMin() {
        if(isEmpty()) System.out.println("Empty");
        else System.out.println(heap.get(1));
    }
    
    void swap(int first, int second) {
        int temp;
        temp = heap.get(first);
        heap.set(first,heap.get(second));
        heap.set(second,temp);
    }
    
    void insert(int x) {
        heap.add(x);
        int idx  = heap.size()-1;
        int curr = idx;
        int parent = idx/2;
        while(curr!=1 && heap.get(curr) < heap.get(parent)) {
        	swap(curr, parent);
        	curr = parent;
        	parent = curr/2;
        }
    }
    
    void delMin() {
        if(isEmpty()) return;
        swap(1,heap.size()-1);
        heap.remove(heap.size()-1);
        int idx = 1;
        int c1 = 2*idx;
        int c2 = 2*idx+1;
        while(idx<heap.size()) {
        	int minChild = Integer.MAX_VALUE;
        	if(c1 < heap.size() && c2 < heap.size()) {
        		minChild = Math.min(heap.get(c1), heap.get(c2));
        	} else if (c1<heap.size() && c2 >= heap.size()) {
        		minChild = heap.get(c1);
        	}
        	if(c1 < heap.size() && heap.get(c1) == minChild && minChild < heap.get(idx)) {
        		int minChildIdx = c1;
        		swap(c1,idx);
        		idx = minChildIdx;
        		c1 = 2*idx;
        		c2 = 2*idx+1;
        	} else if (c2 < heap.size() && heap.get(c2) == minChild && minChild < heap.get(idx)) {
        		int minChildIdx = c2;
        		swap(c2,idx);
        		idx = minChildIdx;
        		c1 = 2*idx;
        		c2 = 2*idx+1;
        	} else {
                break;
            }          	
        }
    }
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        MinHeap minHeap = new MinHeap();
        while(t-- > 0) {
            String op = in.next();
            if(op.equals("insert")) {
                int num = in.nextInt();
                minHeap.insert(num);
            }
            else if(op.equals("delMin")) {
                minHeap.delMin();
            } 
            else {
               minHeap.getMin();
            }
        }
    }
}
