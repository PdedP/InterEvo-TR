####################################################################################################
# File: readability_scores_comparison.py
# Purpose: Generate horizontal stacked bar plots with the participants' responses to the survey
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

# The questionnaire category to be processed
category_name = "Usefulness"
question_labels = []

if category_name == "TestProcess":
    question_labels = ["Q1.4", "Q1.3", "Q1.2", "Q1.1"]
elif category_name == "TestReadability":
    question_labels = ["Q2.4", "Q2.3", "Q2.2", "Q2.1"]
elif category_name == "InteractionProcess":
    question_labels = ["Q3.6", "Q3.5", "Q3.4", "Q3.3", "Q3.2", "Q3.1"]
else:
    question_labels = ["Q4.3", "Q4.2", "Q4.1"]

data = pd.read_csv(data_dir + "/" + category_name + "-likert-responses_v2.csv", index_col=False)
data.replace(to_replace=["Fully disagree", "Partially disagree", "Empty response", "Partially agree", "Fully agree"], 
            value=[1.0,2.0,2.5,3.0,4.0], inplace=True)

# Common elements
rating_scale = np.arange(0,5)
x_ticks_labels = question_labels
x_ticks = np.arange(1,len(x_ticks_labels)+1)

# List with the data values of each column 
ncols = data.shape[1]
count_1s = list()
count_2s = list()
count_3s = list()
count_4s = list()
count_no = list()

# Counts the number of answers for each option in each column (question)
# Reverse order to show Q1 at top of the plot when transposing
for i in range(ncols-1, -1, -1): 
    responses = data.iloc[:,i].values
    count_1s.append(np.count_nonzero(responses == 1))
    count_2s.append(np.count_nonzero(responses == 2))
    count_3s.append(np.count_nonzero(responses == 3))
    count_4s.append(np.count_nonzero(responses == 4))
    count_no.append(np.count_nonzero(responses == 2.5))

# Join data in a data frame
df_summary = pd.DataFrame([count_no, count_1s, count_2s, count_3s, count_4s])
df_summary = df_summary.transpose()
df_summary.columns = ['Empty response', 'Fully disagree', 'Partially disagree', 'Partially agree', 'Fully agree']

# Plot
colors = ['lightgray', '#d8a539', '#efe0c1', '#bde1dd', '#5ab4ac'] # from plot_likert.colors.likert5
plot = df_summary.plot.barh(stacked=True, color=colors)
plot.xaxis.set_label_text('Number of responses', fontsize=12, fontweight="bold")
plt.xticks(np.arange(0,41,2))
plot.tick_params(axis='both', which='major', labelsize=12)
plot.set_yticklabels(question_labels, fontweight="bold")
plot.legend(loc='upper center', mode="expand", ncol=5, fontsize=12)
plt.show()
#plt.savefig(fig_dir + "/" + category_name + "-stack.svg")

