package com.vincent.datastructures.assignment01;

/**
 * Task 1: Summation Algorithm
 * Computes the sum of all integers between n1 and n2 using two approaches:
 * an O(n) loop and an O(1) formula.
 */
public class SummationAlgorithm {

    /**
     * Calculates the sum of all integers from n1 to n2.
     * Uses a for loop, so it runs in O(n) time.
     */
    public static long sumLinear(int n1, int n2) {
        int length = n2 - n1 + 1; // number of integers in the range
        long sum = 0;
        for (int i = 0; i < length; i++) {
            sum += (n1 + i); // add each integer from n1 to n2
        }
        return sum;
    }

    /**
     * Calculates the sum of all integers from n1 to n2.
     * Uses the formula: count * (n1 + n2) / 2, so it runs in O(1) time.
     */
    public static long sumConstant(int n1, int n2) {
        long count = n2 - n1 + 1; // number of integers in the range
        long sum = (count * (n1 + n2)) / 2; // apply the formula for the sum of an arithmetic series
        return sum;
    }
}
