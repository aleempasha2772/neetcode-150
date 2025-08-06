package com.example.neetcode_150.ArraysAndHashing;

import java.util.HashMap;
import java.util.Map;

public class TopKFrequentElements {
	
	
	public static int[] topKFrequent(int[] nums, int k) {
		Map<Integer,Integer> map = new HashMap<>();
		
		for(int i = 0;i<nums.length;i++) {
			map.put(nums[i], map.getOrDefault(nums[i], 0)+1);
		}
		
		int[] arr = map.entrySet()
				.stream()
				.sorted(Map.Entry.<Integer, Integer>comparingByValue().reversed())
				.limit(k)
				.mapToInt(Map.Entry::getKey)
				.toArray();
		return arr;
		
	}
	
	public static void main(String[] args) {
		int[] nums = {1,2,2,3,3,3};
		int k = 2;
		
		int[] arr = topKFrequent(nums,k);
		for(int i=0;i<arr.length;i++) {
			System.out.print(arr[i]+ " ");
		}
	}

}
