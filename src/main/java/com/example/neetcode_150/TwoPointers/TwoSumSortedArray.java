package com.example.neetcode_150.TwoPointers;

import java.util.Arrays;

public class TwoSumSortedArray {
	
	 public static int[] twoSum(int[] numbers, int target) {
		 int left = 0;
		 int right = numbers.length -1;
		 
		 while(left < right) {
			 int sum = numbers[left] + numbers[right];
			 if(sum < target) {
				 left++;
			 }else if(sum > target) {
				 right--;
			 }else {
				 return new int[] {left+1,right+1};
			 }
		 }
		 return new int[] {-1,-1};
	 }
	
	public static void main(String[] args) {
		int[] numbers = {1,2,3,4};
		int target = 3;
		int[] num  = twoSum(numbers,target);
		System.out.println(Arrays.toString(num));
		
	}

}
