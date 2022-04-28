##################################################################
# File: questionnaire_plots.py
# Purpose: Generate SVG plots with participants' responses
##################################################################

import pandas as pd
import plot_likert
import numpy as np
from matplotlib import pyplot as plt
import re
import os

# File location
data_dir = "../data"
fig_dir = "../figs"

if not os.path.exists(fig_dir):
    os.makedirs(fig_dir)

# The questionnarie category to be processed
category_name = "TestReadability"
question_labels = []

if category_name == "TestProcess":
    question_labels = ["Q1.4", "Q1.3", "Q1.2", "Q1.1"]
elif category_name == "TestReadability":
    question_labels = ["Q2.4", "Q2.3", "Q2.2", "Q2.1"]
elif category_name == "InteractionProcess":
    question_labels = ["Q3.6", "Q3.5", "Q3.4", "Q3.3", "Q3.2", "Q3.1"]
else:
    question_labels = ["Q4.3", "Q4.2", "Q4.1"]


# Convert the data to the format required by the library
data_raw = pd.read_csv(data_dir + "/" + category_name + ".csv", true_values=["x"], index_col=False)

# Remove participant id
data_raw.drop("ID", axis=1, inplace=True)

# Each question expands 5 columns (question options)
num_questions = int(data_raw.shape[1]/5)
num_participants = data_raw.shape[0]

# Set the new column names (one per question)
questions_names = []
for i in range(1, num_questions+1):
    questions_names.append("Q" + str(i))

# Fill responses with the tag of the selected option (column name with true value)
responses = []
for i in range(0, num_participants):
    # Get the row i
    participant_responses = data_raw.loc[i:i,] 
    # Get the columns with true values
    participant_responses = participant_responses.loc[:,participant_responses.any()] 
    true_columns = participant_responses.columns
    # Remove ".1", ".2", etc. from column name (inserted by Pandas to avoid duplicate column names)
    true_columns = [re.sub(r"\.[123456]", "", s) for s in true_columns] 
    responses.append(true_columns)

# Create a data frame with the required structure
data_likert = pd.DataFrame(data=responses, columns=questions_names, copy=True)

# Save the file
file_name = data_dir + "/" + category_name + "-likert-responses.csv"
data_likert.to_csv(file_name, index=False)

# Likert plot

## Some customization
scale_5levels = ['Fully disagree', 'Partially disagree', 'Empty response', 'Partially agree', 'Fully agree']

## Plot with number of responses
plot = plot_likert.plot_likert(data_likert,plot_scale=scale_5levels,colors=plot_likert.colors.likert5,figsize=(12,6),xtick_interval=2)
plot.legend(loc='upper center', mode="expand", ncol=5, fontsize=12)
plot.xaxis.set_label_text('Number of responses', fontsize=12, fontweight="bold")
plot.tick_params(axis='both', which='major', labelsize=12)
plot.set_yticklabels(question_labels)
for tick in plot.yaxis.get_major_ticks():
        tick.label1.set_fontweight('bold')
plt.savefig(fig_dir + "/" + category_name + "-likert.svg")
