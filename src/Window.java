import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window extends JFrame {

	private static final long serialVersionUID = -4386390300391209695L;

	private int frameXPos, frameYPos;
	private int frameXSize;
	private int frameYSize;

	private JPanel panel;

	public Window(String title, int frameXPos, int frameYPos, int frameXSize, int frameYSize, JPanel panel) {

		super(title);

		this.frameXPos = frameXPos;
		this.frameYPos = frameYPos;
		this.frameXSize = frameXSize;
		this.frameYSize = frameYSize;

		this.setLocation(frameXPos, frameYPos);
		this.setSize(frameXSize, frameYSize);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setResizable(false);
		
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		this.setUndecorated(true);

		this.setVisible(true);

		// Der Inhalt des Panels wird auf dem Frame angezeigt:
		this.setContentPane(panel);
		this.getContentPane().setLayout(null);
		
		// this.getContentPane().add(panel, null);
	}

	public int getSize(char coordinate) {
		if (coordinate == 'x') {
			return frameXSize;
		} else if (coordinate == 'y') {
			return frameYSize;
		}
		return -1; // Wenn coordinate weder x noch y ist
	}

}
