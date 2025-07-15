package com.example.neetcode_150.ArraysAndHashing;

import java.util.Arrays;

public class ValidAnagras {
	
	public static boolean isAnagrams(String s, String t) {
		
		if(s.length() != t.length()) {
			return false;
		}
		
		char[] s1 = s.toCharArray();
		char[] t1 = t.toCharArray();
		
		Arrays.sort(s1);
		Arrays.sort(t1);
		
		return Arrays.equals(s1, t1);
		
	}
	
	
	public static void main(String[] args) {
		String s = "jar";
		String t = "jam";
		System.out.print(isAnagrams(s,t));
	}

}
