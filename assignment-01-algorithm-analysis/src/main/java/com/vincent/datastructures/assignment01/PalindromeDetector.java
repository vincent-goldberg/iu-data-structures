package com.vincent.datastructures.assignment01;

/**
 * Task 2: Palindrome Detector
 * Recursively checks if a string is a palindrome, ignoring spaces, punctuation, and capitalization.
 */
public class PalindromeDetector {

    /**
     * Checks if a string is a palindrome, ignoring spaces, punctuation, and capitalization.
     * Cleans the input then uses a recursive helper to check.
     */
    public static boolean isPalindrome(String s) {
        // Strip non-alphanumeric characters and convert to lowercase
        String cleaned = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        return isPalindromeHelper(cleaned, 0, cleaned.length() - 1);
    }

    /**
     * Recursively checks if the cleaned string is a palindrome using two pointers
     */
    private static boolean isPalindromeHelper(String s, int lo, int hi) {
        // Base case: if left index is greater than or equal to right index, it's a palindrome
        if (lo >= hi) {
            return true;
        }
        // Recursive case: check if characters at both ends are equal
        if (s.charAt(lo) != s.charAt(hi)) {
            return false;
        }
        // Recurse call to self
        return isPalindromeHelper(s, lo + 1, hi - 1);
    }
}
