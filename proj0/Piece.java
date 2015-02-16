public class Piece{
	private Piece a;
	boolean isFire;
	Board b;
	int x;
	int y;
	String type;
	private boolean hasCapture=false;

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


	public void move(int x, int y){

		Board b = new Board (true);
		if (this.isBomb()){
			if ((this.x+x)%2==0) {b.remove(x-1,y-1);
				b.remove(x-1,y);
				b.remove(x-1,y+1);
				b.remove(x,y-1);
				b.remove(x,y);
				b.remove(x,y+1);
				b.remove(x+1,y-1);
				b.remove(x+1,y);
				b.remove(x+1,y+1);
				hasCapture=true;
		}
		else{
			this.x=x;
			this.y=y;

		}}
		else{
			if ((this.x+x)%2==0){
				b.remove((this.x+x)/2,(this.y+y)/2);
				this.x=x;
				this.y=y;
				hasCapture=true;

			}
			else{
				this.x=x;
				this.y=y;

			}
		}



		}



	}

/////sasas