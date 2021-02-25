
public class Class13 {
	public static void main(String[] args) {
		int[] ar =  {3,4,1,6,2,8,10};
		MyLinkedList ll = Class12.createLinkedList(ar);
		System.out.println("Before .... ");
		Class12.printLL(ll.head);
		System.out.println("After .... ");
		transformLinkedll(ll);
		
		//LRU
		int[] ar1 = {8,1,4,1,8,1,7,2,5,10,2,1,8,1,4,7};
		int k = 4;
		// First : Use array to store [8,1,4,7]
		// find : K,  hit : 1, miss: [notFull : 1, full: N*k]
		// ---- O(N(NK), K) ------
		
		// Second : Use index array [8,1,4,7] = [0,1,2,6] changed to [4,3,2,6] 
		// find : K,  hit : 1, miss: [notFull : 1, full: K]
		// ---- O(N(K), K+K) ------
		
		// Third : Maintain (index,value) in ordered map, cache index in the array
		// find: K, hit: log K,  miss: [notFull : logK, full: logK]
		
		// Fourth: Maintain array and index array in unordered map, and (index,value) in ordered map
		// find: 1, hit: logK, miss: [notFull : logK, full: logK]
		
		// Fifth: Maintain in linked list, maintain prev node in  unordered map
		// find: 1, hit: 1 , miss: [notFull : 1, full: 1]
		
		// Sixth : Maintain doubly linked list, maintain  node in  unordered map
		// find: 1, hit: 1 , miss: [notFull : 1, full: 1]
	}

	private static void transformLinkedll(MyLinkedList ll) {
		int length = Class12.length(ll.head);
		Node curr = ll.head;
		int count = 0;
		Node prev = curr;
		while(count!=length/2) {
			count++;
			prev = curr;
			curr = curr.next;
		}
		prev.next = null;
		Node h1 = ll.head;
		Node h2 = Class12.reverse(curr);
		
		Node p1 = h1;
		h1 = h1.next;
		while(h1 != null && h2 != null) {
			p1.next =h2;
			h2 = h2.next;
			p1 = p1.next;
			p1.next = h1;
			h1 = h1.next;
			p1 = p1.next;
		}
		while (h1 != null) {
			p1.next = h1;
			h1 = h1.next;
			p1 = p1.next;
		}
		while (h2 != null) {
			p1.next = h2;
			h2 = h2.next;
			p1 = p1.next;
		}
		Class12.printLL(ll.head);
	}
}
