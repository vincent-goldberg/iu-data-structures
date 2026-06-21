package com.vincent.datastructures.assignment05;

/**
 * Drives the HashTable class across the required scenarios: insertion,
 * retrieval, updates, collision handling via chaining, removal, and
 * missing-key edge cases.
 */
public class Main {

    public static void main(String[] args) {
        HashTable table = new HashTable();

        // =========================================================
        // Basic insertion and retrieval
        // =========================================================
        table.put(1, 100);
        table.put(2, 200);
        System.out.println("get(1) -> " + table.get(1) + " (expected 100)");
        System.out.println("get(2) -> " + table.get(2) + " (expected 200)");

        // =========================================================
        // Collision: 5, 15, 25 all hash to index 5 and share one chain.
        // =========================================================
        table.put(5, 500);
        table.put(15, 1500);
        table.put(25, 2500);
        System.out.println("get(5) -> " + table.get(5) + " (expected 500)");
        System.out.println("get(15) -> " + table.get(15) + " (expected 1500)");
        System.out.println("get(25) -> " + table.get(25) + " (expected 2500)");

        // =========================================================
        // Update and remove
        // =========================================================
        
        // Updating an existing key replaces its value (no duplicate added).
        table.put(15, 9999);
        System.out.println("get(15) -> " + table.get(15) + " (expected 9999)");

        // Remove a middle node from the collision chain, then the head.
        table.remove(15);
        System.out.println("get(15) -> " + table.get(15) + " (expected -1, removed)");
        table.remove(25);
        System.out.println("get(25) -> " + table.get(25) + " (expected -1, removed)");
        System.out.println("get(5)  -> " + table.get(5)  + " (expected 500, still present)");

        // =========================================================
        // Edge cases: missing key returns -1; removing a missing key is a no-op.
        // =========================================================
    
        System.out.println("get(99) -> " + table.get(99) + " (expected -1, never added)");
        table.remove(99);
        System.out.println("remove(99) on missing key did not crash");
        
        
    }
}
