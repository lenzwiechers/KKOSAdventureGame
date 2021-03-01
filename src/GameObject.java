public abstract class GameObject extends Picture {

	
	private static final long serialVersionUID = 5520024489816059648L;
	
	protected int posX;
	protected int posY;
	protected int width;
	protected int height;
	protected float velX; // velocity
	protected float velY;

	public GameObject(String picName) {

		super(picName);

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
	
	public void tick() {
		
	}

}
