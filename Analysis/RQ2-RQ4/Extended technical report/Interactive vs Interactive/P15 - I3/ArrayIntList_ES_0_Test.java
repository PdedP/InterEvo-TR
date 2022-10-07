public void test00()  throws Throwable  {
  ArrayIntList arrayIntList0 = new ArrayIntList(0);
  arrayIntList0.add(8);
  assertEquals(1, arrayIntList0.size());
  
  int int0 = arrayIntList0.removeElementAt(0);
  assertEquals(8, int0);
}
