import java.awt.*;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Game extends Window {

	private static final long serialVersionUID = 6680112103815633456L;

	public boolean pause; // speichert ob das Spiel pausiert wird

	private final static boolean debug = false; // wenn true, werden die Linien vom Player zu den Gegnern (Lines of
												// Sight) gerenderrt

	static JPanel panel = new JPanel() { // Panel auf dem alle Objekte gerendert werden

		private static final long serialVersionUID = -5196824841345897510L;

		protected void paintComponent(Graphics g) { // Diese Methode rendert Die Lines Of Sight

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

	ObjectHandler handler = new ObjectHandler(panel); // ObjectHandler wird erschaffen

	public Player player; // Player wird deklariert

	Launcher launcher; // Launcher wird deklarieert

	// Delta time: siehe https://en.wikipedia.org/wiki/Delta_timing
	public long dt;
	private long lastT;

	public static int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width; // wird f�r verschiedene Objekte/
																						// Methoden verwendet
	public static int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;

	// Frames
	int targetFPS = 60;
	long frameTime = 1000 / targetFPS;

	long timer;

	boolean running = false;

	PauseWindow pauseMenu; // PauseMenu wird deklariert
	static Camera cam; // Camera wird deklariert

	public Game() { // Konstruktor von Game

		super("Epic Adventure Game", 0, 0, screenWidth, screenHeight, panel); // Aufrufen der �bergeordneten Klasse

		// panel.setBackground(Color.GRAY);

		pauseMenu = new PauseWindow(this); // PauseMenu wird erschaffen

		this.add(pauseMenu);

		handler = new ObjectHandler(panel, screenWidth, screenHeight); // ObjectHandler wird erschaffen

		player = new Player(handler, this, panel);
		player.setPos('y', 12900); 	// 8900

		player.setPos('x', 50);		// 50

		player.setPos('x', 20);

		cam = new Camera(handler, player, screenWidth, screenHeight); // Camera wird erschaffen

		// Wichtige Listener werden geaddet
		this.addKeyListener(player);
		this.addMouseListener(player);

		panel.add(player); // Player aufs Panel

		handler.addObject(player); // Player wird dem ObjectHandler hinzugef�gt

		player.render(); // Player muss ein Mal gerendert werden

		generateMap.generate(handler); // Map wird generiert

		for (int i = 0; i < handler.enemies.size(); i++) {
			System.out.println(handler.enemies.get(i).type);
			if (handler.enemies.get(i).type == 2) {
				panel.add(handler.enemies.get(i).bossHealthBar); // BossHealthBar wird dem Panel hinzugef�gt
				panel.setComponentZOrder(handler.enemies.get(i).bossHealthBar, 0); // BossHealthBar wird ganz oben
																					// gerendert
			}
			panel.setComponentZOrder(handler.enemies.get(i), 0); // Enemies werden ganz oben gerendert
		}
		
		for(int i = 0; i < handler.tutorialLabels.size(); i++) {
			panel.setComponentZOrder(handler.tutorialLabels.get(i), 0); // TutorialLabels werden ganz oben gerendert
		}

		try {
			launcher = new Launcher(this); // Launcher wird erschaffen
		} catch (MalformedURLException e) { // falls was schiefl�uft
			running = true;
			this.setVisible(true);
		}

		run(); // game wird gestartet
	}

	public void run() {

		while (!running) {
			delay(10);
		}
		lastT = System.nanoTime(); // delta time
		System.out.println("-> Game started");
		System.out.println();
		System.out.println("LK1: " + player.LK1);
		System.out.println("LK2: " + player.LK2);
		while (running) {
			if (pause) { // falls pausiert wird
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

			// Handler und Cam ticken:
			handler.tick(dt);
			cam.tick();

			if (frameTime - ((System.currentTimeMillis() - timer)) > 0) {
				delay(frameTime - (System.currentTimeMillis() - timer));
			}
			panel.repaint(); // Lines of Sight werden neu gerendert
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

	public void closeGame() { // Methode zum Beenden des Programms
		this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}
}