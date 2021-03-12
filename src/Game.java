import java.awt.Color;
import java.awt.Toolkit;

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

	static int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
	static int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;

	// Frames
	int targetFPS = 60;
	long frameTime = 1000 / targetFPS;

	long timer;

	ObjectHandler handler;

	Wand waende[] = new Wand[5];
	
	Door door1, door2, door3, door4;
	
	Camera cam;

	public Game() {

		super("Epic Adventure Game", 0, 0, screenWidth, screenHeight, panel);

		panel.setBackground(Color.GRAY);

		handler = new ObjectHandler();

		player = new Player(handler, this, panel);
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

		waende[0] = new Wand(200, 200, 100, 100, handler);
		waende[1] = new Wand(100, 300, 300, 100, handler);
		waende[2] = new Wand(400, 500, 1000, 100, handler);
		waende[3] = new Wand(1000, 400, 100, 100, handler);
		waende[4] = new Wand(500, 400, 100, 100, handler);

		for (int i = 0; i < waende.length; i++) {
			handler.addObject(waende[i]);
			panel.add(waende[i]);
		}
		
		door1 = new Door(200, 100, 100, 100, handler);
		door2 = new Door (800, 400, 100, 100, handler);
		
		door3 = new Door(2000, 200, 100, 100, handler);
		door4 = new Door (1100, 100, 100, 100, handler);
		
		handler.addObject(door1);;
		handler.addObject(door2);
		panel.add(door1);
		panel.add(door2);
		
		handler.addObject(door3);;
		handler.addObject(door4);
		panel.add(door3);
		panel.add(door4);
		
		door1.connectExit(door2);
		
		door3.connectExit(door4);
		
		player.render();
		
		Enemy enemy1 = new Enemy ("item_t", handler);
		handler.addObject(enemy1);
		panel.add(enemy1);
		enemy1.setPos('x', 100);
		enemy1.setPos('y', 100);
		enemy1.setSize('x', 100);
		enemy1.setSize('y', 100);
		
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