package com.example.neetcode_150;

public class SpaceToUnderscore {
	public static String replaceSpacesWithUnderscores(String input) {
        return input.replace(" ", "_");
    }
    
    public static void main(String[] args) {
        // Test with the example
        String input = "Find Minimum in Rotated Sorted Array\n";
        String output = replaceSpacesWithUnderscores(input);
        
        System.out.println("Input: " + input);
        System.out.println("Output: " + output);
        
//        // Additional test cases
//        String test1 = "Hello World";
//        String test2 = "Java Programming Language";
//        String test3 = "Single";
//        String test4 = "  Multiple   Spaces  ";
//        
//        System.out.println("\nAdditional test cases:");
//        System.out.println("'" + test1 + "' -> '" + replaceSpacesWithUnderscores(test1) + "'");
//        System.out.println("'" + test2 + "' -> '" + replaceSpacesWithUnderscores(test2) + "'");
//        System.out.println("'" + test3 + "' -> '" + replaceSpacesWithUnderscores(test3) + "'");
//        System.out.println("'" + test4 + "' -> '" + replaceSpacesWithUnderscores(test4) + "'");
    }
}
