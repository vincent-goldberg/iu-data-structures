package com.vincent.datastructures.assignment05;

/**
 * A single entry in a hash table bucket. Each Node stores a key/value pair
 * and a reference to the next Node, forming a linked list (chain) so that
 * keys colliding at the same index can coexist.
 */
public class Node {
    int key;
    int value;
    Node next;

    public Node(int key, int value) {
        this.key = key;
        this.value = value;
        this.next = null;
    }

}
