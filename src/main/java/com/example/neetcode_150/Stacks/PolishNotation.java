package com.example.neetcode_150.Stacks;

import java.util.Stack;

public class PolishNotation {
	
	public static int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();

        for(String c : tokens){
            if(c.equals("+")) {
            	stack.push(stack.pop() + stack.pop());
            }else if(c.equals("-")) {
            	int a = stack.pop();
            	int b = stack.pop();
            	stack.push(b-a);
            }else if(c.equals("*")) {
            	stack.push(stack.pop() * stack.pop());
            }else if(c.equals("/")) {
            	int a = stack.pop();
            	int b = stack.pop();
            	if(a == 0|| b==0) {
            		throw new ArithmeticException("division is not possible");
            	}
            	stack.push(b/a);
            }else {
            	stack.push(Integer.parseInt(c));
            }
        }
        
        return stack.pop();
    }
	
	
	public static void main(String[] args) {
		String[] tokens = {"1","2","+","3","*","4","-"};
		System.out.println(evalRPN(tokens));
	}
	
	
}
