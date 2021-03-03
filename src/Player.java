import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player extends GameObject implements KeyListener {

	private static final long serialVersionUID = 2917881703989759480L;

	public boolean right = false;
	public boolean left = false;
	public boolean up = false;
	public boolean down = false;

	public boolean jump = false;

	public boolean inAir = false;
	public int airTime = 0;

	public double gravity;

	ObjectHandler handler;

	boolean collide;

	public Player(ObjectHandler newHandler) {

		super("player");

		this.velX = 0.00000025f;
		this.velY = 0.00000025f;

		this.width = 50;
		this.height = 80;
		this.posX = 170;
		this.posY = 100;

		handler = newHandler;

	}

	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == 68) {
			// System.out.println("right");
			right = true;
		} else if (e.getKeyCode() == 65) {
			// System.out.println("left");
			left = true;
		} else if (e.getKeyCode() == 87) {
			jump = true;
		}

	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == 65) {
			left = false;
		} else if (e.getKeyCode() == 68) {
			right = false;
		} else if (e.getKeyCode() == 87) {
			jump = false;
		}
	}

	public void keyTyped(KeyEvent e) {

	}

	public boolean wallCollision() {
		collide = false;

		for (int i = 0; i < handler.wände.size(); i++) {
			if (this.getBounds().intersects(handler.wände.get(i).getBounds())) {
				collide = true;
				return collide;
			}
		}

		return collide;
	}

	public boolean onWall() {
		boolean onWall = false;

		posY += 1;

		if (wallCollision()) {
			onWall = true;
		}

		posY -= 1;

		return onWall;
	}

	public void addGravity() {
		velY += 0.000000025f;
	}

	public void tick(long dt) {

		if (right && !left) {
			posX += velX * dt;
			while (wallCollision()) {
				posX -= 1;
			}
		}
		if (left && !right) {
			posX -= velX * dt;
			while (wallCollision()) {
				posX += 1;
			}
		}

		if (jump && onWall()) {
			velY = -0.0000006f;
		}

		posY += velY * dt;
		while (wallCollision()) {
			posY -= Math.signum(velY);
		}

		addGravity();

		if (onWall()) {
			velY = 0;
		}

	}
}
