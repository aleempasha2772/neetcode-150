package com.example.neetcode_150.TwoPointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ThreeSum {
	
	
	public static List<List<Integer>> threeSum(int[] nums) {
		Arrays.sort(nums);
        int n = nums.length;
        Set<List<Integer>> set = new HashSet<>();
        
        for(int i=0;i<n;i++) {
        	int left = i+1;
        	int right = n-1;
        	while(left < right) {
        		int sum = nums[i]+nums[left]+nums[right];
        		if(sum > 0) {
        			right--;
        		}else if(sum<0) {
        			left++;
        		}else {
        			set.add(Arrays.asList(nums[i],nums[left],nums[right]));
        			left++;
        			right--;
        		}
        	}
        	
        }
        
        return new ArrayList<>(set);
    }
	
	public static void main(String[] args) {
		
		int[] nums = {-1,0,1,2,-1,-4};
		List<List<Integer>> answ = threeSum(nums);
		System.out.print(answ);
		
	}

}
