public void test00()  throws Throwable  {
  ArrayIntList arrayIntList0 = new ArrayIntList(0);
  arrayIntList0.add(0);
  ArrayIntList arrayIntList1 = new ArrayIntList(4394);
  arrayIntList0.retainAll(arrayIntList1);
  arrayIntList0.trimToSize();
  assertNotSame(arrayIntList0, arrayIntList1);
}
