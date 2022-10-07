public void test00()  throws Throwable  {
  ArrayIntList arrayIntList0 = new ArrayIntList(3389);
  arrayIntList0.add(3389);
  arrayIntList0.add(0, 634);
  assertEquals(2, arrayIntList0.size());
  
  arrayIntList0.clear();
  assertEquals(0, arrayIntList0.size());
}
