/*
 * This file was automatically generated by EvoSuite
 * Fri Feb 11 15:48:23 GMT 2022
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
public class ArrayIntList_ES_1_AlreadyValued_Test extends ArrayIntList_ES_1_AlreadyValued_Test_scaffolding {

  @Test(timeout = 4000)
  public void test0()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList();
      arrayIntList0.add(0);
      ArrayIntList arrayIntList1 = new ArrayIntList(0);
      arrayIntList1.addAll(0, (IntCollection) arrayIntList0);
      assertTrue(arrayIntList1.equals((Object)arrayIntList0));
  }
}
