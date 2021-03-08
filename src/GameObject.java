import java.awt.Rectangle;

public abstract class GameObject extends Picture {

	private static final long serialVersionUID = 5520024489816059648L;

	protected int posX;
	protected int posY;
	protected int width;
	protected int height;
	protected float velX; // velocity
	protected float velY;
	
	private boolean collide;
	private boolean onWall;
	private boolean belowWall;
	
	ObjectHandler handler;

	public GameObject(String picName, ObjectHandler handler) {

		super(picName);
		
		this.handler = handler;

	}

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

	public Rectangle getBounds() {
		return new Rectangle(posX, posY, width, height);
	}

	public void setPos(char coordinate, int newVal) {
		if (coordinate == 'x' || coordinate == 'X') {
			posX = newVal;
		} else if (coordinate == 'y' || coordinate == 'Y') {
			posY = newVal;
		}
	}

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
	
	public boolean wallCollision() {
		collide = false;

		for (int i = 0; i < handler.waende.size(); i++) {
			if (this.getBounds().intersects(handler.waende.get(i).getBounds()) && !(this instanceof Wand)) {
				collide = true;
				return collide;
			}
		}

		return collide;
	}
	
	public boolean onWall() {
		onWall = false;

		posY += 1;

		if (wallCollision()) {
			onWall = true;
		}

		posY -= 1;

		return onWall;
	}

	public boolean belowWall() {
		belowWall = false;

		posY -= 1;

		if (wallCollision()) {
			belowWall = true;
		}

		posY += 1;

		return belowWall;
	}

	public void addGravity() {

		if (velY < 0.0000020f && !(this instanceof Wand)) {
			velY += 0.000000030f;
		}
	}

}
