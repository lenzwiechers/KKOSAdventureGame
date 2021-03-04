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

	private boolean collide;
	private boolean onWall;
	private boolean belowWall;

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

		if (e.getKeyCode() == 68 || e.getKeyCode() == 39) {
			// System.out.println("right");
			right = true;
		} else if (e.getKeyCode() == 65 || e.getKeyCode() == 37) {
			// System.out.println("left");
			left = true;
		} else if (e.getKeyCode() == 32) { // Space bar
			jump = true;
		}
	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == 65 || e.getKeyCode() == 37) {
			left = false;
		} else if (e.getKeyCode() == 68 || e.getKeyCode() == 39) {
			right = false;
		} else if (e.getKeyCode() == 32) {
			jump = false;
		}
	}

	public void keyTyped(KeyEvent e) {

	}

	public boolean wallCollision() {
		collide = false;

		for (int i = 0; i < handler.w�nde.size(); i++) {
			if (this.getBounds().intersects(handler.w�nde.get(i).getBounds())) {
				collide = true;
				return collide;
			}
		}

		return collide;
	}

	public boolean onWall() {
		onWall = false;

		posY += 1;

		if (wallCollision()) {
			onWall = true;
		}

		posY -= 1;

		return onWall;
	}

	public boolean belowWall() {
		belowWall = false;

		posY -= 1;

		if (wallCollision()) {
			belowWall = true;
		}

		posY += 1;

		return belowWall;
	}

	public void addGravity() {
		velY += 0.000000030f;
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
			velY = -0.0000008f;
		}

		addGravity();

		posY += velY * dt;
		boolean inWall = false;
		if (wallCollision()) {
			inWall = true;
		}
		while (wallCollision()) {
			posY -= Math.signum(velY);

		}

		if (inWall) {
			velY = 0;
		}

		if (onWall()) {
			velY = 0;
		}

		if (posX > 1300) {
			posX = -50;
		}
		if (posY > 700) {
			posY = -80;
		}
		if (posX < -50) {
			posX = 1300;
		}
		if (posY < -80) {
			posY = 700;
		}
	}
}
