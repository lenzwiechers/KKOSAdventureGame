import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ChooseLK extends Window {
	private Game game;

	static JLabel backgroundLabel = new JLabel();
	
	static JLabel textLabel =  new JLabel();

	static JComboBox LK1, LK2;

	static JPanel b1Panel = new JPanel();

	static BufferedImage b1Image, textLabelImage;

	public ChooseLK(Game game) throws MalformedURLException {
		super("ChooseLK", 1920 / 2 - 250, 1080 / 2 - 250, 500, 500);

		this.setVisible(false);

		this.game = game;

		String LKs[] = { "Informatik", "Sport", "Biologie", "Deutsch", "Englisch", };

		LK1 = new JComboBox(LKs);
		LK2 = new JComboBox(LKs);

		LK1.setBounds(100, 200, 100, 20);
		LK2.setBounds(300, 200, 100, 20);
		this.add(LK1);
		this.add(LK2);
		
		textLabel.setBounds(18, 100, 465, 67);
		this.add(textLabel);

		b1Panel.setBounds(150, 300, 200, 100);
		this.add(b1Panel);

		backgroundLabel.setBounds(0, 0, 500, 500);
		this.add(backgroundLabel);

		URL url1 = new URL("https://i.pinimg.com/originals/d8/c5/cb/d8c5cb4c53c31a1f7b166db644c5726b.gif");
		URL url2 = new URL("https://i.pinimg.com/originals/17/d2/cd/17d2cdf8af071c636252bdfa7c4305bd.gif");

		try {

			b1Image = ImageIO.read(new File("assets/startButton.png"));

		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, "Please check your file paths", "Error", JOptionPane.ERROR_MESSAGE);
		}
		try {

			textLabelImage = ImageIO.read(new File("assets/textLabelImage.png"));

		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, "Please check your file paths", "Error", JOptionPane.ERROR_MESSAGE);
		}

		Icon labelIcon = new ImageIcon(url1);
		Icon b1Icon = new ImageIcon(b1Image);
		Icon textLabelIcon = new ImageIcon(textLabelImage);

		backgroundLabel.setIcon(labelIcon);
		backgroundLabel.setVisible(true);
		
		textLabel.setIcon(textLabelIcon);
		textLabel.setVisible(true);

		b1Panel.setLayout(null);

		JButton startButton = new JButton(b1Icon);

		b1Panel.add(startButton);

		startButton.setVisible(true);

		startButton.setSize(200, 100);

		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!((String) LK1.getItemAt(LK1.getSelectedIndex()) == (String) LK2.getItemAt(LK2.getSelectedIndex()))) {
					game.player.setLK1((String) LK1.getItemAt(LK1.getSelectedIndex()));
					game.player.setLK2((String) LK2.getItemAt(LK2.getSelectedIndex()));
				} else {
					game.player.setLK1((String) LK1.getItemAt(LK1.getSelectedIndex()));
					game.player.setLK2("No LK");
				}
				game.setVisible(true);
				game.running = true;

				System.out.println("-> LK window closed");
				System.out.println();
				
				dispose();
			}
		});
	}

	public void init() {

	}
}
