package com.example.neetcode_150.BinarySearch;

public class Find_Minimum_in_Rotated_Sorted_Array {
	
	    public static int findMin(int[] nums) {
	        int left = 0;
	        int right = nums.length - 1;
	        while(left<right) {
	        	int mid = left + (right - left)/2;
	        	if(nums[mid]> nums[right]) {
	        		left = mid+1;
	        	}else {
	        		right = mid;
	        	}
	        }
	        return nums[left];
	    }
	    public static void main(String[] args) {
	    	int[] nums1 = {4, 5, 6, 7, 0, 1, 2};
	        System.out.println("Test Case 1: " + findMin(nums1)); // Expected: 1
	    }
	
}
