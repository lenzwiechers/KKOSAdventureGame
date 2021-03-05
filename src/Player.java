import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player extends GameObject implements KeyListener {

	private static final long serialVersionUID = 2917881703989759480L;

	public boolean right = false;
	public boolean left = false;
	public boolean up = false;
	public boolean down = false;

	public boolean jump = false;

	public int dashlength = 10;
	public int dashcounter = dashlength;
	public float dashspeed = 0.00000200f;
	public int dashcooldown = 180;
	public int cooldowncounter = dashcooldown;

	public boolean inAir = false;
	public int airTime = 0;

	public double gravity;

	ObjectHandler handler;

	private boolean collide;
	private boolean onWall;
	private boolean belowWall;

	Window window;

	Inventory inventory;

	public Player(ObjectHandler newHandler, Window window) {

		super("player");

		this.velX = 0.00000024f;
		this.velY = 0.00000025f;

		this.width = 50;
		this.height = 80;
		this.posX = 170;
		this.posY = 100;

		handler = newHandler;

		this.window = window;

		inventory = new Inventory(window);

		Item[][] items = new Item[5][5];

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				items[i][j] = new Item(handler);
				inventory.addItem(items[i][j]);
			}
		}
	}

	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == 68 || e.getKeyCode() == 39) { // d/right arrow
			right = true;
		} else if (e.getKeyCode() == 65 || e.getKeyCode() == 37) { // a/left arrow
			left = true;
		} else if (e.getKeyCode() == 32) { // Space bar
			jump = true;
			this.velX = 0.00000045f;
		} else if (e.getKeyCode() == 17) { // Ctrl
			this.velX = 0.00000027f;
		} else if (e.getKeyCode() == 69) { // e
			inventory.showInv();
		} else if (e.getKeyCode() == 81) { // q
			if (dashcounter == dashlength && cooldowncounter == dashcooldown) {
				dashcounter = 0;
				cooldowncounter = 0;
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
			this.velX = 0.00000016f;
		} else if (e.getKeyCode() == 17) {
			this.velX = 0.00000016f;
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

		if (velY < 0.0000020f) {
			velY += 0.000000030f;
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
		if (posX < -50) {
			posX = 1299;
		}
		posY = posY % 700;
		posX = posX % 1300;
	}
}
