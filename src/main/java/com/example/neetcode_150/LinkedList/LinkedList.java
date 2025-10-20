package com.example.neetcode_150.LinkedList;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class LinkedList {
	Node head;
	
	static class Node {
	    int data;
	    Node next;
	    Node() {}
	    Node(int data) { this.data = data; }
	    Node(int data, Node next) { this.data = data; this.next = next; }
	}
	
	public static LinkedList insert(LinkedList list, int data) {
		Node newNode = new Node(data);
		newNode.next = null;
		if(list.head == null) {
			list.head = newNode;
		}
		else {
			
			Node currentNode = list.head;
			while(currentNode.next != null) {
				currentNode = currentNode.next;
			}
			currentNode.next = newNode;
		}
		
		return list;
	}
	
	
	public static void display(LinkedList list) {
    	Node current = list.head;
    	if(current == null) {
    		System.out.println("No linked list");
    	}else {
    		while(current!=null) {
    			System.out.print(current.data+" -> ");
    			current = current.next;
    		}
    	}
    	System.out.println();
    }
	
	
	
	public static LinkedList reverse(LinkedList list) {
		
		if(list.head == null) {
			return null;
		}
		
		Node currentNode = list.head;
		Node prevNode = null;
		Node nextNode = null;
		
		while(currentNode != null) {
			nextNode = currentNode.next;
			currentNode.next = prevNode;
			
			prevNode = currentNode;
			currentNode = nextNode;
			
		}
		
		list.head = prevNode;
		return list;
		
		
	}
	
	public static boolean isCyclicLinkedList(LinkedList list) {
		if(list.head == null) {
			return false;
		}
		
		Set<Node> set = new HashSet<>();
		
		Node currentNode = list.head;
		
		while(currentNode!=null) {
			if(set.contains(currentNode)) {
				return true;
			}
			set.add(currentNode);
			currentNode = currentNode.next;
		}
		return false;
	}
	
	public static void delete(LinkedList list, int data) {
	    if (list.head == null) {
	        System.out.println("List is empty");
	        return;
	    }

	    // Handle head deletions first
	    while (list.head != null && list.head.data == data) {
	        list.head = list.head.next;
	    }

	    // Traverse the rest of the list
	    Node current = list.head;
	    while (current != null && current.next != null) {
	        if (current.next.data == data) {
	            // Skip/delele the matching node
	            current.next = current.next.next;
	        } else {
	            // Only move forward if no deletion occurred
	            current = current.next;
	        }
	    }
	}
	
	public static void delete1(LinkedList list, int data) {
		if(list.head == null) {
			return;
		}
		
		if(list.head != null && list.head.data == data) {
			list.head = list.head.next;
		}
		
		Node currentNode = list.head;
		while(currentNode != null && currentNode.next!=null) {
			if(currentNode.next.data == data) {
				currentNode.next = currentNode.next.next;
			}else {
				currentNode = currentNode.next;
			}
		}
		
	}
	
	public static boolean hasCycle(Node head) {
		
		Node slow = head;
		Node fast = head;
		while(slow!=null && fast!=null && fast.next!=null) {
			slow = slow.next;
			fast = fast.next.next;
			
			if(slow == fast) {
				return true;
			}
		}
		
		return false;
		
	}
	
	public static Node hasCycleNode(Node head) {
		
		Node slow = head;
		Node fast = head;
		while(slow!=null && fast!=null && fast.next!=null) {
			slow = slow.next;
			fast = fast.next.next;
			
			if(slow == fast) {
				while(head!=slow) {
					head = head.next;
					slow = slow.next;
				}
				return slow;
			}
		}
		
		return null;
		
	}
	
	
	
	
	public static LinkedList removeNthNode(LinkedList list, int n) {
		
		if(list.head == null) {
			return null;
		}
		
		int length = 0;
		Node currentNode = list.head;
		
		while(currentNode != null) {
			currentNode = currentNode.next;
			length++;
		}
		System.out.println(length);
		
		if(length-n == 0) {
			currentNode = currentNode.next;
			return list;
		}
		
		currentNode = list.head;
		for(int i=1;i<length-n;i++) {
			currentNode = currentNode.next;
		}
		
		currentNode.next = currentNode.next.next;
		
		return list;
		
	}
	
	public static Node mergeKSoretedLinkedLists(Node[] lists) {
	    PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.data, b.data));
		
	    //adding head of the linkedList
		for(Node list:lists) {
			if(list!=null) {
				pq.add(list);
				System.out.print(list.data+ " ");
			}
		}
		
		Node result = null;
		Node resultEnd = null;
		
		while(!pq.isEmpty()) {
			System.out.println();

			Node smallest = pq.poll();
			System.out.println();

			System.out.print("trace after");
			System.out.println(smallest.data);
			if(result == null) {
				result = smallest;
			}else {
				resultEnd.next = smallest;
			}
 			resultEnd = smallest;
			
			if(smallest.next!=null) {
				pq.add(smallest.next);
			}
		}
		
		return result;
		
		
    }
	
	
	public static boolean palindrome(Node head) {
		Node slow = head;
		Node fast = head;
		
		while(fast!=null && fast.next!=null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		
		Node prev = null;
		Node next = null;
		while(slow!=null) {
			next = slow.next;
			slow.next = prev;
			prev = slow;
			slow = next;
		}
		
		Node left = head;
		Node right = prev;
		while(right!=null) {
			if(left.data !=  right.data) {
				return false;
			}
			left = left.next;
			right = right.next;
		}
		return true;
		
	}
	
	
	
	public static void main(String[] args) {
	    LinkedList list = new LinkedList();
	    LinkedList list2 = new LinkedList();

	    insert(list, 1);
	    insert(list, 3);
	    insert(list, 5);
	    insert(list, 7);

	    insert(list2, 2);
	    insert(list2, 4);
	    insert(list2, 6);
	    insert(list2, 4);
	    insert(list2, 2);
	    System.out.println("Palindrome");
	    System.out.print(palindrome(list2.head));
	    System.out.println();
	    

	    display(list);
	    delete1(list, 1);
	    display(list);
	    reverse(list);
	    System.out.println("reversed");
	    display(list);

	    System.out.println("Is list cyclic? " + isCyclicLinkedList(list)); // false

	    // --- Test hasCycle (Floyd's algorithm) ---
	    
	    // Create a cyclic list manually
	    LinkedList cyclicList = new LinkedList();
	    Node n1 = new Node(1);
	    Node n2 = new Node(2);
	    Node n3 = new Node(3);
	    Node n4 = new Node(4);

	    n1.next = n2;
	    n2.next = n3;
	    n3.next = n4;
	    n4.next = n2; // Create cycle: 4 -> 2

	    cyclicList.head = n1;

	    System.out.println("Has cycle (Floyd)? " + hasCycle(cyclicList.head)); // true
	    
	    Node cycleStart = hasCycleNode(cyclicList.head);
	    System.out.println("Cycle starts at node with data: " + (cycleStart != null ? cycleStart.data : "no cycle"));
	    // Test with acyclic list
	    System.out.println("Has cycle (Floyd) on list? " + hasCycle(list.head)); // false

	    // Optional: test removeNthNode
	    removeNthNode(list, 2);
	    display(list);

	    // ===================================================================
	    // === TEST FOR mergeKSoretedLinkedLists (MERGE K SORTED LINKED LISTS) ===
	    // ===================================================================

	    // Create 3 sorted linked lists
	    LinkedList l1 = new LinkedList();
	    insert(l1, 1);
	    insert(l1, 4);
	    insert(l1, 5);

	    LinkedList l2 = new LinkedList();
	    
	    insert(l2, 2);
	    insert(l2, 6);
	    LinkedList l3 = new LinkedList();
	    insert(l3, 1);
	    insert(l3, 3);
	    insert(l3, 4);

	    // Build array of heads (Node[])
	    Node[] lists = new Node[] {
	        l1.head,
	        l2.head,
	        l3.head
	    };

	    // Merge k sorted linked lists
	    Node mergedHead = mergeKSoretedLinkedLists(lists);

	    // Display the merged list
	    System.out.println("\nMerged K Sorted Linked Lists:");
	    Node current = mergedHead;
	    if (current == null) {
	        System.out.println("Empty result");
	    } else {
	        while (current != null) {
	            System.out.print(current.data + " -> ");
	            current = current.next;
	        }
	        System.out.println("null");
	    }

	    // ===================================================================
	    // === END OF MERGE K LISTS TEST =====================================
	    // ===================================================================
	}
	
	

}
