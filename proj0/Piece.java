public class Piece{

	boolean isFire;
	private Board b;
	private int x;
	private int y;
	private String type;
	private boolean hasCapture;

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

		
		if (this.isBomb()){
			if (Math.abs(this.x-a)==2) {
				b.remove(a-1,y-1);
				b.remove(a-1,y);
				b.remove(a-1,y+1);
				b.remove(a,y-1);
				b.remove(a,y);
				b.remove(a,y+1);
				b.remove(a+1,y-1);
				b.remove(a+1,y);
				b.remove(a+1,y+1);
				hasCapture=true;
		}
		else{
			b.remove(this.x,this.y);
			b.place(this,a,y);

		}}
		else{
			if (Math.abs(this.x-a)==2){
			b.place(this,a,y);

			b.remove((this.x+a)/2,(this.y+y)/2);
			b.remove(this.x,this.y);
			
				hasCapture=true;

			}
			else{
			b.place(this,a,y);
			b.remove(this.x,this.y);
			


			}
		}



		}



	}

/////sasas