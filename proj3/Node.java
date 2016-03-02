public class Node {
    boolean exists;
    Node[] links;
    String output;
    boolean hasl;
    private static final int RUM = 256;

    public Node() {
        links = new Node[RUM];
        exists = false;
        output = "";
        hasl = false;
    }
}
