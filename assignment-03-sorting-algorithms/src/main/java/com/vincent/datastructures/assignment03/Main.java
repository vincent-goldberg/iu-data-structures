package com.vincent.datastructures.assignment03;

import java.util.Random;

public class Main {

    public static void main(String[] args) {

        // =========================================================
        // Task 2: correctness testing
        // =========================================================
        Random rand = new Random();

        // Generate two random arrays
        int[] list1 = new int[10];
        int[] list2 = new int[10];
        for (int i = 0; i < 10; i++) {
            list1[i] = rand.nextInt(100); // Random integers between 0 and  99
            list2[i] = rand.nextInt(100);
        }
        
        System.out.println("===== Test List 1 =====");
        SortingTest.runTest(list1);

        System.out.println("\n===== Test List 2 =====");
        SortingTest.runTest(list2);
    }
}
