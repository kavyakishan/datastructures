import java.util.Stack;



class Node {
	int data;
	Node next;
	
	public Node(int data) {
		this.data = data;
		this.next = null;
	}
}

class MyLinkedList {
	static Node head;
	static Node tail;
	
	public MyLinkedList() {
		this.head = null;
		this.tail = null;
	}
	
	public void insert(int data) {
		Node node = new Node(data);
		if(head == null) {
			head = node;	
		} else {
			tail.next = node;
		}
		tail = node;
	}
}
public class Class12 {

	public static void main(String[] args) {
		int[] ar =  {3,4,1,6,2,8,10,21};
		MyLinkedList ll = createLinkedList(ar);
		printLL(ll.head);
		System.out.println(length(ll.head));
		printRev(ll.head);
		insertAtPos(ll.head,5,32);
		
		int[] ar2 =  {1,2,3,4,5,6,10};
		MyLinkedList ll2 = createLinkedList(ar2);
		insertIntoSortedLL(ll2.head,8);
		deleteAtPos(ll.head,4);
		
		int[] ar3 =  {1,1,3,3,3,6,10};
		MyLinkedList ll3 = createLinkedList(ar3);
		Node head = deleteAll(ll3.head,3);
		printLL(head);
		
		int[] ar4 =  {1,2,3,3,3,6,10};
		MyLinkedList ll4 = createLinkedList(ar4);
		distinct(ll4.head);
		
		int[] ar5 =  {1,2,3,3,3,6,10};
		MyLinkedList ll5 = createLinkedList(ar5);
		unique(ll5.head);
		
		int[] ar6 =  {1,2,3,6,10};
		MyLinkedList ll6 = createLinkedList(ar6);
		reverse(ll6.head);
		
		int[] ar8 =  {1,2,3,6,10};
		MyLinkedList ll8 = createLinkedList(ar8);
		Node head1 = reverseLinkedListGroups(ll8.head,3);
		printLL(head1);
		
		int[] ar7 =  {8,1,9,15,7,2,1};
		MyLinkedList ll7 = createLinkedList(ar7);
		findMid(ll7);
		
	}

	private static void findMid(MyLinkedList ll7) {
		boolean lower = true;
		Node head = ll7.head;
		Node fast = head;
		Node slow = head;
		Node prev = null;
		while(fast != null && fast.next != null) {
			prev = slow;
			slow = slow.next;
			fast = fast.next.next;
		}
		if(fast != null && fast.next == null) {
			System.out.println(slow.data);
		} else if(lower) System.out.println(prev.data);
		else {
			System.out.println(slow.data);
		}
		
	}

	static Node reverse(Node head) {
		Node p1 = null;
		Node curr = head;
		Node p2 = null;
		while(curr != null) {
			p2 = curr.next;
			curr.next = p1;
			p1 = curr;
			curr = p2;
		}
		printLL(p1);
		return p1;
	}

	private static Node reverseLinkedListGroups(Node head,int k) {
		Node curr = head; 
		Node next = null; 
		Node prev = null;         
	    int count = 0;
	    while(count<k && curr != null) {
	    	next = curr.next;
	    	curr.next = prev;
	    	prev = curr;
	    	curr = next;
	    	count++;
	    }
	    if(next != null) 
	    	head.next = reverseLinkedListGroups(next,k);
	    return prev;
	}
	
	private static void unique(Node head) {
		
		
	}

	private static void distinct(Node head) {
		Node curr = head;
		Node prev = head;
		
		while(curr != null) {
			if(prev.data == curr.data) {
				prev.next = curr.next;
				curr = curr.next;
			} else {
				prev = curr;
				curr = curr.next;
			}
		}
		printLL(head);
	}

	private static Node deleteAll(Node head, int data) {
		if(head == null) return head;
		if(head.data == data) return deleteAll(head.next,data);
		head.next = deleteAll(head.next,data);
		return head;
	}

	private static void deleteAtPos(Node head, int pos) {
		if(head == null ) { 
			printLL(head);
		} else if (pos == 1) {
			printLL(head.next);
		} else if(pos > length(head)) {
			printLL(head);
		} else {
			int count = 1;
			Node curr = head;
			Node prev = curr;
			while(count!= pos) {
				prev = curr;
				curr = curr.next;
				count++;
			}
			prev.next = curr.next;
			printLL(head);
		}
		
	}

	private static void insertIntoSortedLL(Node head, int data) {
		Node newNode = new Node(data);
		if(head == null ) { 
			printLL(newNode);
		} else if (data < head.data) {
			newNode.next = head;
			printLL(newNode);
		} else {
			Node curr = head;
			Node prev = curr;
			while(curr.data < data) {
				prev = curr;
				curr = curr.next;
			}
			prev.next = newNode;
			newNode.next = curr;
			printLL(head);
		} 
		
	}

	private static void printRev(Node head) {
		System.out.print("Before reversing" );
		printLL(head);
		System.out.print("After reversing" );
		Node curr = head;
		Stack st = new Stack<Integer>();
		while(curr != null) {
			st.add(curr.data);
			curr = curr.next;
		}
		while(!st.isEmpty()) {
			System.out.print(st.peek() + " ->");
			st.pop();
		}
		System.out.println();
	}

	private static void insertAtPos(Node head, int pos, int data) {
		Node newNode = new Node(data);
		if(head == null) printLL(newNode);
		if(pos == 1) {
			newNode.next = head;
			printLL(newNode);
		} else {
			int count = 1;
			Node curr = head;
			Node prev = curr;
			while(count != pos) {
				prev = curr;
				curr = curr.next;
				count++;
			}
			prev.next = newNode;
			newNode.next = curr;
			printLL(head);
		}
		
	}

	static int length(Node head) {
		if(head == null) return 0;
		Node curr = head;
		int count =0;
		while(curr != null) {
			count++;
			curr = curr.next;
		}
		return count;
	}

	static void printLL(Node head) {
		Node curr = head;
		while(curr != null) {
			System.out.print(curr.data + "->");
			curr = curr.next;
		}
		System.out.println();
	}

	static MyLinkedList createLinkedList(int[] ar) {
		MyLinkedList ll = new MyLinkedList();
		for(int i = 0; i< ar.length; i++) {
			ll.insert(ar[i]);
		}
		return ll;
	}

}
