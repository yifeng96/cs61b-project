package ngordnet;

import ngordnet.GraphHelper;
import edu.princeton.cs.algs4.Digraph;
import java.util.Set;
import java.util.TreeSet;
import java.util.List;
import edu.princeton.cs.introcs.In;
import java.util.Map;
import java.util.TreeMap;

public class Test {
    String[] intt;
    String[] words;

    public static void main(String[] args) {
        In in = new In("./wordnet/hyponyms11.txt");
        TreeSet set = new Treeset();
        while (in.hasNextLine()) {
            set.clear();
            words = in.readLine().split(",");
            for (int j = 1; j < words.length; j++) {
                set.add(words[j]);
                System.out.println(in.hasLine());
            }
        }
    }
}
