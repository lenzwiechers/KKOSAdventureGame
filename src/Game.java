import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.JPanel;


public class Game extends Window {

	private static final long serialVersionUID = 6680112103815633456L;

	private static JPanel panel = new JPanel(); // Darauf kommen alle Objekte
	// muss schon hier initialisiert werden, da es im Aufrufen von super() gebraucht
	// wird.

	private Player player;
	private HUD hud;

	// Delta time: siehe https://en.wikipedia.org/wiki/Delta_timing
	private long dt;
	private long lastT;

	static int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
	static int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;

	// Frames
	int targetFPS = 60;
	long frameTime = 1000 / targetFPS;

	long timer;

	ObjectHandler handler;
	
	Camera cam;

	public Game() {

		super("Epic Adventure Game", 0, 0, screenWidth, screenHeight, panel);

		panel.setBackground(Color.GRAY);

		handler = new ObjectHandler(panel);

		player = new Player(handler, this, panel);
		player.setPos('x', (500) - (player.getSize('x') / 2));
		player.setPos('y', (screenHeight / 2) - (player.getSize('y') / 2));
		
		System.out.println(player.getPos('x'));
		
		cam = new Camera(handler, player, screenWidth, screenHeight);

		hud = new HUD(player);

		this.addKeyListener(player);

		panel.add(player);

		player.setVisible(true);

		handler.addObject(player);
		
		player.render();
		
		Enemy enemy1 = new Enemy ("item_t", handler);
		handler.addObject(enemy1);
		panel.add(enemy1);
		enemy1.setPos('x', 100);
		enemy1.setPos('y', 100);
		enemy1.setSize('x', 100);
		enemy1.setSize('y', 100);
		
		generateMap.generate(panel, handler);
		
		lastT = System.nanoTime(); // delta time

		run();
	}
	
	public void run() {
		while (true) {
			timer = System.currentTimeMillis();

			dt = System.nanoTime() - lastT; // delta time
			lastT = System.nanoTime(); // delta time

			handler.tick(dt);

			hud.tick();
			
			cam.tick();
			
			

			hud.render(getGraphics());

			if (frameTime - ((System.currentTimeMillis() - timer)) > 0) {
				delay(frameTime - (System.currentTimeMillis() - timer));
			}
		}
	}

	// Methode zum verzögern (warten) in ms
	private void delay(long l) {

		try {
			Thread.sleep(l);
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