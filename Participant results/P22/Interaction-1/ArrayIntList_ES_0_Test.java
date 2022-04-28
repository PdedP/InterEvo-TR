/*
 * This file was automatically generated by EvoSuite
 * Thu Jan 13 17:00:57 GMT 2022
 */

package com.org.apache.commons.collections.primitives;

import org.junit.Test;
import static org.junit.Assert.*;
import com.org.apache.commons.collections.primitives.ArrayIntList;
import com.org.apache.commons.collections.primitives.IntCollection;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true) 
public class ArrayIntList_ES_0_Test extends ArrayIntList_ES_0_Test_scaffolding {

  @Test(timeout = 4000)
  public void test0()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList(3732);
      assertEquals(0, arrayIntList0.size());
      
      ArrayIntList arrayIntList1 = new ArrayIntList(arrayIntList0);
      arrayIntList1.add(0);
      arrayIntList0.addAll(0, (IntCollection) arrayIntList1);
      assertEquals(1, arrayIntList0.size());
      
      int int0 = arrayIntList0.set(0, (-1054));
      assertEquals(0, int0);
  }
}