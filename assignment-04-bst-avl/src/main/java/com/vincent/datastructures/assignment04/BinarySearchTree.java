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
    
}
