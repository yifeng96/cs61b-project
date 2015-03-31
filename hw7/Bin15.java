import java.lang.Math;
// Don't forget to answer the follow-up question!
public class Bin15 {

    // A string of exactly 15 characters, each a 0 or 1.
    private String myBinStr;

    // A constantly-whining constructor for your testing purposes.
    public Bin15(String input) {

        // Check for null input
        if (input == null) {
            String msg = "Your binary string is null";
            throw new NullPointerException(msg);
        }

        // Check for length
        if (input.length() != 15) {
            String msg = "Your binary string isn't of length 15";
            throw new IllegalArgumentException(msg);
        }

        // Check for illegal characters
        for (int count = 0; count < 15; count++) {
            char c = input.charAt(count);
            // Careful with comparing vs 0 and comparing vs '0'
            if (c != '0' && c != '1') {
                String msg = "Your binary string contains a non-binary character";
                throw new IllegalArgumentException(msg);
            }
        }

        // The input is good. Let's roll.
        this.myBinStr = input;
    }
    
    @Override
    public boolean equals(Object o) {
        // First check to make sure the other object is a Nana. Otherwise
        // return false.
        if (o != null && o instanceof String) {
            String other = (String) o;
            return this.myBinStr == myBinStr ;
            
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        int sum=0;
        for (int i =0;i<15 ;i++ ) {
            char character = myBinStr.charAt(i);
            sum += (int) character * (i+1) * (i+1); 
        }
        return sum;
    }

    /* DO THIS LAST, AFTER IMPLEMENTING EVERYTHING
    Follow-up question: The current length of our myBinStr is 15. What is the
    longest length possible for this String such that we still can produce a
    perfect hash (assuming we can rewrite the hash function)? Write your answer
    in the method followUpAnswer(). 
    */
    public static final int followUpAnswer() {
        return 32; // YOUR CODE HERE. THIS MAY OR MAY NOT BE CORRECT.
    }
    
    public static void main(String[] args) {
        // Optional testing here. Potentially useless information:
        int c = 0x9 - 1 - 0b01;
        // 0x9 means 9 in hexadecimal
        // 1 means 1 in decimal
        // 0b01 means 01 or 1 in binary
        System.out.println("Note to self: Answer follow-up question!");
    }
}

