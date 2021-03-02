import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player extends GameObject implements KeyListener {

	private static final long serialVersionUID = 2917881703989759480L;

	public boolean right = false;
	public boolean left = false;
	public boolean up = false;
	public boolean down = false;

	ObjectHandler handler;

	public Player(ObjectHandler newHandler) {

		super("player");

		this.velX = 0.00000025f;
		this.velY = 0.00000025f;

		this.width = 50;
		this.height = 80;
		this.posX = 150;
		this.posY = 100;

		handler = newHandler;
	}

	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == 39) {
			// System.out.println("right");
			right = true;
			left = false;
		} else if (e.getKeyCode() == 37) {
			// System.out.println("left");
			left = true;
			right = false;
		}

	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == 37) {
			left = false;
		} else if (e.getKeyCode() == 39) {
			right = false;
		}
	}

	public void keyTyped(KeyEvent e) {

	}

	public boolean collision() {
		boolean collide = false;

		for (int i = 0; i < handler.wände.size(); i++) {
			if (this.getBounds().intersects(handler.wände.get(i).getBounds())) {
				collide = true;
				return collide;
			}
		}

		return collide;
	}

	public void tick() {
		if (right) {
			posX += velX;
			while (collision()) {
				posX -= Math.signum(velX);
			}
		} else if (left) {
			posX -= velX;
			while (collision()) {
				posX += Math.signum(velX);
			}
		}
		
		posY += velY;
		while(collision()) {
			posY -= Math.signum(velY);
		}
	}
}
