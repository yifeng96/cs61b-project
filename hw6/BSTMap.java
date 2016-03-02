import edu.princeton.cs.algs4.BST;
import java.util.Set;

public class BSTMap<K extends Comparable<K>,V> implements Map61B<K,V> {


    
  // is the symbol table empty?
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        return size(root);
    }

    // return number of key-value pairs in BST rooted at x
    private int size(Node x) {
        if (x == null) return 0;
        else return x.N;
    }

   /***********************************************************************
    *  Search BST for given key, and return associated value if found,
    *  return null if not found
    ***********************************************************************/
    @Override
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    @Override
    public V get(K key) {
        return get(root, key);
    }

    private V get(Node x, K key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else              return x.val;
    }

   /***********************************************************************
    *  Insert key-value pair into BST
    *  If key already exists, update with new value
    ***********************************************************************/
    @Override
    public void put(K key, V val) {
        
        root = put(root, key, val);
        
    }

    private Node put(Node x, K key, V val) {
        if (x == null) return new Node(key, val, 1);
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) x.left  = put(x.left,  key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else              x.val   = val;
        x.N = 1 + size(x.left) + size(x.right);
        return x;
    }
    /** Returns the value corresponding to KEY or null if no such value exists. */




    /** Removes all of the mappings from this map. */
    @Override
    public void clear() {
        
        root = null;
    }



    /** Returns true if and only if this dictionary contains KEY as the
     *  key of some key-value pair. */

 

    /** Returns the result of inverting DICT so that keys become values and values
     *  become keys. If more than one key corresponds to the same value, then an
     *  arbitrary key is chosen to be the new value. */


    /** Keys and values are stored in a linked list of Entry objects.
     *  This variable stores the first pair in this linked list. */
    private Node root;

    /** Represents one node in the linked list that stores the key-value pairs
     *  in the dictionary. */
    private class Node {
        private K key;           // sorted by key
        private V val;         // associated data
        private Node left, right;  // left and right subtrees
        private int N;             // number of nodes in subtree

        public Node(K key, V val, int N) {
            this.key = key;
            this.val = val;
            this.N = N;
        }
    }

    /** An iterator that iterates over the keys of the dictionary. */
    

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }
    public K select(int k) {
        if (k < 0 || k >= size()) return null;
        Node x = select(root, k);
        return x.key;
    }

    // Return key of rank k. 
    private Node select(Node x, int k) {
        if (x == null) return null; 
        int t = size(x.left); 
        if      (t > k) return select(x.left,  k); 
        else if (t < k) return select(x.right, k-t-1); 
        else            return x; 
    } 
    public void printInOrder(){
    	for (int i = 0; i < this.size(); i++) {
    		System.out.println(this.get(select(i)));
    	}
    	
    }
}