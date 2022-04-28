/*
 * This file was automatically generated by EvoSuite
 * Thu Dec 16 09:56:27 GMT 2021
 */

package com.org.apache.commons.collections.primitives;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import com.org.apache.commons.collections.primitives.ArrayIntList;
import java.util.ConcurrentModificationException;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true) 
public class ArrayIntList_ES_preferences_Test extends ArrayIntList_ES_preferences_Test_scaffolding {

  @Test(timeout = 4000)
  public void test0()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList(0);
      assertEquals(0, arrayIntList0.size());
      
      arrayIntList0.add(144);
      ArrayIntList arrayIntList1 = new ArrayIntList(arrayIntList0);
      assertEquals(1, arrayIntList0.size());
  }

  @Test(timeout = 4000)
  public void test1()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList(0);
      arrayIntList0.add(144);
      // Undeclared exception!
      try { 
        arrayIntList0.removeAll(arrayIntList0);
        fail("Expecting exception: ConcurrentModificationException");
      
      } catch(ConcurrentModificationException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("com.org.apache.commons.collections.primitives.RandomAccessIntList$ComodChecker", e);
      }
  }

  @Test(timeout = 4000)
  public void test2()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList(0);
      // Undeclared exception!
      try { 
        arrayIntList0.removeElementAt(0);
        fail("Expecting exception: IndexOutOfBoundsException");
      
      } catch(IndexOutOfBoundsException e) {
         //
         // Should be at least 0 and less than 0, found 0
         //
         verifyException("com.org.apache.commons.collections.primitives.ArrayIntList", e);
      }
  }

  @Test(timeout = 4000)
  public void test3()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList(0);
      arrayIntList0.ensureCapacity(144);
      assertEquals(0, arrayIntList0.size());
  }

  @Test(timeout = 4000)
  public void test4()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList(0);
      arrayIntList0.trimToSize();
      assertEquals(0, arrayIntList0.size());
  }

  @Test(timeout = 4000)
  public void test5()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList();
      arrayIntList0.ensureCapacity(1061);
      assertEquals(0, arrayIntList0.size());
  }

  @Test(timeout = 4000)
  public void test6()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList(0);
      arrayIntList0.add(0);
      assertEquals(1, arrayIntList0.size());
  }
}