public void test00()  throws Throwable  {
  ArrayIntList arrayIntList0 = new ArrayIntList();
  arrayIntList0.add((-2701));
  arrayIntList0.add(31);
  ArrayIntList arrayIntList1 = new ArrayIntList(arrayIntList0);
  boolean boolean0 = arrayIntList1.addAll(arrayIntList0);
  assertEquals(2, arrayIntList0.size());
  assertTrue(boolean0);
}

