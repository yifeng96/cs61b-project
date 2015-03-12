package ngordnet;


import java.util.HashMap;

import edu.princeton.cs.introcs.In;

import java.util.Iterator;
import java.util.Collection;

public class NGramMap {
    /** Constructs an NGramMap from WORDSFILENAME and COUNTSFILENAME. */
    private HashMap<Integer, YearlyRecord> yw;
    private TimeSeries<Long> counts;

    public NGramMap(String wordsFilename, String countsFilename) {
        In in = new In(wordsFilename);

        yw = new HashMap();
        while (in.hasNextLine()) {
            String[] arr = in.readLine().split("\t");
            int year = Integer.parseInt(arr[1]);
            String word = (String) arr[0];
            int count = Integer.parseInt(arr[2]);

            if (yw.containsKey(year)) {
                yw.get(year).put(word, count);
            } else {

                HashMap<String, Integer> newvalue = new HashMap();
                newvalue.put(word, count);
                YearlyRecord nv = new YearlyRecord(newvalue);
                yw.put(year, nv);
            }

        }
        In in2 = new In(countsFilename);
        counts = new TimeSeries();
        while (in2.hasNextLine()) {
            String[] arrr = in2.readLine().split(",");

            counts.put(Integer.parseInt(arrr[0]), Long.parseLong(arrr[1]));
        }
    }

    /**
     * Returns the absolute count of WORD in the given YEAR. If the word did not
     * appear in the given year, return 0.
     */
    public int countInYear(String word, int year) {

        return yw.get(year).count(word);
    }

    /** Returns a defensive copy of the YearlyRecord of WORD. */
    public YearlyRecord getRecord(int year) {
        return yw.get(year);
    }

    /** Returns the total number of words recorded in all volumes. */
    public TimeSeries<Long> totalCountHistory() {
        return counts;
    }

    /** Provides the history of WORD between STARTYEAR and ENDYEAR. */
    public TimeSeries<Integer> countHistory(String word, int startYear,
            int endYear) {
        TimeSeries<Integer> myts = new TimeSeries();
        for (int k = startYear; k <= endYear; k++) {
            int val = this.countInYear(word, k);
            myts.put(k, val);
        }
        return myts;
    }

    /** Provides a defensive copy of the history of WORD. */
    public TimeSeries<Integer> countHistory(String word) {
        TimeSeries<Integer> myts = new TimeSeries();
        Iterator<Integer> xxx = yw.keySet().iterator();
        while (xxx.hasNext()) {
            int in2 = xxx.next();
            int count = this.countInYear(word, in2);
            myts.put(in2, count);
        }
        return myts;

    }

    /** Provides the relative frequency of WORD between STARTYEAR and ENDYEAR. */
    public TimeSeries<Double> weightHistory(String word, int startYear,
            int endYear) {
        TimeSeries<Double> myts = new TimeSeries();
        TimeSeries<Integer> my = this.countHistory(word, startYear, endYear);
        TimeSeries<Integer> ts = new TimeSeries(counts, startYear, endYear);
        myts = my.dividedBy(ts);

        return myts;
    }

    /** Provides the relative frequency of WORD. */
    public TimeSeries<Double> weightHistory(String word) {
        TimeSeries<Double> myts = new TimeSeries();
        TimeSeries<Integer> my = this.countHistory(word);

        myts = my.dividedBy(counts);
        return myts;
    }

    /**
     * Provides the summed relative frequency of all WORDS between STARTYEAR and
     * ENDYEAR.
     */
    public TimeSeries<Double> summedWeightHistory(Collection<String> words,
            int startYear, int endYear) {
        TimeSeries<Double> myts = new TimeSeries();

        Iterator<String> xxx = words.iterator();

        while (xxx.hasNext()) {
            String in2 = xxx.next();
            myts = myts.plus(weightHistory(in2, startYear, endYear));
        }

        return myts;
    }

    /** Returns the summed relative frequency of all WORDS. */
    public TimeSeries<Double> summedWeightHistory(Collection<String> words) {
        TimeSeries<Double> myts = new TimeSeries();

        Iterator<String> xxx = words.iterator();

        while (xxx.hasNext()) {
            String in2 = xxx.next();
            myts = myts.plus(weightHistory(in2));
        }

        return myts;
    }

    /**
     * Provides processed history of all words between STARTYEAR and ENDYEAR as
     * processed by YRP.
     */
    public TimeSeries<Double> processedHistory(int startYear, int endYear,
            YearlyRecordProcessor yrp) {
        TimeSeries<Double> myts = new TimeSeries();
        for (int k = startYear; k <= endYear; k++) {
            myts.put(k, yrp.process(this.getRecord(k)));
        }
        return myts;
    }

    /** Provides processed history of all words ever as processed by YRP. */
    public TimeSeries<Double> processedHistory(YearlyRecordProcessor yrp) {
        TimeSeries<Double> myts = new TimeSeries();
        Iterator<Integer> xxx = yw.keySet().iterator();

        while (xxx.hasNext()) {
            Integer in2 = xxx.next();
            myts.put(in2, yrp.process(this.getRecord(in2)));
        }

        return myts;
    }
}
