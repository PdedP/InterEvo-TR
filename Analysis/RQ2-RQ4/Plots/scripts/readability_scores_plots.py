#######################################################################################
# File: readability_scores_plot.py
# Purpose: Generate a heatmap with the readability scores per interaction
#######################################################################################

import pandas as pd
import numpy as np
from matplotlib import pyplot as plt
import os
from image_annotated_heatmap import heatmap, annotate_heatmap

# File location
data_dir = "../data"
fig_dir = "../res"

if not os.path.exists(fig_dir):
    os.makedirs(fig_dir)

# Convert the data to the format required by the library
data_scores_total = pd.read_csv(data_dir + "/participant-scores-total.csv", index_col=False, dtype='float')

# Remove participant id
data_scores_total.drop("ID", axis=1, inplace=True)

# Do some changes in the data structure
# Invert the column order to draw rating from 0 (bottom) to 10 (top)
# Traspose the values to represent participants in the x axis (more space)
new_columns_order = ["Score"+str(i) for i in np.arange(10,-1,-1)]
data_invert = data_scores_total.reindex(columns=new_columns_order)
data = data_invert.values.transpose()

# Customize axes
col_labels = [str(i) for i in np.arange(1,33)] 
row_labels = [str(i) for i in np.arange(10,-1,-1)]

# Heatmap
fig, ax = plt.subplots()
im = heatmap(data, row_labels, col_labels, ax=ax, cmap="YlGn", cbarlabel="FF", col_title="Readability score", row_title="Participant")
texts = annotate_heatmap(im, valfmt="{x:.0f}")
fig.tight_layout()
# It's better to show in full screen and manually save to see all square regions
plt.show()
#plt.savefig(fig_dir + "/readability-scores-heatmap.svg")