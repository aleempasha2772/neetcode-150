package com.example.neetcode_150.Stacks;

import java.util.ArrayList;
import java.util.EmptyStackException;

public class StackImplementation<T> {
	
	private ArrayList<T> stack;
	

	public void display() {
		for(int i =stack.size()-1;i>=0;i--) {
			System.out.println(stack.get(i));
		}
	}

	public StackImplementation() {
		stack = new ArrayList<>();
	}
	
	public void push(T item) {
		stack.add(item);
	}
	
	public T pop() {
		if(isEmpty()) {
			throw new EmptyStackException();
		}
		T item = stack.remove(stack.size()-1);
		return item;
	}
	
	public T peek() {
		if(isEmpty()) {
			throw new EmptyStackException();
		}
		T item = stack.get(stack.size() -1);
		return item;
	}
	
	public int size() {
		int size = stack.size();
		return size;
	}
	
	public boolean isEmpty() {
		return stack.isEmpty();
	}
	
	
	public static void main(String[] args) {
		StackImplementation<Integer> intStack = new StackImplementation<>();
		System.out.println("Stack size : "+ intStack.size());
		intStack.push(10);
		intStack.push(20);
		intStack.push(30);
		intStack.display();
		System.out.println("Stack size after push operation : "+intStack.size());
		int n = intStack.peek();
		System.out.println("the peek of the stack : "+n);
		intStack.pop();
		System.out.println("Stack size after pop operation : "+intStack.size());
		intStack.display();
		
		
		
	}

}
