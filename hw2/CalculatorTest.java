
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
public class CalculatorTest {
/* Do not change this to be private. For silly testing reasons it is public. */
public Calculator tester;
/**
* setUp() performs setup work for your Calculator. In short, we
* initialize the appropriate Calculator for you to work with.
* By default, we have set up the Staff Calculator for you to test your
* tests. To use your unit tests for your own implementation, comment
* out the StaffCalculator() line and uncomment the Calculator() line.
**/
@Before
public void setUp() {
tester = new StaffCalculator(); // Comment me out to test your Calculator
// tester = new Calculator(); // Un-comment me to test your Calculator
}
@Test
public void CalculatorTest(){
int sum= tester.add(1,1);
assertEquals (sum,2);
}
@Test
public void CalculatorTest2(){
int sum3 = tester.add(0,999);
assertEquals (sum3,999);
}
@Test
public void CalculatorTest3(){
int sum= tester.add(-21474,0);
assertEquals (sum,-21474);
}
/* Run the unit tests in this file. */
public static void main(String... args) {
jh61b.junit.textui.runClasses(CalculatorTest.class);
}
}


