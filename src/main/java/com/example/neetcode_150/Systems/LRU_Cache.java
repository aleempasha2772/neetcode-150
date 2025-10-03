package com.example.neetcode_150.Systems;

import java.util.HashMap;
import java.util.Map;


class ListNode {
    int key;
    int value;
    ListNode prev;
    ListNode next;
    
    public ListNode() {}
    
    public ListNode(int key, int value) {
        this.key = key;
        this.value = value;
    }
}



public class LRU_Cache {
	
	private int capacity;
	private Map<Integer,ListNode> cache;
	private ListNode head;
	private ListNode tail;
	
	public LRU_Cache(int capacity) {
		this.capacity = capacity;
		this.cache = new HashMap<>();
		this.head = new ListNode();
		this.tail = new ListNode();
		head.next = tail;
		tail.prev = head;
	}
	
	public int get(int key) {
		if(cache.containsKey(key)) {
			ListNode node = cache.get(key);
			moveToHead(node);
	        return node.value;
		}
		
       return -1;
        
    }
	
	public void put(int key,int value) {
		ListNode node = cache.get(key);
		if(node != null) {
			node.value = value;
			moveToHead(node);
		}else {
			ListNode newNode = new ListNode(key,value);
			cache.put(key, newNode);
			addNode(newNode);
			
			if(cache.size()>capacity) {
				ListNode tailnode = popTail();
				cache.remove(tailnode);
			}
		}
	}
	
	private void addNode(ListNode node) {
		node.prev = head;
		node.next = head.next;
		head.next.prev = node;
		head.next = node;
		
	}
	private void removeNode(ListNode node) {
		ListNode prevNode = node.prev;
		ListNode nextnode = node.next;
		prevNode.next = nextnode;
		nextnode.prev = prevNode;
		
	}
	
	
	private void moveToHead(ListNode node) {
		removeNode(node);
		addNode(node);
	}
	
	private ListNode popTail() {
		ListNode tailNode = tail.prev;
		removeNode(tailNode);
		return tailNode;
	}
	public static void main(String[] args) {
        LRU_Cache cache = new LRU_Cache(2);
        cache.put(1, 10);  // cache: {1=10}
        cache.put(2, 20);  // cache: {1=10, 2=20}
        System.out.println(cache.get(1)); // returns 10
        cache.put(3, 30);  // evicts key 2, cache: {1=10, 3=30}
        System.out.println(cache.get(2)); // returns -1 (not found)
        System.out.println(cache.get(1)); // returns 10
        System.out.println(cache.get(3)); // returns 30
    }
	

}


