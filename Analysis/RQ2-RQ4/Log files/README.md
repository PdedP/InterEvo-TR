This directory contains:

- **overhead_combined**: overhead introduced by interaction in each execution.
- **goals_combined**: different goals and methods observed by participants.
- **interactions_analysis_combined**: analysis of the interactions of each participant's execution, including:
	- *Attemps of interaction*.
	- *Interactions performed*.
	- *Time spent per interaction*.
	- *Readability scores assigned*.
	- *Test cases revised per interaction*.
	
The following relevant data are saved for each interaction:
- *Generation*: generation when the interaction happened.
- *Selected*: number of selected test cases initially.
- *Minimized*: number of different minimizations after minimizing the selected test cases.
- *Archived*: number of those minimizations found in the readability archive.
- *Time*: time spent in the revision of the test cases shown.
- *ReadabilityScores*: scores assigned to each of the test cases shown.
- *Goal*: the target addressed in the interaction. 