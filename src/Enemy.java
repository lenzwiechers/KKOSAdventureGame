import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.geom.Line2D;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JLabel;

public class Enemy extends GameObject {

	private static final long serialVersionUID = 4573246985108449527L;

	private boolean inWall;

	private boolean playerContact;

	public static int x1, x2, y1, y2;

	Line2D[] l = new Line2D[3];

	public boolean isInScreen = false;

	private boolean gravity = true;

	Random rand = new Random();

	Line2D line;

	int hp = 100;

	private boolean right = true; // AKTUELLE GANGRICHTUNG (BENUTZT FÜR ANIMATION)
	private boolean left;
	private boolean lookright = true;
	private int walkcounter = 0;
	private int walkspeed = 5;

	public boolean attacking;
	private boolean chonkerAttackRight; // --> true --> chonker Attack to the right rn
	private boolean chonkerAttackLeft; // --> true --> chonker Attack to the left rn

	private int attackCooldown = 5000;
	private long lastAttackCounter;

	int type;

	private int attackFrameCounter;

	JLabel bossHealthBar;

	float randomNum;

	int totalHP;

	Font font;

	public Enemy(int type, int posX, int posY, ObjectHandler handler) {

		super("chonker", handler);

		velX = 0.00000025f;
		velY = 0.00000025f;

		this.posX = posX;
		this.posY = posY;
		this.width = 99;
		this.height = 99;

		this.velX = 0.0000001f;

		randomNum = ThreadLocalRandom.current().nextInt(10, 30); // 0.0000001f

		velX = randomNum / 100000000;

		this.type = type;

		if (type == 0) {
			changeName("gollumneutral");
			type = 0;
			this.width = 35;
			this.height = 35;
		} else if (type == 1) {
			changeName("chonker");
			this.width = 57;
			this.height = 96;
			type = 1;
		} else if (type == 2) {
			changeName("direktorin");
			velX = 0.0f;
			velY = 0.0f;
			type = 2;
			this.height = 1000;
			this.width = 400;
			bossHealthBar = new JLabel();
			bossHealthBar.setBackground(Color.RED);
			bossHealthBar.setOpaque(true);
			bossHealthBar.setText("D I R E K T O R I N");
			bossHealthBar.setForeground(Color.BLACK);
			try {
				font = Font.createFont(Font.TRUETYPE_FONT, new File("assets/puree____2.ttf")).deriveFont(50f);
				GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
				ge.registerFont(font);
			} catch (FontFormatException | IOException e) {
				System.out.println("Font konnte nicht geladen werden");
			}
			bossHealthBar.setFont(font);
			hp = totalHP = 20000;
		} else {
			type = 3;
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

		if (handler.player.get(0).posX - posX < 2000 && handler.player.get(0).posY - posY < 2000 && type == 2) {
			bossHealthBar.setVisible(true);
			bossHealthBar.setBounds(20, 100, (int) (hp * (handler.screenWidth - 100) / totalHP),
					70);
		} else if (type == 2) {
			bossHealthBar.setVisible(false);
		}

		if (hp < 0) {
			handler.removeObject(this);
			handler.player.get(0).money += rand.nextInt(201) + 200;
			if (rand.nextInt(99) < 15) {
				handler.addObject(new Item(posX, posY, handler, 0));
			}
		}

		if (checkContact() && System.currentTimeMillis() - lastAttackCounter > attackCooldown) {
			attacking = true;
			lastAttackCounter = System.currentTimeMillis();
			attackFrameCounter = 0;
		}

		sCollide = false;
		slCollide = false;

		l[0].setLine(posX + (width / 2), posY,
				handler.player.get(0).getPos('x') + (handler.player.get(0).getSize('x') / 2),
				handler.player.get(0).getPos('y') + 1);
		l[1].setLine(posX + (width / 2), posY,
				handler.player.get(0).getPos('x') + (handler.player.get(0).getSize('x') / 2),
				handler.player.get(0).getPos('y') + (handler.player.get(0).getSize('y') / 2));
		l[2].setLine(posX + (width / 2), posY,
				handler.player.get(0).getPos('x') + (handler.player.get(0).getSize('x') / 2),
				handler.player.get(0).getPos('y') + handler.player.get(0).getSize('y') - 1);

		if (checkContact() && !chonkerAttackRight && !chonkerAttackLeft) {
			if (posX < handler.player.get(0).getPos('x')) {
				posX += velX * dt;
				while (wallCollision()) {
					posX -= 1;
				}
				right = true;
				left = false;
				lookright = true;

			} else if (posX > handler.player.get(0).getPos('x')) {
				posX -= velX * dt;
				while (wallCollision()) {
					posX += 1;
				}
				right = false;
				left = true;
				lookright = false;

			}
		} else if (right && !chonkerAttackRight && !chonkerAttackLeft) {
			line.setLine(posX + width + 5, posY + height + 5, posX + width + 20, posY + height + 5);
			right = false;
			for (int i = 0; i < handler.waende.size(); i++) {
				if (line.intersects(handler.waende.get(i).getBounds())) {
					right = true;
					lookright = true;

				}
			}
			if (right) {
				posX += (velX * dt);
				while (wallCollision()) {
					posX -= 1;
					right = false;
					left = true;
					lookright = false;

				}
			} else {
				right = false;
				left = true;
				lookright = false;

			}

		} else if (left && !chonkerAttackRight && !chonkerAttackLeft) {
			line.setLine(posX - 5, posY + height + 5, posX - 20, posY + height + 5);
			left = false;
			for (int i = 0; i < handler.waende.size(); i++) {
				if (line.intersects(handler.waende.get(i).getBounds())) {
					left = true;
					lookright = false;

				}
			}
			if (left) {
				posX -= (velX * dt);
				while (wallCollision()) {
					posX += 1;
					right = true;
					left = false;
					lookright = true;
				}
			} else {
				left = false;
				right = true;
				lookright = true;

			}
		} else if (chonkerAttackRight) {
			posX += velX * dt;
			while (wallCollision()) {
				posX -= 1;
				right = false;
				left = false;
				lookright = false;
			}
		} else if (chonkerAttackLeft) {
			posX -= velX * dt;
			while (wallCollision()) {
				posX += 1;
				right = false;
				left = false;
			}
		}

		if (!attacking) {
			if (type == 0) {
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
			} else if (type == 1) {
				if (lookright) {
					if (walkcounter >= 0 && walkcounter < walkspeed) {
						if (name != "chonkerwalking1") {
							changeName("chonkerwalking1");
						}
						walkcounter++;
					} else if (walkcounter >= walkspeed && walkcounter < 2 * walkspeed) {
						if (name != "chonkerwalking2") {
							changeName("chonkerwalking2");
						}
						walkcounter++;
					}
					if (walkcounter == 2 * walkspeed) {
						walkcounter = 0;
					}
				} else if (!lookright) {
					if (walkcounter >= 0 && walkcounter < walkspeed) {
						if (name != "ichonkerwalking1") {
							changeName("ichonkerwalking1");
						}
						walkcounter++;
					} else if (walkcounter >= walkspeed && walkcounter < 2 * walkspeed) {
						if (name != "ichonkerwalking2") {
							changeName("ichonkerwalking2");
						}
						walkcounter++;
					}
					if (walkcounter == 2 * walkspeed) {
						walkcounter = 0;
					}
				}
			} else if (type == 2) {

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

		if (gravity && type != 2) {
			addGravity();
		}

		if (shotCollision()) {
			if (handler.player.get(0).LK1.equals("Informatik") || handler.player.get(0).LK2.equals("Informatik")) {
				hp -= 110;
			} else {
				hp -= 55;
			}
		}

		if (slashCollision()) {
			if (handler.player.get(0).LK1.equals("Sport") || handler.player.get(0).LK2.equals("Sport")) {
				hp -= 300;
			} else {
				hp -= 150;
			}
		}

		if (attacking) { // WENN ATTACKIEREND WIRD GOLLUM ERST ANGEHALTEN
			if (type == 0) {
				if (attackFrameCounter == 0) {
					velX = 0;
					if (right) { // GOLLUM WECHSELT ZU SEINEM AUFLADE FRAME
						changeName("gollumwindup");
					} else {
						changeName("igollumwindup");
					}
				} else if (attackFrameCounter == 30) { // UND NACH KURZER ZEIT ZU SCHUSS ANIMATION (IMMER IN
														// ANHÄNGIGKEIT ZUR LAUFRICHTUNG)
					if (right) {
						changeName("gollumattack");
					} else {
						changeName("igollumattack");
					}
					handler.addObject(new FlyingObject("gollumWave", handler, posX + width, posY, new Vector2(
							handler.player.get(0).getPos('x') + 25, handler.player.get(0).getPos('y') + 40)));

				} else if (attackFrameCounter == 60) {
					attackFrameCounter = 0;
					velX = randomNum / 100000000;
					attacking = false;
				}
			} else if (type == 1) {
				if (attackFrameCounter == 0) { // GLEICHES PRINZIEP WIE BEI GOLLUM NUR OHNE STEHEN BLEIBEN
					if (right) {
						changeName("chonkerwindup");
						velX = 0f;
						chonkerAttackRight = true;
					} else {
						changeName("ichonkerwindup");
						velX = 0f;
						chonkerAttackLeft = true;
					}
				} else if (attackFrameCounter == 60) {
					if (right) {
						changeName("chonkerbodyslam");
						velX = 0.0000006f;
						velY = 0;
						gravity = false;
					} else {
						changeName("ichonkerbodyslam");
						velX = 0.0000006f;
						velY = 0;
						gravity = false;
					}
				} else if (attackFrameCounter == 100) {
					gravity = true;
					attacking = false;
					attackFrameCounter = 0;
					velX = 0.0000001f;
					chonkerAttackRight = chonkerAttackLeft = false;
				}
			} else if (type == 2) {
				if (attackFrameCounter == 0) {
					changeName("direktorin_summon"); // spawnEnemyFrame
				} else if (attackFrameCounter == 70) {
					handler.addObject(new Enemy((int) (Math.random() * 2), posX, posY, handler));
					changeName("direktorin");
				} else if (attackFrameCounter == 140) {
					changeName("direktorin_verwarnung"); // Schussframe
				} else if (attackFrameCounter == 210) {
					handler.addObject(new FlyingObject("verwarnung", handler, posX + 20, posY + 300,
							new Vector2(handler.player.get(0).getPos('x') + 25, handler.player.get(0).getPos('y'))));
					changeName("direktorin");
					attacking = false;
					attackFrameCounter = 0;
				}
			} else {
				attacking = false;
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
					Math.pow(l[j].getX2() - l[j].getX1(), 2) + Math.pow(l[j].getY2() - l[j].getY1(), 2)) <= 2000) {
				return true;
			}
		}
		return false;
	}

	public void attack() {

	}

}
