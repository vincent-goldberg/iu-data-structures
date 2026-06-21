package com.vincent.datastructures.assignment05;

/**
 * A hash table mapping int keys to int values. Collisions are handled by
 * chaining: each array slot (bucket) holds the head of a linked list of
 * Nodes whose keys hash to that index.
 */
public class HashTable {
    private static final int SIZE = 10;
    private Node[] buckets;

    // =========================================================
    // Helpers
    // =========================================================
    
    public HashTable() {
        buckets = new Node[SIZE]; // Every slot starts null
    }

    /**
     * Maps a key to a bucket index in [0, SIZE). Math.abs prevents a
     * negative key from yielding a negative index.
     */
    private int hash(int key) {
        return Math.abs(key) % SIZE; 
    }

    // =========================================================
    // Put
    // =========================================================
    
    /**
     * Inserts a key/value pair. If the key already exists, its value is
     * updated instead of adding a duplicate entry.
     */
    public void put(int key, int value) {
        int index = hash(key);

        // Check chain for existing key; update if present
        Node current = buckets[index];
        while (current != null) {
            if (current.key == key) {
                current.value = value;
                return;
            }
            current = current.next;
        }

        // Key not found; insert new node at head of the chain
        Node node = new Node(key, value);
        node.next = buckets[index];
        buckets[index] = node;
    }

    // =========================================================
    // Get
    // =========================================================

    /**
     * Returns the value mapped to the given key, or -1 if the key is not
     * present. (-1 is a sentinel; it cannot be distinguished from a stored
     * value of -1.)
     */
    public int get(int key) {
        int index = hash(key);

        Node current = buckets[index];
        while (current != null) {
            if (current.key == key ) {
                return current.value;
            }
            current = current.next;
        }
        return -1; // Key not found in its bucket's chain
    }
    
    // =========================================================
    // Remove
    // =========================================================

    /**
     * Removes the entry with the given key, if present. Re-links the chain
     * around the removed node so the rest of the bucket stays intact.
     */
    public void remove(int key) {
        int index = hash(key);

        Node current = buckets[index];
        Node prev = null;

        while (current != null) {
            if (current.key == key) {
                if (prev == null) {
                    buckets[index] = current.next; // Removing the head node
                } else {
                    prev.next = current.next; // Skip over current
                }
                return;
            }
            prev = current;
            current = current.next;
        }
        // Key not found, nothing to remove
    }
    
}
