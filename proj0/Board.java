public class Board {
	private Piece[][] myBoard;
	private static final int MINSIZE = 1;
	private static final int MAXSIZE = 8;
	private int selectedX;
	private int selectedY;
	private Piece selectedPiece;
	private boolean hasSelected = false;
	private boolean hasMoved = false;
	private boolean fireTurn = true;
	private boolean onlyFireLeft = false;
	private boolean onlyWaterLeft = false;
	//starts a GUI supported version of the game.
	public static void main(String arg[]) {
        StdDrawPlus.setXscale(0, MAXSIZE);
        StdDrawPlus.setYscale(0, MAXSIZE);
		Board b = new Board(false);
		while(true) {
			if (StdDrawPlus.mousePressed()) {
				int x = (int) StdDrawPlus.mouseX();
				int y = (int) StdDrawPlus.mouseY();
				if (b.canSelect(x, y)) {
					b.select(x, y);
				}
 			}
 			else if(StdDrawPlus.isSpacePressed() && b.canEndTurn()) {
 				b.endTurn();
 			}
 			b.drawBoard();
 			StdDrawPlus.show(100);
 		}
	}
	
	private void drawBoard() {
        for (int i = 0; i < MAXSIZE; i++) {
            for (int j = 0; j < MAXSIZE; j++) {
        			if (hasSelected && i == selectedX && j == selectedY) {
        				StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            	}
                if ((i + j) % 2 == 0) {
                	StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                } else {                 
                	StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                if (myBoard[i][j] != null) {
                    drawPiece(i, j, myBoard[i][j]);
                }
            }
        }
    }
	
	private void drawPiece(int x, int y, Piece p) {
		String img = "img/";

		if (p.isBomb()) {
			img += "bomb";
		}
		else if (p.isShield()) {
			img += "shield";
		}
		else {
			img += "pawn";
		}
		if (p.isFire()) {
			img += "-fire";
		}
		else {
			img += "-water";
		}
		if (p.isKing()) {
			img += "-crowned";
		}
		img += ".png";

		StdDrawPlus.picture(x + .5, y + .5, img, 1, 1);
	} 
	//The constructor for Board. If shouldBeEmpty is true, initializes
	//an empty Board. Otherwise, initializes a Board with the default 
	//configuration. Note that an empty Board could possibly be useful 
	//for testing purposes.
	public Board(boolean shouldBeEmpty){
		if (shouldBeEmpty == true){
			myBoard = new Piece[8][8];
		} else {
			myBoard = new Piece[8][8];
			for (int i = 0; i < 8; i+=2){
				int j = 0;
				this.myBoard[i][j] = new Piece(true, this, i, j, "pawn");
			}
			for (int i = 1; i < 8; i+=2){
				int j = 1;
				this.myBoard[i][j] = new Piece(true, this, i, j, "shield");
			}
			for (int i = 0; i < 8; i+=2){
				int j = 2;
				this.myBoard[i][j] = new Piece(true, this, i, j, "bomb");
			}
			for (int i = 1; i < 8; i+=2){
				int j = 5;
				this.myBoard[i][j] = new Piece(false, this, i, j, "bomb");
			}
			for (int i = 0; i < 8; i+=2){
				int j = 6;
				this.myBoard[i][j] = new Piece(false, this, i, j, "shield");
			}
			for (int i = 1; i < 8; i+=2){
				int j = 7;
				this.myBoard[i][j] = new Piece(false, this, i, j, "king");
			}
		}
	}
	
	//Gets the piece at position (x, y) on the board, or null if there 
	//is no piece. If (x, y) are out of bounds, returns null.
	public Piece pieceAt(int x, int y) {
		if (x > MAXSIZE - 1 || x < MINSIZE - 1 || y > MAXSIZE - 1 || y < MINSIZE - 1 ) {
			return null;
		}
		if (myBoard[x][y] == null) {
			return null;
		} else {
			return myBoard[x][y];
		}
	}
	
	//Returns true if the square at (x, y) can be selected.
	//A square with a piece may be selected if it is the corresponding 
	//player's turn and one of the following is true:
	//The player has not selected a piece yet.
	//The player has selected a piece, but did not move it.
	//An empty square may be selected if one of the following is true:
	//During this turn, the player has selected a Piece which hasn't moved 
	//yet and is selecting an empty spot which is a valid move for the 
	//previously selected Piece.
	//During this turn, the player has selected a Piece, captured, and has 
	//selected another valid capture destination. When performing 
	//multi-captures, you should only select the active piece once; all 
	//other selections should be valid destination points.
	public boolean canSelect(int x, int y) {
		if (x > MAXSIZE - 1 || x < MINSIZE - 1 || y > MAXSIZE - 1 || y < MINSIZE - 1 ) {
			return false;
		}
		if (myBoard[x][y] != null && (!hasSelected || !hasMoved) && fireTurn == myBoard[x][y].isFire()) {
			return true;
		}
		if (myBoard[x][y] == null && hasSelected) {
			if (!hasMoved && validMove(selectedX, selectedY, x, y)) {
			return true;
			} else if (selectedPiece.hasCaptured() && validMove(selectedX, selectedY, x, y)){
				return true;
			}
		}
		return false;
	}
	
	//Returns true if the piece at (xi, yi) can either move to (xf, yf) or 
	//capture to (xf, yf), strictly from a geometry/piece-race point of view. 
	//validMove does not need to take into consideration whose turn it is or 
	//if a move has already been made, but rather can.
	private boolean validMove(int xi, int yi, int xf, int yf) {
		if (xi > MAXSIZE - 1 || xi < MINSIZE - 1 || yi > MAXSIZE - 1 || yi < MINSIZE - 1 || 
			xf > MAXSIZE - 1 || xf < MINSIZE - 1 || yf > MAXSIZE - 1 || yf < MINSIZE - 1) {
			return false;
		}
		if (myBoard[xf][yf] == null && ((xf - xi == 1 && yf - yi == 1) || (xi - xf == 1 && yi - yf == 1))) {
			return true;
		}
		if (myBoard[(xi + xf) / 2][(yi + yf) / 2] != null && myBoard[xf][yf] == null && 
			((xf - xi == 2 && yf - yi == 2) || (xi - xf == 2 && yi - yf == 2))) {
			if (myBoard[(xi + xf) / 2][(yi + yf) / 2].isFire() != myBoard[xi][xf].isFire()) {
				return true;
			}
		}
		return false;
	}
	
	//Selects the square at (x, y). This method assumes canSelect (x,y) returns 
	//true. Optionally, it is recommended to color the background of the 
	//selected square white on the GUI via the pen color function. For any 
	//piece to perform a capture, that piece must have been selected first. 
	//If you select a square with a piece, you are prepping that piece for 
	//movement. If you select an empty square (assuming canSelect returns true), 
	//you should move your most recently selected piece to that square.
	public void select(int x, int y) {
		if (myBoard[x][y] != null) {
			selectedX = x;
			selectedY = y;
			selectedPiece = myBoard[x][y];
			hasSelected = true;
		} else {
			selectedPiece.move(x, y);
			selectedX = x;
			selectedY = y;
			hasMoved = true;
		}
	}
	
	public void place(Piece p, int x, int y) {
		if (x > MAXSIZE - 1 || x < MINSIZE - 1 || y > MAXSIZE - 1 || y < MINSIZE - 1 ) {
		}
		if (myBoard[x][y] == null) {
		} else {
			myBoard[x][y] = p;
		}
	}
	
	public Piece remove(int x, int y){
		Piece removedPiece = myBoard[x][y];
		if (x > MAXSIZE - 1 || x < MINSIZE - 1 || y > MAXSIZE - 1 || y < MINSIZE - 1 ) {
			System.out.println("Out of bound!");
			return null;
		}
		if (myBoard[x][y] == null) {
			System.out.println("No piece!");
			return null;
		} else {
			return removedPiece;
		}
	}
	
	public boolean canEndTurn(){
		return hasMoved;
	}
	
	public void endTurn(){
		if (selectedPiece != null) {
			selectedPiece.doneCapturing();
		}
		hasSelected = false;
		selectedPiece = null;
		hasMoved = false;
		fireTurn = !fireTurn;
	}
	
	public String winner(){
		for (int i = 0; i < MAXSIZE; i++) {
			for (int j = 0; j < MAXSIZE; j++) {
				if (myBoard[i][j] != null) {
					if (myBoard[i][j].isFire()) {
						onlyFireLeft = true;
					}
					else {
						onlyWaterLeft = true;
					}
				}
			}
		}
		if (onlyFireLeft == true && onlyWaterLeft == true) {
			return null;
		}
		if (onlyFireLeft == true && onlyWaterLeft == false) {
			return "Fire";
		}
		if (onlyFireLeft == false && onlyWaterLeft == true) {
			return "Water";
		}
		else {
			return "No one";
		}
	}
}
