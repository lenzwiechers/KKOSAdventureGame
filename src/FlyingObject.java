
// Schüsse von Gollums und der Direktorin
public class FlyingObject extends GameObject {

	private static final long serialVersionUID = -3240563349601296826L;

	public float waveSpeed = 0.0000008f;

	private ObjectHandler handler;

	Enemy enemy;

	Vector2 hom;
	Vector2 tar;
	Vector2 acv;

	public FlyingObject(String name, ObjectHandler handler, int wx, int wy, Vector2 newTar) {
		super(name, handler);

		this.posX = wx;
		this.posY = wy;

		if (name == "gollumWave") { // ENTWEDER WAVE VOM GOLLUM
			width = 35;
			height = 35;
		} else if (name == "verwarnung") { // ODER ATTACKE DER DIREKTORIN
			width = 300;
			height = 100;
		}
		this.handler = handler;

		this.tar = newTar;
		this.hom = new Vector2((float) posX, (float) posY); // VEKTOR VOM START ZUM PLAYER
		this.acv = Vector2.subtract(tar, hom); // VEKTOR VON START ZU ZIEL - HEIMVEKTOR = VEKTOR VOM PLAYER ZUM ZIEL
		acv.norm();

		this.velX = (float) acv.x;
		this.velY = (float) acv.y;
	}

	public void tick(long dt) {

		posX += velX * dt * waveSpeed; //// X UND Y GESCHWINDIGKEITEN WERDEN AUF RICHTIGE WERTE "GESTRECKT"
		posY += velY * dt * waveSpeed;
		
		if (wallCollision()) {
			handler.removeObject(this); // FALLS DAS OBJEKT GEGEN DIE WAND KNALLT WIRD ES ENTFERNT
		}

	}

}
