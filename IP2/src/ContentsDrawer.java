import javax.swing.JLabel;
import javax.swing.JPanel;

public class ContentsDrawer extends JPanel {
	
	JLabel label;
	PuzzleDrawer pd;
	
	public ContentsDrawer(Application app) {
		label = app.getLabel();
		pd = app.getPuzzleDrawer();
	}
	
	
}
