####################################################################################################
# File: readability_scores_comparison.py
# Purpose: Generate a boxplot with the scores assigned by participants (InterEvo-TR vs EvoSuite)
####################################################################################################

import pandas as pd
import numpy as np
from matplotlib import pyplot as plt
import os

# File location
data_dir = "../data"
fig_dir = "../figs"

if not os.path.exists(fig_dir):
    os.makedirs(fig_dir)

# Convert the data to the format required by the library
data = pd.read_csv(data_dir + "/ReadabilityScoresComparison.csv", index_col=False)

# Boxplot
rating_scale = np.arange(0,11)
x_ticks_labels = data.columns
x_ticks = np.arange(1,len(x_ticks_labels)+1)

x = data['InterEvo-TR'].astype(dtype='float64').values
y = data['EvoSuite'].values

plt.clf()
plt.boxplot([x,y])
plt.xticks(ticks=x_ticks,labels=x_ticks_labels,fontsize=12, fontweight='bold')
plt.yticks(ticks=rating_scale)
plt.ylabel("Readability score", fontsize=12, fontweight='bold')
plt.savefig(fig_dir + "/ReadabilityScoresComparison.svg")
