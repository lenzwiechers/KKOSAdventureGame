import java.awt.Rectangle;

import javax.swing.JPanel;

public class Item extends GameObject{

	private static final long serialVersionUID = 2917881703989759480L;

	public boolean inAir = false;
	public int airTime = 0;

	public double gravity;

	ObjectHandler handler;

	private boolean collide;
	private boolean onWall;
	private boolean belowWall;

	boolean picked = false;
	
	
	public Item (int posX, int posY, ObjectHandler newHandler) {

		super("item_t", newHandler);

		this.velX = 0.00000024f;
		this.velY = 0.00000025f;

		this.width = 30;
		this.height = 30;
		this.posX = posX;
		this.posY = posY;

		this.handler = newHandler;
	}
	
	public Item (ObjectHandler newHandler) {

		super("item_t", newHandler);

		this.velX = 0.00000024f;
		this.velY = 0.00000025f;

		this.width = 30;
		this.height = 30;
		this.posX = 500;
		this.posY = 100;

		this.handler = newHandler;
	}

	public void collision(Player player) {
		if(getBounds().intersects(player.getBounds())) {
			picked = true;
			player.itemT = true;
		}
	}
	
	public void addGravity() {

		if (velY < 0.0000020f) {
			velY += 0.000000030f;
		}
	}

	public void tick(long dt) {

		this.name = "item_t";

		addGravity();

		collision(handler.player.get(0));
		if(picked) {
			handler.removeObject(this);
		}
		
		posY += velY * dt;
		boolean inWall = false;
		if (wallCollision()) {
			inWall = true;
		}
		while (wallCollision()) {
			posY --;

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
