import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player extends GameObject implements KeyListener {

	private static final long serialVersionUID = 2917881703989759480L;

	public boolean right = false;
	public boolean left = false;
	public boolean up = false;
	public boolean down = false;

	ObjectHandler handler;

	public Player(ObjectHandler newHandler) {

		super("player");

		this.velX = 0.00000025f;
		this.velY = 0.00000025f;

		this.width = 50;
		this.height = 80;
		this.posX = 150;
		this.posY = 100;

		handler = newHandler;
	}

	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == 39) {
			// System.out.println("right");
			right = true;
			left = false;
		} else if (e.getKeyCode() == 37) {
			// System.out.println("left");
			left = true;
			right = false;
		}

	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == 37) {
			left = false;
		} else if (e.getKeyCode() == 39) {
			right = false;
		}
	}

	public void keyTyped(KeyEvent e) {

	}

	public void tick() {
		for (int i = 0; i < handler.w�nde.size(); i++) {
			
			// Kollision player - W�nde y - Koordinate:
			if (this.posY + this.height >= handler.w�nde.get(i).posY
					&& this.posY <= handler.w�nde.get(i).posY + handler.w�nde.get(i).height
					&& this.posX + this.width >= handler.w�nde.get(i).posX
					&& this.posX <= handler.w�nde.get(i).posX + handler.w�nde.get(i).width) {
				down = false;
				this.posY = handler.w�nde.get(i).posY - this.height;
				
			} else {
				down = true;
			}
			// ------------------------
			
			// Kollision player - W�nde x - Koordinate:
			
			// if(this.posX + this.width >= handler.w�nde.get(i).posX && this.) {
				
			// }
		}
	}

}
