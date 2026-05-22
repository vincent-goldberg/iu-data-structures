package com.vincent.datastructures.assignment01;

/**
 * Assignment 1: Algorithm Design and Analysis
 * Runs test cases for the Summation Algorithm and Palindrome Detector.
 */
public class Main {

    public static void main(String[] args) {

        // Task 1: Summation Algorithm
        System.out.println("=== Task 1: Summation Algorithm ===");
        System.out.printf("%-20s %-12s %-12s%n", "Range", "O(n) Result", "O(1) Result");
        System.out.println("-".repeat(46));
        
        // Test cases for summation algorithm
        printSumRow(3, 7);
        printSumRow(1, 100);
        printSumRow(-5, 5);
        printSumRow(4, 5);   

        // Task 2: Palindrome Detector
        System.out.println();
        System.out.println("=== Task 2: Palindrome Detector ===");
        
        // Test cases for palindrome detector
        testPalindrome("A man, a plan, a canal, Panama");  
        testPalindrome("kayak");                           
        testPalindrome("hello");                           
        testPalindrome("Able was I, ere I saw Elba");      
        testPalindrome("Desserts, I stressed");            
    }

    /** Prints one row of the summation table, showing both algorithm results. */
    private static void printSumRow(int n1, int n2) {
        long linear   = SummationAlgorithm.sumLinear(n1, n2);
        long constant = SummationAlgorithm.sumConstant(n1, n2);
        System.out.printf("%-20s %-12d %-12d%n",
                "[" + n1 + ", " + n2 + "]", linear, constant);
    }

    /** Prints the palindrome test result for one input string. */
    private static void testPalindrome(String s) {
        boolean result = PalindromeDetector.isPalindrome(s);
        System.out.printf("%-40s -> %s%n", "\"" + s + "\"", result);
    }
}
