import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JPanel;

public class Game extends Window {

	private static final long serialVersionUID = 6680112103815633456L;

	public boolean pause;

	private final static boolean debug = true;

	static JPanel panel = new JPanel() {

		private static final long serialVersionUID = -5196824841345897510L;

		protected void paintComponent(Graphics g) {

			Graphics2D g2 = (Graphics2D) g;
			super.paintComponent(g);

			if (debug) {
				g2.setStroke(new BasicStroke(3));

				for (int i = 0; i < generateMap.enemies.size(); i++) {
					if (generateMap.enemies.get(i).isInScreen) {
						if (generateMap.enemies.get(i).checkContact()) {
							g2.setColor(Color.RED);

						} else {
							g2.setColor(Color.BLACK);
						}
						for (int j = 0; j < generateMap.enemies.get(i).l.length; j++) {
							g2.drawLine((int) generateMap.enemies.get(i).l[j].getX1() - Camera.xPos,
									(int) generateMap.enemies.get(i).l[j].getY1() - Camera.yPos,
									(int) generateMap.enemies.get(i).l[j].getX2() - Camera.xPos,
									(int) generateMap.enemies.get(i).l[j].getY2() - Camera.yPos);
						}
					}

				}

			}

		}
	};

	static ObjectHandler handler = new ObjectHandler(panel);

	public Player player;

	Launcher launcher;

	// Delta time: siehe https://en.wikipedia.org/wiki/Delta_timing
	private long dt;
	private long lastT;

	static int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
	static int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;

	// Frames
	int targetFPS = 60;
	long frameTime = 1000 / targetFPS;

	long timer;

	boolean running = false;

	
	PauseWindow pauseMenu;
	static Camera cam;

	public Game() {

		super("Epic Adventure Game", 0, 0, screenWidth, screenHeight, panel);

		// panel.setBackground(Color.GRAY);
		
		pauseMenu = new PauseWindow(this);
		
		this.add(pauseMenu);

		handler = new ObjectHandler(panel, screenWidth, screenHeight);

		player = new Player(handler, this, panel);
		player.setPos('x', (500) - (player.getSize('x') / 2));
		player.setPos('y', (screenHeight / 2) - (player.getSize('y') / 2));

		player.setPos('x', (screenWidth / 2) - (player.getSize('x') / 2));

		cam = new Camera(handler, player, screenWidth, screenHeight);

		this.addKeyListener(player);
		this.addMouseListener(player);

		panel.add(player);

		player.setVisible(true);

		handler.addObject(player);

		player.render();

		generateMap.generate(handler);

		try {
			launcher = new Launcher(this);
		} catch (MalformedURLException e) {
			running = true;
			this.setVisible(true);
		}

		run();
	}

	public void run() {
		while (!running) {
			delay(10);
		}
		lastT = System.nanoTime(); // delta time
		System.out.println("-> Game started");
		System.out.println();
		while (running) {
			if (pause) {
				System.out.println("-> Game paused");
				System.out.println();
				pauseMenu.setVisible(true);
				while (pause) {
					delay(10);
				}
				lastT = System.nanoTime(); // delta time
				
				pauseMenu.setVisible(false);
				
				System.out.println("-> Game resumed");
				System.out.println();
			}

			timer = System.currentTimeMillis();

			dt = System.nanoTime() - lastT; // delta time
			lastT = System.nanoTime(); // delta timne

			handler.tick(dt);

			cam.tick();

			if (frameTime - ((System.currentTimeMillis() - timer)) > 0) {
				delay(frameTime - (System.currentTimeMillis() - timer));
			}
			panel.repaint();
		}
	}

	// Methode zum verzoegern (warten) in ms
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
		} else if (var <= min) { // durch diese Methode wird var nicht ausserhalb der Grenzen von min und max
									// gelassen
			return var = min;
		} else {
			return var;
		}
	}
}