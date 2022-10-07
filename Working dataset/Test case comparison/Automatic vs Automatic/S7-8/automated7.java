public void test00()  throws Throwable  {
  ArrayIntList arrayIntList0 = new ArrayIntList();
  // Undeclared exception!
  try { 
	arrayIntList0.add((-887), (-887));
	fail("Expecting exception: IndexOutOfBoundsException");
  
  } catch(IndexOutOfBoundsException e) {
	 //
	 // Should be at least 0 and at most 0, found -887
	 //
	 verifyException("org.apache.commons.collections.primitives.ArrayIntList", e);
  }
}
