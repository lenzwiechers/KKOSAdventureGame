import java.awt.geom.Line2D;

public class Enemy extends GameObject {

	private static final long serialVersionUID = 4573246985108449527L;

	private boolean inWall;

	private boolean playerContact;

	public static int x1, x2, y1, y2;

	Line2D[] l = new Line2D[3];

	public boolean isInScreen = false;

	Line2D line;

	int hp = 100;

	private boolean right = true;
	private boolean left;
	private boolean lookright = true;
	private int walkcounter = 0;
	private int walkspeed = 5;
	private int typ = 0;

	public Enemy(String picName, ObjectHandler handler) {

		super(picName, handler);

		this.velX = 0.00000025f;
		this.velY = 0.00000025f;

		this.posX = 350;
		this.posY = 300;
		this.width = 100;
		this.height = 100;

		this.velX = 0.0000001f;

		if (picName == "gollum") {
			this.velX = 0.0000004f;
			typ = 0;
		} else if (picName == "chonker") {
			this.velX = 0.0000001f;
			typ = 1;
		} else if (picName == "direktorin") {
			this.velX = 0.0000002f;
			this.velY = 0.0f;
			typ = 3;
		}

		for (int i = 0; i < l.length; i++) {
			l[i] = new Line2D.Float();
		}

	}

	public Enemy(int posX, int posY, ObjectHandler handler) {
		super("item_t", handler);

		this.velX = 0.00000025f;
		this.velY = 0.00000025f;

		this.posX = posX;
		this.posY = posY;
		this.width = 99;
		this.height = 99;

		/*
		 * if (picName == "gollum") { this.velX = 0.0000004f; } else if (picName ==
		 * "chonker") { this.velX = 0.0000001f; }
		 */

		for (int i = 0; i < l.length; i++) {
			l[i] = new Line2D.Float();
		}

		line = new Line2D.Float();
	}

	public void tick(long dt) {

		sCollide = false;
		slCollide = false;

		if (hp < 0) {
			handler.removeObject(this);
		}

		l[0].setLine(posX + (width / 2), posY,
				handler.player.get(0).getPos('x') + (handler.player.get(0).getSize('x') / 2),
				handler.player.get(0).getPos('y') + 1);
		l[1].setLine(posX + (width / 2), posY,
				handler.player.get(0).getPos('x') + (handler.player.get(0).getSize('x') / 2),
				handler.player.get(0).getPos('y') + (handler.player.get(0).getSize('y') / 2));
		l[2].setLine(posX + (width / 2), posY,
				handler.player.get(0).getPos('x') + (handler.player.get(0).getSize('x') / 2),
				handler.player.get(0).getPos('y') + handler.player.get(0).getSize('y') - 1);

		if (checkContact()) {
			if (posX < handler.player.get(0).getPos('x')) {
				posX += velX * dt;
				while (wallCollision()) {
					posX -= 1;
				}
				right = true;
				left = false;
				lookright = true;
				walkcounter = 0;

			} else if (posX > handler.player.get(0).getPos('x')) {
				posX -= velX * dt;
				while (wallCollision()) {
					posX += 1;
				}
				right = false;
				left = true;
				lookright = false;
				walkcounter = 0;

			}
		} else if (right) {
			line.setLine(posX + width + 5, posY + height + 5, posX + width + 20, posY + height + 5);
			right = false;
			for (int i = 0; i < handler.waende.size(); i++) {
				if (line.intersects(handler.waende.get(i).getBounds())) {
					right = true;
					lookright = true;
					walkcounter = 0;

				}
			}
			if (right) {
				posX += (velX * dt);
				while (wallCollision()) {
					posX -= 1;
					right = false;
					left = true;
					lookright = false;
					walkcounter = 0;

				}
			} else {
				right = false;
				left = true;
				lookright = false;
				walkcounter = 0;

			}

		} else if (left) {
			line.setLine(posX - 5, posY + height + 5, posX - 20, posY + height + 5);
			left = false;
			for (int i = 0; i < handler.waende.size(); i++) {
				if (line.intersects(handler.waende.get(i).getBounds())) {
					left = true;
					lookright = false;
					walkcounter = 0;

				}
			}
			if (left) {
				posX -= (velX * dt);
				while (wallCollision()) {
					posX += 1;
					right = true;
					left = false;
					lookright = true;
					walkcounter = 0;

				}
			} else {
				left = false;
				right = true;
				lookright = true;
				walkcounter = 0;

			}
		}
	/*	if (typ == 0) {
			if (lookright) {
				if (walkcounter >= 0 && walkcounter < walkspeed) {
					if (this.name != "gollumwalking1") {
						this.changeName("gollumwalking1");

					}
					walkcounter++;
				} else if (walkcounter >= walkspeed && walkcounter < 2 * walkspeed) {
					if (this.name != "gollumwalking2") {
						this.changeName("gollumwalking2");
					}
					walkcounter++;
				}
				if (walkcounter == 2 * walkspeed) {
					walkcounter = 0;
				}
			} else if (!lookright) {
				if (walkcounter >= 0 && walkcounter < walkspeed) {
					if (this.name != "igollumwalking1") {
						this.changeName("igollumwalking1");

					}
					walkcounter++;
				} else if (walkcounter >= walkspeed && walkcounter < 2 * walkspeed) {
					if (this.name != "igollumwalking2") {
						this.changeName("igollumwalking2");
					}
					walkcounter++;
				}
				if (walkcounter == 2 * walkspeed) {
					walkcounter = 0;
				}
			}
		} else if (typ == 1) {
			if (lookright) {
				if (walkcounter >= 0 && walkcounter < walkspeed) {
					if (this.name != "chonkerwalking1") {
						this.changeName("chonkerwalking1");

					}
					walkcounter++;
				} else if (walkcounter >= walkspeed && walkcounter < 2 * walkspeed) {
					if (this.name != "chonkerwalking2") {
						this.changeName("chonkerwalking2");
					}
					walkcounter++;
				}
				if (walkcounter == 2 * walkspeed) {
					walkcounter = 0;
				}
			} else if (!lookright) {
				if (walkcounter >= 0 && walkcounter < walkspeed) {
					if (this.name != "ichonkerwalking1") {
						this.changeName("ichonkerwalking1");

					}
					walkcounter++;
				} else if (walkcounter >= walkspeed && walkcounter < 2 * walkspeed) {
					if (this.name != "ichonkerwalking2") {
						this.changeName("ichonkerwalking2");
					}
					walkcounter++;
				}
				if (walkcounter == 2 * walkspeed) {
					walkcounter = 0;
				}
			}
		}*/
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

		addGravity();

		if (shotCollision()) {
			hp--;
		}

		if (slashCollision()) {
			hp -= 10;
		}
	}

	public boolean checkContact() {

		for (int j = 0; j < l.length; j++) {
			playerContact = true;
			for (int i = 0; i < handler.waende.size(); i++) {
				if (l[j].intersects(handler.waende.get(i).getBounds())) {
					playerContact = false;
				}
			}
			if (playerContact && Math.sqrt(
					Math.pow(l[j].getX2() - l[j].getX1(), 2) + Math.pow(l[j].getY2() - l[j].getY1(), 2)) <= 1000) {
				return true;
			}
		}
		return false;
	}

}
