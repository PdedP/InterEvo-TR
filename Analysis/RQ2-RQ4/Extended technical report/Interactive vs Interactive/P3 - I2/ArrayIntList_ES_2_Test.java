public void test00()  throws Throwable  {
  ArrayIntList arrayIntList0 = new ArrayIntList();
  arrayIntList0.trimToSize();
  boolean boolean0 = arrayIntList0.add(0);
  boolean boolean1 = arrayIntList0.add((-1320));
  assertTrue(boolean1 == boolean0);
}
