 import java.util.AbstractList;

 public class ArrayList61B<E> extends AbstractList<E>{
 
 int size=0;
 int capacity=1;

public ArrayList61B(int initialCapacity){

if(initialCapacity<1){
	 throw new IllegalArgumentException();
}
else{capacity=initialCapacity;}
size=0;
}
public ArrayList61B(){
	capacity=1;
	size=0;

}

 E[] items = (E[]) new Object[capacity];

public E get(int i) {
if (i<0||i>=size) {
	throw new IllegalArgumentException();
}
return items[i];
}
 


 public boolean add(E item) {
if (item!=null&&capacity>=size){


if (capacity == size)  {
resize((int) (size * 2));
capacity=2*capacity;
}

items[size] =item;
size = size + 1;


}
return true;
}




public int size(){
	return size;
}
 private void resize(int c) {
E[] newItems = (E[]) new Object[c];
for (int i = 0; i < capacity; i += 1) {
newItems[i] = items[i];
}
items = newItems;
}
 }