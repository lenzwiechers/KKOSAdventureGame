import javax.swing.JPanel;

// Launcher �ber den das Spiel gestartet wird (verschiedene Spielst�nde etc.)
public class Launcher extends Window {
	
	private static final long serialVersionUID = -1331029298190778095L;
	
	static JPanel panel = new JPanel();
	
	public Launcher() {
		
		super("Game Launcher", 0, 0, 300, 300, panel);
	
	}
}
