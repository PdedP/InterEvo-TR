public void test00()  throws Throwable  {
  ArrayIntList arrayIntList0 = new ArrayIntList(8);
  arrayIntList0.add(0);
  assertEquals(1, arrayIntList0.size());
  
  int int0 = arrayIntList0.set(0, 1959);
  assertEquals(0, int0);
}
