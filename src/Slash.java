
public class Slash extends GameObject {
	
	
	private static final long serialVersionUID = 1L;
	
	public float slashSpeed = 0.0000004f;
	
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
	
	public Slash (int sx, int sy, ObjectHandler newHandler, Vector2 tar, int tim) {

		super("slash", newHandler);
		
		this.width = 40;
		this.height = 40;
		this.posX = sx;
		this.posY = sy;
		
		this.timer = tim;
		
		this.hom = new Vector2((float)(posX-Game.cam.xPos), (float)(posY-Game.cam.yPos));
		
		//this.tar = tar;
		
		this.acv = Vector2.subtract(tar, hom);
		
		//tar.norm();
		
		
		acv.norm();
		
		

		

		this.velX = (float)acv.x;
		this.velY = (float)acv.y;
		
		this.handler = newHandler;
	}
	
	public Slash(ObjectHandler newHandler) {

		super("slash", newHandler);

		/*this.velX = 0.00000024f;
		this.velY = 0.00000025f;

		this.width = 30;
		this.height = 30;
		this.posX = 500;
		this.posY = 100;
		*/
		this.handler = newHandler;
	}
	
	public void collision(GameObject obj) {
		if(getBounds().intersects(obj.getBounds())) {
			handler.removeObject(this);
		}
	}
	
	
	
	public void addGravity() {

		if (velY < 0.0000020f) {
			velY += 0.000000030f;
		}
	}
	
	public void tick(long dt) {
		
		this.name = "slash";
		
		if(timer<0) {
			handler.removeObject(this);
		}
		
		timer--;
		
		
		posX += velX * dt * slashSpeed;
		posY += velY * dt * slashSpeed;
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
		
		//addGravity();
		/*if (posX < -50) {
			posX = 1299;
		}*/
	}

}

