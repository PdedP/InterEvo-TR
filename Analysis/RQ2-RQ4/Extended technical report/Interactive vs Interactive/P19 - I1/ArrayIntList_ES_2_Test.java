public void test00()  throws Throwable  {
  ArrayIntList arrayIntList0 = new ArrayIntList();
  arrayIntList0.add(0);
  // Undeclared exception!
  try { 
	arrayIntList0.addAll(arrayIntList0);
	fail("Expecting exception: ConcurrentModificationException");
  
  } catch(ConcurrentModificationException e) {
	 //
	 // no message in exception (getMessage() returned null)
	 //
	 verifyException("com.org.apache.commons.collections.primitives.RandomAccessIntList$ComodChecker", e);
  }
}

