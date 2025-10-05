package com.example.neetcode_150.Tries;

import java.util.LinkedList;
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


public class Invert_Binary_Tree {
	
	public TreeNode invertTree(TreeNode root) {
		
		if(root == null) {
			return null;
		}
		
		TreeNode left = invertTree(root.left);
		TreeNode right = invertTree(root.right);
		
		root.left = right;
		root.right = left;
		
		return root;
		
	}
	
	// Helper method to print tree in level-order for easy visualization
	public void printLevelOrder(TreeNode root) {
		if (root == null) {
			System.out.println("null");
			return;
		}
		
		java.util.Queue<TreeNode> queue = new java.util.LinkedList<>();
		queue.offer(root);
		
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				TreeNode current = queue.poll();
				if (current != null) {
					System.out.print(current.val + " ");
					queue.offer(current.left);
					queue.offer(current.right);
				} else {
					System.out.print(" ");
				}
			}
			System.out.println();
		}
	}
	
	
	public TreeNode invertTreeLevelOrder(TreeNode root) {
		if(root == null) {
			return null;
		}
		
		final Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		while(!queue.isEmpty()) {
			
			final TreeNode node = queue.poll();
			
			final TreeNode temp = node.left;
			node.left = node.right;
			node.right = temp;
			
			if(node.left != null) {
				queue.add(node.left);
			}
			if(node.right!=null) {
				queue.add(node.right);
			}
		}
		
		return root;
		
	}
	
	public static void main(String[] args) {
		Invert_Binary_Tree solution = new Invert_Binary_Tree();
		
		// Create sample binary tree:
		//       4
		//      / \
		//     2   7
		//    / \ / \
		//   1  3 6  9
		
		TreeNode root = new TreeNode(4);
		root.left = new TreeNode(2);
		root.right = new TreeNode(7);
		root.left.left = new TreeNode(1);
		root.left.right = new TreeNode(3);
		root.right.left = new TreeNode(6);
		root.right.right = new TreeNode(9);
		
		System.out.println("Original tree:");
		solution.printLevelOrder(root);
		
		TreeNode invertedRoot = solution.invertTreeLevelOrder(root);
		
		System.out.println("\nInverted tree:");
		solution.printLevelOrder(invertedRoot);
		
		// Test with empty tree
		System.out.println("\nTesting with null tree:");
		TreeNode nullRoot = null;
		TreeNode invertedNull = solution.invertTreeLevelOrder(nullRoot);
		solution.printLevelOrder(invertedNull);
	}
}