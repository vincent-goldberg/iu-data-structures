package com.vincent.datastructures.assignment04;

/**
 * A binary search tree of int values. For any node, all values in its left
 * subtree are smaller and all values in its right subtree are larger.
 *
 * Operations are written as a public method that delegates to
 * a private recursive helper which takes and returns a Node. 
 */
public class BinarySearchTree {

    protected Node root;

    /** Inserts a value into the tree. Duplicates are ignored. */
    public void insert(int value) {
        root = insert(root, value);
    }

    protected Node insert(Node node, int value) {
        if (node == null) {
            return new Node(value); // Found empty spot, insert here
        }

        if (value < node.value) {
            node.left = insert(node.left, value); // Recursive left
        } else if (value > node.value) {
            node.right = insert(node.right, value); // Recursive right
        }
        // If value == node.value, it's a duplicate; do nothing

        return node; // Pass subtree back to caller
    }

    /** Search for a value in the tree. Returns true if found, false otherwise. */
    public boolean search(int value) {
        return search(root, value);
    }

    protected boolean search(Node node, int value) {
        if (node == null) {
            return false; // Reached a leaf without finding value
        }

        if (value == node.value) {
            return true; // Found the value
        } else if (value < node.value) {
            return search(node.left, value); // Search left subtree
        } else {
            return search(node.right, value); // Search right subtree
        }
    }

    // =========================================================
    // In-Order Traversal: left, node, right
    // =========================================================

    /** Prints values in ascending order. (left, node, right) */
    public void inOrderTraversal() {
        inOrderTraversal(root);
        System.out.println();
    }

    protected void inOrderTraversal(Node node) {
        if (node == null) {
            return; // Base case: empty subtree
        }
        inOrderTraversal(node.left); // Traverse left subtree
        System.out.print(node.value + " "); // Visit node
        inOrderTraversal(node.right); // Traverse right subtree
    }

    // =========================================================
    // Pre-Order Traversal: node, left, right
    // =========================================================
    
    /** Print each node before its subtrees. (node, left, right) */
    public void preOrderTraversal() {
        preOrderTraversal(root);
        System.out.println();
    }

    protected void preOrderTraversal(Node node) {
        if (node == null) {
            return; // Base case: empty subtree
        }
        System.out.print(node.value + " "); // Visit node
        preOrderTraversal(node.left); // Traverse left subtree
        preOrderTraversal(node.right); // Traverse right subtree
    }

    // =========================================================
    // Post-Order Traversal: left, right, node
    // =========================================================
    public void postOrderTraversal() {
        postOrderTraversal(root);
        System.out.println();
    }

    protected void postOrderTraversal(Node node) {
        if (node == null) {
            return; // Base case: empty subtree
        }
        postOrderTraversal(node.left); // Traverse left subtree
        postOrderTraversal(node.right); // Traverse right subtree
        System.out.print(node.value + " "); // Visit node
    }

    // =========================================================
    // Height Calculation
    // =========================================================
    
    /** Returns the height of the tree (0 for empty tree, 1 for a single node). */
    public int height() {
        return height(root);
    }

    protected int height(Node node) {
        if (node == null) {
            return 0; // An empty subtree has no height
        }
        return 1 + Math.max(height(node.left), height(node.right));
    }

    // =========================================================
    // Delete Node
    // =========================================================

    /** Removes a value from the tree if present. */
    public void delete(int value) {
        root = delete(root, value);
    }

    protected Node delete(Node node, int value {
        if (node == null) {
            return null; // Value not found, nothing to delete

            if (value < < node.value) {
                node.left = delete(node.left, value); // Recursive left
            } else if (value > node.value) {
                node.right = delete(node.right, value); // Recursive right
            } else {
                // Found the node to delete
                
            }
        }
    })
    
}
