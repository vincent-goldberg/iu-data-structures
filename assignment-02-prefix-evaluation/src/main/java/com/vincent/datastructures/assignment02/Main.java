package com.vincent.datastructures.assignment02;
import java.util.Scanner;

/**
 * Entry point for the prefix expression evaluator.
 * Reads a space-separated prefix expression from console input,
 * evaluates it, and prints the result.
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PrefixEvaluator evaluator = new PrefixEvaluator();

        // Loop to allow multiple evaluations until user decides to stop
        while (true) {
            System.out.println("Enter a prefix expression (or 'q' to quit):");
            String expression = scanner.nextLine();
            if (expression.equalsIgnoreCase("q")) {
                break; // Exit the loop if user wants to quit
            }
            // Evaluate the prefix expression and print the result
            int result = evaluator.evaluate(expression);
            System.out.println("Result: " + result);
            System.out.println(); 
        }
        
        scanner.close();
    }
}
