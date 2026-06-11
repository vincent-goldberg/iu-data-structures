# M3 Project 1: Sorting Algorithms

Implements and compares five sorting algorithms: Insertion Sort, Bubble Sort,
Selection Sort, Merge Sort, and Quick Sort.

## What it does

Running `Main` performs two tasks in order:

1. **Correctness testing** — generates two random 10-element lists and sorts
   each one with all five algorithms, printing the original list, the sorted
   result, and a PASS/FAIL check for each. It also tests a few edge cases:
   an empty array, a single-element array, and an array with duplicates.
2. **Performance evaluation** — times each algorithm on random data sets of
   1,000 / 10,000 / 100,000 / 1,000,000 / 10,000,000 integers (values up to
   `Integer.MAX_VALUE`). Each algorithm/size combination is run 5 times, the
   slowest run is discarded, and the remaining four are averaged. Results are
   printed as a table and a log-scale ASCII chart, and also saved to
   `performance_results.csv` for building the report chart.

## How to run

```
mvn compile exec:java
```

## Notes

- The full performance evaluation takes a long time. The O(n^2) sorts
  (insertion, bubble, selection) hit the 20-minute timeout on the larger
  data sets.
- A sort is given at most 20 minutes per run. If it is still going after
  that, it is reported as `> 20 m`. Once an algorithm times out at one size,
  the larger sizes are skipped and also reported as `> 20 m`, since these
  algorithms only get slower as n grows.
- In `performance_results.csv`, timed-out cells are written as `-1`.
