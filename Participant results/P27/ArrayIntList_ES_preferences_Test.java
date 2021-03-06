/*
 * This file was automatically generated by EvoSuite
 * Thu Jan 20 17:27:31 GMT 2022
 */

package com.org.apache.commons.collections.primitives;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import com.org.apache.commons.collections.primitives.ArrayIntList;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true) 
public class ArrayIntList_ES_preferences_Test extends ArrayIntList_ES_preferences_Test_scaffolding {

  @Test(timeout = 4000)
  public void test0()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList();
      arrayIntList0.ensureCapacity(920);
      assertEquals(0, arrayIntList0.size());
  }

  @Test(timeout = 4000)
  public void test1()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList();
      arrayIntList0.add(0, 0);
      arrayIntList0.add(3);
      assertEquals(2, arrayIntList0.size());
  }

  @Test(timeout = 4000)
  public void test2()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList();
      arrayIntList0.add(0, 695);
      assertEquals(1, arrayIntList0.size());
      
      int int0 = arrayIntList0.removeElementAt(0);
      assertEquals(695, int0);
  }

  @Test(timeout = 4000)
  public void test3()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList();
      arrayIntList0.add(0, 11);
      int int0 = arrayIntList0.removeElementAt(0);
      assertEquals(0, arrayIntList0.size());
      assertEquals(11, int0);
  }

  @Test(timeout = 4000)
  public void test4()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList(31);
      // Undeclared exception!
      try { 
        arrayIntList0.add(31, 1);
        fail("Expecting exception: IndexOutOfBoundsException");
      
      } catch(IndexOutOfBoundsException e) {
         //
         // Should be at least 0 and at most 0, found 31
         //
         verifyException("com.org.apache.commons.collections.primitives.ArrayIntList", e);
      }
  }

  @Test(timeout = 4000)
  public void test5()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList(0);
      arrayIntList0.trimToSize();
      assertEquals(0, arrayIntList0.size());
  }

  @Test(timeout = 4000)
  public void test6()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList(0);
      arrayIntList0.ensureCapacity(1603);
      assertEquals(0, arrayIntList0.size());
  }
}
