/*
 * This file was automatically generated by EvoSuite
 * Thu Dec 16 10:02:22 GMT 2021
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
      arrayIntList0.add(0);
      arrayIntList0.add(0, 0);
      assertEquals(2, arrayIntList0.size());
  }

  @Test(timeout = 4000)
  public void test1()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList();
      arrayIntList0.add(0, (-1797));
      arrayIntList0.clear();
      assertEquals(0, arrayIntList0.size());
  }

  @Test(timeout = 4000)
  public void test2()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList(31);
      arrayIntList0.add(31);
      arrayIntList0.add((-2363));
      assertEquals(2, arrayIntList0.size());
  }

  @Test(timeout = 4000)
  public void test3()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList();
      arrayIntList0.add((-1797));
      arrayIntList0.add(6214);
      assertEquals(2, arrayIntList0.size());
  }

  @Test(timeout = 4000)
  public void test4()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList();
      arrayIntList0.add(31);
      ArrayIntList arrayIntList1 = new ArrayIntList(arrayIntList0);
      assertTrue(arrayIntList1.equals((Object)arrayIntList0));
  }

  @Test(timeout = 4000)
  public void test5()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList(0);
      arrayIntList0.add(0, 0);
      int int0 = arrayIntList0.removeElementAt(0);
      assertEquals(0, arrayIntList0.size());
      assertEquals(0, int0);
  }

  @Test(timeout = 4000)
  public void test6()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList();
      arrayIntList0.ensureCapacity(947);
      assertEquals(0, arrayIntList0.size());
  }

  @Test(timeout = 4000)
  public void test7()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList(0);
      arrayIntList0.trimToSize();
      assertEquals(0, arrayIntList0.size());
  }
}
