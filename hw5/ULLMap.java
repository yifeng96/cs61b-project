import java.util.Set; /* java.util.Set needed only for challenge problem. */
 import java.util.Iterator;
/** A data structure that uses a linked list to store pairs of keys and values.
 *  Any key must appear at most once in the dictionary, but values may appear multiple
 *  times. Supports get(key), put(key, value), and contains(key) methods. The value
 *  associated to a key is the value in the last call to put with that key. 
 *
 *  For simplicity, you may assume that nobody ever inserts a null key or value
 *  into your map.
 */ 
public class ULLMap<K,V> implements Map61B<K,V>,Iterable<K>{ 
    /** Keys and values are stored in a linked list of Entry objects.
      * This variable stores the first pair in this linked list. You may
      * point this at a sentinel node, or use it as a the actual front item
      * of the linked list. 
    
      */
    public ULLMap(){}
    private Entry front;
    int n=0;
    @Override
    public V get(K key) { 
  
       
   
   for (Entry x = front; x != null; x = x.next) {
            if (key.equals(x.k)) {return x.v;}
        

}

            return null; 
        }



    @Override
    public void put(K key,V val) { 
        if (containsKey(key)){
       for (Entry x = front; x != null; x = x.next) {
            if (key.equals(x.k)) {x.v=val;}
        }}
    front= new Entry(key,val,front);
    n++;}

    @Override
    public boolean containsKey(K key) { 
   if (n!=0) {    
                for (Entry x = front; x != null; x = x.next) {
                    if (key.equals (x.k)){return true;}
   }
   }
return false; }

    @Override
    public int size() {
        return n; 
}
    @Override
    public void clear() {
    for (Entry x = front; x != null; x = x.next){
        x.k=null;
        x.v=null;
    }
    }


    /** Represents one node in the linked list that stores the key-value pairs
     *  in the dictionary. */
    private class Entry {
    
        /** Stores KEY as the key in this key-value pair, VAL as the value, and
         *  NEXT as the next node in the linked list. */
        public Entry(K k, V v, Entry next) { //FIX ME
            this.k = k;
            this.v = v;
            this.next = next;
        }

        /** Returns the Entry in this linked list of key-value pairs whose key
         *  is equal to KEY, or null if no such Entry exists. */
        public Entry get(K key) { //FIX ME
             if (n!=0) {    
                for (Entry x = front; x != null; x = x.next) {
            if (key.equals(x.k)) {return x;}
        

        }
}


            return null; 
        }

        /** Stores the key of the key-value pair of this node in the list. */
        private K k; //FIX ME
        /** Stores the value of the key-value pair of this node in the list. */
        private V v; //FIX ME
        /** Stores the next Entry in the linked list. */
        private Entry next;
    
    }
public Iterator<K> iterator(){
return new ULLMapIter();
}


public class ULLMapIter implements Iterator<K> {
public int i = 0;
public K next() {
if (hasNext()) {
Entry x = front;
K returnItem = x.k;
i=i+1;
x = x.next;
return returnItem;
}
return null;
}
public boolean hasNext() {
return (i < size());
}
public void remove() {
throw new UnsupportedOperationException();
}
}







public  static <K,V> ULLMap invert(ULLMap<K,V> um){
ULLMap num = new ULLMap();
Iterator<K> umi = um.iterator();
while (umi.hasNext()){
K x= umi.next();
V y=um.get(x);
num.put(y,x);
}
return num;
}

    @Override
    public V remove(K key) { //FIX ME SO I COMPILE
    throw new UnsupportedOperationException();
}

    @Override
    public V remove(K key, K value){ //FIX ME SO I COMPILE
    throw new UnsupportedOperationException();
}

    @Override
    public Set<K> keySet() { //FIX ME SO I COMPILE
    throw new UnsupportedOperationException();
}

}



