package com.example.neetcode_150.LinkedList;

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
		Node prev = null;
		Node next = null;
		
		while(currentNode != null) {
			next = currentNode.next;
			currentNode.next = prev;
			
			prev = currentNode;
			currentNode = next;
			
		}
		list.head = prev;
		return list;
	}
	
	public static void main(String[] args) {
    	LinkedList list = new LinkedList();
    	
    	insert(list,1);
    	insert(list,2);
    	display(list);
    	reverse(list);
    	System.out.println("reversed");
    	display(list);
    	
    }
	
	

}
