import java.awt.MouseInfo;
import java.awt.PointerInfo;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Point;

import javax.swing.JPanel;


public class Player extends GameObject implements KeyListener, MouseListener {

	private static final long serialVersionUID = 2917881703989759480L;

	
	Point a;
	private float mx;
	private float my;
	
	private boolean right = false;
	private boolean left = false;
	private boolean up = false;
	private boolean down = false;

	private boolean jump = false;

	public boolean itemT = false;
	public boolean gun = false;

	private int dashlength = 5;
	private int dashcounter = dashlength;
	private float dashspeed = 0.00000150f;
	private int dashcooldown = 180;
	private int cooldowncounter = dashcooldown;

	private boolean inAir = false;
	private int airTime = 0;

	private double gravity;

	private long enemyContactCounter;

	ObjectHandler handler;
	JPanel panel;

	private boolean inWall;

	Window window;

	Inventory inventory;

	public Player(ObjectHandler newHandler, Window window, JPanel newPanel) {

		super("player", newHandler);

		this.velX = 0.00000024f;
		this.velY = 0.00000025f;

		this.width = 50;
		this.height = 80;
		this.posX = 170;
		this.posY = 100;

		handler = newHandler;
		panel = newPanel;

		this.window = window;

		inventory = new Inventory(window);
		addKeyListener(this);
		addMouseListener(this);
	}

	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == 68 || e.getKeyCode() == 39) { // d/right arrow
			right = true;
		} else if (e.getKeyCode() == 65 || e.getKeyCode() == 37) { // a/left arrow
			left = true;
		} else if (e.getKeyCode() == 32) { // Space bar
			jump = true;
		} else if (e.getKeyCode() == 17) { // Ctrl
			this.velX = 0.0000004f;
		} else if (e.getKeyCode() == 69) { // e
			inventory.showInv();
		} else if (e.getKeyCode() == 81) { // q
			if (dashcounter == dashlength && cooldowncounter == dashcooldown) {
				dashcounter = 0;
				cooldowncounter = 0;
			}
		}else if (e.getKeyCode() == 70) { // f
			enterDoor(atDoor());
		}
	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == 65 || e.getKeyCode() == 37) {
			left = false;
		} else if (e.getKeyCode() == 68 || e.getKeyCode() == 39) {
			right = false;
		} else if (e.getKeyCode() == 32) {
			jump = false;
		} else if (e.getKeyCode() == 17) {
			this.velX = 0.00000024f;
		}
	}

	public void keyTyped(KeyEvent e) {
		
	}
	
	public void mouseClicked(MouseEvent m) {
		
		
	}
	
	public void mousePressed(MouseEvent m) {
		if(gun){
			System.out.println("pew pew");
			handler.addObject(new Shot(this.getPos('x'), this.getPos('y'), handler, new Vector2(m.getX(), m.getY())));
		}
		//System.out.println(m.getX()+this.getPos('x'));
		//System.out.println(m.getY()+this.getPos('y'));
	}
	
	public void mouseReleased(MouseEvent m) {
		
	}
	

	public Door atDoor() {
		Door door = null;

		for (int i = 0; i < handler.tueren.size(); i++) {
			if (this.getBounds().intersects(handler.tueren.get(i).getTpBounds())) {
				door = handler.tueren.get(i);
				return door;
			}
		}

		return door;
	}

	public void enterDoor(Door door) {
		if (door != null) {
			this.setPos('x', door.getExitX());
			this.setPos('y', door.getExitY());
			Camera.renderAll();
		}
	}

	public void tick(long dt) {
		
		a = MouseInfo.getPointerInfo().getLocation();
		mx = (float) a.getX();
		my = (float) a.getY();

		if (dashcounter < dashlength) {
			this.velX = dashspeed;
			dashcounter++;
			if (dashcounter == dashlength) {
				this.velX = 0.00000024f;
			}
		}
		if (cooldowncounter < dashcooldown) {
			cooldowncounter++;
		}

		if (right && !left) {
			if (this.name == "player_inverted") {
				this.changeName("player");
			}
			posX += velX * dt;
			while (wallCollision()) {
				posX -= 1;
			}
		}
		if (left && !right) {
			if (this.name == "player") {
				this.changeName("player_inverted");
			}
			posX -= velX * dt;
			while (wallCollision()) {
				posX += 1;
			}
		}

		if (jump && onWall()) {
			velY = -0.0000009f;
		}

		addGravity();

		posY += velY * dt;
		inWall = false;
		if (wallCollision()) {
			inWall = true;
		}
		while (wallCollision()) {
			posY -= Math.signum(velY);

		}

		for (int i = 0; i < handler.enemies.size(); i++) {
			if (this.getBounds().intersects(handler.enemies.get(i).getBounds())) {
				if (System.currentTimeMillis() - enemyContactCounter > 1000) {
					HUD.HEALTH -= 20;
					enemyContactCounter = System.currentTimeMillis();
				}
			}
		}

		if (inWall) {
			velY = 0;
		}

		if (onWall()) {
			velY = 0;
		}
	}

	

	

	public void mouseEntered(MouseEvent m) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent m) {
		// TODO Auto-generated method stub
		
	}
}
