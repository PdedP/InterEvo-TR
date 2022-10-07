public void test00()  throws Throwable  {
  ArrayIntList arrayIntList0 = new ArrayIntList();
  ArrayIntList arrayIntList1 = new ArrayIntList(arrayIntList0);
  assertEquals(0, arrayIntList1.size());
}
