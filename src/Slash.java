
public class Slash extends GameObject {

	private static final long serialVersionUID = 1L;

	public float slashSpeed = 0.00000025f;

	public boolean inAir = false;
	public int airTime = 0;

	public int timer;

	public double gravity;

	Vector2 hom;
	Vector2 tar;
	Vector2 acv;

	ObjectHandler handler;

	Camera cam;

	private boolean collide;
	private boolean onWall;
	private boolean belowWall;

	private int dir;

	public Slash(int sx, int sy, ObjectHandler newHandler, int dir, int tim) {

		super("slash", newHandler);

		this.width = 60;
		this.height = 60;
		this.posX = sx;
		this.posY = sy;

		this.timer = tim;																	//TIMER ZEIGT WIE LANGE DER SLASH DA IST
		this.dir = dir;

		// this.hom = new Vector2((float) (posX - Game.cam.xPos), (float) (posY -
		// Game.cam.yPos));

		// this.tar = tar;

		// this.acv = Vector2.subtract(tar, hom);

		// tar.norm();

		// acv.norm();
		if (dir == 0) {															//SLASH RICHTUNG WIRD FESTGELLEGT
			this.velX = -1;
			this.velY = 0;
		} else if (dir == 1) {
			this.velX = 1;
			this.velY = 0;
		} else if (dir == 2) {
			this.velX = 0;
			this.velY = -1;
		}

		this.handler = newHandler;
	}

	public Slash(ObjectHandler newHandler) {

		super("slash", newHandler);

		/*
		 * this.velX = 0.00000024f; this.velY = 0.00000025f;
		 * 
		 * this.width = 30; this.height = 30; this.posX = 500; this.posY = 100;
		 */
		this.handler = newHandler;
	}

//	public void collision(GameObject obj) {
//		if (getBounds().intersects(obj.getBounds())) {
//			handler.removeObject(this);
//		}
//	}

	public void addGravity() {

		if (velY < 0.0000020f) {
			velY += 0.000000030f;
		}
	}

	public void tick(long dt) {

		this.name = "slash";

		if (timer < 0) {
			handler.removeObject(this);													//NACH AUSLAUFEN DER ZEIT: SLASH "LAEUFT AUS
		}

		timer--;

		/*
		 * if (handler.player.get(0).getJump()) { System.out.println("j"); velX = 0.0f;
		 * velY = -1.0f; } if (handler.player.get(0).getLeft()) {
		 * System.out.println("l"); velX = -1.0f; velY = 0.0f; width *= -1; } if
		 * (handler.player.get(0).getRight() && !handler.player.get(0).getLeft()) {
		 * System.out.println("r"); velX = 1.0f; velY = 0.0f; posX += 50; }
		 */
		// System.out.println(velX);
		// System.out.println(velY);

		if (handler.player.get(0).getRight()) {
			posX += velX * dt * slashSpeed + (handler.player.get(0).velX*dt);
		} else if(handler.player.get(0).getLeft()) {
			posX += velX * dt * slashSpeed - (handler.player.get(0).velX*dt);			//JUSTIERUNG UM PLAYER GESCHWINDIGKEIT
		}
		else {
			posX += velX * dt * slashSpeed;
		}
		if (!handler.player.get(0).onWall()) {
			posY += velY * dt * slashSpeed + (handler.player.get(0).velY*dt);
		} else {
			posY += velY * dt * slashSpeed;
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
