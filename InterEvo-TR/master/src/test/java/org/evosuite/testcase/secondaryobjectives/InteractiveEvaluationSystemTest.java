package org.evosuite.testcase.secondaryobjectives;

import org.evosuite.EvoSuite;
import org.evosuite.Properties;
import org.evosuite.SystemTestBase;
import org.evosuite.Properties.Criterion;
import org.evosuite.Properties.FrequencyStrategy;
import org.evosuite.Properties.SecondaryObjective;
import org.evosuite.Properties.TestNamingStrategy;
import org.evosuite.utils.Randomness;
import org.junit.Assert;
import org.junit.Test;

import com.examples.interactive.Stack;


public class InteractiveEvaluationSystemTest extends SystemTestBase {

	@Test
	public void testDynaMOSA() {
		
		/*** COMO SE QUITE ESTO YA NO FUNCIONA LA ÚLTIMA PARTE DE ESTE TEST DE MOSTRAR EL TEST RESULTANTE! */
		/* NO TENGO NI IDEA DE A QUÉ SE DEBE ***/
		Properties.CRITERION = new Criterion[] {
	            //these are basic criteria that should be always on by default
	            //Criterion.LINE, Criterion.BRANCH, Criterion.EXCEPTION, Criterion.WEAKMUTATION, 
	            //Properties.Criterion.INPUT, Criterion.OUTPUT, Criterion.METHOD, Criterion.METHODNOEXCEPTION, Criterion.CBRANCH  };
				Criterion.LINE, Criterion.BRANCH, Criterion.EXCEPTION, Criterion.WEAKMUTATION };
				//Criterion.LINE, Criterion.BRANCH};
			
		Properties.SECONDARY_OBJECTIVE = new SecondaryObjective[] { Properties.SecondaryObjective.INTERACTIVE, Properties.SecondaryObjective.TOTAL_LENGTH};
		Properties.INTERACTIVE_PROCESS = true;
		Properties.MINIMUM_READABILITY_VALUE = 0;
		
	//	Properties.LIST_SEC_OBJ = "TOTAL_LENGTH";
		Properties.INTERACTION_STATISTICS =  true;		// SI QUEREMOS QUE IMPRIMA LOS TEST CASES INDIVIDUALES PARA EL CÁLCULO DEL MÁXIMO CON LA OPCIÓN B
		
		Properties.GLOBAL_TIMEOUT = 1000;			//en segundos
		Properties.MINIMIZATION_TIMEOUT = 300;		
		Properties.REPLACE_SYSTEM_IN = false;
		//TODO Properties.MINIMIZING_INTERACTIVE_TIMEOUT = 60;
		Properties.OUTPUT_DIR = "/home/pedro/Documentos/EvoSuite-DynaMOSA/Pruebas/";
		Properties.INTERACTIVE_DIR = "/home/pedro/Documentos/EvoSuite-DynaMOSA/Pruebas/";
	
		// El objetivo de esto es que pare no por tiempo, sino por número de generaciones. 
		// el número de generaciones va en dependencia del search_budget,  es decir, aquí hay 10 generaciones. 
		Properties.STOPPING_CONDITION = Properties.StoppingCondition.MAXGENERATIONS;
		//Properties.SHUFFLE_GOALS = false;
		
		//Parámetros más importantes para plantear un esquema de interactividad
		Properties.POPULATION = 50;
		
		//GRUPO DE PERIODICIDAD
		Properties.ENABLE_SECONDARY_OBJECTIVE_AFTER = 10;
		Properties.REVISE_PERIODICALLY = true;				// Si está habilitado ENABLE_SECONDARY_OBJECTIVE_STARVATION, no se tendrá en cuenta.
		Properties.FREQUENCY_STRATEGY = FrequencyStrategy.PROGRESSIVE;
		Properties.FREQUENCY_SCALE = 2;
		
		
		//GRUPO DE STARVATION
		Properties.ENABLE_SECONDARY_OBJECTIVE_STARVATION = false;
		
		//Parámetros para limitar la interación:
		Properties.PERCENTAGE_TO_REVISE = 0.06;	//debe estar en función de la población: poblaciones más grandes requerirán bajar este parámetro.
		Properties.MAX_TIMES_TO_SORT_REVISE = 5;
		Properties.WHEN_TO_REVISE = Properties.Interaction.SORT;		//Sort debería limitar más el número de interacciones porque es una por generación.
		Properties.MAX_NUMBER_GOALS_IN_INTERACTION = 3;
				
		//Simulated interaction
		Properties.SIMULATED_INTERACTIVITY 		=  false;		
		Properties.REVISIT_CANDIDATES 			=  false;

		//STRING TOOLS
		/*Properties.REVISE_AFTER_PERCENTAGE_OF_COVERAGE = 77;
		Properties.SEARCH_BUDGET = 161;
		Properties.FREQUENCY = 50;								
		Properties.MAX_LINE_LENGHT = 122;
		Properties.MAX_CHAR_COUNT = 673;
		Properties.MAX_NUMBER_IDENTIFIERS = 12;
		Properties.MAX_ENTROPY = 566;*/ 
		
		//ATM
		/*Properties.REVISE_AFTER_PERCENTAGE_OF_COVERAGE = 91;
		Properties.SEARCH_BUDGET = 1751;
		Properties.FREQUENCY = 580;								
		Properties.MAX_LINE_LENGHT = 86;
		Properties.MAX_CHAR_COUNT = 558;
		Properties.MAX_NUMBER_IDENTIFIERS = 9;
		Properties.MAX_ENTROPY = 541;*/
		
		//PARA PROBAR LA NUEVA ESTRATEGIA PROGRESSIVE PONGO FREQUENCY MUY BAJA
		Properties.REVISE_AFTER_PERCENTAGE_OF_COVERAGE = 50;
		Properties.SEARCH_BUDGET = 400;
		Properties.FREQUENCY = 2;								
		Properties.MAX_LINE_LENGHT = 86;
		Properties.MAX_CHAR_COUNT = 558;
		Properties.MAX_NUMBER_IDENTIFIERS = 9;
		Properties.MAX_ENTROPY = 541;
		
		//JSJShopNode
		/*Properties.REVISE_AFTER_PERCENTAGE_OF_COVERAGE = 77;
		Properties.SEARCH_BUDGET = 609;
		Properties.FREQUENCY = 200;								
		Properties.MAX_LINE_LENGHT = 99;
		Properties.MAX_CHAR_COUNT = 448;
		Properties.MAX_NUMBER_IDENTIFIERS = 7;
		Properties.MAX_ENTROPY = 521;*/
		
		//Rational
		/*Properties.REVISE_AFTER_PERCENTAGE_OF_COVERAGE = 50;
		Properties.SEARCH_BUDGET = 400;		//EN REALIDAD ES EL DOBLE
		Properties.FREQUENCY = 134;								
		Properties.MAX_LINE_LENGHT = 72;
		Properties.MAX_CHAR_COUNT = 335;
		Properties.MAX_NUMBER_IDENTIFIERS = 4;
		Properties.MAX_ENTROPY = 516;*/
		
		//Para mantener siempre la misma ejecución
		Randomness.setSeed(0);

		EvoSuite evosuite = new EvoSuite();

		String targetClass = Stack.class.getCanonicalName();
		Properties.TARGET_CLASS = targetClass;

		String[] command = new String[] {
				"-generateMOSuite", "-Dalgorithm=DynaMOSA", "-Dstrategy=MOSuite", "-class", targetClass
		};
		
		Properties.CLASS_TYPE_DIR =  Properties.INTERACTIVE_DIR+"ATM/Readability/";
		Properties.ASSERTIONS = true;
		Properties.EXECUTION_NUMBER = 0;

		//Naming convention 
		//Properties.TEST_NAMING_STRATEGY = TestNamingStrategy.COVERAGE;
		//Properties.JUNIT_TESTS =  true;
		
		Object result = evosuite.parseCommandLine(command);
		Assert.assertNotNull(result);

		
		//NO SÉ CÓMO SE PUEDE SACAR EL TEST SUITE FINAL. AHORA MISMO, SIN EMBARGO, SE EXTRAE A UN FICHERO EXTERNO EN TESTUITEGENERATOR SIEMPRE QUE EXECUTION_NUMBER SEA >=0
		/*GeneticAlgorithm<?> ga = getGAFromResult(result);
		
		@SuppressWarnings("unchecked")
		TestSuiteAdapter<DynaMOSA> mio = (TestSuiteAdapter<DynaMOSA>) ga;
				
	    TestSuiteChromosome best = mio.getBestIndividual();
	    System.out.println("EvolvedTestSuite:\n" + best);*/
		/*TestSuiteChromosome c = (TestSuiteChromosome) ga.getBestIndividual();
		System.out.println(c.toString());*/
	}
	
	
}