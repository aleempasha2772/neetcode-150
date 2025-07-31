package com.example.neetcode_150.Stacks;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ValidParanthesis {
	
	
	public static boolean isValid(String s) {
		
		Stack<Character> stack = new Stack<>();
		Map<Character,Character> map = new HashMap<>();
		
		map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');
		
		
		for(Character c : s.toCharArray()) {
			if(map.containsKey(c)) {
				if(!stack.isEmpty() && stack.peek() == map.get(c)) {
					stack.pop();
				}else {
					return false;
				}
			}else {
				stack.push(c);
			}
		}
		
		return stack.isEmpty();
	}
	
	public static void main(String[] args) {
		String s = "[(])";
		System.out.print(isValid(s));
	}
}
