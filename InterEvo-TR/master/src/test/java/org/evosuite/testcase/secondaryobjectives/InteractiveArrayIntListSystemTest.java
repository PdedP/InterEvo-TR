package org.evosuite.testcase.secondaryobjectives;

import org.evosuite.EvoSuite;
import org.evosuite.Properties;
import org.evosuite.Properties.Criterion;
import org.evosuite.Properties.FrequencyStrategy;
import org.evosuite.Properties.GoalSelectionStrategy;
import org.evosuite.Properties.SecondaryObjective;
import org.evosuite.SystemTestBase;
import org.evosuite.utils.Randomness;
import org.junit.Assert;
import org.junit.Test;

import com.org.apache.commons.collections.primitives.ArrayIntList;

public class InteractiveArrayIntListSystemTest extends SystemTestBase {

	@Test
	public void testArrayIntListClass() {
		
		/*** TO BE CONFIGURED ****/
		
		Properties.INTERACTIVE_DIR = "";	//DIRECTORY USED FOR INTERACTION AND OUTPUT  	
		
		/*** INTERACTIVE: GENERAL ***/
		
		Properties.INTERACTIVE_PROCESS 		= true;
		Properties.INTERACTION_STATISTICS 	= true;
		Properties.SIMULATED_INTERACTIVITY 	= false;
		
		Properties.SECONDARY_OBJECTIVE = new SecondaryObjective[] { Properties.SecondaryObjective.INTERACTIVE, Properties.SecondaryObjective.TOTAL_LENGTH};
		
		Properties.OUTPUT_DIR = Properties.INTERACTIVE_DIR;
		Properties.CLASS_TYPE_DIR = Properties.INTERACTIVE_DIR;
		Properties.ROOT_DIRECTORY = Properties.INTERACTIVE_DIR;
		
		/*** INTERACTIVE OPTIONS ***/
		
		Properties.ENABLE_SECONDARY_OBJECTIVE_AFTER 	= 10;
		Properties.REVISE_AFTER_PERCENTAGE_OF_COVERAGE 	= 50;
		
		Properties.REVISE_PERIODICALLY 	= true;
		Properties.FREQUENCY_STRATEGY 	= FrequencyStrategy.FIXED;
		Properties.FREQUENCY 			= 200;
		
		Properties.PERCENTAGE_TO_REVISE 			= 0.08;	
		Properties.MAX_TIMES_TO_SORT_REVISE 		= 10;
		Properties.WHEN_TO_REVISE = Properties.Interaction.SORT;
		Properties.MAX_NUMBER_GOALS_IN_INTERACTION 	= 3;
				
		Properties.REVISIT_CANDIDATES 			= false;
		Properties.MINIMUM_READABILITY_VALUE 	= 3;
		
		Properties.P_PREFERENCE_SELECTION = 0.2;
		Properties.GOAL_SELECTION_STRATEGY = GoalSelectionStrategy.DIFFERENT_METHODS;
		Properties.RESTRICTED_GOALS = false;
		Properties.RANDOMIZED_GOALS = false;
		
		/*** EVOSUITE GENERAL ***/

		Properties.CRITERION = new Criterion[] {
				Criterion.LINE, Criterion.BRANCH, Criterion.WEAKMUTATION };
		
		Properties.STOPPING_CONDITION = Properties.StoppingCondition.MAXGENERATIONS;
		Properties.POPULATION = 50;
		
		Properties.SEARCH_BUDGET 		= 1000;	//120 seconds
		Properties.GLOBAL_TIMEOUT 		= 2400;	//40 minutes		
		Properties.MINIMIZATION_TIMEOUT = 300;		
		
		
		/*** OTHER ***/
		
		Properties.ASSERTIONS 			= true;
		Properties.ENABLE_SECONDARY_OBJECTIVE_STARVATION = false;
		Properties.REPLACE_SYSTEM_IN 	= false;
		Properties.EXECUTION_NUMBER 	= 0;
		
		
		/*** EXECUITON ***/
		
		Randomness.setSeed(0);

		EvoSuite evosuite = new EvoSuite();

		String targetClass = ArrayIntList.class.getCanonicalName();
		Properties.TARGET_CLASS = targetClass;

		String[] command = new String[] {
				"-generateMOSuite", "-Dalgorithm=DynaMOSA", "-Dstrategy=MOSuite", "-class", targetClass
		};
		
		Object result = evosuite.parseCommandLine(command);
		Assert.assertNotNull(result);
	}	
}