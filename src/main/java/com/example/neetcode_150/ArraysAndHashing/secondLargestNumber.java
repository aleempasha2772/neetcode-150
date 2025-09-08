package com.example.neetcode_150.ArraysAndHashing;

public class secondLargestNumber {
	
	
	
	public static int second(int[] arr) {
		
		for(int i=0;i<arr.length-1;i++) {
			for(int j = i+1;j<arr.length;j++) {
				if(arr[i] < arr[j]) {
					int temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
				}
			}
		}
		
		for(int i=0;i<arr.length;i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
		return arr[1];
	}

	
	public static void main(String[] args) {
		
		int[] arr = {1,2,3,4,5,6,7,8,9,9};
		
		if(arr.length<2) {
			System.out.println("Only one element is present");
			return;
		}
		int largest  = Integer.MIN_VALUE;
		int secondLargest = Integer.MIN_VALUE;		
		for(int i=0;i<arr.length;i++) {
			if(arr[i] > largest) {
				secondLargest = largest;
				largest = arr[i];
			}else if(arr[i] < largest && arr[i]> secondLargest) {
				secondLargest = arr[i];
			}
		}
		
		System.out.println(secondLargest);
		
		System.out.println(second(arr));
	}
	
	
}
