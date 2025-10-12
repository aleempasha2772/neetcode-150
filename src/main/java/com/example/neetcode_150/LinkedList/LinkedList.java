package com.example.neetcode_150.LinkedList;

import java.util.HashSet;
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

	    // Test with acyclic list
	    System.out.println("Has cycle (Floyd) on list? " + hasCycle(list.head)); // false

	    // Optional: test removeNthNode
	    removeNthNode(list, 2);
	    display(list);
	}
	
	

}
