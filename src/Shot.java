
// Schuesse von der Gun vom Spieler
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

	public Shot(int sx, int sy, ObjectHandler newHandler, Vector2 tar) { // Konstruktor

		super("shot", newHandler);

		this.width = 20;
		this.height = 20;
		this.posX = sx;
		this.posY = sy;

		this.hom = new Vector2((float) (posX - Camera.xPos), (float) (posY - Camera.yPos)); // VEKTOR VOM START ZUM
																								// PLAYER


		this.acv = Vector2.subtract(tar, hom); // VEKTOR VON START ZU ZIEL - HEIMVEKTOR = VEKTOR VOM PLAYER ZUM ZIEL

		acv.norm(); // ENDGUELTIGER VEKTOR WIRD NORMIERT DAMIT SHOTS NICHT SCHNELLER SIND FALLS MANN
					// WEITER WEG CLICKT //ENDGUELTIGER VEKTOR WIRD NORMIERT DAMIT SHOTS NICHT
					// SCHNELLER SIND FALLS MANN WEITER WEG CLICKT

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

	// Methode die ständig im Spiel aufgerufen wird, um Veränderungen herbeizuführen
	public void tick(long dt) {

		// generelle Veraenderungen:
		posX += velX * dt * shotSpeed; // X UND Y GESCHWINDIGKEITEN WERDEN AUF RICHTIGE WERTE "GESTRECKT"
		posY += velY * dt * shotSpeed;

		if (wallCollision()) {
			handler.removeObject(this); // FALLS DER SHOT GEGEN DIE WAND KNALLT WIRD ER ENTFERNT
		}
	}

}
