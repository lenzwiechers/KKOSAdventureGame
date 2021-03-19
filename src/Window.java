import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window extends JFrame {

	private static final long serialVersionUID = -4386390300391209695L;

	private int frameXSize;
	private int frameYSize;


	public Window(String title, int frameXPos, int frameYPos, int frameXSize, int frameYSize, JPanel panel) {

		super(title);

		// Einstellen des Frames:
		this.setLocation(frameXPos, frameYPos);
		this.setSize(frameXSize, frameYSize);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setResizable(false);

		this.setExtendedState(JFrame.MAXIMIZED_BOTH);

		this.setUndecorated(true);

		// Der Inhalt des Panels wird auf dem Frame angezeigt:
		this.setContentPane(panel);
		this.getContentPane().setLayout(null);

		// this.getContentPane().add(panel, null);
	}

	public Window(String title, int frameXPos, int frameYPos, int frameXSize, int frameYSize) {

		super(title);


		this.setLocation(frameXPos, frameYPos);
		this.setSize(frameXSize, frameYSize);

		this.setUndecorated(true);

		this.setResizable(false);
		
		this.setVisible(true);
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
