package com.example.neetcode_150.ArraysAndHashing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
	
	
	public static int[] topk(int[] nums, int k) {
		if(nums.length == 0) {
			return null;
		}
		
		Map<Integer,Integer> map = new HashMap<>();
		
		for(int n:nums) {
			map.put(n, map.getOrDefault(n, 0)+1);
		}
		
		int[] arr = map.entrySet()
				.stream()
				.sorted(Map.Entry.<Integer,Integer>comparingByValue().reversed())
				.limit(k)
				.mapToInt(Map.Entry::getKey)
				.toArray();
		
		return arr;
	}
	
	public static int[] topKFrequentElements(int[] nums, int k) {
	    Map<Integer, Integer> map = new HashMap<>();
	    
	    // Count frequencies
	    for (int n : nums) {
	        map.put(n, map.getOrDefault(n, 0) + 1);
	    }

	    // Create buckets: index = frequency (0 to nums.length)
	    List<Integer>[] buckets = new List[nums.length + 1];
	    
	    // Place numbers in bucket based on frequency
	    for (int num : map.keySet()) {
	        int freq = map.get(num);
	        if (buckets[freq] == null) {
	            buckets[freq] = new ArrayList<>();
	        }
	        buckets[freq].add(num);
	    }

	    // Collect top k frequent elements
	    int[] result = new int[k];
	    int counter = 0;
	    for (int freq = nums.length; freq >= 1 && counter < k; freq--) {
	        if (buckets[freq] != null) {
	            for (int num : buckets[freq]) {
	                result[counter++] = num;
	                if (counter == k) break;
	            }
	        }
	    }

	    return result;
	}
	
	public static void main(String[] args) {
		int[] nums = {1,2,2,3,3,3};
		int k = 2;
		
		int[] arr = topk(nums,k);
		for(int i=0;i<arr.length;i++) {
			System.out.print(arr[i]+ " ");
		}
	}

}
