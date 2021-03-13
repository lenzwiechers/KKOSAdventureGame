import java.awt.*;
import javax.swing.JPanel;

public class Game extends Window {

	private static final long serialVersionUID = 6680112103815633456L;

	private final static boolean debug = false;

	private static JPanel panel = new JPanel() {
		protected void paintComponent(Graphics g) {

			Graphics2D g2 = (Graphics2D) g;
			super.paintComponent(g);

			if (debug) {
				g2.setStroke(new BasicStroke(6));

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

	Camera cam;

	public Game() {

		super("Epic Adventure Game", 0, 0, screenWidth, screenHeight, panel);

		// panel.setBackground(Color.GRAY);

		handler = new ObjectHandler(panel, screenWidth, screenHeight);

		player = new Player(handler, this, panel);
		player.setPos('x', (500) - (player.getSize('x') / 2));
		player.setPos('y', (screenHeight / 2) - (player.getSize('y') / 2));

		player.setPos('x', (screenWidth / 2) - (player.getSize('x') / 2));
		// player.setPos('y', 100);

		System.out.println(player.getPos('x'));

		cam = new Camera(handler, player, screenWidth, screenHeight);

		hud = new HUD(player);

		this.addKeyListener(player);

		panel.add(player);

		player.setVisible(true);

		handler.addObject(player);

		player.render();

		generateMap.generate(handler);

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
			panel.repaint();
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