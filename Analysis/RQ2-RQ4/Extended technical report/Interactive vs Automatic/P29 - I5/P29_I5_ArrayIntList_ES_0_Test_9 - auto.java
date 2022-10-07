public void test00()  throws Throwable  {
  ArrayIntList arrayIntList0 = new ArrayIntList(3389);
  arrayIntList0.trimToSize();
  arrayIntList0.ensureCapacity(3389);
  assertEquals(0, arrayIntList0.size());
}