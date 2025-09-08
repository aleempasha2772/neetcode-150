package com.example.neetcode_150.ArraysAndHashing;

public class Encode_and_Decode_Strings {
	
	public static void main(String[] args) {
		
		String[] s = {"neet","code","love","you"};
		
		String s1 = "";
		for(int i=0;i<s.length;i++) {
			s1 +=s1+s[i].length()+s[i];
		}
		
		String[] arr = {};
		
		System.out.print(s1);
		
//		char[] s2 = s1.toCharArray();
//		
//		for(int i=0;i<s2.length;i++) {
//			char[] a = {};
//			a[i] = (char) (a[i] +s2[i]);
//			if(s2[i] == "#") {
//				
//			}
//		}
//		
		
		
		
	}

}
