package com.vincent.datastructures.assignment03;

/**
 * Provides implementations of five sorting algorithms: Insertion Sort, Bubble Sort,
 * Selection Sort, Merge Sort, and Quick Sort.
 * All methods sort integer arrays in ascending order in-place.
 */
public class SortingAlgorithms {

    // =========================================================
    // Insertion Sort
    // =========================================================
    
    /**
     * Sorts an integer array in ascending order using the insertion sort algorithm.
     * Time complexity: O(n^2) worst/average case, O(n) best case (already sorted).
     */
    public static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i]; // Store the current element to be compared
            int j = i - 1;
            // Move elements greater than key one position to the right to make space for key
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j]; // Slide the elements right to make space for key
                j--;
            }
            arr[j + 1] = key; // Place key in its correct position
        }
    }

    // =========================================================
    // Bubble Sort
    // =========================================================

    /**
     * Sorts an integer array in ascending order using the bubble sort algorithm.
     * Repeatedly swaps adjacent elements that are out of order, bubbling the
     * largest unsorted element to its correct position each pass.
     * Time complexity: O(n^2) worst/average case, O(n) best case (already sorted).
     */
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        boolean swapped;
        // Outer loop to traverse through all elements
        for (int i = 0; i < n - 1; i++) {
            swapped = false; // Flag to check if any swapping occurred
            // Inner loop to compare adjacent elements
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr [j + 1]) {
                    // Swap neighbors 
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true; // Set flag to true if swap occurred
                }
            }
            if (!swapped) break; // If no swapping occurred, the array is sorted
        }
    }

    // =========================================================
    // Selection Sort
    // =========================================================

    /**
     * Sorts an integer array in ascending order using the selection sort algorithm.
     * On each pass, finds the minimum element in the unsorted portion and swaps it
     * into its correct position. Always performs exactly O(n^2) comparisons.
     * Time complexity: O(n^2) for all cases (no early exit possible).
     */
    public static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i; // Assume the minimum is the first element
            // Find the minimum element in unsorted portion of array
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j; // Update minIndex if a smaller element is found
                }
            }
            // Swap the minimum element with the first element of the unsorted portion
            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
    }

    // =========================================================
    // Merge Sort
    // =========================================================

    /**
     * Sorts an integer array in ascending order using the merge sort algorithm.
     * Time complexity: O(n log n) for all cases.
     */
    public static void mergeSort(int[] arr) {
        mergeSort(arr, 0, arr.length - 1);
    }

    /**
     * Recursively divides the array into halves and merges them in sorted order.
     */
    private static void mergeSort(int[] arr, int left, int right) {
        if (left >= right) return; // Base case

        int mid = (left + right) / 2; // Find midpoint

        mergeSort(arr, left, mid); // Sort left half
        mergeSort(arr, mid + 1, right); // Sort right half
        merge(arr, left, mid, right); // Merge sorted halves
    }

    /**
     * Merges two adjacent sorted sub-arrays arr[left..mid] and arr[mid+1..right]
     * into a single sorted sequence using a temporary array.
     */
    private static void merge(int[] arr, int left, int mid, int right ) {
        // Create temporary array to hold sorted elements
        int[] temp = new int[(right - left) + 1];
        // Copy data to temp arrays 
        for (int k = 0; k < temp.length; k++) {
            temp[k] = arr[left + k];
        }
        int i = 0; // Left half of temp pointer
        int j = mid - left + 1; // Right half of temp pointer
        int k = left; // Original array pointer

        // Merge sorted halves back into original array
        while (i <= mid - left && j <= right -left) {
            if (temp[i] <= temp[j]){
                arr[k] = temp[i];
                i++;
            } else {
                arr[k] = temp[j];
                j++;
            }
            k++;
        }

        // Copy remaining elements of left half
        while (i <= mid -left) {
            arr[k] = temp[i];
            i++; k++;
        }

        // Copy remaining elements of right half
        while (j <= right - left) {
            arr[k] = temp[j];
            j++; k++;
        }
    }

    // =========================================================
    // Quick Sort
    // =========================================================

    /**
     * Sorts an integer array in ascending order using the quick sort algorithm.
     * Time complexity: O(n log n) average case, O(n^2) worst case (sorted input).
     */
    public static void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    /**
     * Recursively partitions and sorts sub-arrays around a pivot element.
     */
    private static void quickSort(int[] arr, int low, int high) {
        if (low >= high) return; // Base case
        
        // Partition the array and get pivot index
        int pivotIndex = partition(arr, low, high);

        quickSort(arr, low, pivotIndex - 1); // Sort left of pivot
        quickSort(arr, pivotIndex + 1, high); // Sort right of pivot
    }

    /**
     * Partitions arr[low..high] around a pivot (last element), placing all
     * elements less than the pivot to its left. Returns the pivot's final index.
     */
    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low;
        for (int j = low; j <  high; j++) {
            if (arr[j] <= pivot) {
                // Swap elements
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
            }
        }
        // Swap pivot into correct position
        int temp = arr[i];
        arr[i] = pivot;
        arr[high] = temp;
        return i; // Return pivot index
        
    }


}
