public void test00()  throws Throwable  {
  ArrayIntList arrayIntList0 = new ArrayIntList(0);
  // Undeclared exception!
  try { 
	arrayIntList0.set(0, (-3154));
	fail("Expecting exception: IndexOutOfBoundsException");
  
  } catch(IndexOutOfBoundsException e) {
	 //
	 // Should be at least 0 and less than 0, found 0
	 //
	 verifyException("org.apache.commons.collections.primitives.ArrayIntList", e);
  }
}