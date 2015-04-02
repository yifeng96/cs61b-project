public class Username {

    // Potentially useless note: (int) '0' == 48, (int) 'a' == 97

    // Instance Variables (remember, they should be private!)
    // YOUR CODE HERE
    private String name ;
    public Username() {
        
    }

    public Username(String reqName) {
        this.name=reqName;
    }

    @Override
    public boolean equals(Object o) {
        // First check to make sure the other object is a Nana. Otherwise
        // return false.
        if (o != null && o instanceof String) {
            String other = (String) o;
            return this.name == other.name;
            // In other words, two Nana's are equal iff their myNums
            // are the same value.
        }
        return false;
    }

    @Override
    public int hashCode() { 
        // YOUR CODE HERE
        return 0;
    }

    public static void main(String[] args) {
        // You can put some simple testing here.
    }
}