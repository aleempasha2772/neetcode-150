package com.example.neetcode_150.Stacks;
import java.util.Stack;

public class LargestRectangleArea {
	
	
	public static int LargestArea(int[] heights) {
		
		int n = heights.length;
		int maxArea = 0;
		Stack<Integer> stack = new Stack<>();
		
		for(int i=0;i<=n;i++) {
			while(!stack.isEmpty() && (i == n || heights[stack.peek()] >= heights[i])) {
				int height = heights[stack.pop()];
				int width = stack.isEmpty() ? i: i- stack.peek() - 1;
				maxArea = Math.max(maxArea,height * width);
			}
			stack.push(i);
		}
		
		return maxArea;
	}
	
	public static void main(String[] args) {
		int[] heights = {7,1,7,2,2,4};
		System.out.print(LargestArea(heights));
	}

}
