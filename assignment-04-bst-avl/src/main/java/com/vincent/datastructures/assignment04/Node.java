package com.vincent.datastructures.assignment04;

/**
 * A single node in a binary tree. Holds an integer value and references to its
 * left and right children (null when a child is absent). The height field is
 * used by AVLTree to make balancing decisions; the plain BST leaves it unused.
 */
public class Node {
    int value;
    Node left;
    Node right;
    int height;

    Node(int value) {
        this.value = value; 
        this.left = null;
        this.right = null;
        this.height = 1; // A brand-new leaf node has height 1
    }
}
