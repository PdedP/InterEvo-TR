public void test00()  throws Throwable  {
  ArrayIntList arrayIntList0 = new ArrayIntList(0);
  arrayIntList0.add(0, 65535);
  arrayIntList0.add(0, 0);
  arrayIntList0.removeElementAt(1);
  arrayIntList0.trimToSize();
  assertEquals(1, arrayIntList0.size());
}
