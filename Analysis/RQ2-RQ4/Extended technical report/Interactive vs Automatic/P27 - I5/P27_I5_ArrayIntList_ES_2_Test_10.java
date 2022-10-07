public void test00()  throws Throwable  {
  ArrayIntList arrayIntList0 = new ArrayIntList();
  arrayIntList0.add(0, 11);
  int int0 = arrayIntList0.removeElementAt(0);
  assertEquals(0, arrayIntList0.size());
  assertEquals(11, int0);
}
