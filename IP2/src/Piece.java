
abstract public class Piece {
	int length; //length and width of piece
	int width;
	int posx; //zero-indexed position on the board
	int posy;
	
	public Piece() {}
	
	//Make a piece with a specific position and size
	public Piece(int l, int w, int x, int y) {
		length = l;
		width = w;
		posx = x;
		posy = y;
	}
	
	public int getX() { //return x position
		return posx;
	}

	public int getY() { //return y position
		return posy;
	}
	
	public int getLength() { //return length
		return length;
	}
	
	public int getWidth() { //return width
		return width;
	}
	
	public void setX(int x) { //set x position
		posx = x;
	}
	
	public void setY(int y) { //set y position
		posy = y;
	}
	
	public abstract boolean isRed(); //returns whether a piece is red
}
