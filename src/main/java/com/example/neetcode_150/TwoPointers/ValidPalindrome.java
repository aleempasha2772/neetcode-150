package com.example.neetcode_150.TwoPointers;

public class ValidPalindrome {
	
	public static boolean isPalindrome(String s) {
		
		String cleanedString  = s.toLowerCase().replaceAll("[^a-z0-9]", "");
		String str = new StringBuilder(cleanedString).reverse().toString();
		return cleanedString.equals(str);
		
	}
	
	public static void main(String[] args) {
		String s = "Was it a car or a cat I saw?";
		System.out.print(isPalindrome(s));
	}

}
