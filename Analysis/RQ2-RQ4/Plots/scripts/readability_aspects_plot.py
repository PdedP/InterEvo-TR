#######################################################################################
# File: readability_aspects_plot.py
# Purpose: Generate a violin plot with the ratings of the readability aspects
#######################################################################################

import pandas as pd
import numpy as np
from matplotlib import pyplot as plt
import os
import re

# File location
data_dir = "../data"
fig_dir = "../res"

if not os.path.exists(fig_dir):
    os.makedirs(fig_dir)

# Convert the data to the format required by the library
data_raw = pd.read_csv(data_dir + "/readability-aspects.csv", true_values=["x"], index_col=False)

# Remove participant id
data_raw.drop("ID", axis=1, inplace=True)

# Each question expands 6 columns (5 values + No selected)
num_questions = int(data_raw.shape[1]/6)
num_participants = data_raw.shape[0]

# Set the new column names (one per readability aspect)
questions_names = []
for i in range(1, num_questions+1):
    questions_names.append("ReadabilityAspect" + str(i))

# Fill responses with the tag of the selected option (column name with true value)
responses = []
for i in range(0, num_participants):
    # Get the row i
    participant_responses = data_raw.loc[i:i,] 
    # Get the columns with true values
    participant_responses = participant_responses.loc[:,participant_responses.any()] 
    true_columns = participant_responses.columns
    # Remove OXV from column name (keep only the last characters -> value of the option or "no")
    true_columns = [re.sub(r"O[12345]V", "", s) for s in true_columns] 
    responses.append(true_columns)

# Create a data frame with the required structure
data_scores = pd.DataFrame(data=responses, columns=questions_names, copy=True)
data_scores.replace(to_replace="NO", value=np.NaN, inplace=True)

# Save the file
file_name = data_dir + "/readability-aspects-collapsed.csv"
data_scores.to_csv(file_name, index=False)

# Convert to float without NaN (not supported in boxplots/violin plots).
# I process each column independently because I do not want to remove only the row value containing the NaN value (not all row values)
data_aspect1 = pd.to_numeric(data_scores['ReadabilityAspect1'].dropna(axis=0), downcast="float").values
data_aspect2 = pd.to_numeric(data_scores['ReadabilityAspect2'].dropna(axis=0), downcast="float").values
data_aspect3 = pd.to_numeric(data_scores['ReadabilityAspect3'].dropna(axis=0), downcast="float").values
data_aspect4 = pd.to_numeric(data_scores['ReadabilityAspect4'].dropna(axis=0), downcast="float").values
data_aspect5  = pd.to_numeric(data_scores['ReadabilityAspect5'].dropna(axis=0), downcast="float").values

# Violin plot
rating_scale = np.arange(1,6)
x_ticks_labels = ['Var. Values', 'Arg. Methods', 'Test Length', 'Num. Class/Meth', 'Similarity']
x_ticks = np.arange(1,len(x_ticks_labels)+1)

## Plot
plt.violinplot([data_aspect1, data_aspect2, data_aspect3, data_aspect4, data_aspect5], rating_scale, widths=0.3, showmeans=True, showextrema=True, showmedians=True)
plt.xticks(ticks=x_ticks,labels=x_ticks_labels)
plt.yticks(ticks=rating_scale)
plt.tick_params(axis='x', which='major', labelsize=10)
plt.xlabel("Readability aspect", fontsize=12, fontweight="bold")
plt.ylabel("Rating", fontsize=12, fontweight="bold")
plt.savefig(fig_dir + "/readability-aspects-scores.svg")
