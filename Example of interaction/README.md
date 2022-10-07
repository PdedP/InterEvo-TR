

# Example of interactive execution

This document shows the complete interaction process of a user evaluting test cases for the class [ArrayIntList](https://github.com/PdedP/InterEvo-TR/blob/master/Working%20dataset/SUT/ArrayIntList.java) with InterEvo-TR. More specifically, this document focuses on the interventions of participant #2 in this study.

## P2 review
Participant #2 interacted 10 times with InterEvo-TR (numbered 0-9). It took him/her a total interaction time of 11 minutes and 50 seconds.  At the end of the process, the [preference archive](https://github.com/PdedP/InterEvo-TR/blob/master/Participant%20results/P2/ArrayIntList_ES_preferences_Test.java) contained 8 test cases. Below, this document shows the sequence of interventions, interaction by interaction.

#### Interaction 0

In the first interaction, InterEvo-TR shows the tester the two test cases and the target below. After 211 seconds, the tester assigns **both test cases a readability score of 1**. 
```c++
  public void test0()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList();
      arrayIntList0.trimToSize();
      assertEquals(0, arrayIntList0.size());
      
      arrayIntList0.add(1);
      assertEquals(1, arrayIntList0.size());
  }
```
> *First test: readability score 1*

```c++
  public void test0()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList(0);
      arrayIntList0.add(0);
      assertEquals(1, arrayIntList0.size());
  }
```
> *Second test: readability score 1*
```
Mutation (217): 
Class: ArrayIntList
Method: ensureCapacity(I)V:
Line: 181
Type of mutation: InsertUnaryOp
```
> *Target*

#### Interaction 1

In the second interaction, InterEvo-TR shows the tester the two test cases and the target below. After 117 seconds, the tester assigns **both test cases the readability score 7**. 
```c++
  public void test0()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList();
      // Undeclared exception!
      try { 
        arrayIntList0.get(0);
        fail("Expecting exception: IndexOutOfBoundsException");
      
      } catch(IndexOutOfBoundsException e) {
         //
         // Should be at least 0 and less than 0, found 0
         //
         verifyException("com.org.apache.commons.collections.primitives.ArrayIntList", e);
      }
  }
```
> *First test: readability score 7*

```c++
  public void test0()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList();
      // Undeclared exception!
      try { 
        arrayIntList0.removeElementAt(0);
        fail("Expecting exception: IndexOutOfBoundsException");
      
      } catch(IndexOutOfBoundsException e) {
         //
         // Should be at least 0 and less than 0, found 0
         //
         verifyException("com.org.apache.commons.collections.primitives.ArrayIntList", e);
      }
  }
```
> *Second test: readability score 7*
```
Mutation (251): 
Class: ArrayIntList
Method: checkRange(I)V:
Line: 202
Type of mutation: ReplaceComparisonOperator
```
> *Target*

#### Interaction 2
In the third interaction, InterEvo-TR shows the tester the two test cases and the target below. After 69 seconds, the tester assigns the **first test case a readability score of 10**, while the **second test case is assigned a score of 9**.

```c++
  public void test0()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList();
      arrayIntList0.add(1);
      arrayIntList0.add(2384);
      boolean boolean0 = arrayIntList0.removeElement(1);
      assertEquals(1, arrayIntList0.size());
      assertTrue(boolean0);
  }
```
> *First test: readability score 10*

```c++
  public void test0()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList();
      arrayIntList0.add(0, 1);
      arrayIntList0.add(0);
      int int0 = arrayIntList0.removeElementAt(0);
      assertEquals(1, arrayIntList0.size());
      assertEquals(1, int0);
  }
```
> *Second test: readability score 9*
```
Mutation (73): 
Class: ArrayIntList
Method: removeElementAt(I)I:
Line: 131
Type of mutation: InsertUnaryOp
```
> *Target*

#### Interaction 3
In the fourth interaction, InterEvo-TR shows the tester an already-evaluated test (*Interaction 0 - test 2, score 1*) and requests the revision of another one regarding the target below. After 66 seconds, the tester assigns the test case a **readability score of 5**.
```c++
  public void test0()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList();
      arrayIntList0.add(0, 1);
      arrayIntList0.removeElementAt(0);
      arrayIntList0.trimToSize();
      arrayIntList0.add(1);
      assertEquals(1, arrayIntList0.size());
  }
```
> *Test case: readability score 5*
```
Mutation (192): 
Class: ArrayIntList
Method: ensureCapacity(I)V:
Line: 179
Type of mutation: ReplaceArithmeticOperator
```
> *Target*

#### Interaction 4
In the fifth interaction, InterEvo-TR shows the tester the two test cases and the target below. After 34 seconds, the tester assigns the **first test case a readability score of 9**, while the **second test case is assigned a score of 10**.

```c++
  public void test0()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList();
      arrayIntList0.add(0, 1);
      arrayIntList0.add(0);
      assertEquals(2, arrayIntList0.size());
  }
```
> *First test: readability score 9*

```c++
  public void test0()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList();
      arrayIntList0.add(1);
      arrayIntList0.add(2384);
      assertEquals(2, arrayIntList0.size());
  }
```
> *Second test: readability score 10*
```
Mutation (153): 
Class: ArrayIntList
Method: add(II)V:
Line: 164
Type of mutation: ReplaceVariable
```
> *Target*

#### Interaction 5
In the sixth interaction, InterEvo-TR shows the tester the two test cases and the target below. After 71 seconds, the tester assigns the **first test case a readability score of 7**, while the **second test case is assigned a score of 6**.

```c++
  public void test0()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList(1953);
      // Undeclared exception!
      try { 
        arrayIntList0.get(1953);
        fail("Expecting exception: IndexOutOfBoundsException");
      
      } catch(IndexOutOfBoundsException e) {
         //
         // Should be at least 0 and less than 0, found 1953
         //
         verifyException("com.org.apache.commons.collections.primitives.ArrayIntList", e);
      }
  }
```
> *First test: readability score 7*

```c++
  public void test0()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList();
      arrayIntList0.add(0, 1);
      arrayIntList0.removeElementAt(0);
      // Undeclared exception!
      try { 
        arrayIntList0.get(1);
        fail("Expecting exception: IndexOutOfBoundsException");
      
      } catch(IndexOutOfBoundsException e) {
         //
         // Should be at least 0 and less than 0, found 1
         //
         verifyException("com.org.apache.commons.collections.primitives.ArrayIntList", e);
      }
  }
```
> *Second test: readability score 6*
```
Mutation (249): 
Class: ArrayIntList
Method: checkRange(I)V:
Line: 202
Type of mutation: ReplaceComparisonOperator
```
> *Target*

#### Interaction 6
In the seventh interaction, InterEvo-TR InterEvo-TR shows the tester an already evaluated test (Interaction 0 - test 1, score 1) and requests the evaluation of the following test case regarding the target below. After 56 seconds, the tester assigns the test case a **readability score of 5**.

```c++
  public void test0()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList();
      arrayIntList0.ensureCapacity(9);
      assertEquals(0, arrayIntList0.size());
  }
```
> *Test case: readability score 5*
```
Branch target (4): 
Class: ArrayIntList
Method: ensureCapacity(I)V
Line: 181
Branch: IF_ICMPGE - true
```
> *Target*

#### Interaction 7
In the eighth interaction, InterEvo-TR shows the tester two already-evaluated tests (*Interaction 1,  test 1 and 2,  both with score 7*) and requests the evaluation of the following test case regarding the target below. After 31 seconds, the tester assigns the test case a **readability score of 7**.

```c++
  public void test0()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList();
      // Undeclared exception!
      try { 
        arrayIntList0.get(1);
        fail("Expecting exception: IndexOutOfBoundsException");
      
      } catch(IndexOutOfBoundsException e) {
         //
         // Should be at least 0 and less than 0, found 1
         //
         verifyException("com.org.apache.commons.collections.primitives.ArrayIntList", e);
      }
  }
```
> *Test case: readability score 7*
```
Branch target (7): 
Class: ArrayIntList
Method: checkRange(I)V
Line: 202
Branch: IF_ICMPLT - false
```
> *Target*

#### Interaction 8
In the ninth interaction, InterEvo-TR shows the tester an already-evaluated test (Interaction 4 - test 1, score 9) and requests the evaluation of the following test case regarding the target below. After 62 seconds, the tester assigns the test case a **readability score of 7**.

```c++
  public void test0()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList();
      // Undeclared exception!
      try { 
        arrayIntList0.add(2, 2);
        fail("Expecting exception: IndexOutOfBoundsException");
      
      } catch(IndexOutOfBoundsException e) {
         //
         // Should be at least 0 and at most 0, found 2
         //
         verifyException("com.org.apache.commons.collections.primitives.ArrayIntList", e);
      }
  }
```
> *Test case: readability score 7*
```
Mutation (264): 
Class: ArrayIntList
Method: checkRangeIncludingEndpoint(I)V:
Line: 208
Type of mutation: ReplaceComparisonOperator
```
> *Target*

#### Interaction 9
In the last interaction, InterEvo-TR shows the tester the two test cases and the target below.  After 62 seconds, the tester assigns the **first test case a readability score of 3**, while the **second test case is assigned a score of 4**.

```c++
  public void test0()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList();
      ArrayIntList arrayIntList1 = new ArrayIntList(arrayIntList0);
      assertEquals(0, arrayIntList1.size());
  }
```
> *First test: readability score 3*

```c++
  public void test0()  throws Throwable  {
      ArrayIntList arrayIntList0 = new ArrayIntList(0);
      assertEquals(0, arrayIntList0.size());
  }
```
> *Second test: readability score 4*
```
Mutation (6): 
Class: ArrayIntList
Method: <init>(I)V:
Line: 83
Type of mutation: ReplaceComparisonOperator
```
> *Target*

At this point, the process reaches the maximum number of allowed interactions  and continues the search until the search budget is consumed. The resulting test suite can be consulted [here](https://github.com/PdedP/InterEvo-TR/blob/master/Participant%20results/P2/ArrayIntList_ES_Test.java).