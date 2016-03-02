import java.util.ArrayList;

/**
 * Implements autocomplete on prefixes for a given dictionary of terms and
 * @author me
 */
public class Autocomplete {

    private final int hahaha = 5;
    TST ts;
/**
 * @param weights haveterms
 * @param terms lol
 */
    public Autocomplete(String[] terms, double[] weights) {
        if (terms.length != weights.length) {
            throw new IllegalArgumentException();
        }
        ts = new TST();
        for (int i = 0; i < terms.length; i++) {
            if (weights[i] < 0) {
                throw new IllegalArgumentException();
            }
            ts.put(terms[i], weights[i]);
        }

    }

    /**
     * Find the weight of a given term. If it is not in the dictionary, return 0.0
     * @return nothingd
 *    @param term lol
     * 
     */
    public double weightOf(String term) {
        if (ts.contains(term)) {
            if (ts.get(term) < 0) {
                throw new IllegalArgumentException();
            }
            return ts.get(term);
        }
        return 0.0;

    }

    /**
     * Return the top match for given prefix, or null if there is no matching
     * term.
    * @return nothingd
 *    @param prefix lol
     *          Input prefix to match against.
     *  Best (highest weight) matching string in the dictionary.
     */
    public String topMatch(String prefix) {
        return ts.search(prefix, hahaha).get(0);
    }

    /**
     * Returns the top k matching terms (in descending order of weight) as an
     * iterable. If there are less than k matches, return all the matching terms.
     * 
    * @return nothingd
 *    @param prefix lol
     * @param k lol
     */
    public Iterable<String> topMatches(String prefix, int k) {
        if (k < 0) {
            throw new IllegalArgumentException();
        }
        return ts.search(prefix, k);
    }

    /**
     * Returns the highest weighted matches within k edit distance of the word. If
     * the word is in the dictionary, then return an empty list.
     * 
     *  word
     * @return nothingd
     * @param word lol    Maximum edit distance to search
     * @param k haahh
     *          Number of results to return
     * @param dist Iterable in descending weight order of the matches
     */
    public Iterable<String> spellCheck(String word, int dist, int k) {
        ArrayList<String> results = new ArrayList<String>();
        /* YOUR CODE HERE; LEAVE BLANK IF NOT PURSUING BONUS */
        return results;
    }

    /**
     * Test client. Reads the data from the file, then repeatedly reads
     * autocomplete queries from standard input and prints out the top k matching
     * terms.
     * 
     *  @param args lol 
     *          takes the name of an input file and an integer k as command-line
     *          arguments
     */
    public static void main(String[] args) {
        // initialize autocomplete data structure
        In in = new In(args[0]);
        int N = in.readInt();
        String[] terms = new String[N];
        double[] weights = new double[N];
        for (int i = 0; i < N; i++) {
            weights[i] = in.readDouble(); // read the next weight
            in.readChar(); // scan past the tab
            terms[i] = in.readLine(); // read the next term
        }

        Autocomplete autocomplete = new Autocomplete(terms, weights);

        // process queries from standard input
        int k = Integer.parseInt(args[1]);
        while (StdIn.hasNextLine()) {
            String prefix = StdIn.readLine();
            for (String term : autocomplete.topMatches(prefix, k)) {
                StdOut.printf("%14.1f  %s\n", autocomplete.weightOf(term), term);
            }
        }
    }
}
