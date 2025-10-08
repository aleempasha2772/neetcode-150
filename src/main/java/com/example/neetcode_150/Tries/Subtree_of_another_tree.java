package com.example.neetcode_150.Tries;

class Nodee {
    int val;
    Nodee left;
    Nodee right;
    Nodee() {}
    Nodee(int val) { this.val = val; }
    Nodee(int val, Nodee left, Nodee right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class Subtree_of_another_tree {
    
    String preOrderTravelsal(Nodee root) {
        if(root == null) {
            return "null";
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("^").append(root.val);
        sb.append(preOrderTravelsal(root.left));
        sb.append(preOrderTravelsal(root.right));
        
        return sb.toString();
    }
    
    public boolean isSubtree(Nodee root, Nodee subRoot) {
        if (subRoot == null) return true;
        if (root == null) return false;
        
        String tree = preOrderTravelsal(root);
        String subTree = preOrderTravelsal(subRoot);
        
        return tree.contains(subTree);
    }
    
    public static void main(String[] args) {
        Subtree_of_another_tree solution = new Subtree_of_another_tree();
        
        // Example 1: root = [1,2,3,4,5], subRoot = [2,4,5]
        System.out.println("=== Example 1 ===");
        Nodee root1 = new Nodee(1);
        root1.left = new Nodee(2);
        root1.right = new Nodee(3);
        root1.left.left = new Nodee(4);
        root1.left.right = new Nodee(5);
        
        Nodee subRoot1 = new Nodee(2);
        subRoot1.left = new Nodee(4);
        subRoot1.right = new Nodee(5);
        
        boolean result1 = solution.isSubtree(root1, subRoot1);
        System.out.println("Result: " + result1); // Expected: true
        System.out.println();
        
        // Example 2: root = [1,2,3,4,5,null,null,6], subRoot = [2,4,5]
        System.out.println("=== Example 2 ===");
        Nodee root2 = new Nodee(1);
        root2.left = new Nodee(2);
        root2.right = new Nodee(3);
        root2.left.left = new Nodee(4);
        root2.left.right = new Nodee(5);
        root2.left.left.left = new Nodee(6); // Extra Nodee that breaks the subtree match
        
        Nodee subRoot2 = new Nodee(2);
        subRoot2.left = new Nodee(4);
        subRoot2.right = new Nodee(5);
        
        boolean result2 = solution.isSubtree(root2, subRoot2);
        System.out.println("Result: " + result2); // Expected: false
        System.out.println();
        
        // Debug: Show the serialized strings
        System.out.println("=== Debug Information ===");
        System.out.println("Example 1 - Root serialization: " + solution.preOrderTravelsal(root1));
        System.out.println("Example 1 - SubRoot serialization: " + solution.preOrderTravelsal(subRoot1));
        System.out.println();
        System.out.println("Example 2 - Root serialization: " + solution.preOrderTravelsal(root2));
        System.out.println("Example 2 - SubRoot serialization: " + solution.preOrderTravelsal(subRoot2));
    }
}