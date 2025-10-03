package com.example.neetcode_150.Systems;

import java.util.HashMap;
import java.util.Map;

class Node{
	
	int key;
	int value;
	Node prev;
	Node next;
	
	Node(){
		
	}
	
	Node(int key,int value){
		this.key = key;
		this.value = value;
	}
}


public class LRUCache {
	
	private int capacity;
	private Map<Integer,Node> cache;
	private Node head;
	private Node tail;
	
	public LRUCache(int capacity) {
		this.capacity = capacity;
		this.cache = new HashMap<>();
		this.head = new Node();
		this.tail = new Node();
		head.next = tail;
		tail.prev = head;
	}
	
	private void addNode(Node node) {
		node.prev = head;
		node.next = head.next;
		head.next.prev = node;
		head.next = node;
	}
	
	private void removeNode(Node node) {
		Node prevNode = node.prev;
		Node nextNode = node.next;
		prevNode.next = nextNode;
		nextNode.prev = prevNode;
	}
	
	public int getKey(int key) {
		if(cache.containsKey(key)) {
			Node node = cache.get(key);
			moveToHead(node);
			return node.value;
		}
		return -1;
	}
	
	public void putKey(int key,int value) {
		if(cache.containsKey(key)) {
			Node node = cache.get(key);
			node.value = value;
			moveToHead(node);
			
			if(cache.size()>capacity) {
				Node tailNode = popTailNode();
				cache.remove(tailNode);
			}
		}else {
			Node node = new Node(key,value);
			moveToHead(node);
		}
	}
	
	private void moveToHead(Node node) {
		removeNode(node);
		addNode(node);
	}
	
	private Node popTailNode() {
		Node node= tail.prev;
		removeNode(node);
		return node;
	}
	
	public static void Main(String[] args) {
		LRUCache cache = new LRUCache(2);
        cache.putKey(1, 10);  // cache: {1=10}
        cache.putKey(2, 20);  // cache: {1=10, 2=20}
        System.out.println(cache.getKey(1)); // returns 10
        cache.putKey(3, 30);  // evicts key 2, cache: {1=10, 3=30}
        System.out.println(cache.getKey(2)); // returns -1 (not found)
        System.out.println(cache.getKey(1)); // returns 10
        System.out.println(cache.getKey(3)); // returns 30
	}

}
