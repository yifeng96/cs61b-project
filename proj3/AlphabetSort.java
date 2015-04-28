import java.util.HashMap;
import java.util.Scanner;

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
		for (int sb = 0; sb < alphabet.length ; sb++ ) {
			for (int k = sb + 1; k < alphabet.length ; k++ ) {
				if (alphabet[sb].equals(alphabet[k])) {
					throw new IllegalArgumentException();
				}
			}
		}
		t = new Trie();
		if (!input.hasNextLine()) {
			throw new IllegalArgumentException();
		}
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

		if (t.word.contains(x.output)&&x.hasl == false) {

			System.out.println(x.output);

			output = "";

		} 

		else {
			if (t.word.contains(x.output)&&x.hasl == true) {
				System.out.println(x.output);
			}
			for (String element : alphabet) {

				char c = element.charAt(0);

				if (x.links[c] != null) {

					sort(x.links[c]);

				}
			}
		}
	}

	

}
