import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

public class TST {
	private int N; // size
	private Node root; // root of TST

	private class Node {
	private char c; // character
	private Node left, mid, right; // left, middle, and right subtries
	private Double val = null;
	private Double max = null;
	private String word;
	private boolean gone = false;
	private boolean has = false;

	// Double associated with string
	}

	/**
	 * Initializes an empty string symbol table.
	 */
	public TST() {
	}

	/**
	 * Returns the number of key-Double pairs in this symbol table.
	 * 
	 * @return the number of key-Double pairs in this symbol table
	 */
	public int size() {
	return N;
	}

	/**
	 * Does this symbol table contain the given key?
	 * 
	 * @param key
	 *          the key
	 * @return <tt>true</tt> if this symbol table contains <tt>key</tt> and
	 *         <tt>false</tt> otherwise
	 * @throws NullPointerException
	 *           if <tt>key</tt> is <tt>null</tt>
	 */
	public boolean contains(String key) {
	return get(key) != null;
	}

	/**
	 * Returns the Double associated with the given key.
	 * 
	 * @param key
	 *          the key
	 * @return the Double associated with the given key if the key is in the
	 *         symbol table and <tt>null</tt> if the key is not in the symbol
	 *         table
	 * @throws NullPointerException
	 *           if <tt>key</tt> is <tt>null</tt>
	 */
	public Double get(String key) {

	Node x = get(root, key, 0);
	if (x == null) {
		return null;
	}
	return x.val;
	}

	// return subtrie corresponding to given key
	private Node get(Node x, String key, int d) {

	if (key.length() == 0) {
		return root;
	}
	if (x == null) {
		return null;
	}
	char c = key.charAt(d);
	if (c < x.c) {
		return get(x.left, key, d);
	} else if (c > x.c) {
		return get(x.right, key, d);
	} else if (d < key.length() - 1) {
		return get(x.mid, key, d + 1);
	} else {
		return x;
	}
	}

	/**
	 * Inserts the key-Double pair into the symbol table, overwriting the old
	 * Double with the new Double if the key is already in the symbol table. If
	 * the Double is <tt>null</tt>, this effectively deletes the key from the
	 * symbol table.
	 * 
	 * @param key
	 *          the key
	 * @param val
	 *          the Double
	 * @throws NullPointerException
	 *           if <tt>key</tt> is <tt>null</tt>
	 */
	public void put(String key, Double val) {
	if (contains(key)) {
		throw new IllegalArgumentException();
	}
	if (!contains(key)) {
		N++;
	}
	root = put(root, key, val, 0);
	}

	private Node put(Node x, String key, Double val, int d) {
	char c = key.charAt(d);

	if (x == null) {
		x = new Node();
		x.c = c;
	}
	if (x.max == null) {
		x.max = val;
	}
	if (val > x.max) {
		x.max = val;
	}
	if (c < x.c) {
		x.has = true;
		x.left = put(x.left, key, val, d);
	} else if (c > x.c) {
		x.has = true;
		x.right = put(x.right, key, val, d);
	} else if (d < key.length() - 1) {
		x.has = true;
		x.mid = put(x.mid, key, val, d + 1);
	} else {
		x.val = val;
		x.word = key;
	}
	return x;
	}

	public ArrayList<String> keysWithPrefix(String prefix) {
	ArrayList<String> queue = new ArrayList<String>();
	Node x = get(root, prefix, 0);
	if (x == null || get(root, prefix, 0) == null) {
		return queue;
	}
	if (x.val != null) {
		queue.add(prefix);
	}
	collect(x.mid, new StringBuilder(prefix), queue);

	return queue;
	}

	public ArrayList<String> search(String prefix, int k) {
	ArrayList<String> queue = new ArrayList<String>();
	AComparator comparator = new AComparator();
	BComparator comparator2 = new BComparator();
	PriorityQueue<Node> pq = new PriorityQueue<Node>(10, comparator);
	PriorityQueue<Node> minpq = new PriorityQueue<Node>(10, comparator2);
	Node x = get(root, prefix, 0);
	if (x == null) {
		return queue;
	}

	pq.add(x);
	if(prefix.length() != 0){
	if (x.val == null) {
		pq.poll();
		pq.add(x.mid);
	} else if (x.mid == null) {
		queue.add(x.word);
		return queue;
	} else if (x.val < x.max) {
		pq.poll();
		pq.add(x.mid);
	}
}

	while ((minpq.isEmpty() || minpq.size() < k) && pq.size() > 0) {

	while ((pq.peek() == null) || (pq.peek().val == null)
			|| ((pq.peek().val < pq.peek().max)&&pq.peek().gone == false)) {
		Node y = pq.peek();
		if (y.left != null) {
			pq.add(y.left);
		}
		if (y.mid != null) {
			pq.add(y.mid);
		}
		if (y.right != null) {
			pq.add(y.right);
		}
		if (y.val != null) {
			y.gone = true;
		}
		if (y.val == null)
		{			
			pq.remove(y);
		}
		if (y.val == y.max) {
			pq.poll();
			pq.add(y);
		}

		}

		Node z = pq.poll();
		if (minpq.size() == k) {
		minpq.poll();
		}
		minpq.add(z);
	}
	while (pq.size() > 0 && pq.peek().max >= minpq.peek().val) {

		while ((pq.peek() == null) || (pq.peek().val == null)
			|| ((pq.peek().val < pq.peek().max)&&pq.peek().gone == false)) {
		Node y = pq.peek();
		if (y.left != null) {
			pq.add(y.left);
		}
		if (y.mid != null) {
			pq.add(y.mid);
		}
		if (y.right != null) {
			pq.add(y.right);
		}
		if (y.val != null) {
			y.gone = true;
		}
		if (y.val == null)
		{			
			pq.remove(y);
		}

		if (y.val == y.max) {
			pq.poll();
			pq.add(y);
		}
		}

		Node p = pq.poll();
		if (minpq.size() == k) {
		minpq.poll();
		}
		minpq.add(p);
	}
	while (minpq.size() != 0) {
		Node t = minpq.poll();
		queue.add(t.word);
	}
	Collections.reverse(queue);
	return queue;

	}

	class AComparator implements Comparator<Node> {
	@Override
	public int compare(Node x, Node y) {

		if (y.max > x.max) {
		return 1;
		}
		if (y.max < x.max) {
		return -1;
		}
		if (y.val == null&&x.val == null) {
			return 0;
		}
		if (y.val == null) {
			return -1;
		}
		if (x.val == null||y.val > x.val) {
		return 1;
		}

		return -1;
	}

	}

	class BComparator implements Comparator<Node> {
	@Override
	public int compare(Node x, Node y) {

		if (y.val > x.val) {
		return -1;
		}
		if (y.val < x.val) {
		return 1;
		}
		return 0;
	}

	}

	// all keys in subtrie rooted at x with given prefix
	public void collect(Node x, StringBuilder prefix, ArrayList<String> queue) {
	if (x == null) {
		return;
	}
	collect(x.left, prefix, queue);
	if (x.val != null) {
		queue.add(prefix.toString() + x.c);
	}
	collect(x.mid, prefix.append(x.c), queue);
	prefix.deleteCharAt(prefix.length() - 1);
	collect(x.right, prefix, queue);
	}

}
