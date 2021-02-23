import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game extends Window {

	private static final long serialVersionUID = 6680112103815633456L;

	private static JPanel panel = new JPanel(); // Darauf kommen alle Objekte
	// muss schon hier initialisiert werden, da es im Aufrufen von super() gebraucht wird.
	
	private Player player;

	public Game() {
		
		super("Epic Adventure Game", 0, 0, 300, 300, panel);		//HI NIK IS HIER!!
		
		panel.setBackground(Color.BLACK);
		
		player = new Player();
		
		this.addKeyListener(player);
	}

}
