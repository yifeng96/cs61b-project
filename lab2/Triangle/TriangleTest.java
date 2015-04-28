/*
* JUnit tests for the Triangle class
*/
import org.junit.Test;
import static org.junit.Assert.*;
/**
*
* @author melaniecebula
*/
public class TriangleTest {
/** We've already created a testScalene method. Please fill in testEquilateral, and additionally
* create tests for Isosceles, Negative Sides, and Invalid sides
**/
@Test
public void testScalene() {
Triangle t = new Triangle(30, 40, 50);
String result = t.triangleType();
assertEquals("Scalene", result);
}
@Test
public void testEquilateral() {
Triangle t = new Triangle ( 10,10,10);
String result = t.triangleType();
assertEquals("Equilateral",result);
}
@Test
public void testiso() {
Triangle t = new Triangle (10,10,5);
String result = t.triangleType();
assertEquals("Isosceles",result);
}
@Test
public void testneg(){
Triangle t = new Triangle (-10,10,5);
String result = t.triangleType();
assertEquals("At least one length is less than 0!",result);
}
@Test
public void testinvaildside(){
Triangle t = new Triangle (10,10,50);
String result = t.triangleType();
assertEquals("The lengths of the triangles do not form a valid triangle!",result);
}
public static void main(String[] args) {
jh61b.junit.textui.runClasses(TriangleTest.class);
}
}