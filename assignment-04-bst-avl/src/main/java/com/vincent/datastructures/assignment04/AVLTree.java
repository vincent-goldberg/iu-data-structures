package com.vincent.datastructures.assignment04;

/**
 * A self-balancing binary search tree. Inherits all BST operations and adds
 * rotation-based rebalancing so the tree's height stays O(log n) after every
 * insert and delete.
 */
public class AVLTree extends BinarySearchTree {

    // =========================================================
    // Rebalancing Helpers
    // =========================================================
    
    /** Read node's cached height (0 for empty subtree) */
    private int nodeHeight(Node node) {
        return node == null ? 0 : node.height;
    }

    /** Recomputes and stores a node's height from its children's cached heights */
    private void updateHeight(Node node) {
        node.height = 1 + Math.max(nodeHeight(node.left), nodeHeight(node.right));
    }

    /** Returns balance factor: Positive = left-heavy, Negative = right-heavy, 0 = balanced */
    private int balanceFactor(Node node) {
        return node == null ? 0 : nodeHeight(node.left) - nodeHeight(node.right);
    }

    // =========================================================
    // Right Rotation
    // =========================================================

    /** Right rotation around y. Returns the subtree's new root. */
    private Node rotateRight(Node y) {
        Node x = y.left;    // x becomes new root
        Node t2 = x.right;  // Middle subtree to reattach

        x.right = y;        // y drops to x's right
        y.left = t2;        // y adopts the middle subtree on its left
        
        updateHeight(y);  // Update heights after rotation
        updateHeight(x);
        return x;         // New root of this subtree
    }
    
    // =========================================================
    // Left Rotation
    // =========================================================
    
    /** Left rotation around x. Returns the subtree's new root. */
    private Node rotateLeft(Node x) {
        Node y = x.right;   // y becomes new root
        Node t2 = y.left;   // Middle subtree to reattach

        y.left = x;         // x drops to y's left
        x.right = t2;       // x adopts the middle subtree on its right

        updateHeight(x);    // Update heights after rotation
        updateHeight(y);
        return y;           // New root of this subtree  
    } 
    
    // =========================================================
    // Rebalance: LL, RR, LR or RL
    // =========================================================
     
    /** Rebalances one node if its balance factor is +-2. Returns the new subtree root. */
    private Node rebalance(Node node) {
        updateHeight(node);     // First update height to get correct balance factor
        int bf = balanceFactor(node);

        if (bf > 1) {           // Left-heavy
            if (balanceFactor(node.left) < 0) {
                node.left = rotateLeft(node.left); // LR case: Left rotation on left child
            }
            return rotateRight(node); // LL case: Right rotation on node
        }

        if (bf < -1) {        // Right-heavy
            if (balanceFactor(node.right) > 0) {
                node.right = rotateRight(node.right); // RL case: Right rotation on right child
            }
            return rotateLeft(node); // RR case: Left rotation on node
        }

        return node; // Already balanced
    } 
    
    // =========================================================
    // Insert / Delete Overrides
    // =========================================================

    @Override
    protected Node insert(Node node, int value) {
        if (node == null) {
            return new Node(value); // Base case: insert new node
        }

        if (value < node.value) {
            node.left = insert(node.left, value); // Insert into left subtree
        } else if (value > node.value) {
            node.right = insert(node.right, value); // Insert into right subtree
        } else {
            return node; // Duplicate value, do nothing
        }

        return rebalance(node); // Update height and rotate if unbalanced
    }

    @Override
    protected Node delete(Node node, int value) {
        if (node == null) {
            return null; // Base case: value not found
        }

        if (value < node.value) {
            node.left = delete(node.left, value);   // Delete from left subtree
        } else if (value > node.value) {
            node.right = delete(node.right, value); // Delete from right subtree
        } else {
            if (node.left == null) {
                return node.right; // 0 or 1 child
            } else if (node.right == null) {
                return node.left; // 1 child
            } else {
                node.value = findMin(node.right); // 2 children, replace with in-order successor
                node.right = delete(node.right, node.value); // Delete successor
            }
        }
        return rebalance(node); // Update height and rotate if unbalanced
    }
}
