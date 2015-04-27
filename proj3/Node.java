public class Node{
    	boolean exists;
    	Node[] links;
    	String output;
    	private static final int r = 128;
    	
    	public Node() {
    		links = new Node [r];
    		exists = false;
    		output = "";
    	}
    }