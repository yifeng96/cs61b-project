package ngordnet;

import java.util.Collection;
import java.util.HashMap;
import java.util.TreeSet;
import java.util.TreeMap;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

public class YearlyRecord {
    /** Creates a new empty YearlyRecord. */
    private HashMap<String, Integer> myyr;

    public YearlyRecord() {
        myyr = new HashMap<String, Integer>();
    }

    /** Creates a YearlyRecord using the given data. */
    public YearlyRecord(HashMap<String, Integer> otherCountMap) {
        myyr = otherCountMap;
    }

    /** Returns the number of times WORD appeared in this year. */
    public int count(String word) {
        if (!myyr.containsKey(word)) {
            return 0;
        }
        return myyr.get(word);
    }

    /** Records that WORD occurred COUNT times in this year. */
    public void put(String word, int count) {
        myyr.put(word, count);
    }

    /** Returns the number of words recorded this year. */
    public int size() {
        return myyr.size();
    }

    /** Returns all words in ascending order of count. */
    public Collection<String> words() {
        Collection<Integer> k = new TreeSet();
        k = myyr.values();
        Object[] p = k.toArray();
        Arrays.sort(p);
        Collection<String> result = new LinkedList();
        Map<Integer, String> myNewHashMap = new HashMap<>();
        for (Map.Entry<String, Integer> entry : myyr.entrySet()) {
            myNewHashMap.put(entry.getValue(), entry.getKey());
        }
        for (int i = 0; i < p.length; i++) {
            String str = myNewHashMap.get((int) p[i]);
            result.add(str);
        }
        return result;
    }

    /** Returns all counts in ascending order of count. */
    public Collection<Number> counts() {
        Collection<Integer> k = new TreeSet();
        k = myyr.values();
        Object[] p = k.toArray();
        Arrays.sort(p);
        Collection<Number> result = new LinkedList();
        for (int i = 0; i < p.length; i++) {
            result.add((Number) p[i]);
        }
        return result;
    }

    /** Returns rank of WORD. Most common word is rank 1. */
    public int rank(String word) {
        Collection<Number> k = new TreeSet();
        Iterator<Integer> xxxx = myyr.values().iterator();
        while (xxxx.hasNext()) {
            Number in3 = xxxx.next();
            k.add(in3);

        }
        Map<Double, String> myNewHashMap = new HashMap<>();
        for (Map.Entry<String, Integer> entry : myyr.entrySet()) {
            Double dou = entry.getValue().doubleValue();
            String str = entry.getKey();
            if (myNewHashMap.containsKey(dou)) {
                dou += 0.1;
            }
            myNewHashMap.put(dou, str);
        }
        Object[] p = k.toArray();
        TreeMap<String, Number> map = new TreeMap();
        Arrays.sort(p);

        for (int i = 0; i < p.length; i++) {
            map.put(myNewHashMap.get(Double.valueOf(p[i])), p.length - i);

        }
        return (int) map.get(word);
    }

}
