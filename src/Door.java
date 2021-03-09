import java.awt.Rectangle;

public class Door extends GameObject {
	public int x, y;
	public int width, height;
	public Door exitDoor;
	public int exitX, exitY;
	public int tpPosX, tpPosY;

	public Door(ObjectHandler handler) {
		super("Door", handler);
	}

	public Door(int x, int y, int width, int height, ObjectHandler handler, Door exit) {
		super("Door", handler);

		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

		this.exitDoor = exit;

		this.tpPosX = x + width / 2;
		this.tpPosY = y + height / 2;
	}

	public Rectangle getTpBounds() {
		return new Rectangle(x + handler.player.get(0).getSize('x'), y + handler.player.get(0).getSize('y'),
				width - 2 * handler.player.get(0).getSize('x'), height - handler.player.get(0).getSize('y'));
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.x = y;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
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
		this.tpPosX = x + width / 2;
	}

	public void setTpPosY() {
		this.tpPosY = y + width / 2;
	}

	public int getExitX() {
		return exitX;
	}

	public int getExitY() {
		return exitY;
	}
}
