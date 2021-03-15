
public class FlyingObject extends GameObject {

	private static final long serialVersionUID = -3240563349601296826L;

	public float waveSpeed = 0.0000008f;

	private ObjectHandler handler;

	Enemy enemy;

	Vector2 hom;
	Vector2 tar;
	Vector2 acv;

	public FlyingObject(ObjectHandler handler, int wx, int wy, Vector2 newTar, Enemy newEnemy) {
		super("gollumWave", handler);

		this.posX = wx;
		this.posY = wy;
		
		this.width = 35;
		this.height = 35;
		
		this.enemy = newEnemy;
		this.handler = handler;
		
		this.tar = newTar;
		this.hom = new Vector2((float)posX, (float)posY);
		this.acv = Vector2.subtract(tar, hom);
		acv.norm();
		
		
		this.velX = (float) acv.x;
		this.velY = (float) acv.y;
	}

	public void tick(long dt) {

		// if(right) {
		posX += velX * dt * waveSpeed;
		posY += velY * dt * waveSpeed;
		// } else {
		// posX += velX * dt * waveSpeed;
		// posY += velY * dt * waveSpeed;
		// }

		/*
		 * if (right) { posX += velX * dt * waveSpeed + (handler.player.get(0).velX*dt);
		 * } else if(!handler.player.get(0).getLeft()) { posX += velX * dt * waveSpeed -
		 * (handler.player.get(0).velX*dt); } else { posX += velX * dt * waveSpeed; } if
		 * (!handler.player.get(0).onWall()) { posY += velY * dt * waveSpeed +
		 * (handler.player.get(0).velY*dt); } else { posY += velY * dt * waveSpeed; }
		 */

		if (wallCollision()) {
			handler.removeObject(this);
		}

	}

}
