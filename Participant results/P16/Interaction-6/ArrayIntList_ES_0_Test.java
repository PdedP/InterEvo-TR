/*
 * This file was automatically generated by EvoSuite
 * Thu Dec 16 10:03:29 GMT 2021
 */

package com.org.apache.commons.collections.primitives;

import org.junit.Test;
import static org.junit.Assert.*;
import com.org.apache.commons.collections.primitives.ArrayIntList;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true) 
public class ArrayIntList_ES_0_Test extends ArrayIntList_ES_0_Test_scaffolding {

  @Test(timeout = 4000)
  public void test0()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList(8);
      arrayIntList0.add(8);
      assertEquals(1, arrayIntList0.size());
      
      ArrayIntList arrayIntList1 = new ArrayIntList(8);
      boolean boolean0 = arrayIntList0.retainAll(arrayIntList1);
      assertTrue(boolean0);
  }
}
