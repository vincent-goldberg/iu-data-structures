package com.vincent.datastructures.assignment02;

/**
 * Evaluates a valid prefix (Polish) expression using a two-stack algorithm.
 * One stack holds integer operands, the other holds operators.
 */
public class PrefixEvaluator {

    /**
     * Evaluates a space-separated prefix expression.
     * @param expression the prefix expression string (e.g. "* + 16 4 3")
     * @return the integer result of evaluating the expression
     */
    public int evaluate(String expression) {
        MyStack<Integer> valueStack = new MyStack<>();
        MyStack<String> operatorStack = new MyStack<>();
        // Split expression into tokens
        String[] tokens = expression.split(" ");

        // Loop over tokens and evaluate the expression
        for (int i = tokens.length - 1; i >= 0; i--) {
            String token = tokens[i];
            // Push token to operator or value stack based on whether it's an operator or operand
            if (token.equals("+") || token.equals("*")) {
                operatorStack.push(token);
            } else {
                valueStack.push(Integer.parseInt(token));
            }
            // Check if we have enough operands to perform an operation
            if (valueStack.size() >= 2 && operatorStack.size() >= 1) {
                int operand2 = valueStack.pop();
                int operand1 = valueStack.pop();
                String operator = operatorStack.pop();
                if (operator.equals("+")) {
                    valueStack.push(operand1 + operand2);
                } else {
                    valueStack.push(operand1 * operand2);
                }
            }
        }
        // Return the final result, which should be the only value left in the value stack
        return valueStack.pop();
    }

}
