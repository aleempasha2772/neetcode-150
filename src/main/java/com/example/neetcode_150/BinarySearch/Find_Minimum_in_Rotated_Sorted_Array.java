package com.example.neetcode_150.BinarySearch;

public class Find_Minimum_in_Rotated_Sorted_Array {
	
	    public static int findMin(int[] nums) {
	        int l = 0;
	        int r = nums.length - 1;
	        int res = nums[0];

	        while (l <= r) {
	            if (nums[l] < nums[r]) {
	                res = Math.min(res, nums[l]);
	                break;
	            }

	            int m = l + (r - l) / 2;
	            res = Math.min(res, nums[m]);
	            if (nums[m] >= nums[l]) {
	                l = m + 1;
	            } else {
	                r = m - 1;
	            }
	        }
	        return res;
	    }
	    public static void main(String[] args) {
	    	int[] nums1 = {3, 4, 5, 1, 2};
	        System.out.println("Test Case 1: " + findMin(nums1)); // Expected: 1
	    }
	
}
