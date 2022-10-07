public void test00()  throws Throwable  {
  ArrayUnsignedShortList arrayUnsignedShortList0 = new ArrayUnsignedShortList();
  ArrayIntList arrayIntList0 = new ArrayIntList(arrayUnsignedShortList0);
  arrayIntList0.trimToSize();
  assertEquals(0, arrayIntList0.size());
}

