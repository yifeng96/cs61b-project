public class Piece{

	private boolean isFire;
	private Board b;
	private int x;
	private int y;
	private String type;
	private boolean hasCapture;
	private boolean isKing = false;

	public Piece (boolean isFire, Board b, int x, int y, String type) {
		this.isFire=isFire;
		this.b=b;
		this.x=x;
		this.y=y;
		this.type=type;

	}

	public boolean isFire(){
	
		return this.isFire;



	}


	public int side(){
	
		if (this.isFire){
			return 0;
		}
		else{
			return 1;
		}


	}

	public boolean isKing(){
		
		if (this.type=="king"){
			return true;

		}
		else {return false;

		}

	}

	public boolean isBomb(){
	
	if (this.type=="bomb"){
		return true;

	}
	else {return false;

	}

	}
	public boolean isShield(){
		
		if (this.type=="shield"){
			return true;

		}
		else {return false;

		}

	}

	public boolean hasCaptured(){
	
	return  hasCapture;
 

	}
	public void doneCapturing(){
		
		hasCapture=false;


	}


	public void move(int a, int y){
		int p =this.x;
		int q =this.y;
		b.remove(p,q);
		b.place(this, a, y);
		this.x = a;
		this.y = y;
		if (Math.abs(p-a)==2){
			

			b.remove((p+a)/2,(q+y)/2);
			

			hasCapture=true;

			}
			
			
		if (isBomb() && Math.abs(p-a)==2){
			
				Piece pi=b.pieceAt(a-1,y-1);
				if (pi != null && !pi.isShield()) {b.remove(this.x-1,this.y-1);}
				Piece pi1=b.pieceAt(a-1,y);
				if (pi1 != null && !pi1.isShield()) {b.remove(this.x-1,this.y);}
				Piece pi2=b.pieceAt(a-1,y+1);
				if (pi2 != null && !pi2.isShield()) {b.remove(this.x-1,this.y+1);}
				Piece pi3=b.pieceAt(a,y-1);
				if (pi3 != null && !pi3.isShield()) {b.remove(this.x,this.y-1);}
				Piece pi4=b.pieceAt(a,y);
				if (pi4 != null && !pi4.isShield()) {b.remove(this.x,this.y);}
				Piece pi5=b.pieceAt(a,y+1);
				if (pi5 != null && !pi5.isShield()) {b.remove(this.x,this.y+1);}
				Piece pi6=b.pieceAt(a+1,y-1);
				if (pi6 != null && !pi6.isShield()) {b.remove(this.x+1,this.y-1);}
				Piece pi7=b.pieceAt(a+1,y);
				if (pi7 != null && !pi7.isShield()) {b.remove(this.x+1,this.y);}
				Piece pi8=b.pieceAt(a+1,y+1);
				if (pi8 != null && !pi8.isShield()) {b.remove(this.x+1,this.y+1);}
				
				hasCapture=true;
		
			


			
		}
	if ((isFire && y == 7) || (!isFire && y == 0)) {
			isKing = true;


		}

}

	}

/////sasas