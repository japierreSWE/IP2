import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import junit.framework.TestCase;

public class TestCases extends TestCase {
	
	Model model;
	Board board;
	Application app;
	RedPiece rp;
	GreyPiece gp;
	
	protected void setUp() throws Exception {
		super.setUp();
		model = new Model();
		board = model.getBoard();
		board.grid = new int[5][4];
		board.pieces = new ArrayList<Piece>();
		rp = new RedPiece(2, 2, 1, 0);
		gp = new GreyPiece(1, 1, 1, 2);
		board.addPiece(rp);
		board.addPiece(gp);
		app = new Application(model);
		app.setVisible(true);
		//set up a board with one red piece and one grey piece
	}
	
	public void testSelect() throws InterruptedException {
		
		Thread.sleep(500);
		new SelectPieceController(app,model).selectPiece(new Point(150, 100));//should be inside red one
		assertEquals(model.getBoard().selected, rp);
		
		Thread.sleep(500);
		new SelectPieceController(app,model).selectPiece(new Point(150,250)); //should be inside grey piece
		assertEquals(board.selected, gp);
	}
	
	public void testBorders() throws InterruptedException {
		Thread.sleep(500);
		new SelectPieceController(app,model).selectPiece(new Point(150, 100));//should be inside red one
		
		Thread.sleep(500);
		new MovePieceController(app,model).move(KeyEvent.VK_LEFT);
		
		Thread.sleep(500);
		new MovePieceController(app,model).move(KeyEvent.VK_LEFT);
		
		Thread.sleep(500);
		new MovePieceController(app,model).move(KeyEvent.VK_LEFT);
		
		Thread.sleep(500);
		new MovePieceController(app,model).move(KeyEvent.VK_UP);
		//testing upper and left borders
		
		Thread.sleep(500);
		assertEquals(rp.posx,0);
		assertEquals(rp.posy,0); //red piece should be in this position now
		
		
		Thread.sleep(500);
		new MovePieceController(app,model).move(KeyEvent.VK_RIGHT);
		
		Thread.sleep(500);
		new MovePieceController(app,model).move(KeyEvent.VK_RIGHT);
		
		Thread.sleep(500);
		new MovePieceController(app,model).move(KeyEvent.VK_RIGHT);
		
		Thread.sleep(500);
		new MovePieceController(app,model).move(KeyEvent.VK_RIGHT);
		
		Thread.sleep(500);
		new MovePieceController(app,model).move(KeyEvent.VK_RIGHT);
		//test right border
		
		Thread.sleep(500);
		assertEquals(rp.posx,2);
		assertEquals(rp.posy,0); //red piece should be in this position now
		
		
		Thread.sleep(500);
		new MovePieceController(app,model).move(KeyEvent.VK_DOWN);
		
		Thread.sleep(500);
		new MovePieceController(app,model).move(KeyEvent.VK_DOWN);
		
		Thread.sleep(500);
		new MovePieceController(app,model).move(KeyEvent.VK_DOWN);
		
		Thread.sleep(500);
		new MovePieceController(app,model).move(KeyEvent.VK_DOWN);
		
		Thread.sleep(500);
		new MovePieceController(app,model).move(KeyEvent.VK_DOWN);
		//test lower borders
		
		Thread.sleep(500);
		assertEquals(rp.posx,2);
		assertEquals(rp.posy,3); //red piece should be in this position now
	}
	
	public void testPieceBounds() throws InterruptedException { //test invalid moves
		Thread.sleep(500);
		new SelectPieceController(app,model).selectPiece(new Point(150,250)); //should be inside grey piece
		
		Thread.sleep(500);
		new MovePieceController(app,model).move(KeyEvent.VK_LEFT);
		//move grey piece left
		
		Thread.sleep(500);
		new SelectPieceController(app,model).selectPiece(new Point(150, 100));//should be inside red one

		Thread.sleep(500);
		new MovePieceController(app,model).move(KeyEvent.VK_DOWN);
		
		Thread.sleep(500);
		new SelectPieceController(app,model).selectPiece(new Point(50,250)); //should be inside grey piece
		
		Thread.sleep(500);
		new MovePieceController(app,model).move(KeyEvent.VK_RIGHT);//test left side
		assertEquals(gp.posx,0);
		assertEquals(gp.posy,2);
		
		Thread.sleep(500);
		new MovePieceController(app,model).move(KeyEvent.VK_UP);
		
		Thread.sleep(500);
		new MovePieceController(app,model).move(KeyEvent.VK_UP);
		
		Thread.sleep(500);
		new MovePieceController(app,model).move(KeyEvent.VK_RIGHT); //over red piece now
		
		Thread.sleep(500);
		new MovePieceController(app,model).move(KeyEvent.VK_DOWN); //test upper side
		assertEquals(gp.posx,1);
		assertEquals(gp.posy,0);
		
		Thread.sleep(500);
		new MovePieceController(app,model).move(KeyEvent.VK_RIGHT); //over red piece now
		
		Thread.sleep(500);
		new MovePieceController(app,model).move(KeyEvent.VK_RIGHT); 
		
		Thread.sleep(500);
		new MovePieceController(app,model).move(KeyEvent.VK_DOWN); //to the right of red piece now
		
		Thread.sleep(500);
		new MovePieceController(app,model).move(KeyEvent.VK_LEFT);
		assertEquals(gp.posx,3);
		assertEquals(gp.posy,1);
		
		Thread.sleep(500);
		new MovePieceController(app,model).move(KeyEvent.VK_DOWN);
		
		Thread.sleep(500);
		new MovePieceController(app,model).move(KeyEvent.VK_DOWN);
		
		Thread.sleep(500);
		new MovePieceController(app,model).move(KeyEvent.VK_LEFT);
		
		Thread.sleep(500);
		new MovePieceController(app,model).move(KeyEvent.VK_UP);
		assertEquals(gp.posx,2);
		assertEquals(gp.posy,3);
	}

}
