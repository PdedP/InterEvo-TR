/*
 * This file was automatically generated by EvoSuite
 * Tue Jan 25 10:41:28 GMT 2022
 */

package com.org.apache.commons.collections.primitives;

import org.junit.Test;
import static org.junit.Assert.*;
import com.org.apache.commons.collections.primitives.ArrayIntList;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true) 
public class ArrayIntList_ES_1_Test extends ArrayIntList_ES_1_Test_scaffolding {

  @Test(timeout = 4000)
  public void test0()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList();
      ArrayIntList arrayIntList1 = new ArrayIntList(arrayIntList0);
      arrayIntList1.add(14);
      arrayIntList0.addAll(arrayIntList1);
      assertEquals(1, arrayIntList0.size());
      
      boolean boolean0 = arrayIntList0.removeAll(arrayIntList1);
      assertTrue(boolean0);
  }
}
