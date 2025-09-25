package com.example.neetcode_150.BinarySearch;

public class Search_in_Rotated_Sorted_Array {
	
	public static int BinarySearch(int[] arr,int target) {
		int left = 0;
		int right = arr.length -1;
		return modifiedBinarySearch(arr,target,left,right);
	}
	
	
	public static int modifiedBinarySearch(int[] arr, int target,int left,int right) {
		if(left > right) {
			return -1;
		}
		int mid = left + (right - left)/2;
		
		if(arr[mid] == target) {
			return mid;
		}
		if(arr[mid]>=arr[left]) {
			if(arr[left] <= target && target <= arr[mid]) {
				return modifiedBinarySearch(arr,target,left,mid-1);
			}else {
				return modifiedBinarySearch(arr,target,mid+1,right);
			}
		}else {
			if(arr[mid]<= target && target <=arr[right]) {
				return modifiedBinarySearch(arr,target,mid+1,right);
			}else {
				return modifiedBinarySearch(arr,target,left,mid-1);
			}
		}
		
	}

	public static void main(String[] args) {
		int[] nums1 = {3, 4, 5, 6, 1, 2};
        int target1 = 1;
        System.out.println("Input: nums = [3,4,5,6,1,2], target = 1");
        System.out.println("Output: " + BinarySearch(nums1, target1)); // Expected: 4
	}
}
