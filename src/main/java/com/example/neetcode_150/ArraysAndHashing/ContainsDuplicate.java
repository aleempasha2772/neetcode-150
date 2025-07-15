package com.example.neetcode_150.ArraysAndHashing;

import java.util.HashSet;
import java.util.Set;

public class ContainsDuplicate {
	
	public static boolean hasDuplicate(int[] nums) {
		
		Set<Integer> hashSet = new HashSet<>();
		
		for(int i=0;i<nums.length;i++) {
			if(hashSet.contains(nums[i])) {
				return true;
			}
			hashSet.add(nums[i]);
		}
		return false;
	}
	
	
	
	public static void main(String[] args) {
		
		int[] nums = {1,2,3,4};
		
		System.out.println(hasDuplicate(nums));
		
	}
}
