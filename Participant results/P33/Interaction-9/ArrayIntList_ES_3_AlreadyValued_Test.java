/*
 * This file was automatically generated by EvoSuite
 * Wed Jul 27 10:28:11 GMT 2022
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
public class ArrayIntList_ES_3_AlreadyValued_Test extends ArrayIntList_ES_3_AlreadyValued_Test_scaffolding {

  @Test(timeout = 4000)
  public void test0()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList(0);
      // Undeclared exception!
      try { 
        arrayIntList0.set((-1388), 0);
        fail("Expecting exception: IndexOutOfBoundsException");
      
      } catch(IndexOutOfBoundsException e) {
         //
         // Should be at least 0 and less than 0, found -1388
         //
         verifyException("com.org.apache.commons.collections.primitives.ArrayIntList", e);
      }
  }
}
