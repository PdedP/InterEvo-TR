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
package org.evosuite.ga.archive;

import org.evosuite.Properties;
import org.evosuite.runtime.util.AtMostOnceLogger;
import org.evosuite.testcase.TestChromosome;
import org.evosuite.testcase.TestFitnessFunction;
import org.evosuite.testcase.execution.ExecutionResult;
import org.evosuite.testsuite.TestSuiteChromosome;
import org.evosuite.utils.Randomness;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * Preferences Archive.
 * 
 * @author Pedro D.P.
 */
public class PreferencesArchive extends Archive {

  private static final long serialVersionUID = 2618255829836825162L;

  private static final Logger logger = LoggerFactory.getLogger(PreferencesArchive.class);

  /**
   * Map used to store targets (keys of the map) and the corresponding preferred
   * solutions (values of the map)
   */
  private final Map<TestFitnessFunction, TestChromosome> preferred = new LinkedHashMap<>();
  
  public static final PreferencesArchive instance = new PreferencesArchive();

  /**
   * {@inheritDoc}
   */
  @Override
  public void addTarget(TestFitnessFunction target) {
	//NO-OP
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void updateArchive(TestFitnessFunction target, TestChromosome solution, double fitnessValue) {
    super.updateArchive(target, solution, fitnessValue);

    boolean isNewTarget = false;
    boolean isNewSolutionBetterThanCurrent = false;

    TestChromosome currentSolution = this.preferred.get(target);

    if (currentSolution == null) {
      logger.debug("Solution for new target '" + target + "'");
      isNewTarget = true;
    } else {
    	    	
      isNewSolutionBetterThanCurrent = this.isBetterThanCurrent(currentSolution, solution);
      
    }
    if (isNewTarget || isNewSolutionBetterThanCurrent) {
      // update the archive if this is a new target, or if a new solution is preferred over the current solution.
      this.addToArchive(target, solution);
    }
  }

  private void addToArchive(TestFitnessFunction target, TestChromosome solution) {

	this.preferred.put(target, solution);
    this.hasBeenUpdated = true;

    ExecutionResult result = solution.getLastExecutionResult();
    if (result != null && (result.hasTimeout() || result.hasTestException())) {
      AtMostOnceLogger.warn(logger,
          "A solution with a timeout/exception result has been added to the archive of preferences. The goal was "
              + target.toString());
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isArchiveEmpty() {
    return this.preferred.isEmpty();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getNumberOfTargets() {
    return this.preferred.keySet().size();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getNumberOfCoveredTargets() {
    return this.preferred.size();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getNumberOfCoveredTargets(Class<?> targetClass) {
    return (int) this.preferred.keySet().stream().filter(target -> target.getClass() == targetClass)
        .count();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Set<TestFitnessFunction> getCoveredTargets() {
    return this.preferred.keySet();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getNumberOfUncoveredTargets() {
	//NO-OP
    return 0;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getNumberOfUncoveredTargets(Class<?> targetClass) {
	//NO-OP
    return 0;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Set<TestFitnessFunction> getUncoveredTargets() {
	//NO-OP
	return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean hasTarget(TestFitnessFunction target) {
    assert target != null;
    return this.preferred.containsKey(target);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getNumberOfSolutions() {
    return this.preferred.size();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Set<TestChromosome> getSolutions() {
    return new LinkedHashSet<>(this.preferred.values());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public TestChromosome getSolution() {
    return this.getRandomSolution();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public TestChromosome getSolution(TestFitnessFunction target) {
    assert target != null;
    //assert this.preferred.containsKey(target);
    if(this.preferred.containsKey(target))
    	return this.preferred.get(target);
    else
    	return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean hasSolution(TestFitnessFunction target) {
    assert target != null;
    return this.preferred.containsKey(target);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public TestChromosome getRandomSolution() {
    // TODO this gives higher probability to tests that cover more targets. Maybe it is not the best
    // way, but likely the quickest to compute. A proper way to do it would be to first call
    // 'getSolutions' and only then select one at random.
    TestChromosome randomChoice = Randomness.choice(this.getSolutions());
    if (randomChoice == null) {
      return null;
    }
    return randomChoice.clone();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected TestSuiteChromosome createMergedSolution(TestSuiteChromosome solution) {
	  //NO-OP: at least, for the moment
	return solution;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void shrinkSolutions(int size) {
    // NO-OP
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return "NumTargets: " + this.getNumberOfTargets();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void reset() {
    super.reset();
    this.preferred.clear();
  }
  
  public static Archive getArchiveInstance() {
	  return PreferencesArchive.instance;
  }
}
