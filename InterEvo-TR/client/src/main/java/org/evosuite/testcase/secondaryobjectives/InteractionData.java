package org.evosuite.testcase.secondaryobjectives;

import java.util.ArrayList;
import java.util.List;

import org.evosuite.Properties.Interaction;
import org.evosuite.testcase.TestChromosome;
import org.evosuite.testcase.TestFitnessFunction;

/**
 * <p>InteractionData class.</p>
 *
 * @author Pedro D.P.
 */

public class InteractionData {

	//Common to an interaction moment
	int iteration;						//Number of iteration
	
	int numberInteraction = -1; 		//Number of interaction
	Interaction type = Interaction.SORT; //Type of interaction
	
	//Common to an interaction moment + selected target
	boolean tie = true;					//There is a tie to break
	boolean sameMinimization = false;	//No different minimizations found
	
	int selected = 0;					//Number of candidates selected to untie
	int minimized = 0;					//Number of candidates with different minimization
	int archived = 0; 					//Number of candidates found in the archive
	
	long interactionTime = 0L;			//Time spent in the interaction
	long completeTime = 0L;				//Time spent preparing the interaction and with the interaction
	
	int minimizationFailed = 0; 		//Number of candidates whose minimizationFailed.
	int minimizationEmpty = 0; 			//Number of candidates whose minimization is empty.
	
	//Feedback
	List<Integer> readabilityScores = new ArrayList<Integer>();		//Readability scores
	TestFitnessFunction fitness;		//Target
	
	public InteractionData clone() {
		InteractionData c = new InteractionData();
		c.setIteration(this.iteration);
				
		return c;
	}
	
	public int getIteration() {
		return iteration;
	}

	public void setIteration(int iteration) {
		this.iteration = iteration;
	}


	public int getNumberInteraction() {
		return numberInteraction;
	}

	public void setNumberInteraction(int numberInteraction) {
		this.numberInteraction = numberInteraction;
	}
	
	public Interaction getType() {
		return type;
	}

	public void setType(Interaction type) {
		this.type = type;
	}

	public boolean isTie() {
		return tie;
	}

	public void setTie(boolean tie) {
		this.tie = tie;
	}

	public boolean isSameMinimization() {
		return sameMinimization;
	}

	public void setSameMinimization(boolean sameMinimization) {
		this.sameMinimization = sameMinimization;
	}

	public int getSelected() {
		return selected;
	}

	public void setSelected(int selected) {
		this.selected = selected;
	}

	public int getMinimized() {
		return minimized;
	}

	public void setMinimized(int minimized) {
		this.minimized = minimized;
	}

	public int getArchived() {
		return archived;
	}

	public void setArchived(int archived) {
		this.archived = archived;
	}

	public long getInteractionTime() {
		return interactionTime;
	}

	public void setInteractionTime(long interactionTime) {
		this.interactionTime = interactionTime;
	}

	public long getCompleteTime() {
		return completeTime;
	}

	public void setCompleteTime(long completeTime) {
		this.completeTime = completeTime;
	}
	
	public int getMinimizationFailed() {
		return minimizationFailed;
	}

	public void setMinimizationFailed(int minimizationFailed) {
		this.minimizationFailed = minimizationFailed;
	}
	
	public int getMinimizationEmpty() {
		return minimizationEmpty;
	}

	public void setMinimizationEmpty(int minimizationEmpty) {
		this.minimizationEmpty = minimizationEmpty;
	}

	public List<Integer> getReadabilityScores() {
		return readabilityScores;
	}

	public void setReadabilityScores(List<Integer> readabilityScores) {
		this.readabilityScores = readabilityScores;
	}
	
	public TestFitnessFunction getFitness() {
		return fitness;
	}
	public void setFitness(TestFitnessFunction fitness) {
		this.fitness = fitness;
	}
}
