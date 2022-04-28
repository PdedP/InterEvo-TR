#######################################################################################
# File: time_plot.py
# Purpose: Generate a SVG plot to illustrate time spent on interaction
#######################################################################################

import pandas as pd
import numpy as np
from matplotlib import pyplot as plt
import os

# File location
data_dir = "../data"
fig_dir = "../figs"

if not os.path.exists(fig_dir):
    os.makedirs(fig_dir)

# Read the CSV with the time for each interaction and participant, last column is the average per interaction
data_raw = pd.read_csv(data_dir + "/" + "InteractionTimes.csv", index_col=False)

print(data_raw.head())

# Bar plot (average)
avg_time = data_raw['AVERAGE']
num_interaction = np.arange(1,11,1)
plt.bar(x=num_interaction, height=avg_time, color="#7C7C7C")
plt.xlabel("Interaction moment", fontsize=12, fontweight="bold")
plt.ylabel("Time (seconds)", fontsize=12, fontweight="bold")
plt.xticks(ticks=num_interaction)
plt.savefig(fig_dir + "/avg-interaction-time.svg")

# Get some statistics to draw line plots
upper_limits = []
lower_limits = []
num_rows = data_raw.shape[0]
for i in range(0, num_rows):
    # Get the row i (interaction moment i)
    interaction_times = data_raw.loc[i:i,].values
    # Max and min
    upper_limits.append(np.max(interaction_times))
    lower_limits.append(np.min(interaction_times))

# Line plot with area between upper and lower values
fig, ax = plt.subplots()
ax.plot(num_interaction, avg_time, color="#7C7C7C")
ax.fill_between(num_interaction, lower_limits, upper_limits, alpha=0.2, color="#D9D9D9")
plt.xlabel("Interaction", fontsize=12, fontweight="bold")
plt.ylabel("Time (seconds)", fontsize=12, fontweight="bold")
plt.xticks(ticks=num_interaction)
plt.yticks(ticks=np.arange(0,501,50))
plt.savefig(fig_dir + "/Interaction-time.svg")