public void test00()  throws Throwable  {
  ArrayIntList arrayIntList0 = new ArrayIntList(0);
  arrayIntList0.trimToSize();
  assertEquals(0, arrayIntList0.size());
}
