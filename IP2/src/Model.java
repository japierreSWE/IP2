
public class Model {
	int moves; //number of moves
	Board board;
	static boolean won = false; //is the game won?
	
	public Model() {
		moves = 0;
		board = new Board();
	}
	
	//increases the number of moves made
	public void increaseMoves() {
		++moves;
	}
	
	public void reset() {
		won = false;
		moves = 0;
		board = new Board();
	}
	
	public int getMoves() {
		return moves;
	}
	
	public Board getBoard() {
		return board;
	}
	
	public boolean hasWon() {
		return won;
	}
	
	//method called if game is won
	public void win() {
		won = true;
	}
}
