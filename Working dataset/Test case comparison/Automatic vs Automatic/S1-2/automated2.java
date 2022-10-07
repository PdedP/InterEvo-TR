public void test00()  throws Throwable  {
  ArrayIntList arrayIntList0 = new ArrayIntList();
  arrayIntList0.add(0);
  arrayIntList0.add(0, 3);
  int int0 = arrayIntList0.removeElementAt(0);
  assertEquals(1, arrayIntList0.size());
  assertEquals(3, int0);
}

