package com.vincent.datastructures.assignment02;
import java.util.ArrayList;

/**
 * This class implements a stack data structure using an ArrayList.
 * The stack follows a LIFO principle, where the last element added 
 * is the first one removed.
*/
public class MyStack<T> {
    private ArrayList<T> stack;

    public MyStack() {
        stack = new ArrayList<>();
    }

    /**
     * Adds an item to the top of the stack.
     * @param item the item to push
     */
    public void push(T item) {
        stack.add(item);
    }

    /**
    * Removes and returns the item at the top of the stack.
    * @return the top item
    * @throws RuntimeException if the stack is empty
    */
    public T pop() {
        if (stack.isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        return stack.remove(stack.size() - 1);
    }

    /**
     * Returns the number of items in the stack.
     * @return the count of items
     */
    public int size() {
        return stack.size();
    }

}
