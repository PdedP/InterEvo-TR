/*
 * This file was automatically generated by EvoSuite
 * Thu Dec 16 09:47:40 GMT 2021
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
      ArrayIntList arrayIntList0 = new ArrayIntList(0);
      arrayIntList0.add(0);
      arrayIntList0.toString();
      assertEquals(1, arrayIntList0.size());
  }
}
