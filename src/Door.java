import java.awt.Rectangle;

public class Door extends GameObject {

	private static final long serialVersionUID = 8628846381003012337L;

	public Door exitDoor;
	public int exitX, exitY;
	public int tpPosX, tpPosY;

	public Door(ObjectHandler handler) {
		super("Door", handler);
	}

	public Door(int x, int y, int width, int height, ObjectHandler handler) {
		super("Door", handler);

		this.posX = x;
		this.posY = y;
		this.width = width;
		this.height = height;

		setTpPos();
	}

	public Rectangle getTpBounds() {
		return new Rectangle(posX + 10, posY + 10, width - 20, height - 10);
	}

	public void setTpPos() {
		this.tpPosX = posX + width / 2 - handler.player.get(0).getSize('x') / 2;
		this.tpPosY = posY + height - (handler.player.get(0).getSize('y'));
	}

	public void connectExit(Door exit) {
		this.exitDoor = exit;

		this.exitX = exit.tpPosX;
		this.exitY = exit.tpPosY;
	}

	public void setExit(Door exit) {
		this.exitDoor = exit;
	}

	public void setExitX() {
		if (exitDoor != null) {
			this.exitX = exitDoor.tpPosX;
		}
	}

	public void setExitY() {
		if (exitDoor != null) {
			this.exitY = exitDoor.tpPosY;
		}
	}

	public void setTpPosX() {
		this.tpPosX = posX + width / 2;
	}

	public void setTpPosY() {
		this.tpPosY = posY + width / 2;
	}

	public int getExitX() {
		return exitX;
	}

	public int getExitY() {
		return exitY;
	}
}
