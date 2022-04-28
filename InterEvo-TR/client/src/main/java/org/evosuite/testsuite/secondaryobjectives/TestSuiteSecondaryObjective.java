/**
 * Copyright (C) 2010-2018 Gordon Fraser, Andrea Arcuri and EvoSuite
 * contributors
 * <p>
 * This file is part of EvoSuite.
 * <p>
 * EvoSuite is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published
 * by the Free Software Foundation, either version 3.0 of the License, or
 * (at your option) any later version.
 * <p>
 * EvoSuite is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser Public License for more details.
 * <p>
 * You should have received a copy of the GNU Lesser General Public
 * License along with EvoSuite. If not, see <http://www.gnu.org/licenses/>.
 */
package org.evosuite.testsuite.secondaryobjectives;

import org.evosuite.Properties;
import org.evosuite.coverage.ibranch.IBranchSecondaryObjective;
import org.evosuite.coverage.rho.RhoTestSuiteSecondaryObjective;
import org.evosuite.ga.SecondaryObjective;
import org.evosuite.testsuite.TestSuiteChromosome;


public class TestSuiteSecondaryObjective {


    public static void setSecondaryObjectives() {
    	
    	/* ADDED */
    	 
    	String[] sec_objs= Properties.LIST_SEC_OBJ.isEmpty() ?  new String [] { } : Properties.LIST_SEC_OBJ.split(";");
    	if(sec_objs.length > 0)
    		Properties.SECONDARY_OBJECTIVE = new Properties.SecondaryObjective[sec_objs.length];
    	else
    		Properties.SECONDARY_OBJECTIVE = new Properties.SecondaryObjective[] {};
    	
    	int j=0;
    	
    	while(j < Properties.SECONDARY_OBJECTIVE.length) {
    		switch (sec_objs[j]) {
    	        case "AVG_LENGTH":
    	        	Properties.SECONDARY_OBJECTIVE[j] = Properties.SecondaryObjective.AVG_LENGTH;
    	        	break;
    	        case "MAX_LENGTH":
    	        	Properties.SECONDARY_OBJECTIVE[j] = Properties.SecondaryObjective.MAX_LENGTH;
    	        	break;
    	        case "TOTAL_LENGTH":
    	        	Properties.SECONDARY_OBJECTIVE[j] = Properties.SecondaryObjective.TOTAL_LENGTH;
    	        	break;
    	        case "EXCEPTIONS":
    	        	Properties.SECONDARY_OBJECTIVE[j] = Properties.SecondaryObjective.EXCEPTIONS;
    	        	break;
    	        case "SIZE":
    	        	Properties.SECONDARY_OBJECTIVE[j] = Properties.SecondaryObjective.SIZE;
    	        	break;
    	        case "IBRANCH":
    	        	Properties.SECONDARY_OBJECTIVE[j] = Properties.SecondaryObjective.IBRANCH;
    	        	break;
    	        case "RHO":
    	        	Properties.SECONDARY_OBJECTIVE[j] = Properties.SecondaryObjective.RHO;
    	        	break;
    	        case "INTERACTIVE":
    	        	Properties.SECONDARY_OBJECTIVE[j] = Properties.SecondaryObjective.INTERACTIVE;
    	        	break;
    	        default:
    	          throw new RuntimeException(
    	              "ERROR: asked for unknown secondary objective 2 \"" + sec_objs[j] + "\"");
    	      }
    		
    		j++;
    	}
    	
        for (Properties.SecondaryObjective secondaryObjective : Properties.SECONDARY_OBJECTIVE) {
            SecondaryObjective<TestSuiteChromosome> secondaryObjectiveInstance = null;
            switch (secondaryObjective) {
                case AVG_LENGTH:
                    secondaryObjectiveInstance = new MinimizeAverageLengthSecondaryObjective();
                    break;
                case MAX_LENGTH:
                    secondaryObjectiveInstance = new MinimizeMaxLengthSecondaryObjective();
                    break;
                case TOTAL_LENGTH:
                    secondaryObjectiveInstance = new MinimizeTotalLengthSecondaryObjective();
                    break;
                case EXCEPTIONS:
                    secondaryObjectiveInstance = new MinimizeExceptionsSecondaryObjective();
                    break;
                case SIZE:
                    secondaryObjectiveInstance = new MinimizeSizeSecondaryObjective();
                    break;
                case IBRANCH:
                    secondaryObjectiveInstance = new IBranchSecondaryObjective();
                    break;
                case RHO:
                    secondaryObjectiveInstance = new RhoTestSuiteSecondaryObjective();
                    break;
                case INTERACTIVE:
                    secondaryObjectiveInstance = new InteractiveEvaluationSecondaryObjective();
                    break;
                default:
                    throw new RuntimeException(
                            "ERROR: asked for unknown secondary objective \"" + secondaryObjective.name() + "\"");
            }
            TestSuiteChromosome.addSecondaryObjective(secondaryObjectiveInstance);
        }
    }
}
