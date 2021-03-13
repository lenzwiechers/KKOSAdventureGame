import java.awt.geom.Line2D;

public class Enemy extends GameObject {

	private static final long serialVersionUID = 4573246985108449527L;

	private boolean inWall;

	private boolean playerContact;

	public static int x1, x2, y1, y2;

	Line2D l;

	public Enemy(String picName, ObjectHandler handler) {

		super(picName, handler);

		this.velX = 0.00000025f;
		this.velY = 0.00000025f;

		this.posX = 350;
		this.posY = 300;
		this.width = 100;
		this.height = 100;

		if (picName == "gollum") {
			this.velX = 0.0000004f;
		} else if (picName == "chonker") {
			this.velX = 0.0000001f;
		}

		l = new Line2D.Float();

	}

	public void tick(long dt) {

		addGravity();

		posY += velY * dt;
		inWall = false;
		if (wallCollision()) {
			inWall = true;
		}
		while (wallCollision()) {
			this.posY--;
		}
		if (inWall) {
			this.velY = 0;
		}

		if (onWall()) {
			this.velY = 0;
		}

		// System.out.println(y2);

		l.setLine(posX, posY, handler.player.get(0).getPos('x'), handler.player.get(0).getPos('y'));

		// System.out.println(l.intersects(handler.player.get(0).getBounds()));
	}

	public boolean checkContact() {
		for (int i = 0; i < handler.waende.size(); i++) {
			if (l.intersects(handler.waende.get(i).getBounds())) {
				playerContact = true;
				System.out.println("yessir");
				return true;
			}
		}
		playerContact = false;
		return false;
	}

}
