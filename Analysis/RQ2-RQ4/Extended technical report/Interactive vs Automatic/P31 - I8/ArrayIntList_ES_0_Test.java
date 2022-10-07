public void test00()  throws Throwable  {
  ArrayIntList arrayIntList0 = new ArrayIntList(576);
  // Undeclared exception!
  try { 
	arrayIntList0.add(3353, 0);
	fail("Expecting exception: IndexOutOfBoundsException");
  
  } catch(IndexOutOfBoundsException e) {
	 //
	 // Should be at least 0 and at most 0, found 3353
	 //
	 verifyException("com.org.apache.commons.collections.primitives.ArrayIntList", e);
  }
}
