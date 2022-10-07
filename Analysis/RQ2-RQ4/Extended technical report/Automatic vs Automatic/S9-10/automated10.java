public void test00()  throws Throwable  {
  ArrayIntList arrayIntList0 = new ArrayIntList();
  arrayIntList0.add(0, (-1));
  arrayIntList0.add(0);
  int int0 = arrayIntList0.removeElementAt(1);
  assertEquals(1, arrayIntList0.size());
  assertEquals(0, int0);
}
