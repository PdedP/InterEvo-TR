public void test00()  throws Throwable  {
  ArrayIntList arrayIntList0 = new ArrayIntList();
  arrayIntList0.add(65535);
  ArrayIntList arrayIntList1 = new ArrayIntList(arrayIntList0);
  assertTrue(arrayIntList1.equals((Object)arrayIntList0));
}
