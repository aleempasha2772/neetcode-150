package com.example.neetcode_150.SlidingWindow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SlidingWindowMaximum {
	
	
	public static int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int numWindows = n - k + 1;
        int[] arr = new int[numWindows];
        
        for(int i=0;i<numWindows;i++) {
        	List<Integer> list = new ArrayList<>();
        	for(int j = i;j< i + k;j++) {
        		list.add(nums[j]);
        	}
        	arr[i] = Collections.max(list);
        }
        return arr;
        
    }
	
	public static void main(String[] args) {
		
		int[] nums = {1,2,1,0,4,2,6};
		int k = 3;
		int[] arr = maxSlidingWindow(nums,k);
		for(int i=0;i<arr.length;i++) {
			System.out.print(arr[i]+" ");
		}
		
		
	}

}
