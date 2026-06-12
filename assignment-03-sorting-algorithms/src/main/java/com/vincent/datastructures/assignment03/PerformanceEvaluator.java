package com.vincent.datastructures.assignment03;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

/**
 * Evaluates the performance of all five sorting algorithms across five dataset sizes.
 * Runs each algorithm five times per size, discards the slowest run, and averages the rest.
 * Results are printed as a formatted table and ASCII bar chart.
 */
public class PerformanceEvaluator {

    private static final int[] SIZES = {1000, 10000, 100000, 1000000, 10000000};
    private static final String[] NAMES = {"Insertion", "Bubble", "Selection", "Merge", "Quick"};
    private static final int RUNS = 5;
    private static final long MAX_MS = 20L * 60 * 1000; // 20 minutes in milliseconds

    // =========================================================
    // Entry Point
    // =========================================================

    /**
     * Runs the full performance evaluation and prints the results table and chart.
     */
    public static void runEvaluation() {
        System.out.println("\n===== Performance Evaluation =====");
        System.out.println("Trials per cell: " + RUNS + " (discarding slowest)\n");

        // Store average times for each algorithm at each dataset size
        long[][] results = new long[NAMES.length][SIZES.length];

        for (int a = 0; a < NAMES.length; a++) {
            System.out.printf("  %-12s", NAMES[a] + ":");
            boolean timedOut = false;
            for (int d = 0; d < SIZES.length; d++) {
                if (timedOut) {
                    results[a][d] = -1; // Skip larger sizes if already timed out
                    System.out.print("  --"); // -- = skipped due to previous timeout
                } else {
                    results[a][d] = measureTime(a, SIZES[d]);
                    if (results[a][d] < 0) {
                        timedOut = true;
                        System.out.print("   X"); // X = timed out
                    } else {
                        System.out.print("   ."); // . = completed
                    }
                }
                System.out.flush(); // Print each dot immediately without waiting for newline
            }
            System.out.println();
        }

        System.out.println();
        printTable(results);
        writeCSV(results);
    }

    // =========================================================
    // Measurement
    // =========================================================

    /**
     * Times an algorithm on a fresh random array of the given size.
     * Runs five times, discards the slowest, and returns the average in milliseconds.
     * Returns -1 if any single run exceeds 20 minutes.
     */
    private static long measureTime(int algorithmIndex, int size) {
        Random rand = new Random();
        long[] times = new long[RUNS];

        for (int r = 0; r < RUNS; r++) {
            int[] data = generateArray(size, rand); // Fresh array for each run

            // Run the sort on a daemon thread so we can stop waiting after
            // 20 minutes instead of being stuck until the sort finishes
            Thread sorter = new Thread(() -> runSort(algorithmIndex, data));
            sorter.setDaemon(true); // Daemon thread won't keep the program alive after main exits

            long start = System.currentTimeMillis();
            sorter.start();
            try {
                sorter.join(MAX_MS); // Wait for the sort, but at most 20 minutes
            } catch (InterruptedException e) {
                return -1;
            }
            long elapsed = System.currentTimeMillis() - start;

            if (sorter.isAlive()) return -1; // Still sorting after 20 minutes = timeout
            times[r] = elapsed;
        }

        // Discard the slowest run, average the remaining four
        long max = Long.MIN_VALUE;
        long sum = 0;
        for (long t : times) {
            sum += t;
            if (t > max) max = t;
        }
        return (sum - max) / (RUNS - 1);
    }

    /**
     * Generates a random integer array with values in the range [0, Integer.MAX_VALUE).
     */
    private static int[] generateArray(int size, Random rand) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(Integer.MAX_VALUE);
        }
        return arr;
    }

    /**
     * Routes the sort call to the correct algorithm by index.
     */
    private static void runSort(int algorithmIndex, int[] data) {
        switch (algorithmIndex) {
            case 0 -> SortingAlgorithms.insertionSort(data);
            case 1 -> SortingAlgorithms.bubbleSort(data);
            case 2 -> SortingAlgorithms.selectionSort(data);
            case 3 -> SortingAlgorithms.mergeSort(data);
            case 4 -> SortingAlgorithms.quickSort(data);
        }
    }

    // =========================================================
    // Results Table
    // =========================================================

    /**
     * Prints a formatted table of average runtimes for each algorithm and dataset size.
     */
    private static void printTable(long[][] results) {
        System.out.println("===== Results Table =====");
        System.out.printf("%-12s", "Algorithm");
        for (int size : SIZES) System.out.printf("%12s", formatSize(size));
        System.out.println();
        System.out.println("-".repeat(12 + 12 * SIZES.length));

        for (int a = 0; a < NAMES.length; a++) {
            System.out.printf("%-12s", NAMES[a]);
            for (int d = 0; d < SIZES.length; d++) {
                System.out.printf("%12s", formatTime(results[a][d]));
            }
            System.out.println();
        }
    }

    // =========================================================
    // CSV Export
    // =========================================================

    /**
     * Writes the results to a CSV file so they can be plotted with an external tool.
     * Each row is one algorithm; columns are dataset sizes. Timeout cells are written as -1.
     */
    private static void writeCSV(long[][] results) {
        String filePath = "performance_results.csv";
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            // Write header row
            writer.print("Algorithm");
            for (int size : SIZES) writer.print("," + size);
            writer.println();

            // Write one row per algorithm
            for (int a = 0; a < NAMES.length; a++) {
                writer.print(NAMES[a]);
                for (int d = 0; d < SIZES.length; d++) {
                    writer.print("," + results[a][d]); // -1 used for timeout
                }
                writer.println();
            }
            System.out.println("Results saved to " + filePath);
        } catch (IOException e) {
            System.out.println("Could not write CSV: " + e.getMessage());
        }
    }

    // =========================================================
    // Formatting Helpers
    // =========================================================

    /**
     * Formats a millisecond value as a readable string.
     */
    private static String formatTime(long ms) {
        if (ms < 0)    return "> 20 m";
        if (ms == 0)   return "< 1 ms";
        if (ms < 1000) return ms + " ms";
        return String.format("%.2f s", ms / 1000.0);
    }

    /**
     * Formats a dataset size as K or M shorthand (e.g. 10000 to 10K).
     */
    private static String formatSize(int size) {
        if (size >= 1000000) return (size / 1000000) + "M";
        if (size >= 1000)     return (size / 1000) + "K";
        return String.valueOf(size);
    }

}
