/*
 * This file was automatically generated by EvoSuite
 * Thu Sep 15 16:57:32 GMT 2022
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
      ArrayIntList arrayIntList0 = new ArrayIntList();
      arrayIntList0.add(0);
      int int0 = arrayIntList0.set(0, 2);
      assertEquals(0, int0);
  }

  @Test(timeout = 4000)
  public void test01()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList();
      arrayIntList0.add(0, 1);
      arrayIntList0.add(0);
      int int0 = arrayIntList0.removeElementAt(1);
      assertEquals(1, arrayIntList0.size());
      assertEquals(0, int0);
  }

  @Test(timeout = 4000)
  public void test02()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList(0);
      arrayIntList0.add(0);
      arrayIntList0.add(1);
      arrayIntList0.set(0, 2);
      arrayIntList0.clear();
      assertEquals(0, arrayIntList0.size());
  }

  @Test(timeout = 4000)
  public void test03()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList(0);
      arrayIntList0.add(0, 1);
      arrayIntList0.add(1, (-1313));
      arrayIntList0.add(1);
      assertEquals(3, arrayIntList0.size());
  }

  @Test(timeout = 4000)
  public void test04()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList(0);
      // Undeclared exception!
      try { 
        arrayIntList0.set(0, 0);
        fail("Expecting exception: IndexOutOfBoundsException");
      
      } catch(IndexOutOfBoundsException e) {
         //
         // Should be at least 0 and less than 0, found 0
         //
         verifyException("com.org.apache.commons.collections.primitives.ArrayIntList", e);
      }
  }

  @Test(timeout = 4000)
  public void test05()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList(0);
      arrayIntList0.trimToSize();
      assertEquals(0, arrayIntList0.size());
  }

  @Test(timeout = 4000)
  public void test06()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList();
      arrayIntList0.ensureCapacity(281);
      assertEquals(0, arrayIntList0.size());
  }

  @Test(timeout = 4000)
  public void test07()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList(0);
      arrayIntList0.add(0, 1);
      ArrayIntList arrayIntList1 = new ArrayIntList(arrayIntList0);
      assertEquals(1, arrayIntList0.size());
      assertTrue(arrayIntList1.equals((Object)arrayIntList0));
  }

  @Test(timeout = 4000)
  public void test08()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList(0);
      arrayIntList0.add(0, 1);
      arrayIntList0.add(0);
      int int0 = arrayIntList0.set(1, 1);
      assertEquals(2, arrayIntList0.size());
      assertEquals(0, int0);
  }

  @Test(timeout = 4000)
  public void test09()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList(0);
      // Undeclared exception!
      try { 
        arrayIntList0.add(1, (-1313));
        fail("Expecting exception: IndexOutOfBoundsException");
      
      } catch(IndexOutOfBoundsException e) {
         //
         // Should be at least 0 and at most 0, found 1
         //
         verifyException("com.org.apache.commons.collections.primitives.ArrayIntList", e);
      }
  }

  @Test(timeout = 4000)
  public void test10()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList();
      // Undeclared exception!
      try { 
        arrayIntList0.add((-767), (-767));
        fail("Expecting exception: IndexOutOfBoundsException");
      
      } catch(IndexOutOfBoundsException e) {
         //
         // Should be at least 0 and at most 0, found -767
         //
         verifyException("com.org.apache.commons.collections.primitives.ArrayIntList", e);
      }
  }

  @Test(timeout = 4000)
  public void test11()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList(0);
      // Undeclared exception!
      try { 
        arrayIntList0.removeElementAt(1489);
        fail("Expecting exception: IndexOutOfBoundsException");
      
      } catch(IndexOutOfBoundsException e) {
         //
         // Should be at least 0 and less than 0, found 1489
         //
         verifyException("com.org.apache.commons.collections.primitives.ArrayIntList", e);
      }
  }

  @Test(timeout = 4000)
  public void test12()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList();
      // Undeclared exception!
      try { 
        arrayIntList0.removeElementAt((-3076));
        fail("Expecting exception: IndexOutOfBoundsException");
      
      } catch(IndexOutOfBoundsException e) {
         //
         // Should be at least 0 and less than 0, found -3076
         //
         verifyException("com.org.apache.commons.collections.primitives.ArrayIntList", e);
      }
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
      arrayIntList0.add(0, 20);
      assertEquals(2, arrayIntList0.size());
  }

  @Test(timeout = 4000)
  public void test15()  throws Throwable  {
      ArrayIntList arrayIntList0 = null;
      try {
        arrayIntList0 = new ArrayIntList((-2934));
        fail("Expecting exception: IllegalArgumentException");
      
      } catch(IllegalArgumentException e) {
         //
         // capacity -2934
         //
         verifyException("com.org.apache.commons.collections.primitives.ArrayIntList", e);
      }
  }
}
