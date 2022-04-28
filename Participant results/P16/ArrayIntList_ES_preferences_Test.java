/*
 * This file was automatically generated by EvoSuite
 * Thu Dec 16 10:13:06 GMT 2021
 */

package com.org.apache.commons.collections.primitives;

import org.junit.Test;
import static org.junit.Assert.*;
import com.org.apache.commons.collections.primitives.ArrayIntList;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true) 
public class ArrayIntList_ES_preferences_Test extends ArrayIntList_ES_preferences_Test_scaffolding {

  @Test(timeout = 4000)
  public void test0()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList(0);
      arrayIntList0.add(4646);
      arrayIntList0.add(0);
      assertEquals(2, arrayIntList0.size());
  }

  @Test(timeout = 4000)
  public void test1()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList(0);
      arrayIntList0.add(772);
      assertEquals(1, arrayIntList0.size());
      
      int int0 = arrayIntList0.removeElementAt(0);
      assertEquals(772, int0);
  }

  @Test(timeout = 4000)
  public void test2()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList(0);
      arrayIntList0.add(0);
      assertEquals(1, arrayIntList0.size());
      
      arrayIntList0.add(0);
      assertEquals(2, arrayIntList0.size());
      
      arrayIntList0.clear();
      assertEquals(0, arrayIntList0.size());
  }

  @Test(timeout = 4000)
  public void test3()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList(8);
      arrayIntList0.trimToSize();
      arrayIntList0.add(8);
      assertEquals(1, arrayIntList0.size());
  }
}