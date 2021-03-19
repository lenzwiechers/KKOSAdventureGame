
// Item-Klasse
public class Item extends GameObject {

	private static final long serialVersionUID = 2917881703989759480L;

	public boolean inAir = false;
	public int airTime = 0;

	public int type;

	public double gravity;

	ObjectHandler handler;

	boolean picked;

	public Item(int posX, int posY, ObjectHandler newHandler, int type) { // Konstruktor1

		super("item_t", newHandler);
		this.type = type;

		this.width = 50;
		this.height = 50;

		// verschiedene Item-typen:
		if (type == 0) {
			this.changeName("potion");
		} else if (type == 1) {
			this.changeName("gun"); // NAME DES ITEMS WIRD FESTGELLEGT
		} else if (type == 2) {
			this.changeName("sword");
		} else if (type == 3) {
			this.changeName("dash");
		}
		this.velX = 0.00000024f;
		this.velY = 0.00000025f;

		this.posX = posX;
		this.posY = posY;
		this.handler = newHandler;
	}

	public Item(ObjectHandler newHandler) { // Konstruktor2

		super("item_t", newHandler);

		this.velX = 0.00000024f;
		this.velY = 0.00000025f;

		this.width = 30;
		this.height = 30;
		this.posX = 500;
		this.posY = 100;

		this.handler = newHandler;
	}

	public void addGravity() {

		if (velY < 0.0000020f) {
			velY += 0.000000030f;
		} // GRAVITATION FÜRS ITEM
	}

	public void collision(Player player) {
		if (getBounds().intersects(player.getBounds())) {
			if (type == 3) {
				player.setDash(true);
				handler.removeObject(this);
			} else {
				picked = true; // KOLLISION MIT SPIELER
				player.item[type] = true;
				handler.removeObject(this);
			}
		}
	}

	// Methode die ständig im Spiel aufgerufen wird, um Veränderungen herbeizuführen
	public void tick(long dt) {

		addGravity(); // GRAVITATION

		collision(handler.player.get(0)); // SPIELER NIMMT DAS ITEM AUF

		// generelle Veraenderungen
		posY += velY * dt;
		boolean inWall = false;
		if (wallCollision()) {
			inWall = true;
		}
		while (wallCollision()) {
			posY--;

		}

		if (inWall) {
			velY = 0;
		}

		if (onWall()) {
			velY = 0;
		}
	}
}
