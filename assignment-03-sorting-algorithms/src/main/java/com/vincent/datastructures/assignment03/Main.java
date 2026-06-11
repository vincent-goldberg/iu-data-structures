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
            list1[i] = rand.nextInt(Integer.MAX_VALUE);
            list2[i] = rand.nextInt(Integer.MAX_VALUE);
        }

        System.out.println("===== Test List 1 =====");
        SortingTest.runTest(list1);

        System.out.println("\n===== Test List 2 =====");
        SortingTest.runTest(list2);

        // Edge cases: empty array, single element, and duplicates
        System.out.println("\n===== Edge Case: Empty Array =====");
        SortingTest.runTest(new int[]{});

        System.out.println("\n===== Edge Case: Single Element =====");
        SortingTest.runTest(new int[]{42});

        System.out.println("\n===== Edge Case: Duplicates =====");
        SortingTest.runTest(new int[]{7, 3, 7, 3, 7, 3, 7});

        // =========================================================
        // Task 3 & 4: performance evaluation
        // =========================================================
        PerformanceEvaluator.runEvaluation();
    }
}
