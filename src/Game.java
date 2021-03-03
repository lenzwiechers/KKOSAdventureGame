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

	// Frames
	int targetFPS = 60;
	long frameTime = 1000 / targetFPS;

	long timer;

	ObjectHandler handler;

	Wand w�nde[] = new Wand[2];

	public Game() {

		super("Epic Adventure Game", 0, 0, 1300, 700, panel);

		// panel.setBackground(Color.RED);

		handler = new ObjectHandler();

		player = new Player(handler);

		this.addKeyListener(player);

		panel.add(player);

		player.setVisible(true);

		// player.setBounds(100, 100, 50, 80); // xPos, yPos, xSize, ySize

		handler.addObject(player);

		for (int i = 0; i < w�nde.length; i++) {
			w�nde[i] = new Wand();
			handler.addObject(w�nde[i]);
			panel.add(w�nde[i]);
			w�nde[i].setSize('x', 100);
			w�nde[i].setSize('y', 100);
		}

		w�nde[0].setPos('x', 200);
		w�nde[0].setPos('y', 200);

		w�nde[1].setPos('x', 100);
		w�nde[1].setPos('y', 300);
		w�nde[1].setSize('x', 300);

		lastT = System.nanoTime(); // delta time

		while (true) {
			timer = System.currentTimeMillis();

			dt = System.nanoTime() - lastT; // delta time
			lastT = System.nanoTime(); // delta time

			// System.out.println(player.velX*dt);

			handler.tick(player, dt);

			handler.tick(dt);

			handler.renderAll();

			if (frameTime - ((System.currentTimeMillis() - timer)) > 0) {
				try {
					Thread.sleep(frameTime - (System.currentTimeMillis() - timer));
				} catch (InterruptedException ex) {
					Thread.currentThread().interrupt();
				}
			}
		}

	}

	// Methode zum verz�gern (warten) in ms
	private void delay(int time) {

		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}