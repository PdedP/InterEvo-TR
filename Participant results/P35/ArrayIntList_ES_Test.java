/*
 * This file was automatically generated by EvoSuite
 * Fri Sep 16 08:18:24 GMT 2022
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
public class ArrayIntList_ES_Test extends ArrayIntList_ES_Test_scaffolding {

  @Test(timeout = 4000)
  public void test00()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList(0);
      arrayIntList0.trimToSize();
      assertEquals(0, arrayIntList0.size());
  }

  @Test(timeout = 4000)
  public void test01()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList();
      arrayIntList0.add(8);
      assertEquals(1, arrayIntList0.size());
      
      arrayIntList0.clear();
      assertEquals(0, arrayIntList0.size());
  }

  @Test(timeout = 4000)
  public void test02()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList(1);
      arrayIntList0.add(1);
      arrayIntList0.add((-1));
      assertEquals(2, arrayIntList0.size());
  }

  @Test(timeout = 4000)
  public void test03()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList();
      arrayIntList0.ensureCapacity(18);
      assertEquals(0, arrayIntList0.size());
  }

  @Test(timeout = 4000)
  public void test04()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList(3681);
      // Undeclared exception!
      try { 
        arrayIntList0.set(3681, (-2160));
        fail("Expecting exception: IndexOutOfBoundsException");
      
      } catch(IndexOutOfBoundsException e) {
         //
         // Should be at least 0 and less than 0, found 3681
         //
         verifyException("com.org.apache.commons.collections.primitives.ArrayIntList", e);
      }
  }

  @Test(timeout = 4000)
  public void test05()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList(1);
      arrayIntList0.add(857);
      arrayIntList0.add(1);
      arrayIntList0.ensureCapacity(3);
      assertEquals(2, arrayIntList0.size());
  }

  @Test(timeout = 4000)
  public void test06()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList(1);
      arrayIntList0.add((-1));
      arrayIntList0.add((-604));
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
  public void test07()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList(1);
      arrayIntList0.add((-1));
      arrayIntList0.add(1, 1);
      int int0 = arrayIntList0.removeElementAt(1);
      assertEquals(1, arrayIntList0.size());
      assertEquals(1, int0);
  }

  @Test(timeout = 4000)
  public void test08()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList();
      arrayIntList0.add(0, 1);
      assertEquals(1, arrayIntList0.size());
      
      arrayIntList0.set(0, 8);
  }

  @Test(timeout = 4000)
  public void test09()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList();
      // Undeclared exception!
      try { 
        arrayIntList0.add(637, 415);
        fail("Expecting exception: IndexOutOfBoundsException");
      
      } catch(IndexOutOfBoundsException e) {
         //
         // Should be at least 0 and at most 0, found 637
         //
         verifyException("com.org.apache.commons.collections.primitives.ArrayIntList", e);
      }
  }

  @Test(timeout = 4000)
  public void test10()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList(1);
      // Undeclared exception!
      try { 
        arrayIntList0.add((-2741), 8);
        fail("Expecting exception: IndexOutOfBoundsException");
      
      } catch(IndexOutOfBoundsException e) {
         //
         // Should be at least 0 and at most 0, found -2741
         //
         verifyException("com.org.apache.commons.collections.primitives.ArrayIntList", e);
      }
  }

  @Test(timeout = 4000)
  public void test11()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList(1);
      // Undeclared exception!
      try { 
        arrayIntList0.removeElementAt((-1365));
        fail("Expecting exception: IndexOutOfBoundsException");
      
      } catch(IndexOutOfBoundsException e) {
         //
         // Should be at least 0 and less than 0, found -1365
         //
         verifyException("com.org.apache.commons.collections.primitives.ArrayIntList", e);
      }
  }

  @Test(timeout = 4000)
  public void test12()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList(1);
      arrayIntList0.trimToSize();
      assertEquals(0, arrayIntList0.size());
  }

  @Test(timeout = 4000)
  public void test13()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList();
      arrayIntList0.add(31);
      arrayIntList0.add(0, 1);
      arrayIntList0.clear();
      assertEquals(0, arrayIntList0.size());
  }

  @Test(timeout = 4000)
  public void test14()  throws Throwable  {
      ArrayIntList arrayIntList0 = null;
      try {
        arrayIntList0 = new ArrayIntList((-4011));
        fail("Expecting exception: IllegalArgumentException");
      
      } catch(IllegalArgumentException e) {
         //
         // capacity -4011
         //
         verifyException("com.org.apache.commons.collections.primitives.ArrayIntList", e);
      }
  }

  @Test(timeout = 4000)
  public void test15()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList();
      // Undeclared exception!
      try { 
        arrayIntList0.set(0, 8);
        fail("Expecting exception: IndexOutOfBoundsException");
      
      } catch(IndexOutOfBoundsException e) {
         //
         // Should be at least 0 and less than 0, found 0
         //
         verifyException("com.org.apache.commons.collections.primitives.ArrayIntList", e);
      }
  }

  @Test(timeout = 4000)
  public void test16()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList(1);
      ArrayIntList arrayIntList1 = new ArrayIntList(arrayIntList0);
      assertEquals(0, arrayIntList1.size());
  }
}
