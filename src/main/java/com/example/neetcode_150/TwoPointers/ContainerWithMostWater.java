package com.example.neetcode_150.TwoPointers;

public class ContainerWithMostWater {
	
	public static int maxArea(int[] heights) {
		
		int n = heights.length;
		int left = 0;
		int right = n-1;
		int maxArea = 0;
		
		while(left<right) {
			int area = Math.min(heights[left],heights[right]) * (right-left);
			maxArea = Math.max(maxArea, area);
			if(heights[left]<=heights[right]) {
				left++;
			}else {
				right--;
			}
		}
		return maxArea;
	}
	
	
	public static void main(String[] args) {
		
		int[] heights = {1,7,2,5,4,7,3,6};
		
		System.out.print(maxArea(heights));
		
	}
}
