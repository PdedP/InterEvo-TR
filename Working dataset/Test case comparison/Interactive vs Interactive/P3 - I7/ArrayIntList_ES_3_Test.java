public void test00()  throws Throwable  {
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
