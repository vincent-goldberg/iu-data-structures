package com.vincent.datastructures.assignment04;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    // =========================================================
    // Helper Functions
    // =========================================================

    /** Returns unique random values in [min, max] via shuffle-and-take. */
    private static int[] generateUniqueValues(int count, int min, int max) {
        List<Integer> pool = new ArrayList<>();
        
        for (int v = min; v <= max; v++) {
            pool.add(v);    // Every value in range added exaclt once
        }
        Collections.shuffle(pool);      // Randomize order

        int[] values = new int[count];
        for (int i = 0; i < count; i++) {
            values[i] = pool.get(i);    // Collect unique count from value pool
        }
        return values;
    }

    /** Prints a tree's height and its three transversals, all labeled. */
    private static void analyze(String label, BinarySearchTree tree) {
        System.out.println("===== " + label + " =====");
        System.out.println("Height: " + tree.height());
        System.out.print("In-order:   ");
        tree.inOrderTraversal();
        System.out.print("Pre-order:  ");
        tree.preOrderTraversal();
        System.out.print("Post-order: ");
        tree.postOrderTraversal();
        System.out.println();
    }

    /** Builds a BST and AVL from the same data; prints their heights side by side. */
    private static void compareHeights(String label, int[] values) {
        BinarySearchTree bst = new BinarySearchTree();
        AVLTree avl = new AVLTree();
        for (int v : values) {
            bst.insert(v);
            avl.insert(v);
        }
        System.out.printf("%-28s | n=%-4d | BST height=%-4d | AVL height=%-3d%n",
                            label, values.length, bst.height(), avl.height());
    }

    public static void main(String[] args) {
        // Generate the shared dataset for Task 2 and Task 4
        int[] values = generateUniqueValues(512, 1, 1024);

        // =========================================================
        // Task 2: BST
        // =========================================================

        BinarySearchTree bst = new BinarySearchTree();
        for (int v : values) {
            bst.insert(v);
        }
        analyze("Task 2: BST (512 random unique values)", bst);

        // =========================================================
        // Task 4: AVL
        // =========================================================

        AVLTree avl = new AVLTree();
        for (int v : values) {
            avl.insert(v);
        }
        analyze("Task 4: AVL (same 512 values)", avl);
        
        // =========================================================
        // Task 5: Comparative Testing
        // =========================================================
        System.out.println("===== Task 5: BST vs AVL height comparison =====");

        int[] ascendingValues = new int[512];
        for (int i = 0; i < 512; i++) {
            ascendingValues[i] = i + 1;     
        }
        
        int[] descendingValues = new int[512];
        for (int i = 0; i < 512; i++) {
            descendingValues[i] = 512 - i;     
        }

        int[] randomSet = generateUniqueValues(512, 1, 1024); 

        compareHeights("Sorted ascending (1...512)", ascendingValues);
        compareHeights("Sorted descending (512...1)", descendingValues);
        compareHeights("Random unique (1...1024)", randomSet);
    }
}
