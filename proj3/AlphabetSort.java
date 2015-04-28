import java.util.*;

public class AlphabetSort {
	String output;
	static String[] alphabet;
	static Trie t;
	static Node a;

	public static void main(String[] args) {
		AlphabetSort as = new AlphabetSort();
		Scanner input = new Scanner(System.in);
		if (!input.hasNextLine()) {
			throw new IllegalArgumentException();
		}
		String alph = input.nextLine();
		alphabet = alph.split("(?<=\\G.{1})");

		t = new Trie();
		while (input.hasNextLine()) {
			t.insert(input.nextLine());
		}
		HashMap<String, Integer> map = new HashMap();
		for (int i = 0; i < alphabet.length; i++) {
			map.put(alphabet[i], i);
		}
		a = t.root;
		as.sort(a);

	}

	public void sort(Node x) {

		if (t.word.contains(x.output)) {

			System.out.println(x.output);

			output = "";

		} else {
			for (int p = 0; p < alphabet.length; p++) {

				char c = alphabet[p].charAt(0);

				if (x.links[c] != null) {

					sort(x.links[c]);

				}
			}
		}
	}

	public void sort2() {

	}

}