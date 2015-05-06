/* Radix.java */
package radix;
import java.util.*;


/**
 * Sorts is a class that contains an implementation of radix sort.
 * @author
 */
public class Sorts {


    /**
     *  Sorts an array of int keys according to the values of <b>one</b>
     *  of the base-16 digits of each key. Returns a <b>NEW</b> array and
     *  does not modify the input array.
     *  
     *  @param key is an array of ints.  Assume no key is negative.
     *  @param whichDigit is a number in 0...7 specifying which base-16 digit
     *    is the sort key. 0 indicates the least significant digit which
     *    7 indicates the most significant digit
     *  @return an array of type int, having the same length as "keys"
     *    and containing the same keys sorted according to the chosen digit.
     **/

    private static int find_binary(int num, int whichDigit) {        
        while ((7 - whichDigit) > 0) {
            num = num << 4;
            whichDigit += 1;
        }
        while (whichDigit > 0) {
            num = num >>> 4;
            whichDigit -= 1;
        }
        return num;        
    }

    private static int find_pos(int[] bucket, int[] keys, int digit) {
        int pos = bucket[digit];
        while (keys[pos] != 0) {
            pos++;
        }
        return pos;
    }

    private static void init_bucket(int[] bucket) {
        int total = 0;
        for (int i = 0; i < bucket.length; i++) {
            total += bucket[i];
            bucket[i] = total; 
        }
        for (int i = bucket.length - 2; i >= 0; i--) {
            bucket[i+1] = bucket[i];
        }
        bucket[0] = 0;
    }

    public static int[] countingSort(int[] keys, int whichDigit) {
        int[] bucket = new int[16];
        int[] binary = new int[keys.length];
        for (int i: keys) {
            bucket[find_binary(i, whichDigit)] += 1;
        }
        init_bucket(bucket);

        for (int i = 0; i < keys.length; i++) {
            int digit = find_binary(keys[i], whichDigit);
            int pos = find_pos(bucket, binary, digit);
            binary[pos] = keys[i];
        }
        return binary;
    }

    /**
     *  radixSort() sorts an array of int keys (using all 32 bits
     *  of each key to determine the ordering). Returns a <b>NEW</b> array
     *  and does not modify the input array
     *  @param key is an array of ints.  Assume no key is negative.
     *  @return an array of type int, having the same length as "keys"
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
  return input;
}

    public static void main(String[] args) {
        int[] l = {0, 123456,7,6,322314,0,123414,3234,2,9642,1234,17};
        int[] result = radixSort(l);
        for (int w:result) {
            System.out.println(w);
        }
    }

}
