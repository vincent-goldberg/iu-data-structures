import csv
import matplotlib.pyplot as plt
import pandas as pd

# Globals
RESULTS_CSV_FP = "./outputs/performance_results.csv"
TIMEOUT = -1 # Indicates results timed out over 20 minutes

# =========================================================
# Plot results
# =========================================================

# Read the CSV into a dict of lists
algorithms = {}
with open(RESULTS_CSV_FP, "r") as f:
    reader = csv.reader(f)
    header = next(reader)
    sizes = [int(size) for size in header[1:]]      # Dataset size from header
    for row in reader:
        algorithms[row[0]] = [int(time) for time in row[1:]]  # Times for each sizels

# Plot one line per algorithm 
plt.figure(figsize=(10, 6))

for name, times in algorithms.items():
    xs, ys = [], []
    for size, ms in zip(sizes, times):
        if ms == TIMEOUT:
            continue  # Skip timeouts for plotting
        seconds = ms / 1000
        if seconds == 0:
            seconds = 0.0005 # "< 1 ms" -> 0.5 ms for log scale
        xs.append(size)
        ys.append(seconds)
    line, = plt.plot(xs, ys, marker='o', label=name)
    if TIMEOUT in times:
        plt.annotate("> 20 m", xy=(xs[-1], ys[-1]),
                     xytext=(8, 0), textcoords="offset points",
                     va="center", fontsize=9, color=line.get_color())


# Output graph
plt.xscale('log')
plt.xticks(sizes, ["1K", "10K", "100K", "1M", "10M"])
plt.minorticks_off()   # hide the leftover log minor ticks on x
plt.yscale('log')
plt.xlabel("Input size (n)")
plt.ylabel("Average time in seconds (log scale)")
plt.title("Average Sorting Runtimes")
plt.legend()
plt.grid(True, which="both", linestyle="--", alpha=0.4)

plt.tight_layout()
plt.savefig("./outputs/sorting_chart.png", dpi=200)     # save BEFORE show
plt.show()

# =========================================================
# Table results
# =========================================================

# Helper function to format times for display in the table and report
def format_time(ms):
    """Mirror the Java formatTime rules so report and console match."""
    if ms == -1:
        return "> 20 m"
    if ms == 0:
        return "< 1 ms"
    if ms < 1000:
        return f"{ms} ms"
    return f"{ms / 1000:.2f} s"

# Read and format 
df = pd.read_csv(RESULTS_CSV_FP, index_col="Algorithm")
formatted = df.map(format_time)                       # apply to every cell
formatted.columns = [f"{int(c):,}" for c in df.columns]  # 1000000 -> "1,000,000"

# ---- render as an image ----
fig, ax = plt.subplots(figsize=(9, 2.5))
ax.axis("off")     # no axes; the table IS the figure

table = ax.table(cellText=formatted.values,
                 rowLabels=formatted.index,
                 colLabels=formatted.columns,
                 cellLoc="center", loc="center")
table.scale(1, 1.6)            # stretch rows taller for readability

plt.title("Average Sorting Runtimes")
plt.savefig("./outputs/sorting_table.png", dpi=200, bbox_inches="tight")
plt.show()
