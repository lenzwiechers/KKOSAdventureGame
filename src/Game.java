import java.awt.Color;

import javax.swing.JPanel;

public class Game extends Window {

	private static final long serialVersionUID = 6680112103815633456L;

	private static JPanel panel = new JPanel(); // Darauf kommen alle Objekte
	// muss schon hier initialisiert werden, da es im Aufrufen von super() gebraucht
	// wird.

	private Player player;
	private Item item_t;
	private HUD hud;

	// Delta time: siehe https://en.wikipedia.org/wiki/Delta_timing
	private long dt;
	private long lastT;

	static int screenWidth = 1300;
	static int screenHeight = 700;

	// Frames
	int targetFPS = 60;
	long frameTime = 1000 / targetFPS;

	long timer;

	ObjectHandler handler;

	Wand w�nde[] = new Wand[4];
	
	Camera cam;

	public Game() {

		super("Epic Adventure Game", 0, 0, screenWidth, screenHeight, panel);

		panel.setBackground(Color.GRAY);

		handler = new ObjectHandler();

		player = new Player(handler, this);
		player.setPos('x', (screenWidth / 2) - (player.getSize('x') / 2));
		player.setPos('y', (screenHeight / 2) - (player.getSize('y') / 2));
		
		System.out.println(player.getPos('x'));
		
		cam = new Camera(handler, player, screenWidth, screenHeight);

		hud = new HUD(player);

		this.addKeyListener(player);

		panel.add(player);

		player.setVisible(true);

		handler.addObject(player);

		item_t = new Item(handler, player, panel);
		
		panel.add(item_t);

		item_t.setVisible(true);

		handler.addObject(item_t);

		w�nde[0] = new Wand(200, 200, 100, 100);
		w�nde[1] = new Wand(100, 300, 300, 100);
		w�nde[2] = new Wand(400, 500, 500, 100);
		w�nde[3] = new Wand(1000, 400, 100, 100);

		for (int i = 0; i < w�nde.length; i++) {
			handler.addObject(w�nde[i]);
			panel.add(w�nde[i]);
		}
		
		player.render();
		
		lastT = System.nanoTime(); // delta time

		while (true) {
			timer = System.currentTimeMillis();

			dt = System.nanoTime() - lastT; // delta time
			lastT = System.nanoTime(); // delta time

			handler.tick(dt);

			hud.tick();
			
			cam.tick();

			hud.render(getGraphics());

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

	public static float clamp(float var, float min, float max) {
		if (var >= max) {
			return var = max;
		} else if (var <= min) { // durch diese Methode wird var nicht ausserhalb der Grenzen von min un max
									// gelassen
			return var = min;
		} else {
			return var;
		}
	}
}