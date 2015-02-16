public class Piece {
	private boolean isFire;
	private Board b;
	private int x;
	private int y;
	private String type;
	private boolean isKing = false;
	private boolean hasCaptured;
	
	public Piece(boolean isFire, Board b, int x, int y, String type){
		this.isFire = isFire;
		this.b = b;
		this.x = x;
		this.y = y;
		this.type = type;
	}

	public boolean isFire(){
		return isFire;
	}
	
	//Returns 0 if the piece is a fire piece, or 1 if the piece is a 
	//water piece. This might seem redundant with isFire(), and there 
	//are better ways, but for this 0th project, we'll just provide 
	//these both as tools that you might want to use later.
	public int side(){
		if (isFire == true){
		return 0;
		} else {
		return 1;
		}
	}
	
	public boolean isKing(){
		return isKing;
	}
	
	public boolean isBomb(){
		return type.equals("bomb");
	}
	
	public boolean isShield(){
		return type.equals("shield");
	}
	
	//Assumes this Piece's movement from its current position to (x, y) 
	//is valid. Moves the piece to (x, y), capturing any intermediate 
	//piece if applicable. This will be a difficult method to write.
	public void move(int x, int y){
		b.remove(this.x, this.y);
		b.place(this, x, y);
		int origX = this.x;
		int origY = this.y;
		this.x = x;
		this.y = y;
		Piece mayRemove = b.pieceAt((origX + this.x)/2, (origY + this.y)/2);
		if (this.x - origX == 2 || origX - this.x == 2) {
			if (mayRemove != null) {
				b.remove((origX + this.x)/2, (origY + this.y)/2);
				hasCaptured = true;
			}
		}
		if (this.isBomb() && (this.x - origX == 2 || origX - this.x == 2)){
			if (mayRemove != null) {
				b.remove(this.x, this.y);
				b.remove(this.x + 1, this.y + 1);
				b.remove(this.x + 1, this.y);
				b.remove(this.x + 1, this.y - 1);
				b.remove(this.x, this.y + 1);
				b.remove(this.x, this.y - 1);
				b.remove(this.x - 1, this.y + 1);
				b.remove(this.x - 1, this.y);
				b.remove(this.x - 1, this.y - 1);
				hasCaptured = true;
			}
		}
		if ((isFire && y == 7) || (!isFire && y == 0)) {
			isKing = true;
		}
	}
	
	public boolean hasCaptured(){
		return hasCaptured;
	}
	
	public void doneCapturing(){
		hasCaptured = false;		
	}
}

