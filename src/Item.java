

public class Item extends GameObject {

	private static final long serialVersionUID = 2917881703989759480L;

	public boolean inAir = false;
	public int airTime = 0;

	public int type;

	public double gravity;

	ObjectHandler handler;

	private boolean collide;
	private boolean onWall;
	private boolean belowWall;

	boolean picked;

	public Item(int posX, int posY, ObjectHandler newHandler, int type) {
		

		super("item_t", newHandler);
		this.type = type;
		
		this.width = 50;
		this.height = 50;
				
		if (type == 0) {
			this.changeName("potion");
			this.velX = 0.00000024f;
			this.velY = 0.00000025f;

			
			this.posX = posX;
			this.posY = posY;

			this.changeName("potion");
		} else if (type == 1) {
			this.changeName("gun");
			this.velX = 0.00000024f;
			this.velY = 0.00000025f;

			this.posX = posX;
			this.posY = posY;

			this.changeName("gun");
		} else if (type == 2) {
			this.changeName("sword");
			this.velX = 0.00000024f;
			this.velY = 0.00000025f;

			this.posX = posX;
			this.posY = posY;

			this.changeName("sword");
		}
		this.handler = newHandler;
	}

	public Item(ObjectHandler newHandler) {

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
		if (getBounds().intersects(player.getBounds())) {
			picked = true;
			player.item[type] = true;
			player.inventory.addItem(this);
			handler.removeObject(this);
		}
	}

	public void addGravity() {

		if (velY < 0.0000020f) {
			velY += 0.000000030f;
		}
	}

	public void tick(long dt) {

		
		addGravity();

		collision(handler.player.get(0));

		posY += velY * dt;
		boolean inWall = false;
		if (wallCollision()) {
			inWall = true;
		}
		while (wallCollision()) {
			posY--;

		}

		if (inWall) {
			velY = 0;
		}

		if (onWall()) {
			velY = 0;
		}
	}
}
