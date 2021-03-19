
public class Wand extends GameObject {

	private static final long serialVersionUID = -8172086180946832062L;
	
	//Eine Wand ist eig nur ein Rechteck mit Graphik

	public Wand(ObjectHandler newHandler) {

		super("wand", newHandler);

	}

	public Wand(int x, int y, int w, int h, ObjectHandler newHandler) {

		super("wand", newHandler);

		this.posX = x;
		this.posY = y;
		this.width = w;
		this.height = h;
	}

}
