import java.awt.Rectangle;

import javax.swing.JPanel;

public class Item extends GameObject{

	private static final long serialVersionUID = 2917881703989759480L;

	public boolean inAir = false;
	public int airTime = 0;

	public double gravity;
	
	Player player;

	ObjectHandler handler;
	
	JPanel panel;

	private boolean collide;
	private boolean onWall;
	private boolean belowWall;

	boolean picked = false;
	
	
	
	public Item (ObjectHandler newHandler, Player newPlayer, JPanel newPanel) {

		super("item_t");

		this.velX = 0.00000024f;
		this.velY = 0.00000025f;

		this.width = 30;
		this.height = 30;
		this.posX = 500;
		this.posY = 100;

		this.player = newPlayer;
		this.handler = newHandler;
		this.panel = newPanel;

	}

	public Rectangle getBounds() {
		return new Rectangle(posX, posY, 30, 30);
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

	public void collision(Player player) {
		if(getBounds().intersects(player.getBounds())) {
			picked = true;
			player.itemT = true;
			//System.out.println("ya");
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

		collision(player);
		if(picked) {
			System.out.println("ye");
			handler.removeObject(this);
			panel.remove(this);
		}
		
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
