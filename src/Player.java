import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player extends GameObject implements KeyListener {
	
	
	private static final long serialVersionUID = 2917881703989759480L;

	public Player(){
		
		super();
		
	}

	public void keyPressed(KeyEvent e) {
	
		System.out.println(e.getKeyCode());
	
	}

	public void keyReleased(KeyEvent e) {
		
	}

	public void keyTyped(KeyEvent e) {
		
	}

}
