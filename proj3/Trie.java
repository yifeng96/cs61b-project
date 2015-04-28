import java.util.HashSet;

/**
 * Prefix-Trie. Supports linear time find() and insert(). Should support
 * determining whether a word is a full word in the Trie or a prefix.
 * 
 * @author
 */
public class Trie {
	private static final int r = 128;
	HashSet<String> word = new HashSet();
	String out;

	public Node root = new Node();

	public boolean find(String s, boolean isFullWord) {
	Node x = root;
	if (isFullWord) {
		return word.contains(s);
	}
	for (int i = 0; i < s.length(); i++) {
		char c = s.charAt(i);
		if (x.links[c] == null) {
		return false;
		}
		x = x.links[c];
	}
	return true;

	}

	public void insert(String s) {
	if (s == null || s.equals("")) {
		throw new IllegalArgumentException();
	}
	put(root, s, 0);
	}

	private Node put(Node x, String s, int d) {
	word.add(s);

	if (x == null) {
		x = new Node();
	}
	if (d != 0) {
		x.output = out + s.charAt(d - 1);
	}

	out = x.output;
	if (d == s.length()) {
		x.exists = true;
		return x;
	}

	char c = s.charAt(d);
	x.links[c] = put(x.links[c], s, d + 1);

	return x;
	}

	public static void main(String[] args) {
	Trie t = new Trie();
	t.insert("hello");
	t.insert("hey");
	t.insert("goodbye");
	System.out.println(t.find("hell", false));
	System.out.println(t.find("hello", true));
	System.out.println(t.find("good", false));
	System.out.println(t.find("bye", false));
	System.out.println(t.find("heyy", false));
	System.out.println(t.find("hell", true));
	}
}
