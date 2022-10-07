public void test00()  throws Throwable  {
  ArrayIntList arrayIntList0 = new ArrayIntList();
  arrayIntList0.trimToSize();
  arrayIntList0.add((-71));
  arrayIntList0.add((-1320));
  assertEquals(2, arrayIntList0.size());
}
