import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PauseWindow extends JPanel {

	private static final long serialVersionUID = -8583415179057020252L;

	public Game game;

	static JLabel label;

	static JLabel pauseLabel1;
	static JLabel pauseLabel2;

	static BufferedImage pauseImage;

	JButton saveButton;
	JButton quitButton;
	JButton returnButton;

	URL url1;

	public PauseWindow(Game game) {
		this.setLocation(1920 / 2 - 270, 1080 / 2 - 270);
		this.setSize(540, 540);

		this.setLayout(null);

		this.setVisible(false);

		this.game = game;

		returnButton = new JButton("Return to Game");
		returnButton.setBounds(100, 300, 340, 30);
		returnButton.setBackground(Color.MAGENTA);
		returnButton.setForeground(Color.BLACK);
		add(returnButton);

		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// game.pause = false;
				// Aus irgendeinem Grund funktioniert das nicht, da man sich danach nicht mehr
				// bewegen kann (!?), obwohl im Player über den KeyListener das Gleiche gecallt
				// wird.
			}
		});

		saveButton = new JButton("Save and quit");
		saveButton.setBounds(100, 350, 340, 30);
		saveButton.setBackground(Color.MAGENTA);
		saveButton.setForeground(Color.BLACK);
		add(saveButton);

		quitButton = new JButton("Quit Game");
		quitButton.setBounds(100, 400, 340, 30);
		quitButton.setBackground(Color.MAGENTA);
		quitButton.setForeground(Color.BLACK);
		add(quitButton);

		pauseLabel1 = new JLabel("PAUSE");
		pauseLabel1.setBounds(142, 20, 255, 70);
		pauseLabel1.setFont(new Font("Serif", Font.PLAIN, 80));
		pauseLabel1.setForeground(Color.BLACK);
		add(pauseLabel1);

		pauseLabel2 = new JLabel(
				"<html><body> Press 'ESC' to return to the Game, or one of the buttons below:</body></html>");
		pauseLabel2.setBounds(100, 100, 400, 150);
		pauseLabel2.setFont(new Font("Serif", Font.PLAIN, 40));
		pauseLabel2.setForeground(Color.BLACK);
		add(pauseLabel2);

		label = new JLabel();
		label.setBounds(0, 0, 540, 540);
		this.add(label);

		try {
			url1 = new URL("https://i0.wp.com/trippy.me/wp-content/uploads/psychedelic-gradient.gif?resize=540%2C540");
		} catch (MalformedURLException e) {
			System.out.println("URL could not be found!");
		}

		Icon labelIcon = new ImageIcon(url1);

		label.setIcon(labelIcon);
		label.setVisible(true);

	}
}
