import java.awt.event.KeyEvent;

public class MovePieceController {
	
	Application app;
	Model model;
	
	public MovePieceController(Application app, Model model) {
		this.app = app;
		this.model = model;
	}
	
	public void move(int keyCode) {
		
		if(model.hasWon()) {
			return;
		}
		
		int result = -2; //placeholder value
		//move based on keycode
		switch(keyCode) {
		
			case KeyEvent.VK_UP:
				result = model.getBoard().move(Board.UP);
				break;
				
			case KeyEvent.VK_DOWN:
				result = model.getBoard().move(Board.DOWN);
				break;
			
			case KeyEvent.VK_LEFT:
				result = model.getBoard().move(Board.LEFT);
				break;
				
			case KeyEvent.VK_RIGHT:
				result = model.getBoard().move(Board.RIGHT);
				break;
		
		}
		
		if(result == Board.VALID) {
			model.increaseMoves();
		} else if(result == Board.WON) {
			model.win();
		}
		
		app.repaint();
		
	}
	
}
