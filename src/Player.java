import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player extends GameObject implements KeyListener {
	
	
	private static final long serialVersionUID = 2917881703989759480L;
	
	public boolean right = false;
	public boolean left = false;
	public boolean up = false;
	public boolean down = false;

	public Player(){
		
		super("player");
		
		this.velX = 0.00000025f;
		this.velY = 0.00000025f;	
		
		this.width = 50;
		this.height = 80;
		this.posX = 100;
		this.posY = 100;
	}

	public void keyPressed(KeyEvent e) {
	
		if(e.getKeyCode() == 39) {
			// System.out.println("right");
			right = true;
			left = false;
		} else if(e.getKeyCode() == 37) {
			// System.out.println("left");
			left = true;
			right = false;
		}
	
	}

	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == 37) {
			left = false;
		} else if(e.getKeyCode() == 39) {
			right = false;
		}
	}

	public void keyTyped(KeyEvent e) {
		
	}

}
