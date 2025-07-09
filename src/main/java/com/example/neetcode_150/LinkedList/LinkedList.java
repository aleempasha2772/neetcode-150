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
	
	
	
	
	public static void main(String[] args) {
    	LinkedList list = new LinkedList();
    	
    	insert(list,1);
    	insert(list,2);
    	insert(list,3);
    	
    	display(list);
    	reverse(list);
    	System.out.println("reversed");
    	display(list);
    	System.out.println(isCyclicLinkedList(list));
    	
    }
	
	

}
