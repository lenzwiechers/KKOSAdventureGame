import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PauseWindow extends Window implements KeyListener {
	public Game game;

	public PauseWindow(Game game) {
		super("Pause menu", 1920 / 2 - 200, 1080 / 2 - 300, 400, 600);

		this.game = game;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == 27) { // ESC
			
				game.pause = false;
				this.setVisible(false);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}
}
