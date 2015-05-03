/**
 * Implements autocomplete on prefixes for a given dictionary of terms and
 * @author hahaah
 */
public class Trie {

    String out;

    Node root = new Node();
/**
 * Implements autocomplete on prefixes for a given dictionary of terms and
 * @return nothing
 *@param s lol
 *
 */
    public boolean find(String s, boolean isFullWord) {
        Node x = root;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (x.links[c] == null) {
                return false;
            }
            x = x.links[c];

        }
        if (isFullWord && !x.exists) {
            return false;
        }
        return true;

    }
/**
 * Implements autocomplete on prefixes for a given dictionary of terms and
 * @param s lol
 */
    public void insert(String s) {
        if (s == null || s.equals("")) {
            throw new IllegalArgumentException();
        }
        put(root, s, 0);
    }

    private Node put(Node x, String s, int d) {

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
        x.hasl = true;
        x.links[c] = put(x.links[c], s, d + 1);

        return x;
    }
/**
 * Implements autocomplete on prefixes for a given dictionary of terms and
 * weights.
 */
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
