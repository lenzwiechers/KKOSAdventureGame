import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;

// abstrakte Klasse für alle Spielobjekte
public abstract class GameObject extends Picture {

	private static final long serialVersionUID = 5520024489816059648L;

	protected int posX;
	protected int posY;
	protected int width;
	protected int height;
	protected float velX; // velocity
	protected float velY;

	private boolean collide;
	protected boolean sCollide;
	protected boolean slCollide;
	private boolean onWall;
	private boolean belowWall;

	ObjectHandler handler;

	public GameObject(String picName, ObjectHandler handler) { // Konstruktor1

		super(picName);

		this.handler = handler;

	}

	public GameObject(int posX, int posY, int width, int height, String text, Color color) { // Konstruktor2
		super("");

		this.posX = posX;
		this.posY = posY;
		this.width = width;
		this.height = height;

		setText(text);

		setForeground(color);

		setFont(new Font("Serif", Font.PLAIN, 40));
	}

	// Methode um die Koordinaten herauszufinden:
	public int getPos(char coordinate) {
		if (coordinate == 'x' || coordinate == 'X') {
			return posX;
		} else if (coordinate == 'y' || coordinate == 'Y') {
			return posY;
		} else {
			System.out.println("ERROR, WRONG INPUT");
			return -1;
		}
	}

	// Für Kollision mit anderen Objekten:
	public Rectangle getBounds() {
		return new Rectangle(posX, posY, width, height);
	}

	// Methode zum Setzen der Position:
	public void setPos(char coordinate, int newVal) {
		if (coordinate == 'x' || coordinate == 'X') {
			posX = newVal;
		} else if (coordinate == 'y' || coordinate == 'Y') {
			posY = newVal;
		}
	}

	// Methode um die Groeße herauszufinden
	public int getSize(char coordinate) {
		if (coordinate == 'x' || coordinate == 'X') {
			return width;
		} else if (coordinate == 'y' || coordinate == 'Y') {
			return height;
		} else {
			System.out.println("ERROR, WRONG INPUT");
			return -1;
		}
	}

	// Methode um die Groeße zu veraendern:
	public void setSize(char coordinate, int newVal) {
		if (coordinate == 'x' || coordinate == 'X') {
			width = newVal;
		} else if (coordinate == 'y' || coordinate == 'Y') {
			height = newVal;
		}
	}

	public void render() {
		this.setBounds(posX, posY, width, height);
	}

	public void tick(long dt) {

	}

	// Verschiedene Methoden für Kollision mit anderen Objekten:
	public boolean collision(GameObject obj) {
		if (getBounds().intersects(obj.getBounds())) {
			return true;
		}
		return false;
	}

	public int enemyCollision() {
		for (int i = 0; i < handler.enemies.size(); i++) {
			if (collision(handler.enemies.get(i))) {
				if (handler.enemies.get(i).type == 0)
					return 0;
				if (handler.enemies.get(i).type == 1)
					return 1;
				if (handler.enemies.get(i).type == 2)
					return 2;
			}
		}
		return -1;
	}

	public int hardEnemyCollision() { // wenn ein Gegner gerade im attack modus ist

		for (int i = 0; i < handler.enemies.size(); i++) {
			if (collision(handler.enemies.get(i)) && handler.enemies.get(i).attacking) {
				if (handler.enemies.get(i).type == 0)
					return 0;
				if (handler.enemies.get(i).type == 1)
					return 1;
				if (handler.enemies.get(i).type == 2)
					return 2;
			}
		}
		return -1;
	}

	public int waveCollision() {
		for (int i = 0; i < handler.stuff.size(); i++) {
			if (collision(handler.stuff.get(i))) {
				handler.removeObject(handler.stuff.get(i));
				if (handler.enemies.get(i).type == 0)
					return 0;
				if (handler.enemies.get(i).type == 1)
					return 1;
				if (handler.enemies.get(i).type == 2)
					return 2;
			}
		}
		return -1;
	}

	public boolean wallCollision() {
		collide = false;

		for (int i = 0; i < handler.waende.size(); i++) {
			if (collision(handler.waende.get(i)) && !(this instanceof Wand)) {
				collide = true;
				return collide;
			}
		}

		return collide;
	}

	public boolean shotCollision() {
		sCollide = false;

		for (int i = 0; i < handler.shot.size(); i++) {
			if (collision(handler.shot.get(i)) && !(this instanceof Shot)) {

				handler.removeObject(handler.shot.get(i));
				sCollide = true;

				return sCollide;
			}
		}

		return sCollide;
	}

	public boolean slashCollision() {
		slCollide = false;

		for (int i = 0; i < handler.slashes.size(); i++) {
			if (collision(handler.slashes.get(i)) && !(this instanceof Slash)) {

				handler.removeObject(handler.slashes.get(i));
				slCollide = true;

				return slCollide;
			}
		}

		return slCollide;
	}

	// Um herauszufinden, ob sich das Objekt gerade auf einer Wand befindet:
	public boolean onWall() {
		onWall = false;

		posY += 1;

		if (wallCollision()) {
			onWall = true;
		}

		posY -= 1;

		return onWall;
	}

	// Um herauszufinden ob sich das Objekt gerade unter einer Wand befindet:
	public boolean belowWall() {
		belowWall = false;

		posY -= 1;

		if (wallCollision()) {
			belowWall = true;
		}

		posY += 1;

		return belowWall;
	}

	// Methode die Gravitation erhöht:
	public void addGravity() {

		if (velY < 0.0000020f && !(this instanceof Wand)) {
			velY += 0.000000030f;
		}
	}

}
