import java.util.Formatter;
/** Scheme-like pairs that can be used to form a list of integers.
* @author P. N. Hilfinger, with some modifications by Josh Hug and melaniecebula
* [Do not modify this file.]
*/
public class IntList {
/** First element of list. */
public int head;
/** Remaining elements of list. */
public IntList tail;
/** A List with head HEAD0 and tail TAIL0. */
public IntList(int head0, IntList tail0) {
head = head0;
tail = tail0;
}
/** A List with null tail, and head = 0. */
public IntList() {
/* NOTE: public IntList () { } would also work. */
this(0, null);
}
/** Returns a list equal to L with all elements squared. Destructive. */
public static void dSquareList(IntList L) {
while (L != null) {
L.head = L.head * L.head;
L = L.tail;
}
}
/** Returns a list equal to L with all elements squared. Non-destructive. */
public static IntList squareListIterative(IntList L) {
if (L == null) {
return null;
}
IntList res = new IntList(L.head * L.head, null);
IntList ptr = res;
L = L.tail;
while (L != null) {
ptr.tail = new IntList(L.head * L.head, null);
L = L.tail;
ptr = ptr.tail;
}
return res;
}
/** Returns a list equal to L with all elements squared. Non-destructive. */
public static IntList squareListRecursive(IntList L) {
if (L == null) {
return null;
}
return new IntList(L.head * L.head, squareListRecursive(L.tail));
}
/** DO NOT MODIFY ANYTHING ABOVE THIS LINE! */
/** Returns a list consisting of the elements of A followed by the
** elements of B. May modify items of A. Don't use 'new'. */
public static IntList dcatenate(IntList A, IntList B) {
if (A==null ){
return B;
}
if (B==null){
return A;
}
while (A != null) {
A.head = A.head ;
A = A.tail ;
if (A .tail == null){
A.tail = B;
break;}
}
return A;
}
/** Returns a list consisting of the elements of A followed by the
** elements of B. May NOT modify items of A. Use 'new'. */
public static IntList catenate(IntList A, IntList B) {
if (A==null){
return B;
}
return new IntList(A.head, catenate(A.tail,B));
}
