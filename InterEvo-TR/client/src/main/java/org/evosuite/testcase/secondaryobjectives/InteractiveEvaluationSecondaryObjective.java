package org.evosuite.testcase.secondaryobjectives;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Console;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.evosuite.ClientProcess;
import org.evosuite.Properties;
import org.evosuite.TestSuiteGenerator;
import org.evosuite.TestSuiteGeneratorHelper;
import org.evosuite.ga.SecondaryObjective;
import org.evosuite.runtime.RuntimeSettings;
import org.evosuite.runtime.util.SystemInUtil;
import org.evosuite.testcase.ConstantInliner;
import org.evosuite.testcase.TestChromosome;
import org.evosuite.testcase.TestFitnessFunction;
import org.evosuite.utils.LoggingUtils;

import com.thoughtworks.xstream.mapper.DefaultImplementationsMapper;

/**
 * <p>InteractiveEvaluationSecondaryObjective class.</p>
 *
 * @author Pedro D.P.
 */

public class InteractiveEvaluationSecondaryObjective extends SecondaryObjective<TestChromosome> {/**
	 * 
	 */
	private static final long serialVersionUID = -9193157273285450057L;
	
	private static Map<String, Integer> valuedMinimizations  = new HashMap<String, Integer>();
	private static Map<String, TestChromosome> valuedMinimizationsAssertions  = new HashMap<String, TestChromosome>();
	
	private static List<InteractionData> interactions = new ArrayList<InteractionData>();
	private static InteractionData idata;
	
	private static boolean newRealInteractions = false;
	
	private int getReadability(TestChromosome chromosome) {
		return chromosome.getReadabilityValue();
	}
	
	@Override
	public int compareChromosomes(TestChromosome chromosome1, TestChromosome chromosome2) {
		int value1 = 	getReadability(chromosome1);
		int value2 = 	getReadability(chromosome2);
		
		return value2 - value1;
	}
	
	public TestChromosome setReadability(List<TestChromosome> population, TestFitnessFunction goal, int timesInteracted, TestChromosome bestInPreferenceArchive) {
		//Minimize test cases with respect to the goal	
		org.evosuite.testcase.TestCaseMinimizer minimizer = new org.evosuite.testcase.TestCaseMinimizer(goal);
		ConstantInliner inliner = new ConstantInliner();
		
		// 1) MINIMIZE: we will keep the minimized copies
		Map<String, TestChromosome> minimizedVersions = new HashMap<>();	//MAP: string with the minimized test and the minimized test itself
		Map<TestChromosome, Integer> readabilityValues = new HashMap<>();	//MAP: minimized test and its readability value
		
		boolean successMinimizing = true;
		int minimizationFailed = 0, minimizationEmpty = 0;
		for (TestChromosome individual : population) {
			
			//Create a clone and minimize
			TestChromosome copy =  individual.clone();
			inliner.inline(copy);
			successMinimizing = minimizer.minimize(copy);
	
			//Discard those not minimized
			if(!successMinimizing) {
				minimizationFailed++;
				continue;
			}
			
			//Discard also those whose minimization is empty
			if(copy.getTestCase().size() == 0) {
				minimizationEmpty++;
				continue;
			}
			
			//Check if there is another minimized test with the same minimization	
			String copyString = copy.getTestCase().toCode(copy.getLastExecutionResult().getCopyOfExceptionMapping());	//To include exceptions (an exception can change the minimization of a test case)
			boolean newMinimization = true;
			for(Map.Entry<String, TestChromosome> entry : minimizedVersions.entrySet()) {
				if(entry.getKey().equals(copyString)) {
					newMinimization = false;
					break;
				}
			}
						
			if(newMinimization) {
				minimizedVersions.put(copyString, copy);
			}
		}
		
		idata.minimizationFailed = minimizationFailed;
		idata.minimizationEmpty = minimizationEmpty;
		idata.minimized = minimizedVersions.size();
		
		// We should continue only in two cases:
		// - When the size of the population has not decreased to one (that would mean that all tests had the same minimization). 
		// - When we have a test case in the preference archive to compare
		if(minimizedVersions.size() > 1 || (minimizedVersions.size() == 1 && bestInPreferenceArchive != null) ){
			
			//2) PASS THE INFORMATION TO EXTERNAL FILES FOR THEIR REVISION (only in case not all the minimizations are in the archive of valued minimizations).
			boolean minimizationsToValue = false;
			Map<Integer, Integer> scoresAlreadyValued = new HashMap<>();
			int num = 0;
			for(Map.Entry<String, TestChromosome> entry : minimizedVersions.entrySet()) {
				
				String copyString = entry.getKey();
				if(!valuedMinimizations.containsKey(copyString)){
					minimizationsToValue = true; 
				}
				else {
					scoresAlreadyValued.put(num, valuedMinimizations.get(copyString).intValue());
				}
				num++;
			}
			
			if(minimizationsToValue || Properties.REVISIT_CANDIDATES) {
			
				//Create the appropriate directory
				String directoryInteraction = Properties.INTERACTIVE_DIR + "/" + "Interaction-" + timesInteracted;				
				try {
					Files.createDirectories(Paths.get(directoryInteraction));
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				PrintStream ps2 = null;
				num = 0;
				int archive = 0;
				for(Map.Entry<String, TestChromosome> entry : minimizedVersions.entrySet()) {
					
					TestChromosome cm = minimizedVersions.get(entry.getKey());
					String copyString = entry.getKey();
					
					if(!scoresAlreadyValued.containsKey(num) || Properties.REVISIT_CANDIDATES){
						
						if (Properties.ASSERTIONS) {		//Assertions take time, so they are only added once we know readability values are required. 
							TestSuiteGeneratorHelper.addAssertionsInteractive(cm.getTestCase());	
							copyString = cm.getTestCase().toCode(cm.getLastExecutionResult().getCopyOfExceptionMapping());
						}
						
						//Select TXT format or JUNIT depending on the type of interaction
						if(Properties.SIMULATED_INTERACTIVITY) {
							try {					
								ps2 = new PrintStream(new File(directoryInteraction + "/tests" + num + ".txt"));
							} catch (FileNotFoundException e) {
								e.printStackTrace();
							}
							ps2.println(copyString);
							ps2.close();
						}
						else {
							TestSuiteGenerator.writeJUnitTestsAndCreateResult(cm, "_ES_" + num + "_Test", true, directoryInteraction);
						}
						
						if(scoresAlreadyValued.containsKey(num))	//This is for statistics when revisit_candidates is true
							archive++;								//the candidate was found in the archive
					}
					else {
						archive++;	//the candidate was found in the archive
						
						//The test cases in the archive are shown, 
						if(!Properties.SIMULATED_INTERACTIVITY) {
							TestSuiteGenerator.writeJUnitTestsAndCreateResult(valuedMinimizationsAssertions.get(copyString), "_ES_" + num + "_AlreadyValued_Test", true, directoryInteraction);
						}
					}
					num++;	//simulate_legibility.sh has to be prepared to receive test cases in non-consecutive numeric order (e.g., tests1.txt, tests4.txt...)
				}
				
				idata.archived = archive;
				
				//Show the objective
				try {					
					ps2 = new PrintStream(new File(directoryInteraction + "/goal" + ".txt"));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				ps2.println(goal.toString());
				ps2.close();
			
				// 3) COLLECT TESTER OPINION ON TEST READABILITY
				BufferedReader simulatedReader = null;
				PrintStream ps = System.out;
				
				if(Properties.SIMULATED_INTERACTIVITY) {
					//Set up the script
					ProcessBuilder pb = new ProcessBuilder();
					List<String> args = new ArrayList<String>();
					args.add(Properties.ROOT_DIRECTORY + "/simulate_legibility.sh");
					args.add(directoryInteraction);
					args.add(Properties.READABILITY_FILE);
					args.add(Integer.toString(Properties.READABILITY_SCALE));	
					args.add(Integer.toString(Properties.MAX_LINE_LENGHT));
					args.add(Integer.toString(Properties.MAX_CHAR_COUNT));
					args.add(Integer.toString(Properties.MAX_NUMBER_IDENTIFIERS));
					args.add(Integer.toString(Properties.MAX_ENTROPY));
					pb.command(args).directory(new File(Properties.ROOT_DIRECTORY));
					
					Process p = null;
					try {
						p = pb.start();
						p.waitFor();
					} catch (Exception e) {
						e.printStackTrace();
						LoggingUtils.getEvoLogger().info("\nERROR: Command execution failed.");
					}
										
					try {
						simulatedReader = new BufferedReader(new FileReader(directoryInteraction + "/" + Properties.READABILITY_FILE));
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
						LoggingUtils.getEvoLogger().info("\nERROR: File " + Properties.READABILITY_FILE + "could not be read.");
					}
				}

				//Inform the tester of the interaction
				if(Properties.SIMULATED_INTERACTIVITY) { 
					ps.println("\nIT IS TIME TO INTERACT! The files with the tests are in this folder: " + directoryInteraction);
					LoggingUtils.getEvoLogger().info("* " + ClientProcess.getPrettyPrintIdentifier() + "IT IS TIME TO INTERACT! The files with " + 
														" the tests are in this folder: " + directoryInteraction);
				}
				else {
					ps.println("\nIT IS TIME TO INTERACT! Go to the folder " + directoryInteraction + ".\nAfter that, revise and provide a "
							+ "readability value for each test not already valued.\n_______________________________________________________________________________________________________\n");
					LoggingUtils.getEvoLogger().info("* " + ClientProcess.getPrettyPrintIdentifier() + "IT IS TIME TO INTERACT! Go to the folder " + 
							directoryInteraction + ".\nAfter that, revise and provide a readability value for each test not already valued.\n");
									
					//Detect whether the target was addressed in a previous interaction
					for(int i = interactions.size(); i-- > 0; ) {
						InteractionData id = interactions.get(i);
						if(id.getNumberInteraction() != -1 && id.getFitness().equals(idata.getFitness())){
							ps.println("> Note: this goal was also addressed at interaction number " + id.getNumberInteraction());
							LoggingUtils.getEvoLogger().info("> " + ClientProcess.getPrettyPrintIdentifier() + "Note: this goal was also addressed at interaction number " + id.getNumberInteraction() + ".\n");
							break;
						}
					}
				
					//Show the test case in the preference archive (if any)
					if(bestInPreferenceArchive !=  null) {
						TestSuiteGenerator.writeJUnitTestsAndCreateResult(bestInPreferenceArchive, "_ES_Preferred_Test", true, directoryInteraction);
						ps.println("> The test in the preference archive has the following score: " + bestInPreferenceArchive.getReadabilityValue());
					}
					
					//Inform of the readability scores of the tests in the archive 
					for(Map.Entry<Integer, Integer> entry : scoresAlreadyValued.entrySet()) {
						ps.println("> Test " + entry.getKey() + " has already been valued with score: " + entry.getValue());
						LoggingUtils.getEvoLogger().info("> " + ClientProcess.getPrettyPrintIdentifier() + "Test " + entry.getKey() + " has already been valued with score: "+  entry.getValue());
					}
				}
				
				//Start the time counter
				long startInteractionTime = System.currentTimeMillis();
				
				num = 0;
				for(Map.Entry<String, TestChromosome> entry : minimizedVersions.entrySet()) {
					if(!scoresAlreadyValued.containsKey(num) || Properties.REVISIT_CANDIDATES){
						
						int readValue = -1;
						if(Properties.SIMULATED_INTERACTIVITY) 
							try {
								readValue = Integer.valueOf(simulatedReader.readLine());
							} catch (Exception e) {
								e.printStackTrace();
							}
						else {
							
							if(Properties.REVISIT_CANDIDATES && scoresAlreadyValued.containsKey(num)) {
								LoggingUtils.getEvoLogger().info("* " + ClientProcess.getPrettyPrintIdentifier() + "The legibility assigned to test " + num + 
										" in a previous interaction is: " + valuedMinimizations.get(entry.getKey()).intValue());
								ps.println("The readability assigned to test " + num + " in a previous interaction is: " + 
										valuedMinimizations.get(entry.getKey()).intValue());
							}	
							
							Scanner reader = new Scanner(System.in);
							
							while (readValue < 0 || readValue > Properties.MAX_READABILITY_SCORE) {
								try {
									ps.println("- Readability for test " + num + " [0-" + Properties.MAX_READABILITY_SCORE + "]: ");
									LoggingUtils.getEvoLogger().info(ClientProcess.getPrettyPrintIdentifier() + "Readability for test " + num + " [0-" + Properties.MAX_READABILITY_SCORE + "]: ");
									readValue = reader.nextInt();
								}catch(java.util.InputMismatchException e) {
									ps.println("An integer value is expected. Please, try again.");
									LoggingUtils.getEvoLogger().info(ClientProcess.getPrettyPrintIdentifier() + "An integer value is expected. Please, try again.\n");
									reader.nextLine();
								}
								
								if(readValue < 0 || readValue > Properties.MAX_READABILITY_SCORE) {
									ps.println("Invalid value. Please, try again.");
									LoggingUtils.getEvoLogger().info(ClientProcess.getPrettyPrintIdentifier() + "Invalid value. Please, try again.\n");
								}
							}
						}
						
						idata.readabilityScores.add(readValue);
												
						TestChromosome cmAssertions = entry.getValue().clone();
						
						//Add the readability value
						readabilityValues.put(cmAssertions, readValue);
						//Add to the archive of minimizations (if revisiting candidates is true, this operation replaces the old score by the new score)
						valuedMinimizations.put(entry.getKey(), readValue);
						valuedMinimizationsAssertions.put(entry.getKey(), cmAssertions);
					}
					else {
						//We have to transfer the historic value to individuals with the same minimization 
						readabilityValues.put(valuedMinimizationsAssertions.get(entry.getKey()), scoresAlreadyValued.get(num));	
					}
					
					num++;
				}
				
				//Stop the time counter
				long interactionTime = (System.currentTimeMillis() - startInteractionTime);
			
				idata.interactionTime = interactionTime;
				idata.numberInteraction = timesInteracted;
				newRealInteractions = true;
			}
			else {
				idata.archived = minimizedVersions.size();
				
				num = 0;
				for(Map.Entry<String, TestChromosome> entry : minimizedVersions.entrySet()) {
					readabilityValues.put(valuedMinimizationsAssertions.get(entry.getKey()), scoresAlreadyValued.get(num));
					num++;
				}
			}
		}
		else {
			idata.sameMinimization = true;
		}
		
		//Extract the best test case
		TestChromosome moreReadable = null;
		Integer bestScore = Integer.MIN_VALUE;
		for(Map.Entry<TestChromosome, Integer> entry : readabilityValues.entrySet()) {	
			if(entry.getValue() > bestScore) {
				bestScore = entry.getValue();
				moreReadable = entry.getKey();
			}
		}
		
		if(moreReadable != null)
			moreReadable.setReadabilityValue(bestScore);
		
		return moreReadable;
	}
	
	@Override
	public int compareGenerations(TestChromosome parent1, TestChromosome parent2, TestChromosome child1,
			TestChromosome child2) {
		return Math.max(getReadability(child1), getReadability(child2))
		        - Math.max(getReadability(parent1), getReadability(parent2));
	}
	
	
	public static void writeInteractions(long totalTime) {
		
		try {
			File f = new File(Properties.INTERACTIVE_DIR + File.separator + Properties.INTERACTION_STATISTICS_FILENAME);
			BufferedWriter out = new BufferedWriter(new FileWriter(f, true));
			StringBuilder r = new StringBuilder();
			if (f.length() == 0L) {
				//HEADER
				r.append("Generation,Selected,Tie,MinFailed,MinEmpty,Minimized,SameMin,Archived,Time,ReadabilityScores,Goal\n");
			}
			
			long preparationTimeTotal = 0L;	//this variable sums up the preparation time of each interaction 
			long interactionTimeTotal = 0L; //idem but for the interaction time
			
			//DATA
			Properties.INTERACTIVE_PROCESS = false;
			for (InteractionData id : interactions) {
				
				r.append(id.iteration);
				r.append(",");
				r.append(id.selected);
				r.append(",");
				r.append(id.tie);
				r.append(",");
				r.append(id.minimizationFailed);
				r.append(",");
				r.append(id.minimizationEmpty);
				r.append(",");
				r.append(id.minimized);
				r.append(",");
				r.append(id.sameMinimization);
				r.append(",");
				r.append(id.archived);
				r.append(",");
				r.append(String.valueOf(id.interactionTime / 1000L));
				r.append(",");
				
				List<Integer> listScores = id.getReadabilityScores();
				if(!listScores.isEmpty()) {		
					int i = 0;
					while(i < listScores.size() - 1) {
						r.append(listScores.get(i));
						r.append("-");
						
						i++;
					}
					r.append(listScores.get(i));
				}
				r.append(",");
				r.append(id.fitness);
				r.append("\n");
				
				//ADDED: to compute the overhead in preparation time of interactions
				interactionTimeTotal = interactionTimeTotal + id.interactionTime;
				preparationTimeTotal = preparationTimeTotal + (id.completeTime - id.interactionTime);
			}
			Properties.INTERACTIVE_PROCESS = true;
			
			out.write(r.toString());
			out.close();
			
			if(Properties.EXECUTION_NUMBER >= 0) {
			
				//ADDED: for the overhead
				File f2 = new File(Properties.CLASS_TYPE_DIR + File.separator + Properties.INTERACTION_OVERHEAD_FILENAME);
				BufferedWriter out2 = new BufferedWriter(new FileWriter(f2, true));
				StringBuilder r2 = new StringBuilder();
				
				if (f2.length() == 0L) {
					r2.append("Execution,TotalTime,InteractionTime,PreparationTime\n");	
				}
				
				r2.append(Properties.EXECUTION_NUMBER);
				r2.append(",");
				r2.append(String.valueOf(totalTime / 1000L));
				r2.append(",");
				r2.append(String.valueOf(interactionTimeTotal / 1000L));
				r2.append(",");
				r2.append(String.valueOf(preparationTimeTotal / 1000L));
				r2.append("\n");
				
				out2.write(r2.toString());
				out2.close();
			}
			
		} catch (IOException e) {
			logger.warn("Error while writing interaction statistics: " + e.getMessage());
		}
	}
	
	public static InteractionData getIData() {
		return idata;
	}
	
	public static void setIData(InteractionData id) {
		idata = id;
	}
	
	public static void initializeIData() {
		idata = new InteractionData();
	}
	
	public static List<InteractionData> getInteractions() {
		return interactions;
	}

	public static boolean isNewRealInteractions() {
		return newRealInteractions;
	}

	public static void setNewRealInteractions(boolean newRealInteractions) {
		InteractiveEvaluationSecondaryObjective.newRealInteractions = newRealInteractions;
	}
}
