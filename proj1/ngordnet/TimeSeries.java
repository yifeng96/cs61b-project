package ngordnet;


import java.util.TreeSet;

import java.util.TreeMap;
import java.util.Iterator;
import java.util.Collection;
import java.util.NavigableSet;


public class TimeSeries<T extends Number> extends TreeMap<Integer, T> {
    /** Constructs a new empty TimeSeries. */
    private TreeMap<Integer, T> myts;

    public TimeSeries() {
        myts = new TreeMap<Integer, T>();
    }

    /**
     * Returns the years in which this time series is valid. Doesn't really need
     * to be a NavigableSet. This is a private method and you don't have to
     * implement it if you don't want to.
     */
    private NavigableSet<Integer> validYears(int startYear, int endYear) {
        return null;
    }

    /**
     * Creates a copy of TS, but only between STARTYEAR and ENDYEAR. inclusive
     * of both end points.
     */
    public TimeSeries(TimeSeries<T> ts, int startYear, int endYear) {
        myts = new TreeMap<Integer, T>();
        for (int k = startYear; k <= endYear; k++) {
            T val = ts.get(k);
            myts.put(k, val);
        }

    }

    /** Creates a copy of TS. */
    public TimeSeries(TimeSeries<T> ts) {
        myts = ts;
    }

    /**
     * Returns the quotient of this time series divided by the relevant value in
     * ts. If ts is missing a key in this time series, return an
     * IllegalArgumentException.
     */
    public TimeSeries<Double> dividedBy(TimeSeries<? extends Number> ts) {

        if (!this.keySet().containsAll(ts.keySet())) {
            throw new IllegalArgumentException();
        }
        TimeSeries<Double> result = new TimeSeries();
        Iterator<Integer> xxx = this.keySet().iterator();
        while (xxx.hasNext()) {
            int in = xxx.next();

            Double quot = this.get(in).doubleValue() / ts.get(in).doubleValue();
            result.put(in, quot);
        }
        return result;
    }

    /**
     * Returns the sum of this time series with the given ts. The result is a a
     * Double time series (for simplicity).
     */
    public TimeSeries<Double> plus(TimeSeries<? extends Number> ts) {

        TimeSeries<Double> result = new TimeSeries();
        Iterator<Integer> xxx = this.keySet().iterator();
        while (xxx.hasNext()) {
            int in2 = xxx.next();
            if (ts.keySet().contains(in2)) {
                Double sum = this.get(in2).doubleValue()
                        + ts.get(in2).doubleValue();
                result.put(in2, sum);
            } else {
                Double a = this.get(in2).doubleValue();
                result.put(in2, a);
            }
        }
        Iterator<Integer> xxy = ts.keySet().iterator();
        while (xxy.hasNext()) {
            int in3 = xxy.next();
            if (!result.containsKey(in3)) {
                Double b = ts.get(in3).doubleValue();
                result.put(in3, b);
            }

        }
        return result;
    }

    /** Returns all years for this time series (in any order). */
    public Collection<Number> years() {
        Collection<Number> k = new TreeSet();
        Iterator<Integer> xxxx = this.keySet().iterator();
        while (xxxx.hasNext()) {
            Number in3 = xxxx.next();
            k.add(in3);

        }

        return k;
    }

    /** Returns all data for this time series (in any order). */
    public Collection<Number> data() {
        Collection<Number> k = new TreeSet();
        Iterator<T> xxxx = this.values().iterator();
        while (xxxx.hasNext()) {
            Number in3 = xxxx.next();
            k.add(in3);

        }

        return k;
    }

}
