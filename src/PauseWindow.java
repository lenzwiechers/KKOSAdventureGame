import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PauseWindow extends Window implements KeyListener {
	public Game game;

	static JPanel b1Panel = new JPanel();

	static JPanel b2Panel = new JPanel();

	static JLabel label = new JLabel();

	static BufferedImage b1Image, b2Image;

	public PauseWindow(Game game) {
		super("Pause menu", 1920 / 2 - 270, 1080 / 2 - 270, 540, 540);

		this.game = game;

		b1Panel.setBounds(170, 110, 200, 100);
		b2Panel.setBounds(170, 320, 200, 100);
		this.add(b1Panel);
		this.add(b2Panel);

		label.setBounds(0, 0, 540, 540);
		this.add(label);
		
		URL url1 = null;

		try {
			url1 = new URL("URL url1 = new URL(\"https://i0.wp.com/trippy.me/wp-content/uploads/psychedelic-gradient.gif?resize=540%2C540\");");
		} catch (MalformedURLException e) {
			
		} try {

			b1Image = ImageIO.read(new File("assets/playButton.png"));

		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, "Please check your file paths", "Error", JOptionPane.ERROR_MESSAGE);
		}
		try {

			b2Image = ImageIO.read(new File("assets/loadButton.png"));

		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, "Please check your file paths", "Error", JOptionPane.ERROR_MESSAGE);
		}

		Icon labelIcon = new ImageIcon(url1);
		Icon b1Icon = new ImageIcon(b1Image);
		Icon b2Icon = new ImageIcon(b2Image);

		label.setIcon(labelIcon);
		label.setVisible(true);

		b1Panel.setLayout(null);
		b2Panel.setLayout(null);

		JButton resumeButton = new JButton(b1Icon);

		JButton saveButton = new JButton(b2Icon);

		b1Panel.add(resumeButton);
		b2Panel.add(saveButton);

		resumeButton.setVisible(true);
		saveButton.setVisible(true);

		resumeButton.setSize(200, 100);

		saveButton.setSize(200, 100);
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
