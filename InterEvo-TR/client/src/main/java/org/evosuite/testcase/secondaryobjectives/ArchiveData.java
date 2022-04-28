package org.evosuite.testcase.secondaryobjectives;

import java.io.Serializable;

import org.evosuite.testcase.TestChromosome;
import org.evosuite.testcase.TestFitnessFunction;

/**
 * <p>ArchiveData class.</p>
 *
 * @author Pedro D.P.
 */

public class ArchiveData implements Serializable {

	private static final long serialVersionUID = 1L;

	public int getIteration() {
		return iteration;
	}
	public void setIteration(int iteration) {
		this.iteration = iteration;
	}
	public TestFitnessFunction getFitness() {
		return fitness;
	}
	public void setFitness(TestFitnessFunction fitness) {
		this.fitness = fitness;
	}
	public TestChromosome getTc() {
		return tc;
	}
	public void setTc(TestChromosome tc) {
		this.tc = tc;
	}
	
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	
	//Common to a interaction moment
	int iteration;	//Number of interaction
	TestFitnessFunction fitness;
	String method  = "";
	TestChromosome tc = new TestChromosome();
}
