package com.example.neetcode_150.SlidingWindow;

public class BestTimeToBuyAndSellStock {
	
	public static int maxProfit(int[] arr) {
		int n = arr.length;
		int left = 0;
		int right =1;
		int maxProfit = 0;
		//prices = [10,1,5,6,7,1]
		while(right<n) {
			if(arr[left]<arr[right]) {
				int profit = arr[right] - arr[left];
				maxProfit = Math.max(maxProfit, profit);
			}else {
				left = right;
			}
			right++;
		}
		return maxProfit;
	}
	
	public static void main(String[] args) {
		int[] prices = {10,8,7,5,2};
		System.out.print(maxProfit(prices));
	}
}
