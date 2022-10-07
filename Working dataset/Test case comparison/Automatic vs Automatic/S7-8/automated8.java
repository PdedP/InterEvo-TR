public void test00()  throws Throwable  {
  ArrayUnsignedShortList arrayUnsignedShortList0 = new ArrayUnsignedShortList();
  ArrayIntList arrayIntList0 = new ArrayIntList(arrayUnsignedShortList0);
  // Undeclared exception!
  try { 
	arrayIntList0.add((-1703), 304);
	fail("Expecting exception: IndexOutOfBoundsException");
  
  } catch(IndexOutOfBoundsException e) {
	 //
	 // Should be at least 0 and at most 0, found -1703
	 //
	 verifyException("org.apache.commons.collections.primitives.ArrayIntList", e);
  }
}
