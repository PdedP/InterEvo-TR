public void test00()  throws Throwable  {
  ArrayIntList arrayIntList0 = new ArrayIntList();
  arrayIntList0.add(2);
  arrayIntList0.add(2);
  arrayIntList0.clear();
  assertEquals(0, arrayIntList0.size());
}
