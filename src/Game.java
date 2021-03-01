import javax.swing.JPanel;

public class Game extends Window {

	private static final long serialVersionUID = 6680112103815633456L;

	private static JPanel panel = new JPanel(); // Darauf kommen alle Objekte
	// muss schon hier initialisiert werden, da es im Aufrufen von super() gebraucht
	// wird.

	private Player player;

	// Delta time: siehe https://en.wikipedia.org/wiki/Delta_timing
	private long dt;
	private long lastT;
	
	ObjectHandler handler;

	public Game() {

		super("Epic Adventure Game", 0, 0, 1300, 700, panel);

		// panel.setBackground(Color.RED);

		player = new Player();
		
		handler = new ObjectHandler();

		this.addKeyListener(player);

		panel.add(player);

		player.setVisible(true);

		player.setBounds(100, 100, 50, 80); // xPos, yPos, xSize, ySize

		lastT = System.nanoTime(); // delta time

		while (true) {
			
			dt = System.nanoTime() - lastT; // delta time
			lastT = System.nanoTime(); // delta time
			
			
			
			// System.out.println(player.velX*dt);
			
			handler.tick(player, dt);
			
			handler.addObject(player);
			
			handler.renderAll();
		
			// System.out.println("a");
			
			delay(10);
		}
	}

	

	// Methode zum verzögern (warten) in ms
	private void delay(int time) {

		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
