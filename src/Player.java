import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Point;

import javax.swing.JPanel;

public class Player extends GameObject implements KeyListener, MouseListener {

	private static final long serialVersionUID = 2917881703989759480L;

	Point a;

	private boolean right = false;
	private boolean left = false;
	private boolean lookright = true;
	private int walkcounter = 0;
	private int walkspeed = 5;

	private boolean jump = false;

	boolean[] item = new boolean[4];

	public boolean pause = false;
	public boolean pauseRelease = false;

	private int equipped = -1;
	private int dashlength = 10;
	private int dashcounter = dashlength;
	private float dashspeed = 0.00000200f;
	private int dashcooldown = 180;
	private int cooldowncounter = dashcooldown;

	public boolean[] hp = new boolean[3];

	private long enemyContactCounter;

	ObjectHandler handler;
	JPanel panel;

	private boolean inWall;

	Window window;

	Picture h0;
	Picture h1;
	Picture h2;

	Inventory inventory;

	String LK;

	private float walkSpeed = 0.00000025f;
	private float sprintSpeed = 0.0000004f;

	private boolean sprinting;

	public Player(ObjectHandler newHandler, Window window, JPanel newPanel, String LK) {

		super("player", newHandler);

		this.h0 = new Picture("heart");
		this.h1 = new Picture("heart");
		this.h2 = new Picture("heart");

		h0.setBounds(Camera.xPos + 20, Camera.yPos + 20, 50, 50);
		h1.setBounds(Camera.xPos + 80, Camera.yPos + 20, 50, 50);
		h2.setBounds(Camera.xPos + 140, Camera.yPos + 20, 50, 50);

		Game.panel.add(h0);
		Game.panel.add(h1);
		Game.panel.add(h2);

		hp[0] = true;
		hp[1] = true;
		hp[2] = true;

		this.velX = walkSpeed;
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

	public boolean getLeft() {
		return this.left;
	}

	public boolean getRight() {
		return this.right;
	}

	public boolean getJump() {
		return this.jump;
	}

	public void keyPressed(KeyEvent e) {

		// System.out.println(e.getKeyCode());

		if (e.getKeyCode() == 68 || e.getKeyCode() == 39) { // d/right arrow
			right = true;
		} else if (e.getKeyCode() == 65 || e.getKeyCode() == 37) { // a/left arrow
			left = true;
		} else if (e.getKeyCode() == 32) { // Space bar
			jump = true;
		} else if (e.getKeyCode() == 16) { // Shift
			this.velX = sprintSpeed;
			sprinting = true;
		} else if (e.getKeyCode() == 69) { // e
			inventory.showInv();
		} else if (e.getKeyCode() == 82 && item[0] && equipped == 0) { // r
			for (int i = 4; i > -1; i--) {
				if (!hp[i]) {
					hp[i] = true;
					break;
				}
			}
		} else if (e.getKeyCode() == 83) { // s
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
		else if(e.getKeyCode() == 49) equipped = 0;
		else if(e.getKeyCode() == 50) equipped = 1;
		else if(e.getKeyCode() == 51) equipped = 2;
		else if(e.getKeyCode() == 52) equipped = 3;
	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == 65 || e.getKeyCode() == 37) {
			left = false;
		} else if (e.getKeyCode() == 68 || e.getKeyCode() == 39) {
			right = false;
		} else if (e.getKeyCode() == 65 && e.getKeyCode() == 37 && e.getKeyCode() == 68 && e.getKeyCode() == 39) {
			walkcounter = 0;
		} else if (e.getKeyCode() == 32) {
			jump = false;
		} else if (e.getKeyCode() == 16) {
			this.velX = walkSpeed;
			sprinting = false;
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

	public void mouseClicked(MouseEvent m) {

	}

	public void mousePressed(MouseEvent m) {
		if (item[1] && equipped == 1) {
			handler.addObject(new Shot(this.getPos('x'), this.getPos('y'), handler, new Vector2(m.getX(), m.getY())));
		}
		if (item[2] && onWall() && !lookright && equipped == 2) {
			handler.addObject(new Slash(this.getPos('x'), this.getPos('y'), handler, 0, 3));
		}
		if (item[2] && onWall() && lookright && equipped == 2) {
			handler.addObject(new Slash(this.getPos('x'), this.getPos('y'), handler, 1, 3));
		}
		if (item[2] && !onWall() && equipped == 2) {
			handler.addObject(new Slash(this.getPos('x'), this.getPos('y'), handler, 2, 3));
		}
	}

	public void mouseReleased(MouseEvent m) {

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

		if (hp[0])
			h0.setVisible(true);
		else
			h0.setVisible(false);

		if (hp[1])
			h1.setVisible(true);
		else
			h1.setVisible(false);

		if (hp[2])
			h2.setVisible(true);
		else
			h2.setVisible(false);
		if (dashcounter < dashlength) {
			this.velX = dashspeed;
			dashcounter++;
			if (dashcounter == dashlength) {
				if (sprinting) {
					this.velX = sprintSpeed;
				} else {
					this.velX = walkSpeed;
				}
			}
		}
		if (cooldowncounter < dashcooldown) {
			cooldowncounter++;
		}

		if (right && !left) {
			if (walkcounter >= 0 && walkcounter < walkspeed) {
				if (this.name != "walking1") {
					this.changeName("walking1");

				}
				walkcounter++;
			} else if (walkcounter >= walkspeed && walkcounter < 2 * walkspeed) {
				if (this.name != "walking2") {
					this.changeName("walking2");
				}
				walkcounter++;
			}  else if (walkcounter >= 2 * walkspeed && walkcounter < 3 * walkspeed) {
				if (this.name != "walking3") {
					this.changeName("walking3");
				}
				walkcounter++;
			}
			if (walkcounter == 3 * walkspeed) {
				walkcounter = 0;

			}
			posX += velX * dt;
			while (wallCollision()) {
				posX -= 1;
			}
			lookright = true;
		}

		if (left && !right) {
			if (walkcounter >= 0 && walkcounter < walkspeed) {
				if (this.name != "iwalking1") {
					this.changeName("iwalking1");

				}
				walkcounter++;
			} else if (walkcounter >= walkspeed && walkcounter < 2 * walkspeed) {
				if (this.name != "iwalking2") {
					this.changeName("iwalking2");
				}
				walkcounter++;
			} else if (walkcounter >= 2 * walkspeed && walkcounter < 3 * walkspeed) {
				if (this.name != "iwalking3") {
					this.changeName("iwalking3");
				}
				walkcounter++;
			}
			if (walkcounter == 3 * walkspeed) {
				walkcounter = 0;

			}
			posX -= velX * dt;
			while (wallCollision()) {
				posX += 1;
			}
			lookright = false;

		}

		if (jump && onWall()) {
			velY = -0.0000009f;
		}

		if (!onWall() && lookright) {
			if (this.name != "jumping") {
				this.changeName("jumping");
			}
		}

		if (!onWall() && !lookright) {
			if (this.name != "jumpinginverted") {
				this.changeName("jumpinginverted");
			}
		}

		if (onWall() && lookright && !right) {
			if (this.name != "player") {
				this.changeName("player");
			}
		}

		if (onWall() && !lookright && !left) {
			if (this.name != "player_inverted") {
				this.changeName("player_inverted");
			}
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

	public void mouseEntered(MouseEvent m) {

	}

	public void mouseExited(MouseEvent m) {

	}
}
