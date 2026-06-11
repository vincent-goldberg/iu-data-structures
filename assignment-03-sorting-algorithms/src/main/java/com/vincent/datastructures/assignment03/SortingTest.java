package com.vincent.datastructures.assignment03;

import java.util.Arrays;

/**
 * Tests the correctness of all five sorting algorithms implemented in SortingAlgorithms.
 * Takes random integer arrays, runs algorithms, and prints outputs to verify each algorithm sorts correctly.
 */
public class SortingTest {

    /**
     * Prints the elements of an integer array to the console.
     */
    private static void printArray(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }

    /**
     * Returns a new array containing a copy of the given array's elements,
     * leaving the original unmodified for repeated testing.
     */
    private static int[] copyArray(int[] arr) {
        return Arrays.copyOf(arr, arr.length);
    }

    /**
     * Returns true if the array is in ascending order.
     */
    private static boolean isSorted(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) return false;
        }
        return true;
    }

    /**
     * Prints a PASS/FAIL line for the given array.
     */
    private static void printCheck(int[] arr) {
        System.out.println(isSorted(arr) ? "PASS" : "FAIL");
    }

    /**
     * Tests all five sorting algorithms on the given array.
     * Prints the original array followed by the sorted result of each algorithm.
     * A fresh copy of the original is passed to each sort to preserve the input.
     */
    public static void runTest(int[] arr) {
        int[] arrCopy;
        
        System.out.println("Original array:");
        printArray(arr);
        System.out.println();

        // Test Insertion Sort
        arrCopy = copyArray(arr);
        SortingAlgorithms.insertionSort(arrCopy);
        System.out.println("After Insertion Sort:");
        printArray(arrCopy);
        printCheck(arrCopy);

        // Test Bubble Sort
        arrCopy = copyArray(arr);
        SortingAlgorithms.bubbleSort(arrCopy);
        System.out.println("After Bubble Sort:");
        printArray(arrCopy);
        printCheck(arrCopy);

        // Test Selection Sort
        arrCopy = copyArray(arr);
        SortingAlgorithms.selectionSort(arrCopy);
        System.out.println("After Selection Sort:");
        printArray(arrCopy);
        printCheck(arrCopy);

        // Test Merge Sort
        arrCopy = copyArray(arr);
        SortingAlgorithms.mergeSort(arrCopy);
        System.out.println("After Merge Sort:");
        printArray(arrCopy);
        printCheck(arrCopy);

        // Test Quick Sort
        arrCopy = copyArray(arr);
        SortingAlgorithms.quickSort(arrCopy);
        System.out.println("After Quick Sort:");
        printArray(arrCopy);
        printCheck(arrCopy);
    }


}
