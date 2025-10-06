package com.example.neetcode_150.Tries;

class Node {
    int val;
    Node left;
    Node right;
    Node(int x) { val = x; }
}

public class LCA_BST {

    public Node lowestCommonAncestor(Node root, Node p, Node q) {
        if(root == null || p == null || q == null){
            return null;
        }

        if(Math.max(p.val, q.val) < root.val){
            return lowestCommonAncestor(root.left, p, q);
        } else if(Math.min(p.val, q.val) > root.val){
            return lowestCommonAncestor(root.right, p, q);
        } else {
            return root;
        }
    }
    
    // Helper method to print level-order traversal for visualization
    public void printLevelOrder(Node root) {
        if (root == null) {
            System.out.println("Empty tree");
            return;
        }
        
        java.util.Queue<Node> queue = new java.util.LinkedList<>();
        queue.offer(root);
        System.out.print("Level order: ");
        
        while (!queue.isEmpty()) {
            Node current = queue.poll();
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
    
    // Helper method to search for a node with given value in BST
    public Node findNode(Node root, int val) {
        if (root == null) return null;
        if (root.val == val) return root;
        if (val < root.val) return findNode(root.left, val);
        return findNode(root.right, val);
    }
    
    public static void main(String[] args) {
        LCA_BST solution = new LCA_BST();
        
        // Create BST: [6,2,8,0,4,7,9,null,null,3,5]
        //       6
        //      / \
        //     2   8
        //    / \ / \
        //   0  4 7  9
        //     / \
        //    3   5
        Node root = new Node(6);
        root.left = new Node(2);
        root.right = new Node(8);
        root.left.left = new Node(0);
        root.left.right = new Node(4);
        root.right.left = new Node(7);
        root.right.right = new Node(9);
        root.left.right.left = new Node(3);
        root.left.right.right = new Node(5);
        
        System.out.println("=== Binary Search Tree ===");
        solution.printLevelOrder(root);
        System.out.println();
        
        // Test Case 1: LCA of 2 and 8 should be 6
        Node p1 = solution.findNode(root, 2);
        Node q1 = solution.findNode(root, 8);
        Node lca1 = solution.lowestCommonAncestor(root, p1, q1);
        System.out.println("Test 1: LCA of " + p1.val + " and " + q1.val + " = " + 
                          (lca1 != null ? lca1.val : "null")); // Expected: 6
        
        // Test Case 2: LCA of 2 and 4 should be 2
        Node p2 = solution.findNode(root, 2);
        Node q2 = solution.findNode(root, 4);
        Node lca2 = solution.lowestCommonAncestor(root, p2, q2);
        System.out.println("Test 2: LCA of " + p2.val + " and " + q2.val + " = " + 
                          (lca2 != null ? lca2.val : "null")); // Expected: 2
        
        // Test Case 3: LCA of 3 and 5 should be 4
        Node p3 = solution.findNode(root, 3);
        Node q3 = solution.findNode(root, 5);
        Node lca3 = solution.lowestCommonAncestor(root, p3, q3);
        System.out.println("Test 3: LCA of " + p3.val + " and " + q3.val + " = " + 
                          (lca3 != null ? lca3.val : "null")); // Expected: 4
        
        // Test Case 4: LCA of 0 and 5 should be 2
        Node p4 = solution.findNode(root, 0);
        Node q4 = solution.findNode(root, 5);
        Node lca4 = solution.lowestCommonAncestor(root, p4, q4);
        System.out.println("Test 4: LCA of " + p4.val + " and " + q4.val + " = " + 
                          (lca4 != null ? lca4.val : "null")); // Expected: 2
        
        // Test Case 5: LCA of 7 and 9 should be 8
        Node p5 = solution.findNode(root, 7);
        Node q5 = solution.findNode(root, 9);
        Node lca5 = solution.lowestCommonAncestor(root, p5, q5);
        System.out.println("Test 5: LCA of " + p5.val + " and " + q5.val + " = " + 
                          (lca5 != null ? lca5.val : "null")); // Expected: 8
        
        // Test Case 6: Same node (edge case)
        Node p6 = solution.findNode(root, 4);
        Node q6 = solution.findNode(root, 4);
        Node lca6 = solution.lowestCommonAncestor(root, p6, q6);
        System.out.println("Test 6: LCA of " + p6.val + " and " + q6.val + " = " + 
                          (lca6 != null ? lca6.val : "null")); // Expected: 4
        
        // Test Case 7: One node is root
        Node p7 = solution.findNode(root, 6);
        Node q7 = solution.findNode(root, 3);
        Node lca7 = solution.lowestCommonAncestor(root, p7, q7);
        System.out.println("Test 7: LCA of " + p7.val + " and " + q7.val + " = " + 
                          (lca7 != null ? lca7.val : "null")); // Expected: 6
        
        System.out.println("\n=== All test cases completed ===");
    }
}