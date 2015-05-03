public class Node {
  boolean exists;
  Node[] links;
  String output;
  boolean hasl;
  private static final int r = 256;

  public Node() {
    links = new Node[r];
    exists = false;
    output = "";
    hasl = false;
  }
}
