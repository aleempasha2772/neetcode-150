package com.example.neetcode_150.Systems;


class CacheNode<V>{
	private final V value;
	private final Long createdTime;
	private volatile Long lastAccessTime;
	
	public CacheNode(V value) {
		this.value = value;
		this.createdTime = System.currentTimeMillis();
		this.lastAccessTime = createdTime;
	}
	
	public V getValue() {
		this.lastAccessTime = System.currentTimeMillis();
		return value;
	}
	public long getLastAccessTime() { return lastAccessTime; }
	
	
}


public class Cache {
	
	
}
