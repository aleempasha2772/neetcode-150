package com.example.neetcode_150.Stacks;

import java.util.Stack;

public class DailyTemparatures {
	
	
	public static int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        Stack<Integer> stack = new Stack<>();
        int[] arr = new int[n];
        
        for(int i=n-1;i>=0;i--) {
        	while(!stack.isEmpty() && temperatures[i] >= temperatures[stack.peek()]) {
        		stack.pop();
        	}
        	if(!stack.isEmpty()) {
        		arr[i] = stack.peek() - i;
        		
        	}
        	
        	stack.push(i);
        }
        return arr;
    }
	
	public static void main(String[] args) {
		int[] temperatures = {30,38,30,36,35,40,28};
		
		int[] arr = dailyTemperatures(temperatures);
		
		for(int i=0;i<arr.length;i++) {
			System.out.print(arr[i]+ " ");
		}
		
		
		
	}
	

}
