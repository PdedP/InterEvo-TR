public void test00()  throws Throwable  {
  ArrayIntList arrayIntList0 = new ArrayIntList();
  arrayIntList0.add(282);
  assertEquals(1, arrayIntList0.size());
  
  int int0 = arrayIntList0.removeElementAt(0);
  assertEquals(282, int0);
}
