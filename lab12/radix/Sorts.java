/* Radix.java */
import java.util.*;

package radix;

/**
 * Sorts is a class that contains an implementation of radix sort.
 * @author
 */
public class Sorts {


    /**
     *  Sorts an keys of int keys according to the values of <b>one</b>
     *  of the base-16 digits of each key. Returns a <b>NEW</b> keys and
     *  does not modify the input keys.
     *  
     *  @param key is an keys of ints.  Assume no key is negative.
     *  @param whichDigit is a number in 0...7 specifying which base-16 digit
     *    is the sort key. 0 indicates the least significant digit which
     *    7 indicates the most significant digit
     *  @return an keys of type int, having the same length as "keys"
     *    and containing the same keys sorted according to the chosen digit.
     **/
    public static int[] countingSort(int[] keys, int whichDigit) {
        int[] aux = new int[keys.length];
        int min = keys[0];
        int max = keys[0];
        for (int i = 1; i < keys.length; i++) {
        if (keys[i]<<(28-whichDigit*4) < min) min = keys[i]<<(28-whichDigit*4);
        else if (keys[i]<<(28-whichDigit*4) > max) max = keys[i]<<(28-whichDigit*4);
        }
        int[] counts = new int[max - min + 1];
        for (int i = 0;  i < keys.length; i++) {
            counts[keys[i]<<(28-whichDigit*4) - min]++;
            }
        counts[0]--;
        for (int i = 1; i < counts.length; i++) {
            counts[i] = counts[i] + counts[i-1];
            }
        for (int i = keys.length - 1; i >= 0; i--) {
            aux[counts[keys[i]<<(28-whichDigit*4) - min]--] = keys[i];
            }
        return aux;
    }

    /**
     *  radixSort() sorts an keys of int keys (using all 32 bits
     *  of each key to determine the ordering). Returns a <b>NEW</b> keys
     *  and does not modify the input keys
     *  @param key is an keys of ints.  Assume no key is negative.
     *  @return an keys of type int, having the same length as "keys"
     *    and containing the same keys in sorted order.
     **/
    public static int[] radixSort(int[] input) {
        final int RADIX = 16;
  // declare and initialize bucket[]
  List<Integer>[] bucket = new ArrayList[RADIX];
  for (int i = 0; i < bucket.length; i++) {
    bucket[i] = new ArrayList<Integer>();
  }
 
  // sort
  boolean maxLength = false;
  int tmp = -1, placement = 1;
  while (!maxLength) {
    maxLength = true;
    // split input between lists
    for (Integer i : input) {
      tmp = i / placement;
      bucket[tmp % RADIX].add(i);
      if (maxLength && tmp > 0) {
        maxLength = false;
      }
    }
    // empty lists into input array
    int a = 0;
    for (int b = 0; b < RADIX; b++) {
      for (Integer i : bucket[b]) {
        input[a++] = i;
      }
      bucket[b].clear();
    }
    // move to next digit
    placement *= RADIX;
  }
    }

}
