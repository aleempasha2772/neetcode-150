package com.example.neetcode_150.TwoPointers;

public class TrappingRainWater {

	public static int maxArea(int[] arr) {
		int n = arr.length;
		int left = 0;
		int right = n-1;
		
		int leftMax = 0;
		int rightMax = 0;
		int maxWater = 0;
		
		while(left < right) {
			if(arr[left]<=arr[right]) {
				if(arr[left] > leftMax) {
					leftMax = arr[left];
				}else {
					maxWater = maxWater + (leftMax - arr[left]);
				}
				left++;
			}else {
				if(arr[right]> rightMax) {
					rightMax = arr[right];
				}else {
					maxWater = maxWater + (rightMax - arr[right]);
				}
				right--;
			}
		}
		return maxWater;
	}
	
	
	public static void main(String[] args) {
		int[] height = {0,2,0,3,1,0,1,3,2,1};
		System.out.print(maxArea(height));
	}
}
