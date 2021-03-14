import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

// Launcher über den das Spiel gestartet wird (verschiedene Spielstände etc.)
public class Launcher extends Window {

	private static final long serialVersionUID = -1331029298190778095L;

	static JPanel b1Panel = new JPanel();

	static JPanel b2Panel = new JPanel();

	static JPanel b3Panel = new JPanel();

	static JLabel label = new JLabel();

	static BufferedImage b1Image, b2Image, b3Image;

	public Game game;

	public Launcher(Game game) throws MalformedURLException {
		super("Game Launcher", 1920 / 2 - 512, 1080 / 2 - 384, 1024, 768);

		this.game = game;

		b1Panel.setBounds(412, 117, 200, 100);
		b2Panel.setBounds(412, 334, 200, 100);
		b3Panel.setBounds(412, 551, 200, 100);
		this.add(b1Panel);
		this.add(b2Panel);
		this.add(b3Panel);

		label.setBounds(0, 0, 1024, 768);
		this.add(label);

		URL url1 = new URL("https://i0.wp.com/trippy.me/wp-content/uploads/psychedelic-gradient.gif?resize=540%2C540");
		URL url2 = new URL("https://cdn130.picsart.com/316707967251201.gif");
		URL url3 = new URL("https://i.pinimg.com/originals/53/e9/21/53e921ba67680b1f145c778b7eab5131.gif");

		try {

			b1Image = ImageIO.read(new File("assets/playButton.png"));

		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, "Please check your file paths", "Error", JOptionPane.ERROR_MESSAGE);
		}
		try {

			b2Image = ImageIO.read(new File("assets/loadButton.png"));

		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, "Please check your file paths", "Error", JOptionPane.ERROR_MESSAGE);
		}
		try {

			b3Image = ImageIO.read(new File("assets/exitButton.png"));

		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, "Please check your file paths", "Error", JOptionPane.ERROR_MESSAGE);
		}

		Icon labelIcon = new ImageIcon(url3);
		Icon b1Icon = new ImageIcon(b1Image);
		Icon b2Icon = new ImageIcon(b2Image);
		Icon b3Icon = new ImageIcon(b3Image);

		label.setIcon(labelIcon);
		label.setVisible(true);

		b1Panel.setLayout(null);
		b2Panel.setLayout(null);
		b3Panel.setLayout(null);

		JButton playButton = new JButton(b1Icon);

		JButton loadButton = new JButton(b2Icon);

		JButton exitButton = new JButton(b3Icon);

		b1Panel.add(playButton);
		b2Panel.add(loadButton);
		b3Panel.add(exitButton);

		playButton.setVisible(true);
		loadButton.setVisible(true);
		exitButton.setVisible(true);

		playButton.setSize(200, 100);

		loadButton.setSize(200, 100);

		exitButton.setSize(200, 100);

		playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.setVisible(true);
				game.running = true;
				dispose();
			}
		});
		loadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Not implemented yet", "ERROR!", JOptionPane.ERROR_MESSAGE);
			}
		});
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				System.exit(0);
			}
		});
	}
}
