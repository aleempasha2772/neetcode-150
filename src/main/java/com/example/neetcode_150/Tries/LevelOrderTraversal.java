package com.example.neetcode_150.Tries;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode() {}
	TreeNode(int val) { this.val = val; }
	TreeNode(int val, TreeNode left, TreeNode right) {
		this.val = val;
		this.left = left;
		this.right = right;
	}
}

public class LevelOrderTraversal {
	
	public static List<List<Integer>> levelOrder(TreeNode root) {
		
		if(root == null) {
			return null;
		}
		
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		return null;
		
	}

}
