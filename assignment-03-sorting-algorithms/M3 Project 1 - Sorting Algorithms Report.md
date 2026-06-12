# M3 Project 1: Sorting Algorithms - Performance Analysis Report

## Testing Results (Task 2)

All five algorithms were tested on two random 10-element lists. Each
screenshot shows the original unsorted list followed by the output of each
algorithm, with a PASS/FAIL check that verifies the result is in ascending
order.

![[test_list_1.png]]

![[test_list_2.png]]

Edge cases were also tested: an empty array, a single-element array, and an
array with duplicate values.

![[edge_case_empty.png]]

![[edge_case_single.png]]

![[edge_case_duplicates.png]]

## Test Setup

Each algorithm was timed on randomly generated int arrays (values up to
Integer.MAX_VALUE) at five sizes: 1,000 / 10,000 / 100,000 / 1,000,000 /
10,000,000. Every algorithm/size combination was run 5 times, the slowest
run was discarded as an outlier, and the remaining 4 runs were averaged.
Only the sort itself was timed (data generation excluded). Any run that
went past 20 minutes was cut off and recorded as "> 20 m". Once an
algorithm timed out at one size, the larger sizes were skipped and also
recorded as "> 20 m", since these algorithms only get slower as n grows.

## Results Table

| Algorithm | 1,000  | 10,000 | 100,000 | 1,000,000           | 10,000,000 |
|-----------|--------|--------|---------|---------------------|------------|
| Insertion | < 1 ms | 6 ms   | 374 ms  | 38.47 s             | > 20 m     |
| Bubble    | 1 ms   | 34 ms  | 7.43 s  | > 20 m              | > 20 m     |
| Selection | 1 ms   | 28 ms  | 3.18 s  | 1094.86 s (~18.2 m) | > 20 m     |
| Merge     | < 1 ms | 1 ms   | 10 ms   | 113 ms              | 1.29 s     |
| Quick     | < 1 ms | 1 ms   | 6 ms    | 77 ms               | 859 ms     |

The raw averages are in `performance_results.csv` (timed-out cells are
stored as -1 there).

Console output from the evaluation run, showing the live progress (each
dot is a completed cell, X is a timeout, -- is a skipped size) and the
final results table:

![[perf_run_progress.png]]

![[console_results_table.png]]

## Chart

![[sorting_chart.png]]

Both axes use a log scale so the fast and slow algorithms fit on one chart.
A line ends where the algorithm exceeded the 20 minute limit at the next
size. Times that measured under 1 ms are plotted at 0.5 ms since a log
scale can't show 0.

## Observations and Conclusions

The results split the five algorithms into two clear groups: the O(n^2)
sorts (insertion, bubble, selection) and the O(n log n) sorts (merge,
quick). On the log-log chart this shows up as two different slopes. Every
time n grows 10x, the quadratic sorts take roughly 100x longer (insertion
went from 374 ms at 100K to 38.47 s at 1M, about a 103x jump), while merge
and quick only take around 10x longer. That difference is what makes the
quadratic group fall off a cliff: bubble sort couldn't finish 1,000,000
numbers inside 20 minutes, and selection sort just barely made it at 18.2
minutes. Meanwhile quick sort handled 10,000,000 numbers in under a second.

A few more specific observations:

- Below about 10,000 elements, the algorithm choice barely matters. All
  five finished in 34 ms or less, so for small inputs a simple sort is
  perfectly fine.
- Bubble sort was the worst of the quadratic group at every size, since it
  does a swap for almost every comparison instead of one swap per pass
  (selection) or one shift chain per element (insertion).
- Quick sort beat merge sort at every size (859 ms vs 1.29 s at 10M, about
  1.5x faster). They're both O(n log n), so the gap comes from constant
  factors: quick sort works in place while my merge sort allocates a temp
  array at every merge step, which costs time in copying and memory
  allocation.

Practical takeaways: for any data set that could grow large, an O(n log n)
sort is the only reasonable option, because the quadratic sorts go from
"fine" to "won't finish today" within two orders of magnitude. Quick sort
is the best general-purpose pick here, which matches why most standard
libraries build on it. Merge sort is worth the small extra cost when you
need a stable sort or a guaranteed O(n log n) worst case (quick sort can
degrade to O(n^2) on bad pivot choices, even though that never happened on
this random data). Insertion sort still has a niche for very small or
nearly-sorted inputs, which is why real libraries use it as the base case
inside their fancier sorts. Bubble sort and selection sort mostly serve as
teaching examples; nothing in these results gives a reason to use either
in practice.
