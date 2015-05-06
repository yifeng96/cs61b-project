import static org.junit.Assert.*;
import org.junit.Test;

public class IntListTest {

    /** Example test that verifies correctness of the IntList.list static 
     *  method. The main point of this is to convince you that 
     *  assertEquals knows how to handle IntLists just fine.
     */

    @Test 
    public void testList() {
        IntList one = new IntList(1, null);
        IntList twoOne = new IntList(2, one);
        IntList threeTwoOne = new IntList(3, twoOne);

        IntList x = IntList.list(3, 2, 1);
        assertEquals(threeTwoOne, x);
    }

    @Test
    public void testdSquareList() {
      IntList L = IntList.list(1, 2, 3);
      IntList.dSquareList(L);
      assertEquals(IntList.list(1, 4, 9), L);
    }

    /** Do not use the new keyword in your tests. You can create
     *  lists using the handy IntList.list method.  
     * 
     *  Make sure to include test cases involving lists of various sizes
     *  on both sides of the operation. That includes the empty list, which
     *  can be instantiated, for example, with 
     *  IntList empty = IntList.list(). 
     *
     *  Keep in mind that dcatenate(A, B) is NOT required to leave A untouched.
     *  Anything can happen to A. 
     */
    @Test
    public void testSquareListRecursive() {
	IntList l = IntList.list(1,2,3);
	IntList l_copy = IntList.list(1,2,3);
	IntList result = IntList.list(1,4,9);
	assertEquals(IntList.squareListRecursive(l), result);
	assertEquals(l, l_copy); //Non-Destructive test
    }

    @Test
    public void testDcatenate() {
	IntList l1 = IntList.list(1,2,3);
	IntList l2 = IntList.list(4,5,6);
	IntList result = IntList.list(1,2,3,4,5,6);
	assertEquals(IntList.dcatenate(l1,l2), result);
    }

    @Test
    public void testCatenate() {
	IntList l1 = IntList.list(1,2,3);
	IntList l2 = IntList.list(4,5,6);
	IntList l1_copy = IntList.list(1,2,3);
	IntList l2_copy = IntList.list(4,5,6);
	IntList result = IntList.list(1,4,2,5,3,6);
	assertEquals(IntList.catenate(l1,l2), result);
	assertEquals(l1,l1_copy);
	assertEquals(l2,l2_copy);
    }

    /* Run the unit tests in this file. */
    public static void main(String... args) {
        jh61b.junit.textui.runClasses(IntListTest.class);
    }       
}   
