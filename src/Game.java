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

	Wand wände[] = new Wand[4];

	public Game() {

		super("Epic Adventure Game", 0, 0, 1300, 700, panel);

		// panel.setBackground(Color.RED);

		handler = new ObjectHandler();

		player = new Player(handler, this);

		this.addKeyListener(player);

		panel.add(player);

		player.setVisible(true);

		// player.setBounds(100, 100, 50, 80); // xPos, yPos, xSize, ySize

		handler.addObject(player);
		
		wände[0]  = new Wand(200, 200, 100, 100);
		wände[1]  = new Wand(100, 300, 300, 100);
		wände[2]  = new Wand(400, 500, 500, 100);
		wände[3]  = new Wand(1000, 400, 100, 100);

		for (int i = 0; i < wände.length; i++) {
			handler.addObject(wände[i]);
			panel.add(wände[i]);
		}

		lastT = System.nanoTime(); // delta time

		while (true) {
			timer = System.currentTimeMillis();

			dt = System.nanoTime() - lastT; // delta time
			lastT = System.nanoTime(); // delta time

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
	// Methode zum verzögern (warten) in ms
	private void delay(int time) {

		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}