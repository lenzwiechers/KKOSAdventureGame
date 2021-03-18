
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

		this.hom = new Vector2((float) (posX - Game.cam.xPos), (float) (posY - Game.cam.yPos));							//VEKTOR VOM START ZUM PLAYER

		// this.tar = tar;

		this.acv = Vector2.subtract(tar, hom);																			//VEKTOR VON START ZU ZIEL - HEIMVEKTOR = VEKTOR VOM PLAYER ZUM ZIEL

		// tar.norm();

		acv.norm();																										//ENDGUELTIGER VEKTOR WIRD NORMIERT DAMIT SHOTS NICHT SCHNELLER SIND FALLS MANN WEITER WEG CLICKT																					//ENDGUELTIGER VEKTOR WIRD NORMIERT DAMIT SHOTS NICHT SCHNELLER SIND FALLS MANN WEITER WEG CLICKT
		
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

	public void tick(long dt) {
		/*if (handler.player.get(0).getRight()) {
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
		}*/
		
		posX += velX * dt * shotSpeed;																		// X UND Y GESCHWINDIGKEITEN WERDEN AUF RICHTIGE WERTE "GESTRECKT"
		posY += velY * dt * shotSpeed;
		
		if(wallCollision()) {
			handler.removeObject(this);																		// FALLS DER SHOT GEGEN DIE WAND KNALLT WIRD ER ENTFERNT
		}
	}

}
