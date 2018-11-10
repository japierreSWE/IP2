import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JPanel;

public class PuzzleDrawer extends JPanel {
	
	Model model;
	Application app;
	ArrayList<Rectangle> rects = new ArrayList<Rectangle>();
	
	public PuzzleDrawer(Model m, Application a) {
		model = m;
		app = a;
		this.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				new SelectPieceController(app, model).selectPiece(e.getPoint());
				
			}
		});
	}
	
	//return the rectangles referring to the pieces
	public ArrayList<Rectangle> getRects() {
		return rects;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Board board = model.getBoard();
		rects.clear();
		
		g.fillRect(100, 480, 200, 20);
		
		//paint all pieces in their correct locations on the grid
		for(Piece p : board.getPieces()) {
			int x = p.getX() * 100; //x, y, length and width of resulting rect
			int y = p.getY() * 100; 
			int l = p.getLength() * 100;
			int w = p.getWidth() * 100;
			Rectangle rect = new Rectangle(x,y,l,w);
			rects.add(rect);
			
			if(p.isRed()) {
				g.setColor(Color.RED);
				g.fillRect(x, y, l, w);
				g.setColor(Color.BLACK);
				g.drawRect(x, y, l, w);
			} else {
				g.setColor(Color.GRAY);
				g.fillRect(x, y, l, w);
				g.setColor(Color.BLACK);
				g.drawRect(x, y, l, w);
			}
			
			if(board.isSelected(p)) { //if the piece is selected
				
				Graphics2D g2 = (Graphics2D)g;
				Stroke temp = g2.getStroke();
				g2.setStroke(new BasicStroke(3));
				g2.drawRect(x, y, l, w);
				g2.setStroke(temp);
			}
			
		}
		
		if(model.hasWon()) {
			g.setFont(new Font("Arial", Font.PLAIN, 20));
			g.drawString("Congratulations!", 125, 250);
		}
		
	}
	
}
