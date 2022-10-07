public void test00()  throws Throwable  {
  ArrayIntList arrayIntList0 = new ArrayIntList();
  arrayIntList0.add(1);
  arrayIntList0.add(1);
  assertEquals(2, arrayIntList0.size());
  
  ArrayIntList arrayIntList1 = new ArrayIntList();
  boolean boolean0 = arrayIntList0.retainAll(arrayIntList1);
  assertTrue(boolean0);
}
