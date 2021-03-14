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

	public boolean attacking;

	private int attackCooldown = 5000;
	private long lastAttackCounter;

	private int type;

	private int attackFrameCounter;

	public Enemy(String picName, int posX, int posY, ObjectHandler handler) {

		super(picName, handler);

		this.velX = 0.00000025f;
		this.velY = 0.00000025f;

		this.posX = posX;
		this.posY = posY;
		this.width = 99;
		this.height = 99;

		if (picName == "gollumneutral") {
			this.velX = 0.0000002f;
			this.width = this.height = 35;
			type = 0;
		} else if (picName == "chonker") {
			this.velX = 0.0000001f;
			type = 1;
		} else if (picName == "direktorin") {
			this.velX = 0.0000002f;
			this.velY = 0.0f;
			type = 1;
		} else {
			type = 1;
		}

		for (int i = 0; i < l.length; i++) {
			l[i] = new Line2D.Float();
		}

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
			} else if (posX > handler.player.get(0).getPos('x')) {
				posX -= velX * dt;
				while (wallCollision()) {
					posX += 1;
				}
				right = false;
				left = true;
			}
		} else if (right) {
			line.setLine(posX + width + 5, posY + height + 5, posX + width + 20, posY + height + 5);
			right = false;
			for (int i = 0; i < handler.waende.size(); i++) {
				if (line.intersects(handler.waende.get(i).getBounds())) {
					right = true;
				}
			}
			if (right) {
				posX += (velX * dt);
				while (wallCollision()) {
					posX -= 1;
					right = false;
					left = true;
				}
			} else {
				right = false;
				left = true;
			}

		} else if (left) {
			line.setLine(posX - 5, posY + height + 5, posX - 20, posY + height + 5);
			left = false;
			for (int i = 0; i < handler.waende.size(); i++) {
				if (line.intersects(handler.waende.get(i).getBounds())) {
					left = true;
				}
			}
			if (left) {
				posX -= (velX * dt);
				while (wallCollision()) {
					posX += 1;
					right = true;
					left = false;
				}
			} else {
				left = false;
				right = true;
			}
		}

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

		if (checkContact() && System.currentTimeMillis() - lastAttackCounter > attackCooldown) {
			attacking = true;
			lastAttackCounter = System.currentTimeMillis();
			attackFrameCounter = 0;
		}
		if (attacking) {
			if (type == 0) {
				if (attackFrameCounter == 0) {
					changeName("gollumwindup");
				} else if (attackFrameCounter == 30) {
					attackFrameCounter = 0;
					attacking = false;
					if (posX < handler.player.get(0).posX) {
						handler.addObject(new GollumWave(handler, posX + width, posY, true));
						
					} else {
						handler.addObject(new GollumWave(handler, posX, posY, false));
					}
					changeName("gollumneutral");
				}
			}
			attackFrameCounter++;
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

	public void attack() {

	}

}
