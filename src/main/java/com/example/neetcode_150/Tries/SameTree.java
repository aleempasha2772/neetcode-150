package com.example.neetcode_150.Tries;

//class TreeNode {
//	int val;
//	TreeNode left;
//	TreeNode right;
//	TreeNode() {}
//	TreeNode(int val) { this.val = val; }
//	TreeNode(int val, TreeNode left, TreeNode right) {
//		this.val = val;
//		this.left = left;
//		this.right = right;
//	}
//}

public class SameTree {
    
    String preOrderTraversal(TreeNode root){
        if(root == null){
            return "null";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("^").append(root.val);
        sb.append(preOrderTraversal(root.left));
        sb.append(preOrderTraversal(root.right));

        return sb.toString();
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        return (p.val == q.val) && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
    
    // Helper method to print tree in level-order for visualization
    public void printLevelOrder(TreeNode root) {
        if (root == null) {
            System.out.println("Empty tree");
            return;
        }
        
        java.util.Queue<TreeNode> queue = new java.util.LinkedList<>();
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
    
    public static void main(String[] args) {
        SameTree solution = new SameTree();
        
        // Example 1: p = [1,2,3], q = [1,2,3] -> true
        System.out.println("=== Example 1 ===");
        TreeNode p1 = new TreeNode(1);
        p1.left = new TreeNode(2);
        p1.right = new TreeNode(3);
        
        TreeNode q1 = new TreeNode(1);
        q1.left = new TreeNode(2);
        q1.right = new TreeNode(3);
        
        System.out.print("Tree p1: ");
        solution.printLevelOrder(p1);
        System.out.print("Tree q1: ");
        solution.printLevelOrder(q1);
        
        boolean result1 = solution.isSameTree(p1, q1);
        System.out.println("Result: " + result1); // Expected: true
        System.out.println();
        
        // Example 2: p = [4,7], q = [4,null,7] -> false
        System.out.println("=== Example 2 ===");
        TreeNode p2 = new TreeNode(4);
        p2.left = new TreeNode(7);
        
        TreeNode q2 = new TreeNode(4);
        q2.right = new TreeNode(7);
        
        System.out.print("Tree p2: ");
        solution.printLevelOrder(p2);
        System.out.print("Tree q2: ");
        solution.printLevelOrder(q2);
        
        boolean result2 = solution.isSameTree(p2, q2);
        System.out.println("Result: " + result2); // Expected: false
        System.out.println();
        
        // Example 3: p = [1,2,3], q = [1,3,2] -> false
        System.out.println("=== Example 3 ===");
        TreeNode p3 = new TreeNode(1);
        p3.left = new TreeNode(2);
        p3.right = new TreeNode(3);
        
        TreeNode q3 = new TreeNode(1);
        q3.left = new TreeNode(3);
        q3.right = new TreeNode(2);
        
        System.out.print("Tree p3: ");
        solution.printLevelOrder(p3);
        System.out.print("Tree q3: ");
        solution.printLevelOrder(q3);
        
        boolean result3 = solution.isSameTree(p3, q3);
        System.out.println("Result: " + result3); // Expected: false
        System.out.println();
        
        // Additional Test: Both null trees -> true
        System.out.println("=== Edge Case: Both null ===");
        boolean result4 = solution.isSameTree(null, null);
        System.out.println("Result: " + result4); // Expected: true
        System.out.println();
        
        // Additional Test: One null, one not -> false
        System.out.println("=== Edge Case: One null ===");
        TreeNode p5 = new TreeNode(1);
        boolean result5 = solution.isSameTree(p5, null);
        System.out.println("Result: " + result5); // Expected: false
    }
}