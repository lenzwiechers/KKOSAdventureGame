import javax.swing.JLabel;

public class GameObject extends JLabel {

	private int posX;
	private int posY;
	private int width;
	private int height;
	private int velX; // velocity
	private int vely;

	public GameObject() {

		super();

	}

	public int getCoordinate(char coordinate) {
		if (coordinate == 'x' || coordinate == 'X') {
			return posX;
		} else if (coordinate == 'y' || coordinate == 'Y') {
			return posY;
		} else {
			System.out.println("u dumb?");
			return -1;
		}
	}

	public void setCoordinate(char coordinate, int newVal) {
		if (coordinate == 'x' || coordinate == 'X') {
			posX = newVal;
		} else if (coordinate == 'y' || coordinate == 'Y') {
			posY = newVal;
		}
	}

}
