import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class PauseWindow extends JPanel{
	public Game game;

	static JLabel label = new JLabel();
	
	static JLabel pauseLabel = new JLabel();

	static BufferedImage pauseImage;
	
	public PauseWindow(Game game) {
		this.setLocation(1920 / 2 -270, 1080 / 2 - 270);
		this.setSize(540, 540);
		
		this.setLayout(null);
		
		this.setVisible(false);

		this.game = game;
		
		pauseLabel.setBounds(540 / 2 - 142, 540 / 2 - 200, 284, 149);
		this.add(pauseLabel);

		label.setBounds(0, 0, 540, 540);
		this.add(label);		
		
		URL url1 = null;

		try {
			url1 = new URL("https://i0.wp.com/trippy.me/wp-content/uploads/psychedelic-gradient.gif?resize=540%2C540");
		} catch (MalformedURLException e) {
			
		} try {

			pauseImage = ImageIO.read(new File("assets/pause.png"));

		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, "Please check your file paths", "Error", JOptionPane.ERROR_MESSAGE);
		}

		Icon labelIcon = new ImageIcon(url1);
		Icon pauseIcon = new ImageIcon(pauseImage);

		label.setIcon(labelIcon);
		label.setVisible(true);
		
		pauseLabel.setIcon(pauseIcon);
		pauseLabel.setVisible(true);
	}
}
