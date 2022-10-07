public void test00()  throws Throwable  {
  ArrayUnsignedShortList arrayUnsignedShortList0 = new ArrayUnsignedShortList();
  ArrayIntList arrayIntList0 = new ArrayIntList(arrayUnsignedShortList0);
  arrayIntList0.add(0);
  arrayIntList0.add(1);
  assertEquals(2, arrayIntList0.size());
  
  int int0 = arrayIntList0.removeElementAt(1);
  assertEquals(1, int0);
}
