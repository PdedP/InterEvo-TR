/*
 * This file was automatically generated by EvoSuite
 * Thu Dec 16 09:50:37 GMT 2021
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
      ArrayIntList arrayIntList0 = new ArrayIntList();
      arrayIntList0.ensureCapacity(983);
      assertEquals(0, arrayIntList0.size());
  }

  @Test(timeout = 4000)
  public void test01()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList();
      // Undeclared exception!
      try { 
        arrayIntList0.removeElementAt((-1));
        fail("Expecting exception: IndexOutOfBoundsException");
      
      } catch(IndexOutOfBoundsException e) {
         //
         // Should be at least 0 and less than 0, found -1
         //
         verifyException("com.org.apache.commons.collections.primitives.ArrayIntList", e);
      }
  }

  @Test(timeout = 4000)
  public void test02()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList(0);
      arrayIntList0.ensureCapacity(983);
      assertEquals(0, arrayIntList0.size());
  }

  @Test(timeout = 4000)
  public void test03()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList(0);
      arrayIntList0.trimToSize();
      assertEquals(0, arrayIntList0.size());
  }

  @Test(timeout = 4000)
  public void test04()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList();
      // Undeclared exception!
      try { 
        arrayIntList0.set(1139, 440);
        fail("Expecting exception: IndexOutOfBoundsException");
      
      } catch(IndexOutOfBoundsException e) {
         //
         // Should be at least 0 and less than 0, found 1139
         //
         verifyException("com.org.apache.commons.collections.primitives.ArrayIntList", e);
      }
  }

  @Test(timeout = 4000)
  public void test05()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList();
      arrayIntList0.add(0, 0);
      arrayIntList0.add(0, (-1486));
      assertEquals(2, arrayIntList0.size());
  }

  @Test(timeout = 4000)
  public void test06()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList(2029);
      // Undeclared exception!
      try { 
        arrayIntList0.add((-511), 2029);
        fail("Expecting exception: IndexOutOfBoundsException");
      
      } catch(IndexOutOfBoundsException e) {
         //
         // Should be at least 0 and at most 0, found -511
         //
         verifyException("com.org.apache.commons.collections.primitives.ArrayIntList", e);
      }
  }

  @Test(timeout = 4000)
  public void test07()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList();
      arrayIntList0.add(0, 6730);
      // Undeclared exception!
      try { 
        arrayIntList0.removeElementAt(1);
        fail("Expecting exception: IndexOutOfBoundsException");
      
      } catch(IndexOutOfBoundsException e) {
         //
         // Should be at least 0 and less than 1, found 1
         //
         verifyException("com.org.apache.commons.collections.primitives.ArrayIntList", e);
      }
  }

  @Test(timeout = 4000)
  public void test08()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList(0);
      arrayIntList0.add(0);
      arrayIntList0.add(1);
      arrayIntList0.add(0, 0);
      assertEquals(3, arrayIntList0.size());
  }

  @Test(timeout = 4000)
  public void test09()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList();
      arrayIntList0.add(0, 6730);
      assertEquals(1, arrayIntList0.size());
      
      int int0 = arrayIntList0.set(0, 0);
      assertEquals(6730, int0);
  }

  @Test(timeout = 4000)
  public void test10()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList();
      arrayIntList0.add(0);
      int int0 = arrayIntList0.set(0, 1225);
      assertEquals(1, arrayIntList0.size());
      assertEquals(0, int0);
  }

  @Test(timeout = 4000)
  public void test11()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList(0);
      // Undeclared exception!
      try { 
        arrayIntList0.add(2556, 0);
        fail("Expecting exception: IndexOutOfBoundsException");
      
      } catch(IndexOutOfBoundsException e) {
         //
         // Should be at least 0 and at most 0, found 2556
         //
         verifyException("com.org.apache.commons.collections.primitives.ArrayIntList", e);
      }
  }

  @Test(timeout = 4000)
  public void test12()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList();
      arrayIntList0.add(0, 0);
      ArrayIntList arrayIntList1 = new ArrayIntList(arrayIntList0);
      assertEquals(1, arrayIntList0.size());
      
      arrayIntList0.add(103);
      ArrayIntList arrayIntList2 = new ArrayIntList();
      arrayIntList2.addAll(arrayIntList0);
      arrayIntList2.retainAll(arrayIntList1);
  }

  @Test(timeout = 4000)
  public void test13()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList();
      arrayIntList0.trimToSize();
      assertEquals(0, arrayIntList0.size());
  }

  @Test(timeout = 4000)
  public void test14()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList(0);
      arrayIntList0.add(0);
      assertEquals(1, arrayIntList0.size());
      
      arrayIntList0.add(0, (-3532));
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
  public void test15()  throws Throwable  {
      ArrayIntList arrayIntList0 = null;
      try {
        arrayIntList0 = new ArrayIntList((-631));
        fail("Expecting exception: IllegalArgumentException");
      
      } catch(IllegalArgumentException e) {
         //
         // capacity -631
         //
         verifyException("com.org.apache.commons.collections.primitives.ArrayIntList", e);
      }
  }
}