/*
 * This file was automatically generated by EvoSuite
 * Tue Jan 25 10:35:38 GMT 2022
 */

package com.org.apache.commons.collections.primitives;

import org.junit.Test;
import static org.junit.Assert.*;
import com.org.apache.commons.collections.primitives.ArrayIntList;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true) 
public class ArrayIntList_ES_0_AlreadyValued_Test extends ArrayIntList_ES_0_AlreadyValued_Test_scaffolding {

  @Test(timeout = 4000)
  public void test0()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList();
      arrayIntList0.add(0, 0);
      arrayIntList0.add(0);
      assertEquals(2, arrayIntList0.size());
      
      arrayIntList0.clear();
      assertEquals(0, arrayIntList0.size());
  }
}
