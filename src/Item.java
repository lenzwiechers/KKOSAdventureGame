public class Item extends GameObject {

	private static final long serialVersionUID = 2917881703989759480L;

	public boolean inAir = false;
	public int airTime = 0;

	public double gravity;

	ObjectHandler handler;

	private boolean collide;
	private boolean onWall;
	private boolean belowWall;

	public Item(ObjectHandler newHandler) {

		super("item_t");

		this.velX = 0.00000024f;
		this.velY = 0.00000025f;

		this.width = 30;
		this.height = 30;
		this.posX = 500;
		this.posY = 100;

		handler = newHandler;

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

		this.name = "item_t";

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
