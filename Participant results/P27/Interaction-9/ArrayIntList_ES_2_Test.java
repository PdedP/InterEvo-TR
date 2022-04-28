/*
 * This file was automatically generated by EvoSuite
 * Thu Jan 20 17:26:43 GMT 2022
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
      ArrayIntList arrayIntList0 = new ArrayIntList(31);
      ArrayIntList arrayIntList1 = new ArrayIntList(arrayIntList0);
      arrayIntList1.ensureCapacity(3);
      assertTrue(arrayIntList0.equals((Object)arrayIntList1));
  }
}
