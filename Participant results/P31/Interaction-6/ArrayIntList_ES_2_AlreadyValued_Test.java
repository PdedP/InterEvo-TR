/*
 * This file was automatically generated by EvoSuite
 * Tue Feb 15 11:50:09 GMT 2022
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
public class ArrayIntList_ES_2_AlreadyValued_Test extends ArrayIntList_ES_2_AlreadyValued_Test_scaffolding {

  @Test(timeout = 4000)
  public void test0()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList();
      arrayIntList0.add(0);
      arrayIntList0.add((-13));
      assertEquals(2, arrayIntList0.size());
      
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
}
