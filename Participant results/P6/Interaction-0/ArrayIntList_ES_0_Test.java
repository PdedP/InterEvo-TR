/*
 * This file was automatically generated by EvoSuite
 * Tue Dec 14 12:06:51 GMT 2021
 */

package com.org.apache.commons.collections.primitives;

import org.junit.Test;
import static org.junit.Assert.*;
import com.org.apache.commons.collections.primitives.ArrayIntList;
import com.org.apache.commons.collections.primitives.RandomAccessIntList;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true) 
public class ArrayIntList_ES_0_Test extends ArrayIntList_ES_0_Test_scaffolding {

  @Test(timeout = 4000)
  public void test0()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList();
      RandomAccessIntList.RandomAccessIntSubList randomAccessIntList_RandomAccessIntSubList0 = new RandomAccessIntList.RandomAccessIntSubList(arrayIntList0, 0, 0);
      randomAccessIntList_RandomAccessIntSubList0.add((-869));
      int int0 = arrayIntList0.set(0, 0);
      assertEquals(1, arrayIntList0.size());
      assertEquals((-869), int0);
  }
}
