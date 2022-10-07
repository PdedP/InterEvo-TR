public void test00()  throws Throwable  {
  ArrayIntList arrayIntList0 = new ArrayIntList(1054);
  arrayIntList0.trimToSize();
  arrayIntList0.ensureCapacity(1054);
  assertEquals(0, arrayIntList0.size());
}
