/*
 * Copyright (C) 2010-2018 Gordon Fraser, Andrea Arcuri and EvoSuite
 * contributors
 *
 * This file is part of EvoSuite.
 *
 * EvoSuite is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published
 * by the Free Software Foundation, either version 3.0 of the License, or
 * (at your option) any later version.
 *
 * EvoSuite is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with EvoSuite. If not, see <http://www.gnu.org/licenses/>.
 */
package org.evosuite.ga.metaheuristics.mosa.structural;

import org.apache.commons.lang3.tuple.Pair;
import org.evosuite.ClientProcess;
import org.evosuite.Properties;
import org.evosuite.Properties.Criterion;
import org.evosuite.TestGenerationContext;
import org.evosuite.TestSuiteGenerator;
import org.evosuite.TestSuiteGeneratorHelper;
import org.evosuite.coverage.branch.BranchCoverageFactory;
import org.evosuite.coverage.branch.BranchCoverageGoal;
import org.evosuite.coverage.branch.BranchCoverageTestFitness;
import org.evosuite.coverage.cbranch.CBranchTestFitness;
import org.evosuite.coverage.exception.ExceptionCoverageFactory;
import org.evosuite.coverage.exception.ExceptionCoverageHelper;
import org.evosuite.coverage.exception.ExceptionCoverageTestFitness;
import org.evosuite.coverage.exception.TryCatchCoverageTestFitness;
import org.evosuite.coverage.io.input.InputCoverageTestFitness;
import org.evosuite.coverage.io.output.OutputCoverageTestFitness;
import org.evosuite.coverage.line.LineCoverageTestFitness;
import org.evosuite.coverage.method.MethodCoverageTestFitness;
import org.evosuite.coverage.method.MethodNoExceptionCoverageTestFitness;
import org.evosuite.coverage.mutation.StrongMutationTestFitness;
import org.evosuite.coverage.mutation.WeakMutationTestFitness;
import org.evosuite.coverage.statement.StatementCoverageTestFitness;
import org.evosuite.ga.FitnessFunction;
import org.evosuite.ga.metaheuristics.GeneticAlgorithm;
import org.evosuite.graphs.cfg.BytecodeInstruction;
import org.evosuite.graphs.cfg.BytecodeInstructionPool;
import org.evosuite.graphs.cfg.ControlDependency;
import org.evosuite.setup.CallContext;
import org.evosuite.setup.DependencyAnalysis;
import org.evosuite.setup.callgraph.CallGraph;
import org.evosuite.testcase.ConstantInliner;
import org.evosuite.testcase.TestCase;
import org.evosuite.testcase.TestChromosome;
import org.evosuite.testcase.TestFitnessFunction;
import org.evosuite.testcase.execution.ExecutionResult;
import org.evosuite.testcase.execution.ExecutionTrace;
import org.evosuite.testcase.execution.TestCaseExecutor;
import org.evosuite.testcase.secondaryobjectives.ArchiveData;
import org.evosuite.testcase.secondaryobjectives.InteractionData;
import org.evosuite.testcase.secondaryobjectives.InteractiveEvaluationSecondaryObjective;
import org.evosuite.testsuite.TestSuiteChromosome;
import org.evosuite.utils.ArrayUtil;
import org.evosuite.utils.LoggingUtils;
import org.evosuite.utils.Randomness;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

/**
 * A class for managing multiple coverage targets simultaneously.
 */
public class MultiCriteriaManager extends StructuralGoalManager implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(MultiCriteriaManager.class);

    private static final long serialVersionUID = 8161137239404885564L;

    protected BranchFitnessGraph graph;

    protected Map<BranchCoverageTestFitness, Set<TestFitnessFunction>> dependencies;

    /**
     * Maps branch IDs to the corresponding fitness function, only considering branches we want to
     * take.
     */
    protected final Map<Integer, TestFitnessFunction> branchCoverageTrueMap = new LinkedHashMap<>();

    /**
     * Maps branch IDs to the corresponding fitness function, only considering the branches we do
     * <em>not</em> want to take.
     */
    protected final Map<Integer, TestFitnessFunction> branchCoverageFalseMap = new LinkedHashMap<>();

    /**
     * Maps branch IDs to the corresponding fitness function, only considering root branches of
     * methods (i.e. the goal is to just invoke the method).
     */
    private final Map<String, TestFitnessFunction> branchlessMethodCoverageMap = new LinkedHashMap<>();

	/*ADDED*/
	/**
	 *  Number of sort interactions performed
	 */
	private int numberSortInteractions;


	/**
	 *  Set of goals that have been the target of an interaction
	 */
	private  Set<TestFitnessFunction> goalsInteracted = new HashSet<TestFitnessFunction>();

    /**
     * Creates a new {@code MultiCriteriaManager} with the given list of targets. The targets are
     * encoded as fitness functions, which are expected to be minimization functions.
     *
     * @param targets The targets to cover encoded as minimization functions
     */
    public MultiCriteriaManager(List<TestFitnessFunction> targets) {
        super(targets);

        // initialize the dependency graph among branches
        this.graph = getControlDependencies4Branches(targets);

        // initialize the dependency graph between branches and other coverage targets (e.g., statements)
        // let's derive the dependency graph between branches and other coverage targets (e.g., statements)
        for (Criterion criterion : Properties.CRITERION) {
            switch (criterion) {
                case BRANCH:
                    break; // branches have been handled by getControlDepencies4Branches
                case EXCEPTION:
                    break; // exception coverage is handled by calculateFitness
                case LINE:
                    addDependencies4Line();
                    break;
                case STATEMENT:
                    addDependencies4Statement();
                    break;
                case WEAKMUTATION:
                    addDependencies4WeakMutation();
                    break;
                case STRONGMUTATION:
                    addDependencies4StrongMutation();
                    break;
                case METHOD:
                    addDependencies4Methods();
                    break;
                case INPUT:
                    addDependencies4Input();
                    break;
                case OUTPUT:
                    addDependencies4Output();
                    break;
                case TRYCATCH:
                    addDependencies4TryCatch();
                    break;
                case METHODNOEXCEPTION:
                    addDependencies4MethodsNoException();
                    break;
                case CBRANCH:
                    addDependencies4CBranch();
                    break;
                default:
                    LoggingUtils.getEvoLogger().error("The criterion {} is not currently supported in DynaMOSA", criterion.name());
            }
        }

        // initialize current goals
        this.currentGoals.addAll(graph.getRootBranches());
    }

    private void addDependencies4TryCatch() {
        logger.debug("Added dependencies for Try-Catch");
        for (FitnessFunction<TestChromosome> ff : this.getUncoveredGoals()) {
            if (ff instanceof TryCatchCoverageTestFitness) {
                TryCatchCoverageTestFitness stmt = (TryCatchCoverageTestFitness) ff;
                BranchCoverageTestFitness branch = new BranchCoverageTestFitness(stmt.getBranchGoal());
                this.dependencies.get(branch).add(stmt);
            }
        }
    }

    private void initializeMaps(Set<TestFitnessFunction> set) {
        for (TestFitnessFunction ff : set) {
            BranchCoverageTestFitness goal = (BranchCoverageTestFitness) ff;

            // Skip instrumented branches - we only want real branches
            if (goal.getBranch() != null && goal.getBranch().isInstrumented()) {
                continue;
            }

            if (goal.getBranch() == null) { // the goal is to call the method at hand
                branchlessMethodCoverageMap.put(goal.getClassName() + "." + goal.getMethod(), ff);
            } else if (goal.getBranchExpressionValue()) { // we want to take the given branch
                branchCoverageTrueMap.put(goal.getBranch().getActualBranchId(), ff);
            } else { // we don't want to take the given branch
                branchCoverageFalseMap.put(goal.getBranch().getActualBranchId(), ff);
            }
        }
    }

    private void addDependencies4Output() {
        logger.debug("Added dependencies for Output");
        for (TestFitnessFunction ff : this.getUncoveredGoals()) {
            if (ff instanceof OutputCoverageTestFitness) {
                OutputCoverageTestFitness output = (OutputCoverageTestFitness) ff;
                ClassLoader loader = TestGenerationContext.getInstance().getClassLoaderForSUT();
                BytecodeInstructionPool pool = BytecodeInstructionPool.getInstance(loader);
                if (pool.getInstructionsIn(output.getClassName(), output.getMethod()) == null) {
                    this.currentGoals.add(ff);
                    continue;
                }
                for (BytecodeInstruction instruction : pool.getInstructionsIn(output.getClassName(), output.getMethod())) {
                    if (instruction.getBasicBlock() != null) {
                        Set<ControlDependency> cds = instruction.getBasicBlock().getControlDependencies();
                        if (cds.size() == 0) {
                            this.currentGoals.add(ff);
                        } else {
                            for (ControlDependency cd : cds) {
                                BranchCoverageTestFitness fitness = BranchCoverageFactory.createBranchCoverageTestFitness(cd);
                                this.dependencies.get(fitness).add(ff);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * This methods derive the dependencies between {@link InputCoverageTestFitness} and branches.
     * Therefore, it is used to update 'this.dependencies'
     */
    private void addDependencies4Input() {
        logger.debug("Added dependencies for Input");
        for (TestFitnessFunction ff : this.getUncoveredGoals()) {
            if (ff instanceof InputCoverageTestFitness) {
                InputCoverageTestFitness input = (InputCoverageTestFitness) ff;
                ClassLoader loader = TestGenerationContext.getInstance().getClassLoaderForSUT();
                BytecodeInstructionPool pool = BytecodeInstructionPool.getInstance(loader);
                if (pool.getInstructionsIn(input.getClassName(), input.getMethod()) == null) {
                    this.currentGoals.add(ff);
                    continue;
                }
                for (BytecodeInstruction instruction : pool.getInstructionsIn(input.getClassName(), input.getMethod())) {
                    if (instruction.getBasicBlock() != null) {
                        Set<ControlDependency> cds = instruction.getBasicBlock().getControlDependencies();
                        if (cds.size() == 0) {
                            this.currentGoals.add(ff);
                        } else {
                            for (ControlDependency cd : cds) {
                                BranchCoverageTestFitness fitness = BranchCoverageFactory.createBranchCoverageTestFitness(cd);
                                this.dependencies.get(fitness).add(ff);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * This methods derive the dependencies between {@link MethodCoverageTestFitness} and branches.
     * Therefore, it is used to update 'this.dependencies'
     */
    private void addDependencies4Methods() {
        logger.debug("Added dependencies for Methods");
        for (BranchCoverageTestFitness branch : this.dependencies.keySet()) {
            MethodCoverageTestFitness method = new MethodCoverageTestFitness(branch.getClassName(), branch.getMethod());
            this.dependencies.get(branch).add(method);
        }
    }

    /**
     * This methods derive the dependencies between {@link MethodNoExceptionCoverageTestFitness} and branches.
     * Therefore, it is used to update 'this.dependencies'
     */
    private void addDependencies4MethodsNoException() {
        logger.debug("Added dependencies for MethodsNoException");
        for (BranchCoverageTestFitness branch : this.dependencies.keySet()) {
            MethodNoExceptionCoverageTestFitness method = new MethodNoExceptionCoverageTestFitness(branch.getClassName(), branch.getMethod());
            this.dependencies.get(branch).add(method);
        }
    }

    /**
     * This methods derive the dependencies between {@link CBranchTestFitness} and branches.
     * Therefore, it is used to update 'this.dependencies'
     */
    private void addDependencies4CBranch() {
        logger.debug("Added dependencies for CBranch");
        CallGraph callGraph = DependencyAnalysis.getCallGraph();
        for (BranchCoverageTestFitness branch : this.dependencies.keySet()) {
            for (CallContext context : callGraph.getMethodEntryPoint(branch.getClassName(), branch.getMethod())) {
                CBranchTestFitness cBranch = new CBranchTestFitness(branch.getBranchGoal(), context);
                this.dependencies.get(branch).add(cBranch);
                logger.debug("Added context branch: " + cBranch);
            }
        }
    }

    /**
     * This methods derive the dependencies between {@link WeakMutationTestFitness} and branches.
     * Therefore, it is used to update 'this.dependencies'
     */
    private void addDependencies4WeakMutation() {
        logger.debug("Added dependencies for Weak-Mutation");
        for (TestFitnessFunction ff : this.getUncoveredGoals()) {
            if (ff instanceof WeakMutationTestFitness) {
                WeakMutationTestFitness mutation = (WeakMutationTestFitness) ff;
                Set<BranchCoverageGoal> goals = mutation.getMutation().getControlDependencies();
                if (goals.size() == 0) {
                    this.currentGoals.add(ff);
                } else {
                    for (BranchCoverageGoal goal : goals) {
                        BranchCoverageTestFitness fitness = new BranchCoverageTestFitness(goal);
                        this.dependencies.get(fitness).add(ff);
                    }
                }
            }
        }
    }

    /**
     * This methods derive the dependencies between {@link org.evosuite.coverage.mutation.StrongMutationTestFitness} and branches.
     * Therefore, it is used to update 'this.dependencies'
     */
    private void addDependencies4StrongMutation() {
        logger.debug("Added dependencies for Strong-Mutation");
        for (TestFitnessFunction ff : this.getUncoveredGoals()) {
            if (ff instanceof StrongMutationTestFitness) {
                StrongMutationTestFitness mutation = (StrongMutationTestFitness) ff;
                Set<BranchCoverageGoal> goals = mutation.getMutation().getControlDependencies();
                if (goals.size() == 0) {
                    this.currentGoals.add(ff);
                } else {
                    for (BranchCoverageGoal goal : goals) {
                        BranchCoverageTestFitness fitness = new BranchCoverageTestFitness(goal);
                        this.dependencies.get(fitness).add(ff);
                    }
                }
            }
        }
    }

    /**
     * This methods derive the dependencies between  {@link LineCoverageTestFitness} and branches.
     * Therefore, it is used to update 'this.dependencies'
     */
    private void addDependencies4Line() {
        logger.debug("Added dependencies for Lines");
        for (TestFitnessFunction ff : this.getUncoveredGoals()) {
            if (ff instanceof LineCoverageTestFitness) {
                LineCoverageTestFitness line = (LineCoverageTestFitness) ff;
                ClassLoader loader = TestGenerationContext.getInstance().getClassLoaderForSUT();
                BytecodeInstructionPool pool = BytecodeInstructionPool.getInstance(loader);
                BytecodeInstruction instruction = pool.getFirstInstructionAtLineNumber(line.getClassName(), line.getMethod(), line.getLine());
                Set<ControlDependency> cds = instruction.getControlDependencies();
                if (cds.size() == 0)
                    this.currentGoals.add(ff);
                else {
                    for (ControlDependency cd : cds) {
                        BranchCoverageTestFitness fitness = BranchCoverageFactory.createBranchCoverageTestFitness(cd);
                        this.dependencies.get(fitness).add(ff);
                    }
                }
            }
        }
    }

    /**
     * This methods derive the dependencies between  {@link StatementCoverageTestFitness} and branches.
     * Therefore, it is used to update 'this.dependencies'
     */
    private void addDependencies4Statement() {
        logger.debug("Added dependencies for Statements");
        for (TestFitnessFunction ff : this.getUncoveredGoals()) {
            if (ff instanceof StatementCoverageTestFitness) {
                StatementCoverageTestFitness stmt = (StatementCoverageTestFitness) ff;
                if (stmt.getBranchFitnesses().size() == 0)
                    this.currentGoals.add(ff);
                else {
                    for (BranchCoverageTestFitness branch : stmt.getBranchFitnesses()) {
                        this.dependencies.get(branch).add(stmt);
                    }
                }
            }
        }
    }

    //ADDED
    /**
     * This method returns the list of covered goals.
     */
    public List<TestFitnessFunction> goalOrdering (){
    	
    	List<TestFitnessFunction> covered_goals_selected = new ArrayList<>();
    	covered_goals_selected.addAll(this.archive.newlyAdded);
    	
    	//Is it randomized? Depending on the case, shuffle or reverse. 
    	if(Properties.RANDOMIZED_GOALS) {
    		Collections.shuffle(covered_goals_selected);
    	}
    	else {
    		Collections.reverse(covered_goals_selected);
    	}
    	
    	return covered_goals_selected;
    }
    
    //ADDED
    /**
     * This method prepares and execute interactions. It selects the goals to address and the test cases to be shown.
     * After that, it calls updatePreferenceArchive to update the preference archive, if required. 
     * 
     * @param population 
     */
    public void executeInteraction(List<TestChromosome> population) {

    	this.archivePreferences.setHasBeenUpdated(false);
    	
    	//Selection of goals
      	List<TestFitnessFunction> covered_goals_selected = new ArrayList<>();
      	covered_goals_selected = goalOrdering();
    	
    	//Used for GoalSelectionStrategy DIFFERENT_METHODS
    	List<String> methods = new ArrayList<>();
    	String targetMethod = "";
    	
    	//Main loop
    	int countRealInteractions = 0;	//to count the number of real interactions with the tester (useful to compare it with MAX_NUMBER_GOALS_IN_INTERACTION)
    	for (TestFitnessFunction f : covered_goals_selected) {
    		
    		if (Properties.MAX_TIMES_TO_SORT_REVISE > numberSortInteractions && countRealInteractions < Properties.MAX_NUMBER_GOALS_IN_INTERACTION) {
    			
    			if(Properties.GOAL_SELECTION_STRATEGY == Properties.GoalSelectionStrategy.ONLYWEAKMUTATION && !(f instanceof WeakMutationTestFitness)) {
    				continue;
    			}
    			else if(Properties.GOAL_SELECTION_STRATEGY == Properties.GoalSelectionStrategy.DIFFERENT_METHODS) { 
    				targetMethod = f.getTargetMethod() + f.getTargetClass();
    				if(methods.contains(targetMethod)) {
    					continue;
    				}
    			}
				
    			long startCompleteTime = System.currentTimeMillis();
				
    			//Retrieve the best test so far for this target in the coverage archive
    			TestChromosome bestInCoverageArchive = this.archive.getSolution(f); 
    			double bestFitness = bestInCoverageArchive.getFitness(f);
				
    			//Find the tests with the same fitness
    			int countCandidateTests = 0;
    			List<Integer> candidateTests = new ArrayList<>();
	        	
	        	int counter = 0;
	        	for (TestChromosome test : population) {
	        		//Count those tests covering the goal
	        		if(f.getFitness(test) == bestFitness) { 
	        			countCandidateTests++;
	        			candidateTests.add(counter);
	        		}
	        		counter++;
	        	}
	        	
	        	//If countCandidateTests is 0, do not continue
	        	if(countCandidateTests == 0)
	        		continue;
	        	
    			//Select randomly tests from the list of candidates according to the percentage of candidates to revise
	        	List<TestChromosome> selectedTests = new ArrayList<>();
	        	
	        	int sizeSet = (int) (Properties.POPULATION * Properties.PERCENTAGE_TO_REVISE);
    			// In principle, we should try to provide at least 2 to value
    			if(sizeSet < 2) sizeSet = 2;
				
    			//Check whether a test already exist for this target in the preference archive
    			TestChromosome bestInPreferenceArchive = this.archivePreferences.getSolution(f);
    			if(bestInPreferenceArchive == null) {	//Goal not revised. bestInCoverageArchive needs to be added (we reserve one position).
        			sizeSet--;
        		}
					
    			// Two cases: the number of tests tied is lower or equal than the size of candidates to revise => put them all in selectedTests
    			//			  the number of tests tied is greater than the size of candidates to revise => random selection of tests
    			if(countCandidateTests <= sizeSet) {
    				for(int i = 0; i < countCandidateTests; i++) {
    					selectedTests.add(population.get(candidateTests.get(i)));
    				}
    			}
    			else {
    				List<Integer> alreadySelected = new ArrayList<>(); 
    				for(int i = 0; i < sizeSet; i++) {
    					boolean newRval = false;
    					while (!newRval) {
    						int rval = Randomness.nextInt(0,countCandidateTests);
    						
    						if(!alreadySelected.contains(rval)) {
    							selectedTests.add(population.get(candidateTests.get(rval)));
    							alreadySelected.add(rval);
    							newRval = true;
    						}
    					}
    				}
    			}
	        	
    			if(bestInPreferenceArchive == null) {
        			selectedTests.add(bestInCoverageArchive);	//Finally, add the test in the coverage archive (if not already revised)
        		}
	        	
    			//Reset the values of interaction data
    			InteractionData id = (InteractiveEvaluationSecondaryObjective.getIData()).clone();
    			InteractiveEvaluationSecondaryObjective.setIData(id);
    			InteractiveEvaluationSecondaryObjective.getIData().setSelected(selectedTests.size());
    			InteractiveEvaluationSecondaryObjective.getIData().setFitness(f);

    			//The readability should be valued when:
    			// - At least two candidates are tied (selectedTests.size() > 1); or
    			// - There is only one candidate to be valued, which will be compared with the one in the preference archive
	        	if(selectedTests.size() > 1 || selectedTests.size() == 1 && bestInPreferenceArchive != null) {
	        		
	        		InteractiveEvaluationSecondaryObjective ieso = new InteractiveEvaluationSecondaryObjective();
	        		
	        		InteractiveEvaluationSecondaryObjective.setNewRealInteractions(false);
	        		TestChromosome moreReadable = ieso.setReadability(selectedTests, f, numberSortInteractions, bestInPreferenceArchive);
		    		
		    		if(InteractiveEvaluationSecondaryObjective.isNewRealInteractions()) {
		    			//increase the number of total interactions, real interactions and add the goal to goalsInteracted
		    			numberSortInteractions++;
		    			countRealInteractions++;
		    			goalsInteracted.add(f);
		    			
		    			if(numberSortInteractions == Properties.MAX_TIMES_TO_SORT_REVISE) {
		    				PrintStream ps = System.out;
		    				ps.println("\n >>> Maximum number of interactions reached. Please, wait until the execution ends.");
		    				LoggingUtils.getEvoLogger().info(ClientProcess.getPrettyPrintIdentifier() + ">>> Maximum number of interactions reached. Please, wait until the execution ends.\n");
		    			}
		    			
		    			if(Properties.GOAL_SELECTION_STRATEGY == Properties.GoalSelectionStrategy.DIFFERENT_METHODS) { 
		    				methods.add(targetMethod);
		    			}
		    		}

		    		if(moreReadable != null) {
		    			//The test case will be taken into account when sufficiently readable
		    			if(moreReadable.getReadabilityValue() >= Properties.MINIMUM_READABILITY_VALUE) {		
		    				TestChromosome.enableFirstSecondaryObjective();
		    				updateArchivePreferences(f, moreReadable);
		    				TestChromosome.disableFirstSecondaryObjective();
		    			}
		    		}
	        	}
	        	else {
	    			InteractiveEvaluationSecondaryObjective.getIData().setTie(false);
	    		}
	        	
	        	long completeTime = (System.currentTimeMillis() - startCompleteTime);
    			InteractiveEvaluationSecondaryObjective.getIData().setCompleteTime(completeTime);
				
    			//Save InteractionData
    			InteractiveEvaluationSecondaryObjective.getInteractions().add(InteractiveEvaluationSecondaryObjective.getIData());
    		}
    	}
    }
    
    // ADDED
    public void updateArchivePreferences(TestFitnessFunction f, TestChromosome c) {
    	
    	//Add to the preferences archive
    	this.archivePreferences.updateArchive(f, c, 0);		//note: the fitness is not relevant at this step => 0 	
    }
    
    // ADDED
    /**
     * This method shows the preference archive when it has been updated.
     */public void showArchivePreferences() {
	
		if(this.archivePreferences.hasBeenUpdated()) {
		
			TestSuiteChromosome suite = new TestSuiteChromosome();
			this.archivePreferences.getSolutions().forEach(suite::addTest);
			TestSuiteGenerator.writeJUnitTestsAndCreateResult(suite, "_ES_preferences_Test", true, Properties.INTERACTIVE_DIR);
			
			PrintStream ps = System.out;
			LoggingUtils.getEvoLogger().info("\n * The archive with your preferences has been updated and can be consulted in this folder: " + Properties.INTERACTIVE_DIR + "\n");	
			ps.println("\n * The archive with your preferences has been updated and can be consulted in this folder: " + Properties.INTERACTIVE_DIR);
		}
		else {
			PrintStream ps = System.out;
			if(Properties.MAX_TIMES_TO_SORT_REVISE > numberSortInteractions){
				ps.println("Execution still in progress. Please, wait until the execution ends or new interactions are required.");
				LoggingUtils.getEvoLogger().info(ClientProcess.getPrettyPrintIdentifier() + "Execution still in progress. Please, wait until the execution ends or new interactions are required.\n");
			}
			else {
				ps.println("Execution still in progress. Please, wait until the execution ends.");
				LoggingUtils.getEvoLogger().info(ClientProcess.getPrettyPrintIdentifier() + "Execution still in progress. Please, wait until the execution ends.\n");	
			}
		}
	}
	
	// ADDED
    /**
     * This method outputs the content of the coverage archive 
     * 
     *  For experiments
     */
	public void writeArchiveMinimized(){
				
		for(TestFitnessFunction f : this.archive.getCoveredTargets()) {
			org.evosuite.testcase.TestCaseMinimizer minimizer = new org.evosuite.testcase.TestCaseMinimizer(f);
			ConstantInliner inliner = new ConstantInliner();
			
			//Create a clone and minimize
			TestChromosome copy = this.archive.getSolution(f).clone();
			inliner.inline(copy);
			
			Properties.TEST_ARCHIVE = false;
			boolean successMinimizing = minimizer.minimize(copy);
			Properties.TEST_ARCHIVE = true;
			
			if(successMinimizing) {
				if (Properties.ASSERTIONS) {		
					TestSuiteGeneratorHelper.addAssertionsInteractive(copy.getTestCase());	
				}
				this.archive.whenAddedToArchive.get(f).setTc(copy);
			}
		}
		
		try {
			File f = new File(Properties.CLASS_TYPE_DIR + File.separator + "archive_statistics.csv");
			BufferedWriter out = new BufferedWriter(new FileWriter(f, true));
			StringBuilder r = new StringBuilder();
			if (f.length() == 0L) {
				//HEADER
				r.append("Generation,LengthLines,LengthCharacters,Target,Method\n");
			}
		
			List<ArchiveData> orderedByIteration = new ArrayList<>();
			for(Entry<TestFitnessFunction, ArchiveData> a : this.archive.whenAddedToArchive.entrySet()) {
				orderedByIteration.add(a.getValue());
			}
			
			orderedByIteration = orderedByIteration.stream().sorted(
	    			Comparator.comparing(ArchiveData::getIteration)).collect(Collectors.toList());
			
			Properties.INTERACTIVE_PROCESS = false;
			for(ArchiveData entry: orderedByIteration) {
				r.append(entry.getIteration());
				r.append(",");
				String testCase = entry.getTc().getTestCase().toCode(entry.getTc().getLastExecutionResult().getCopyOfExceptionMapping());
				r.append(testCase.split(System.getProperty("line.separator")).length);
				r.append(",");
				r.append(testCase.length());
				r.append(",");
				r.append(entry.getFitness());
				r.append(",");
				r.append(entry.getMethod());
				r.append("\n");
			}
			Properties.INTERACTIVE_PROCESS = true;
			
			out.write(r.toString());
			out.close();

		} catch (IOException e) {
			logger.warn("Error while writing statistics of archive: " + e.getMessage());
		}
		
	}
    

    /**
     * Calculates the fitness of the given test chromosome w.r.t. the current set of goals. To this
     * end, the test chromosome is executed, it's execution trace recorded and the resulting
     * coverage analyzed. This information is further used to update the set of current
     * goals (as given by {@link MultiCriteriaManager#getCurrentGoals()} and the population of the
     * archive.
     *
     * @param c the chromosome whose fitness to calculate (must be a {@link TestChromosome})
     */
    @Override
    public void calculateFitness(TestChromosome c, GeneticAlgorithm<TestChromosome> ga) {
        // Run the test and record the execution result.
        TestCase test = c.getTestCase();
        ExecutionResult result = TestCaseExecutor.runTest(test);
        c.setLastExecutionResult(result);
        c.setChanged(false);

        // If the test failed to execute properly, or if the test does not cover anything,
        // it means none of the current gaols could be reached.
        if (result.hasTimeout() || result.hasTestException() || result.getTrace().getCoveredLines().size() == 0) {
            currentGoals.forEach(f -> c.setFitness(f, Double.MAX_VALUE)); // assume minimization
            return;
        }

        Set<TestFitnessFunction> visitedTargets = new LinkedHashSet<>(getUncoveredGoals().size() * 2);

        /*
         * The processing list of current targets. If it turns out that any such target has been
         * reached, we also enqueue its structural and control-dependent children. This is to
         * determine which of those children are already reached by control flow. Only the missed
         * children will be part of the currentGoals for the next generation (together with the
         * missed goals of the currentGoals of the current generation).
         */
        LinkedList<TestFitnessFunction> targets = new LinkedList<>(this.currentGoals);

        // 1) We update the set of current goals.
        while (targets.size() > 0 && !ga.isFinished()) {
            // We evaluate the given test case against all current targets.
            // (There might have been serendipitous coverage of other targets, though.)
            TestFitnessFunction target = targets.poll();

            int pastSize = visitedTargets.size();
            visitedTargets.add(target);
            if (pastSize == visitedTargets.size())
                continue;

            double fitness = target.getFitness(c);

            /*
             * Checks if the current test target has been reached and, in accordance, marks it as
             * covered or uncovered.
             */
            if (fitness == 0.0) { // assume minimization function
                updateCoveredGoals(target, c); // marks the current goal as covered

                /*
                 * If the coverage criterion is branch coverage, we also add structural children
                 * and control dependencies of the current target to the processing queue. This is
                 * to see which ones of those goals are already reached by control flow.
                 */
                if (target instanceof BranchCoverageTestFitness) {
                    for (TestFitnessFunction child : graph.getStructuralChildren(target)) {
                        targets.addLast(child);
                    }
                    for (TestFitnessFunction dependentTarget : dependencies.get(target)) {
                        targets.addLast(dependentTarget);
                    }
                }
            } else {
                currentGoals.add(target); // marks the goal as uncovered
            }
        }

        // Removes all newly covered goals from the list of currently uncovered goals.
        currentGoals.removeAll(this.getCoveredGoals());

        // 2) We update the archive.
        final ExecutionTrace trace = result.getTrace();
        for (int branchid : trace.getCoveredFalseBranches()) {
            TestFitnessFunction branch = this.branchCoverageFalseMap.get(branchid);
            if (branch == null)
                continue;
            updateCoveredGoals(branch, c);
        }
        for (int branchid : trace.getCoveredTrueBranches()) {
            TestFitnessFunction branch = this.branchCoverageTrueMap.get(branchid);
            if (branch == null)
                continue;
            updateCoveredGoals(branch, c);
        }
        for (String method : trace.getCoveredBranchlessMethods()) {
            TestFitnessFunction branch = this.branchlessMethodCoverageMap.get(method);
            if (branch == null)
                continue;
            updateCoveredGoals(branch, c);
        }

        // let's manage the exception coverage
        if (ArrayUtil.contains(Properties.CRITERION, Criterion.EXCEPTION)) {
            // if one of the coverage criterion is Criterion.EXCEPTION,
            // then we have to analyze the results of the execution do look
            // for generated exceptions
            Set<ExceptionCoverageTestFitness> set = deriveCoveredExceptions(c);
            for (ExceptionCoverageTestFitness exp : set) {
                // let's update the list of fitness functions
                updateCoveredGoals(exp, c);
                // new covered exceptions (goals) have to be added to the archive
                if (!ExceptionCoverageFactory.getGoals().containsKey(exp.getKey())) {
                    // let's update the newly discovered exceptions to ExceptionCoverageFactory
                    ExceptionCoverageFactory.getGoals().put(exp.getKey(), exp);
                }
            }
        }
    }

    /**
     * This method analyzes the execution results of a TestChromosome looking for generated exceptions.
     * Such exceptions are converted in instances of the class {@link ExceptionCoverageTestFitness},
     * which are additional covered goals when using as criterion {@link Properties.Criterion Exception}
     *
     * @param t TestChromosome to analyze
     * @return list of exception goals being covered by t
     */
    public Set<ExceptionCoverageTestFitness> deriveCoveredExceptions(TestChromosome t) {
        Set<ExceptionCoverageTestFitness> covered_exceptions = new LinkedHashSet<>();
        ExecutionResult result = t.getLastExecutionResult();

        if (result.calledReflection())
            return covered_exceptions;

        for (Integer i : result.getPositionsWhereExceptionsWereThrown()) {
            if (ExceptionCoverageHelper.shouldSkip(result, i)) {
                continue;
            }

            Class<?> exceptionClass = ExceptionCoverageHelper.getExceptionClass(result, i);
            String methodIdentifier = ExceptionCoverageHelper.getMethodIdentifier(result, i); //eg name+descriptor
            boolean sutException = ExceptionCoverageHelper.isSutException(result, i); // was the exception originated by a direct call on the SUT?

            /*
             * We only consider exceptions that were thrown by calling directly the SUT (not the other
             * used libraries). However, this would ignore cases in which the SUT is indirectly tested
             * through another class
             */

            if (sutException) {

                ExceptionCoverageTestFitness.ExceptionType type = ExceptionCoverageHelper.getType(result, i);
                /*
                 * Add goal to list of fitness functions to solve
                 */
                ExceptionCoverageTestFitness goal = new ExceptionCoverageTestFitness(Properties.TARGET_CLASS, methodIdentifier, exceptionClass, type);
                covered_exceptions.add(goal);
            }
        }
        return covered_exceptions;
    }

    public BranchFitnessGraph getControlDependencies4Branches(List<TestFitnessFunction> fitnessFunctions) {
        Set<TestFitnessFunction> setOfBranches = new LinkedHashSet<>();
        this.dependencies = new LinkedHashMap<>();

        List<BranchCoverageTestFitness> branches = new BranchCoverageFactory().getCoverageGoals();
        for (BranchCoverageTestFitness branch : branches) {
            setOfBranches.add(branch);
            this.dependencies.put(branch, new LinkedHashSet<>());
        }

        // initialize the maps
        this.initializeMaps(setOfBranches);

        return new BranchFitnessGraph(setOfBranches);
    }
    
	
	public int getNumberSortInteractions() {
		return numberSortInteractions;
	}

	public void setNumberSortInteractions(int numberSortInteractions) {
		this.numberSortInteractions = numberSortInteractions;
	}

	/* ADDED */
	/**
	 * {@inheritDoc}
	 */
	public void writeGoalsInteracted() {
		try {
			File f = new File(Properties.CLASS_TYPE_DIR + File.separator + "goals.csv");
			BufferedWriter out = new BufferedWriter(new FileWriter(f, true));
			StringBuilder r = new StringBuilder();
			if (f.length() == 0L) {
				//HEADER
				r.append("Execution,DifferentGoals,DifferentMethods\n");
			}
			
	    	List<String> methods = new ArrayList<>();
	    	String targetMethod = "";
	    	int counter = 0;
	    	for (TestFitnessFunction tf : goalsInteracted) {
	    		targetMethod = tf.getTargetMethod() + tf.getTargetClass();
	    		if(methods.contains(targetMethod)) {
	    			continue;
	    		}
	    		else {
	    			methods.add(targetMethod);
	    			counter++;
	    		}
	    	}
			
			r.append(Properties.EXECUTION_NUMBER);
			r.append(",");
			r.append(goalsInteracted.size());
			r.append(",");
			r.append(counter);
			r.append("\n");
			
			out.write(r.toString());
			out.close();

		} catch (IOException e) {
			logger.warn("Error while writing goals different: " + e.getMessage());
		}
	}
}
