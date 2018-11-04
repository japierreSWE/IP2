import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Label;

public class Application extends JFrame {

	private ContentsDrawer cd;
	PuzzleDrawer pd;
	JLabel label;
	Model model;
	/**
	 * @wbp.nonvisual location=181,277
	 */
	private final JLabel congratulations = new JLabel("New label");
	
	/**
	 * Create the frame.
	 */
	public Application(Model m) {
		model = m;
		setTitle("Block Puzzle");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 426, 620);
		pd = new PuzzleDrawer(m, this);
		label = new JLabel("New label");
		cd = new ContentsDrawer(this, m);
		cd.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(cd);
		
		
		pd.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		this.addKeyListener(new KeyAdapter() {
			
			public void keyPressed(KeyEvent e) {
				new MovePieceController(Application.this, model).move(e);
			}
			
		});
		
		this.addKeyListener(new KeyAdapter() {
			
			public void keyPressed(KeyEvent e) {
				new ResetController(Application.this, model).reset(e);
			}
			
		});
		
		GroupLayout gl_contentPane = new GroupLayout(cd);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(7)
							.addComponent(pd, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(157)
							.addComponent(label)))
					.addContainerGap(9, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(24)
					.addComponent(pd, GroupLayout.PREFERRED_SIZE, 500, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addGap(34))
		);
		cd.setLayout(gl_contentPane);
	}
	
	public Application() {
		this(new Model());
	}
	
	/**
	 * Get the frame's label.
	 *
	 */
	public JLabel getLabel() {
		return label;
	}
	
	public JLabel getCongrats() {
		return congratulations;
	}
	
	/**
	 * Get the puzzle drawer.
	 */
	public PuzzleDrawer getPuzzleDrawer() {
		return pd;
	}
}
