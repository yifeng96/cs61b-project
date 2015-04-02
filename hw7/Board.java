public class Board {

    public static final int SIZE = 8;
    // You can call this variable by Board.SIZE.

	private Piece[][] pieces;
    private boolean isFireTurn;

    public Board() {
        pieces = new Piece[SIZE][SIZE];
        isFireTurn = true;
    }

    /** Makes a custom Board. Not a completely safe operation because you could do
    * some bad stuff here, but this is for the purposes of testing out hash
    * codes so let's forgive the author. 
    */
    public Board(Piece[][] pieces) {
        this.pieces = pieces;
        isFireTurn = true;
    }

	@Override
	public boolean equals(Object o) {
        return true; // YOUR CODE HERE
	}

    @Override
    public int hashCode() {
        int a=0;
        int b=0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < 4; j++) {
                a=a<<1;

                if (pieces[i][j] != null) {
                    a=a|1;
                }
    }
    for (int j = 4; j < 8; j++) {
                b=b<<1;

                if (pieces[i][j] != null) {
                    b=b|1;
                }
    }
}
    int sum = a ^ b;
    return sum;
}
}