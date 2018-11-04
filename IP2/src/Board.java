import java.util.ArrayList;
import java.util.Arrays;

public class Board {
	final int numRows = 5; //# rows and columns
	final int numCols = 4;
	ArrayList<Piece> pieces; //pieces on board
	Piece selected; //selected piece
	int[][] grid; //keeps track of pieces and space in board
	
	public final static int UP = 1;
	public final static int DOWN = 2;
	public final static int LEFT = 3;
	public final static int RIGHT = 4;
	public final static int VALID = 5;
	public final static int INVALID = 6;
	public final static int WON = 7;
	//constants to refer to directions to move a piece
	
	public Board() {
		pieces = new ArrayList<Piece>();
		grid = new int[5][4];
		
		//all spaces initially have a value of zero.
		for(int i = 0; i<grid.length; i++) {
			for(int j = 0; j<grid[i].length; j++) {
				grid[i][j] = 0;
			}
		}
		
		RedPiece rp = new RedPiece(2, 2, 1, 0);
		GreyPiece gp1 = new GreyPiece(1, 1, 3, 2);
		this.addPiece(rp);
		this.addPiece(gp1);
	}
	
	//return list of pieces
	public ArrayList<Piece> getPieces() {
		return pieces;
	}
	
	/**
	 * Selects a piece
	 * @param piece piece to select
	 */
	public void selectPiece(Piece piece) {
		selected = piece;
	}
	
	/**
	 * Check if a piece is selected
	 */
	public boolean isSelected(Piece piece) {
		return piece == selected;
	}
	
	/**
	 * Adds a piece to the grid
	 * @param piece the piece to add
	 */
	public void addPiece(Piece piece) {
		
		int posy = piece.getY();
		int posx = piece.getX();
		
		//for each space the piece takes up, mark that space
		for(int i = 0; i<piece.getWidth(); i++) {
			
			posx = piece.getX();
			
			for(int j = 0; j<piece.getLength(); j++) {
				
				grid[posy][posx] = 1;
				++posx;
			}
			++posy;
		}
		pieces.add(piece);
	}
	
	//Copies grid into arr
	void copy(int[][] arr) {
		
		for(int i = 0; i<grid.length; i++) {
			for(int j = 0; j<grid[i].length; j++) {
				arr[i][j] = grid[i][j];
			}
		}
		
	}
	
	/**
	 * Moves selected piece on the grid
	 * @param piece piece to move
	 * @param direction the direction to move it in
	 */
	public int move(int direction) {
		
		//can't move selected piece if no piece is selected
		if(selected == null) {
			return INVALID;
		}
		
		//if the red piece is in a winning position, and the direction is down,
		//win the game
		if(selected.isRed() && selected.getX() == 1 && selected.getY() == 3) {
			
			if(direction == DOWN) {
				return WON;
			}
			
		}
		
		int[][] temp = new int[numRows][numCols]; //temp variable to check if the move can be made
		copy(temp);
		
		int posy = selected.getY();
		int posx = selected.getX();
		
		//first, unmark the piece's space in the graph
		for(int i = 0; i<selected.getWidth(); i++) {
			
			posx = selected.getX();
			
			for(int j = 0; j<selected.getLength(); j++) {
				
				temp[posy][posx] = 0;
				++posx;
			}
			++posy;
		}
		
		posy = selected.getY();
		posx = selected.getX();
		//reset values
		
		//edit the piece's position based on the direction
		switch(direction) {
		
			case UP:
				--posy;
				break;
		
			case DOWN:
				++posy;
				break;
			
			case LEFT:
				--posx;
				break;
				
			case RIGHT:
				++posx;
				break;
		}
		
		//if the position is outside of the board, the move isn't valid.
		//so don't go through with moving it.
		if(posx < 0 || posx > numCols-1 || posy < 0 || posy > numRows-1) {
			return INVALID;
		}
		
		int newPosx = posx; //these value must be stored
		int newPosy = posy;
		
		//now check if the piece can be put in its new position
		for(int i = 0; i<selected.getWidth(); i++) {
			
			posx = newPosx;
			
			for(int j = 0; j<selected.getLength(); j++) {
				
				//if the position is outside of the board, the move isn't valid.
				//so don't go through with moving it.
				if(posx < 0 || posx > numCols-1 || posy < 0 || posy > numRows-1) {
					return INVALID;
				}
				
				//if this space isn't being used, then
				//the move is still valid
				if(temp[posy][posx] == 0) {
					temp[posy][posx] = 1;
					++posx;
				} else { //if the space is being used, the move is invalid. abort method
					return INVALID;
				}
			}
			++posy;
		}
		
		//at this point, the move is verified valid.
		//update piece position and show its mark on the grid.
		selected.setX(newPosx);
		selected.setY(newPosy);
		grid = temp;
		return VALID;
	}
}
