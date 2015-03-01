 import java.util.AbstractList;

 public class ArrayList61B<E>{
E [] items;
int size;
public ArrayList61B(int initialCapacity){
if(initialCapacity<1){
	 throw new IllegalArgumentException();
}
else{size=initialCapacity;}
}
public ArrayList61B(){
	size=1;
}
public E get(int i) {
return items[i];
}
 public boolean add(E item) {
// what position does the 0th insert go into? position 0
if (size == items.length) {
resize((int) (size * 2));
}
items[size] = item;
size = size + 1;
return true;
}
public int size(){
	return size;
}
 private void resize(int c) {
E[] newItems = (E[]) new Object[c];
for (int i = 0; i < items.length; i += 1) {
newItems[i] = items[i];
}
items = newItems;
}
 }