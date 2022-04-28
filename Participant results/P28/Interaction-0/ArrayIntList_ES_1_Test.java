/*
 * This file was automatically generated by EvoSuite
 * Wed Jan 26 09:53:43 GMT 2022
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
      ArrayIntList arrayIntList0 = new ArrayIntList(0);
      arrayIntList0.add(0);
      ArrayIntList arrayIntList1 = new ArrayIntList(arrayIntList0);
      arrayIntList0.addAll(arrayIntList1);
      arrayIntList0.addAll(arrayIntList1);
      int int0 = arrayIntList0.removeElementAt(1);
      assertEquals(2, arrayIntList0.size());
      assertEquals(0, int0);
  }
}
