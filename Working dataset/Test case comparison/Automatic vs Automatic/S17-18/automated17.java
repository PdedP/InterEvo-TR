public void test00()  throws Throwable  {
  ArrayIntList arrayIntList0 = new ArrayIntList();
  arrayIntList0.add(2);
  assertEquals(1, arrayIntList0.size());
  
  arrayIntList0.add(1, 0);
  arrayIntList0.clear();
  assertEquals(0, arrayIntList0.size());
}