import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class SelectPieceController {
	
	Application app;
	Model model;
	
	public SelectPieceController(Application app, Model model) {
		this.app = app;
		this.model = model;
	}
	
	/**
	 * Selects a piece if the user clicked on a piece
	 * @param e A mouse event referring to the click
	 */
	public void selectPiece(MouseEvent e) {
		
		if(model.hasWon()) {
			return;
		}
		
		Point point = e.getPoint();
		ArrayList<Piece> pieces = model.getBoard().getPieces();
		ArrayList<Rectangle> rects = app.getPuzzleDrawer().getRects();
		boolean foundSelected = false;
		
		for(Rectangle rect : rects) { //check all rectangles in GUI
			
			if(rect.contains(point)) { //if the click was in that rectangle
				
				for(Piece piece : pieces) { //find the piece with the same position as the rectangle
					
					if(piece.getX() == rect.x/100 && piece.getY() == rect.y/100) {
						model.getBoard().selectPiece(piece);
						foundSelected = true;
						//select the piece
					}
					
				}
				if(foundSelected) { //don't need to search any further
					break;
				}
				
			}
			
		}
		
		if(!foundSelected) {
			model.getBoard().selectPiece(null); //unselect if the mouse click didn't intersect any rects
		}
		
		app.repaint();
		
	}
	
}
