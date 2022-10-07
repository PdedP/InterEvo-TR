/*
 * This file was automatically generated by EvoSuite
 * Wed Jul 27 10:37:28 GMT 2022
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
      arrayIntList0.add(1408);
      arrayIntList0.add(616);
      assertEquals(2, arrayIntList0.size());
      
      int int0 = arrayIntList0.removeElementAt(1);
      assertEquals(1, arrayIntList0.size());
      assertEquals(616, int0);
  }

  @Test(timeout = 4000)
  public void test1()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList();
      arrayIntList0.trimToSize();
      arrayIntList0.ensureCapacity(11);
      assertEquals(0, arrayIntList0.size());
  }

  @Test(timeout = 4000)
  public void test2()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList();
      // Undeclared exception!
      try { 
        arrayIntList0.add((-2458), 1);
        fail("Expecting exception: IndexOutOfBoundsException");
      
      } catch(IndexOutOfBoundsException e) {
         //
         // Should be at least 0 and at most 0, found -2458
         //
         verifyException("com.org.apache.commons.collections.primitives.ArrayIntList", e);
      }
  }

  @Test(timeout = 4000)
  public void test3()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList();
      arrayIntList0.ensureCapacity(32);
      assertEquals(0, arrayIntList0.size());
  }

  @Test(timeout = 4000)
  public void test4()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList();
      arrayIntList0.trimToSize();
      arrayIntList0.add(1408);
      arrayIntList0.add(616);
      assertEquals(2, arrayIntList0.size());
  }

  @Test(timeout = 4000)
  public void test5()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList(19);
      // Undeclared exception!
      try { 
        arrayIntList0.add((-4830), (-3769));
        fail("Expecting exception: IndexOutOfBoundsException");
      
      } catch(IndexOutOfBoundsException e) {
         //
         // Should be at least 0 and at most 0, found -4830
         //
         verifyException("com.org.apache.commons.collections.primitives.ArrayIntList", e);
      }
  }

  @Test(timeout = 4000)
  public void test6()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList();
      arrayIntList0.ensureCapacity(11);
      assertEquals(0, arrayIntList0.size());
  }

  @Test(timeout = 4000)
  public void test7()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList();
      // Undeclared exception!
      try { 
        arrayIntList0.add(632, 632);
        fail("Expecting exception: IndexOutOfBoundsException");
      
      } catch(IndexOutOfBoundsException e) {
         //
         // Should be at least 0 and at most 0, found 632
         //
         verifyException("com.org.apache.commons.collections.primitives.ArrayIntList", e);
      }
  }

  @Test(timeout = 4000)
  public void test8()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList();
      arrayIntList0.trimToSize();
      arrayIntList0.ensureCapacity(0);
      assertEquals(0, arrayIntList0.size());
  }
}