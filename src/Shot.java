
public class Shot extends GameObject {
	
	
	private static final long serialVersionUID = 1L;
	
	public int shotSpeed = 10^9;
	
	public boolean inAir = false;
	public int airTime = 0;
	
	public double gravity;
	
	Vector2 hom;
	Vector2 acv;

	ObjectHandler handler;
	
	private boolean collide;
	private boolean onWall;
	private boolean belowWall;
	
	public Shot (int posX, int posY, ObjectHandler newHandler, Vector2 tar) {

		super("shot", newHandler);
		
		this.hom = new Vector2((float)posX, (float)posY);
		
		this.acv = Vector2.subtract(tar, hom);
		
		acv.norm();
		
		this.velX = (float)(acv.x)/1000000000;
		this.velY = (float)(acv.y)/1000000000;

		this.width = 20;
		this.height = 20;
		this.posX = posX;
		this.posY = posY;

		this.handler = newHandler;
	}
	
	public Shot (ObjectHandler newHandler) {

		super("shot", newHandler);

		/*this.velX = 0.00000024f;
		this.velY = 0.00000025f;

		this.width = 30;
		this.height = 30;
		this.posX = 500;
		this.posY = 100;
		*/
		this.handler = newHandler;
	}
	
	/*public void collision(GameObject obj) {
		if(getBounds().intersects(obj.getBounds())) {
			handler.removeObject(this);
		}
	}*/
	
	
	
	public void addGravity() {

		if (velY < 0.0000020f) {
			velY += 0.000000030f;
		}
	}
	
	public void tick(long dt) {
		
		//System.out.println(acv.x);
		//System.out.println(acv.y);
		this.name = "shot";

		//addGravity();
		
		posX += velX * dt;
		posY += velY * dt;
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
		/*if (posX < -50) {
			posX = 1299;
		}*/
	}

}
