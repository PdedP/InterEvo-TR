/*
 * This file was automatically generated by EvoSuite
 * Tue Dec 14 12:16:27 GMT 2021
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
      arrayIntList0.add(0);
      ArrayIntList arrayIntList1 = new ArrayIntList(arrayIntList0);
      arrayIntList1.add(1, 8);
      assertEquals(2, arrayIntList1.size());
  }
}
