package ngordnet;

import java.util.Collection;
import java.util.HashMap;
import java.util.TreeSet;

import java.util.LinkedList;
import java.util.Arrays;

import java.util.Map;

import java.util.Comparator;

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
        String[] arr = new String[myyr.size()];
        int i = 0;
        for (String str : myyr.keySet()) {
            arr[i] = str;
            i += 1;
        }
        Comparator<String> c = new MyComparator();
        Arrays.sort(arr, c);
        return myyr.size() - Arrays.asList(arr).indexOf(word);


    }
    private class MyComparator implements Comparator<String> {
        public int compare(String str1, String str2) {
            if (myyr.get(str1) > myyr.get(str2)) {
                return 1;
            }

            
            return -1;
            
        }   
    }
}
