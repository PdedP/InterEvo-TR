/*
 * This file was automatically generated by EvoSuite
 * Mon Jan 31 17:31:23 GMT 2022
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
public class ArrayIntList_ES_Test extends ArrayIntList_ES_Test_scaffolding {

  @Test(timeout = 4000)
  public void test00()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList(0);
      arrayIntList0.add((-4191));
      arrayIntList0.add((-1));
      arrayIntList0.clear();
      assertEquals(0, arrayIntList0.size());
  }

  @Test(timeout = 4000)
  public void test01()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList();
      arrayIntList0.add(440);
      arrayIntList0.add((-2181));
      assertEquals(2, arrayIntList0.size());
  }

  @Test(timeout = 4000)
  public void test02()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList();
      arrayIntList0.add((-4191));
      assertEquals(1, arrayIntList0.size());
      
      int int0 = arrayIntList0.set(0, 0);
      assertEquals((-4191), int0);
  }

  @Test(timeout = 4000)
  public void test03()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList();
      // Undeclared exception!
      try { 
        arrayIntList0.add((-2), (-2));
        fail("Expecting exception: IndexOutOfBoundsException");
      
      } catch(IndexOutOfBoundsException e) {
         //
         // Should be at least 0 and at most 0, found -2
         //
         verifyException("com.org.apache.commons.collections.primitives.ArrayIntList", e);
      }
  }

  @Test(timeout = 4000)
  public void test04()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList(1625);
      arrayIntList0.add(1625);
      arrayIntList0.add(0, (-181));
      assertEquals(2, arrayIntList0.size());
  }

  @Test(timeout = 4000)
  public void test05()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList(30);
      // Undeclared exception!
      try { 
        arrayIntList0.removeElementAt(350);
        fail("Expecting exception: IndexOutOfBoundsException");
      
      } catch(IndexOutOfBoundsException e) {
         //
         // Should be at least 0 and less than 0, found 350
         //
         verifyException("com.org.apache.commons.collections.primitives.ArrayIntList", e);
      }
  }

  @Test(timeout = 4000)
  public void test06()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList(30);
      arrayIntList0.ensureCapacity(31);
      assertEquals(0, arrayIntList0.size());
  }

  @Test(timeout = 4000)
  public void test07()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList(0);
      assertEquals(0, arrayIntList0.size());
      
      arrayIntList0.add((-4191));
      ArrayIntList arrayIntList1 = new ArrayIntList(arrayIntList0);
      assertEquals(1, arrayIntList0.size());
      assertTrue(arrayIntList1.equals((Object)arrayIntList0));
  }

  @Test(timeout = 4000)
  public void test08()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList(30);
      arrayIntList0.add(350);
      int int0 = arrayIntList0.set(0, (-739));
      assertEquals(1, arrayIntList0.size());
      assertEquals(350, int0);
  }

  @Test(timeout = 4000)
  public void test09()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList(0);
      // Undeclared exception!
      try { 
        arrayIntList0.set(0, (-2989));
        fail("Expecting exception: IndexOutOfBoundsException");
      
      } catch(IndexOutOfBoundsException e) {
         //
         // Should be at least 0 and less than 0, found 0
         //
         verifyException("com.org.apache.commons.collections.primitives.ArrayIntList", e);
      }
  }

  @Test(timeout = 4000)
  public void test10()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList(0);
      arrayIntList0.add((-4191));
      arrayIntList0.add(28);
      int int0 = arrayIntList0.removeElementAt(1);
      assertEquals(1, arrayIntList0.size());
      assertEquals(28, int0);
  }

  @Test(timeout = 4000)
  public void test11()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList();
      // Undeclared exception!
      try { 
        arrayIntList0.add(31, 31);
        fail("Expecting exception: IndexOutOfBoundsException");
      
      } catch(IndexOutOfBoundsException e) {
         //
         // Should be at least 0 and at most 0, found 31
         //
         verifyException("com.org.apache.commons.collections.primitives.ArrayIntList", e);
      }
  }

  @Test(timeout = 4000)
  public void test12()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList(0);
      // Undeclared exception!
      try { 
        arrayIntList0.removeElementAt((-4191));
        fail("Expecting exception: IndexOutOfBoundsException");
      
      } catch(IndexOutOfBoundsException e) {
         //
         // Should be at least 0 and less than 0, found -4191
         //
         verifyException("com.org.apache.commons.collections.primitives.ArrayIntList", e);
      }
  }

  @Test(timeout = 4000)
  public void test13()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList(0);
      arrayIntList0.trimToSize();
      assertEquals(0, arrayIntList0.size());
  }

  @Test(timeout = 4000)
  public void test14()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList(0);
      arrayIntList0.ensureCapacity(31);
      arrayIntList0.trimToSize();
      assertEquals(0, arrayIntList0.size());
  }

  @Test(timeout = 4000)
  public void test15()  throws Throwable  {
      ArrayIntList arrayIntList0 = null;
      try {
        arrayIntList0 = new ArrayIntList((-796));
        fail("Expecting exception: IllegalArgumentException");
      
      } catch(IllegalArgumentException e) {
         //
         // capacity -796
         //
         verifyException("com.org.apache.commons.collections.primitives.ArrayIntList", e);
      }
  }
}