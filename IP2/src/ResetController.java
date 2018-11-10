import java.awt.event.KeyEvent;

public class ResetController {
	
	Application app;
	Model model;
	
	public ResetController(Application app, Model model) {
		this.app = app;
		this.model = model;
	}
	
	public void reset(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_R) {
			model.reset();
			app.repaint();
		}
		
	}
	
}
