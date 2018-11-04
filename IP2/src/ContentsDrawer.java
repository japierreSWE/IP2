import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ContentsDrawer extends JPanel {
	
	JLabel label;
	PuzzleDrawer pd;
	Model model;
	
	public ContentsDrawer(Application app, Model m) {
		label = app.getLabel();
		pd = app.getPuzzleDrawer();
		model = m;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		label.setText("Number of moves: " + model.getMoves());
	}
	
}
