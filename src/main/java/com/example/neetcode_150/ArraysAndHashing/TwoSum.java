package com.example.neetcode_150.ArraysAndHashing;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {
	
	
	public static int[] twoSum(int[] arr,int target) {
		
		Map<Integer,Integer> map = new HashMap<>();
		
		for(int i=0;i<arr.length;i++) {
			map.put(arr[i], i);
		}
		
		
		for(int i=0;i<arr.length;i++) {
			int difference = target-arr[i];
			
			if(map.containsKey(difference)&& map.get(difference)!=i) {
				return new int[] {i,map.get(difference)};
			}
		}
		return new int[0];
	}
	
	
	public static void main(String[] args) {
		
		int[] nums = {3,4,5,6};
		int target = 7;
		
		int[] sum  = twoSum(nums,target);
		System.out.print(Arrays.toString(sum));
		
		
	}
}
