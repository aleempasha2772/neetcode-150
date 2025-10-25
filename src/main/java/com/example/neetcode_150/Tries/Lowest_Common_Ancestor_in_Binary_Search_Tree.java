package com.example.neetcode_150.Tries;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class Lowest_Common_Ancestor_in_Binary_Search_Tree {
    
    public int maxDepth(TreeNode root) {
        if(root == null) {
            return -1;
        }
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        
        return 1 + Math.max(left, right);
    }
    
    // Helper method to print tree in level-order traversal
    public void printLevelOrder(TreeNode root) {
        if (root == null) {
            System.out.println("Empty tree (null)");
            return;
        }
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        System.out.print("Level order: ");
        
        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            if (current != null) {
                System.out.print(current.val + " ");
                queue.offer(current.left);
                queue.offer(current.right);
            } else {
                System.out.print("null ");
            }
        }
        System.out.println();
    }
    
    // Helper method to create a BST from an array (for testing)
    public TreeNode createBST(int[] values) {
        if (values.length == 0) return null;
        
        TreeNode root = new TreeNode(values[0]);
        for (int i = 1; i < values.length; i++) {
            insertIntoBST(root, values[i]);
        }
        return root;
    }
    
    private void insertIntoBST(TreeNode root, int val) {
        if (val < root.val) {
            if (root.left == null) {
                root.left = new TreeNode(val);
            } else {
                insertIntoBST(root.left, val);
            }
        } else {
            if (root.right == null) {
                root.right = new TreeNode(val);
            } else {
                insertIntoBST(root.right, val);
            }
        }
    }
    
    
    
    private static List<List<Integer>> levelOrderTraversal(TreeNode root){
    	List<List<Integer>> list = new ArrayList<>();
    	Queue<TreeNode> q = new LinkedList<>();
    	
    	if(root == null) {
    		return null;
    	}
    	
    	q.add(root);
    	
    	while(!q.isEmpty()) {
    		List<Integer> list1 = new ArrayList<>();
    		int levelSize = q.size();
    		for(int i=0;i<levelSize;i++) {
    			TreeNode temp = q.poll();
    			list1.add(temp.val);
    			if(temp.left!=null) {
    				q.add(temp.left);
    			}
    			
    			if(temp.right != null) {
    				q.add(temp.right);
    			}
    		}
    		list.add(list1);
    	}
    	
    	return list;
    }
    
    public static void main(String[] args) {
        Lowest_Common_Ancestor_in_Binary_Search_Tree solution = 
            new Lowest_Common_Ancestor_in_Binary_Search_Tree();
        
        System.out.println("=== Test Case 1: Balanced BST ===");
        // Create BST: [3,1,5,null,null,4,6]
        //       3
        //      / \
        //     1   5
        //        / \
        //       4   6
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(1);
        root1.right = new TreeNode(5);
        root1.right.left = new TreeNode(4);
        root1.right.right = new TreeNode(6);
        
        solution.printLevelOrder(root1);
        int depth1 = solution.maxDepth(root1);
        System.out.println("Max Depth: " + depth1); // Expected: 2
        System.out.println();
        
        System.out.println("=== Test Case 2: Skewed BST (left) ===");
        // Create BST: [5,4,3,2,1]
        // 5
        //  \
        //   4
        //    \
        //     3
        //      \
        //       2
        //        \
        //         1
        TreeNode root2 = new TreeNode(5);
        root2.left = new TreeNode(4);
        root2.left.left = new TreeNode(3);
        root2.left.left.left = new TreeNode(2);
        root2.left.left.left.left = new TreeNode(1);
        
        solution.printLevelOrder(root2);
        int depth2 = solution.maxDepth(root2);
        System.out.println("Max Depth: " + depth2); // Expected: 4
        System.out.println();
        
        System.out.println("=== Test Case 3: Skewed BST (right) ===");
        // Create BST: [1,2,3,4,5]
        TreeNode root3 = new TreeNode(1);
        root3.right = new TreeNode(2);
        root3.right.right = new TreeNode(3);
        root3.right.right.right = new TreeNode(4);
        root3.right.right.right.right = new TreeNode(5);
        
        solution.printLevelOrder(root3);
        int depth3 = solution.maxDepth(root3);
        System.out.println("Max Depth: " + depth3); // Expected: 4
        System.out.println();
        
        System.out.println("=== Test Case 4: Single Node ===");
        TreeNode root4 = new TreeNode(42);
        solution.printLevelOrder(root4);
        int depth4 = solution.maxDepth(root4);
        System.out.println("Max Depth: " + depth4); // Expected: 0
        System.out.println();
        
        System.out.println("=== Test Case 5: Empty Tree ===");
        TreeNode root5 = null;
        solution.printLevelOrder(root5);
        int depth5 = solution.maxDepth(root5);
        System.out.println("Max Depth: " + depth5); // Expected: -1
        System.out.println();
        
        System.out.println("=== Test Case 6: Using createBST helper ===");
        // Create BST from array [4,2,7,1,3,6,9]
        int[] values = {4, 2, 7, 1, 3, 6, 9};
        TreeNode root6 = solution.createBST(values);
        solution.printLevelOrder(root6);
        int depth6 = solution.maxDepth(root6);
        System.out.println("Max Depth: " + depth6); // Expected: 2
        
        
        
        System.out.println("///////////////////////////////////////////////////////////");
        System.out.println(levelOrderTraversal(root1));
    }
}