/*
 * This file was automatically generated by EvoSuite
 * Tue Dec 14 12:21:17 GMT 2021
 */

package com.org.apache.commons.collections.primitives;

import org.junit.Test;
import static org.junit.Assert.*;
import com.org.apache.commons.collections.primitives.ArrayIntList;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true) 
public class ArrayIntList_ES_2_Test extends ArrayIntList_ES_2_Test_scaffolding {

  @Test(timeout = 4000)
  public void test0()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList();
      arrayIntList0.add(0, 0);
      arrayIntList0.add(569);
      int int0 = arrayIntList0.removeElementAt(0);
      assertEquals(1, arrayIntList0.size());
      assertEquals(0, int0);
  }
}
