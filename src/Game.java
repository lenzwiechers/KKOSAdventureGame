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

	public Game() {

		super("Epic Adventure Game", 0, 0, 1300, 700, panel);

		// panel.setBackground(Color.RED);

		player = new Player();

		this.addKeyListener(player);

		panel.add(player);

		player.setVisible(true);

		player.setBounds(100, 100, 50, 80); // xPos, yPos, xSize, ySize

		lastT = System.nanoTime(); // delta time

		while (true) {
			
			dt = System.nanoTime() - lastT; // delta time
			lastT = System.nanoTime(); // delta time
			
			if(player.left) {
				player.setPos('x', player.getPos('x') - (int) (player.velX*dt));
			} else if(player.right) {
				player.setPos('x', player.getPos('x') + (int) (player.velX*dt));
			}
			
			// System.out.println(player.velX*dt);
			
			render(player);
		
			// System.out.println("a");
			
			delay(10);
		}
	}

	private void render(GameObject obj) {
		obj.setBounds(obj.getPos('x'), obj.getPos('y'), obj.getSize('x'), obj.getSize('y'));
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
