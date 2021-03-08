
public class Enemy extends GameObject {

	private static final long serialVersionUID = 4573246985108449527L;
	
	private boolean inWall;

	public Enemy(String picName, ObjectHandler handler) {
		
		super(picName, handler);
		
		this.velX = 0.00000025f;
		this.velY = 0.00000025f;
		
	}
	
	public void tick(long dt) {
		addGravity();
		
		posY += velY * dt;
		inWall = false;
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
	}
	
}
