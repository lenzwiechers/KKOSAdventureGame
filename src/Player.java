import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public class Player extends GameObject implements KeyListener {

	private static final long serialVersionUID = 2917881703989759480L;

	private boolean right = false;
	private boolean left = false;
	private boolean up = false;
	private boolean down = false;

	private boolean jump = false;

	public boolean itemT = false;

	public boolean pause = false;
	public boolean pauseRelease = false;

	private int dashlength = 20;
	private int dashcounter = dashlength;
	private float dashspeed = 0.00000200f;
	private int dashcooldown = 180;
	private int cooldowncounter = dashcooldown;

	private boolean inAir = false;
	private int airTime = 0;

	private double gravity;

	private long enemyContactCounter;

	ObjectHandler handler;
	JPanel panel;

	private boolean inWall;

	Window window;

	Inventory inventory;

	String LK;

	public Player(ObjectHandler newHandler, Window window, JPanel newPanel, String LK) {

		super("player", newHandler);

		this.velX = 0.00000024f;
		this.velY = 0.00000025f;

		this.width = 50;
		this.height = 80;
		this.posX = 170;
		this.posY = 100;

		handler = newHandler;
		panel = newPanel;

		this.window = window;

		inventory = new Inventory(window);

		this.LK = LK;
	}

	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == 68 || e.getKeyCode() == 39) { // d/right arrow
			right = true;
		} else if (e.getKeyCode() == 65 || e.getKeyCode() == 37) { // a/left arrow
			left = true;
		} else if (e.getKeyCode() == 32) { // Space bar
			jump = true;
		} else if (e.getKeyCode() == 17) { // Ctrl
			this.velX = 0.0000004f;
		} else if (e.getKeyCode() == 69) { // e
			inventory.showInv();
		} else if (e.getKeyCode() == 81) { // q
			if (dashcounter == dashlength && cooldowncounter == dashcooldown) {
				dashcounter = 0;
				cooldowncounter = 0;
			}
		} else if (e.getKeyCode() == 70) { // f
			enterDoor(atDoor());
		} else if (e.getKeyCode() == 27) { // ESC
			if (!pauseRelease) {
				pause = true;
			} else {
				pause = false;
			}
		}
	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == 65 || e.getKeyCode() == 37) {
			left = false;
		} else if (e.getKeyCode() == 68 || e.getKeyCode() == 39) {
			right = false;
		} else if (e.getKeyCode() == 32) {
			jump = false;
		} else if (e.getKeyCode() == 17) {
			this.velX = 0.00000024f;
		} else if (e.getKeyCode() == 27) { // ESC
			if (pause) {
				pauseRelease = true;
			} else {
				pauseRelease = false;
			}
		}
	}

	public void keyTyped(KeyEvent e) {

	}

	public Door atDoor() {
		Door door = null;

		for (int i = 0; i < handler.tueren.size(); i++) {
			if (this.getBounds().intersects(handler.tueren.get(i).getTpBounds())) {
				door = handler.tueren.get(i);
				return door;
			}
		}

		return door;
	}

	public void enterDoor(Door door) {
		if (door != null) {
			this.setPos('x', door.getExitX());
			this.setPos('y', door.getExitY());
			Camera.renderAll();
		}
	}

	public void tick(long dt) {
		if (dashcounter < dashlength) {
			this.velX = dashspeed;
			dashcounter++;
			if (dashcounter == dashlength) {
				this.velX = 0.00000024f;
			}
		}
		if (cooldowncounter < dashcooldown) {
			cooldowncounter++;
		}

		if (right && !left) {
			if (this.name == "player_inverted") {
				this.changeName("player");
			}
			posX += velX * dt;
			while (wallCollision()) {
				posX -= 1;
			}
		}
		if (left && !right) {
			if (this.name == "player") {
				this.changeName("player_inverted");
			}
			posX -= velX * dt;
			while (wallCollision()) {
				posX += 1;
			}
		}

		if (jump && onWall()) {
			velY = -0.0000009f;
		}

		addGravity();
		posY += velY * dt;
		inWall = false;
		if (wallCollision()) {
			inWall = true;
		}
		while (wallCollision()) {
			posY -= Math.signum(velY);

		}

		for (int i = 0; i < handler.enemies.size(); i++) {
			if (this.getBounds().intersects(handler.enemies.get(i).getBounds())) {
				if (System.currentTimeMillis() - enemyContactCounter > 1000) {
					HUD.HEALTH -= 20;
					enemyContactCounter = System.currentTimeMillis();
				}
			}
		}

		if (inWall) {
			velY = 0;
		}

		if (onWall()) {
			velY = 0;
		}
	}
}
