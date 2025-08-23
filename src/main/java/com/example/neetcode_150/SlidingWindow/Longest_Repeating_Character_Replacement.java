package com.example.neetcode_150.SlidingWindow;

import java.util.HashMap;
import java.util.Map;

public class Longest_Repeating_Character_Replacement {
	
	public static int characterReplacement(String s, int k) {
		Map<Character,Integer> map = new HashMap<>();
		int res = 0;
		int left = 0;
		int maxf = 0;
		
		for(int right = 0;right<s.length();right++) {
			map.put(s.charAt(right), map.getOrDefault(s.charAt(right), 0)+1);
			maxf = Math.max(maxf, map.get(s.charAt(right)));
			
			while((right-left+1) - maxf > k) {
				map.put(s.charAt(left), map.get(s.charAt(left))-1);
				left++;
			}
			
			res = Math.max(res, right-left+1);
			
		}
		
		return res;
		
	}
	
	
	public static void main(String[] args) {
		String s = "AAABABB";
		int k =1;
		System.out.print(characterReplacement(s,k));
	}
	
	

}
