# InterEvo-TR

InterEvo-TR has been implemented as an extension of the open-source [EvoSuite](https://github.com/EvoSuite/evosuite) test case generation tool for Java classes, which allows incorporating readability assessment of generated test cases based on the tester's preferences.

### Interactive options

InterEvo-TR comes with a set of new parameters to adjust *when* and *how* interactions take place. Among others:

#### General settings
```
Interaction_process:               Specify whether the process is interactive (true) or not (false).
Interactive_dir:              	   Directory in which to output generated files during interaction.
Goal_selection_strategy:		   Specify the strategy to select the goals at interaction moments.
```

#### Adjustment of interaction time

```
Revise_frequency:                  Interaction will take place at regular intervals of this amount of search budget.
Max_times:              		   Maximum number of times that the user is willing to interact during the search.
Revise_after_percentage_coverage:  Specify when the interactive secondary objective will be enabled depending on the percentage of covered goals.
Max_targets_interaction_moment:	   Maximum number of goals that the user is willing to address in one interaction moment.
```

#### Solutions shown

```
Percentage_to_revise:              Percentage [0,1] of tests from the population size that the user is willing to revise at most.
```

#### Readability assessment

```
Max_readability_score:             Allowed readability scores in the range [0,MAX_READABILITY_SCORE].
Readability_threshold:             Readability score under which test cases will not be transferred to the preference archive.
P_preference_selection:  	       Probability [0,1] of selecting a test case from the preference archive to breed a new test case.
```

### Preparation

Executions enabling the interactive module can be done in the Eclipse IDE. To prepare the workspace:
- Download the code of InterEVO-TR. 
- Install the Eclipse IDE.
- In Eclipse, import the code of InterEVO-TR into the workspace as "Existing Maven project".
- Create your own execution file (filename ending in *SystemTest.java*) and adjust the interaction-related parameters as desired. An example executing a simple class `Stack.java` can be found in the file `InteractiveExampleSystemTest.java`.
- To start the execution, click *Run/Run* in the menu. 

### Execution

In Eclipse, interaction takes place through the console and the files to be revised are placed in the interactive directory.

Each new interaction is created in a separate subdirectory with name ```Interaction-X```, where ```X``` is the number of the individual interaction starting from 0. A single interaction consists of:

- A **coverage target** the search has already covered.
- A **set of test cases** covering that target to revise.

Once the test cases have been inspected, *readability scores* for them have to be entered through the console to resume the execution.
The best-valued test cases can be consulted in the *preference archive* (placed in the interactive directory).

### Final test suite

The final test suite includes the non-redundant best-valued test cases by the user, complemented with other test cases required to reach the same coverage achieved at the end of the search.