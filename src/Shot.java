
public class Shot extends GameObject {

	private static final long serialVersionUID = 1L;

	public float shotSpeed = 0.0000008f;

	public boolean inAir = false;
	public int airTime = 0;

	public double gravity;

	Vector2 hom;
	Vector2 tar;
	Vector2 acv;

	ObjectHandler handler;

	Camera cam;

	private boolean collide;
	private boolean onWall;
	private boolean belowWall;

	public Shot(int sx, int sy, ObjectHandler newHandler, Vector2 tar) {

		super("shot", newHandler);

		this.width = 20;
		this.height = 20;
		this.posX = sx;
		this.posY = sy;

		this.hom = new Vector2((float) (posX - Game.cam.xPos), (float) (posY - Game.cam.yPos));

		// this.tar = tar;

		this.acv = Vector2.subtract(tar, hom);

		// tar.norm();

		acv.norm();

		this.velX = (float) acv.x;
		this.velY = (float) acv.y;

		this.handler = newHandler;
	}

	public Shot(ObjectHandler newHandler) {

		super("shot", newHandler);

		/*
		 * this.velX = 0.00000024f; this.velY = 0.00000025f;
		 * 
		 * this.width = 30; this.height = 30; this.posX = 500; this.posY = 100;
		 */
		this.handler = newHandler;
	}

	/*
	 * public void collision(GameObject obj) {
	 * if(getBounds().intersects(obj.getBounds())) { handler.removeObject(this); } }
	 */

	public void addGravity() {

		if (velY < 0.0000020f) {
			velY += 0.000000030f;
		}
	}

	public void tick(long dt) {
		if (handler.player.get(0).getRight()) {
			posX += velX * dt * shotSpeed + (handler.player.get(0).velX*dt);
		} else if(handler.player.get(0).getLeft()) {
			posX += velX * dt * shotSpeed - (handler.player.get(0).velX*dt);
		}
		else {
			posX += velX * dt * shotSpeed;
		}
		if (!handler.player.get(0).onWall()) {
			posY += velY * dt * shotSpeed + (handler.player.get(0).velY*dt);
		} else {
			posY += velY * dt * shotSpeed;
		}
		
		boolean inWall = false;
		if (wallCollision()) {
			inWall = true;
		}

		if (inWall) {
			handler.removeObject(this);
		}

		if (onWall()) {
			handler.removeObject(this);
		}

		// addGravity();
		/*
		 * if (posX < -50) { posX = 1299; }
		 */
	}

}
