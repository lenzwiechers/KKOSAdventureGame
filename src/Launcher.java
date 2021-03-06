import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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

// Launcher �ber den das Spiel gestartet wird (verschiedene Spielst�nde etc.)
public class Launcher extends Window {

	private static final long serialVersionUID = -1331029298190778095L;

	static JPanel b1Panel = new JPanel();

	static JPanel b2Panel = new JPanel();

	static JPanel b3Panel = new JPanel();

	static JLabel label = new JLabel();

	static BufferedImage b1Image, b2Image, b3Image;

	public Game game;

	public ChooseLK chooseLK;

	BufferedReader reader;

	String line;

	int x, y, type;

	@SuppressWarnings("unused")
	public Launcher(Game game) throws MalformedURLException { // Konstruktor
		super("Game Launcher", 1920 / 2 - 512, 1080 / 2 - 384, 1024, 768);

		System.out.println("-> Launcher started");
		System.out.println();

		this.game = game;

		// Setzt alle Buttons und panels
		chooseLK = new ChooseLK(game);

		b1Panel.setBounds(412, 117, 200, 100);
		b2Panel.setBounds(412, 334, 200, 100);
		b3Panel.setBounds(412, 551, 200, 100);
		this.add(b1Panel);
		this.add(b2Panel);
		this.add(b3Panel);

		label.setBounds(0, 0, 1024, 768);
		this.add(label);

		// Wandelt eine URL in ein image um, welches dann gerendert werden soll:
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

		// Macht die bilder in ein Icon
		Icon labelIcon = new ImageIcon(url3);
		Icon b1Icon = new ImageIcon(b1Image);
		Icon b2Icon = new ImageIcon(b2Image);
		Icon b3Icon = new ImageIcon(b3Image);

		label.setIcon(labelIcon);
		label.setVisible(true);

		// Einstellen der Buttons:
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

		// Action Listener f�r die Buttons:
		// Falls der playButton gedr�ckt wird:
		playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Startet das Game
				startGame(game, false);
			}
		});
		// Falls der loadButton gedr�ckt wird:
		loadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// L�dt das Spiel:
				try {

					reader = new BufferedReader(new FileReader("assets/saveFile.txt")); // Reader der die Datei ausliest

					// Laden des Players:
					line = reader.readLine();
					game.handler.player.get(0).setPos('x', Integer.parseInt(line));

					line = reader.readLine();
					game.handler.player.get(0).setPos('y', Integer.parseInt(line));

					// Laden der HP des Players:
					line = reader.readLine();
					game.handler.player.get(0).totalHP = Integer.parseInt(line);
					for (int i = 9; i > Integer.parseInt(line) - 1; i--) {
						game.handler.player.get(0).hp[i] = false;
					}

					// Laden der Items auf der Map:
					for (int i = 0; i < game.handler.items.size(); i++) {
						game.handler.objects.remove(game.handler.items.get(i));
					}

					game.handler.items.clear();

					line = reader.readLine();

					while (!(line.equals("END OF ITEMS"))) {
						x = Integer.parseInt(line);
						System.out.println("x: " + x);
						y = Integer.parseInt(reader.readLine());
						System.out.println("y: " + y);
						type = Integer.parseInt(reader.readLine());
						System.out.println("type: " + type);
						game.handler.addObject(new Item(x, y, game.handler, type));
						System.out.println("---------------------------");
						line = reader.readLine();
					}

					// Laden der Gegner auf der Map:
					for (int i = 0; i < game.handler.enemies.size(); i++) {
						game.handler.objects.remove(game.handler.enemies.get(i));
					}

					game.handler.enemies.clear();

					line = reader.readLine();

					while (!(line.equals("END OF ENEMIES"))) {
						x = Integer.parseInt(line);
						System.out.println("x: " + x);
						y = Integer.parseInt(reader.readLine());
						System.out.println("y: " + y);
						type = Integer.parseInt(reader.readLine());
						System.out.println("type: " + type);
						game.handler.addObject(new Enemy(type, x, y, game.handler));
						System.out.println("---------------------------");
						line = reader.readLine();
					}

					// Laden des Inventars des Players:
					for (int i = 0; i < 3; i++) {
						line = reader.readLine();
						game.handler.player.get(0).item[i] = Boolean.parseBoolean(line);
					}

					line = reader.readLine();

					// Laden, ob dash m�glich ist:
					line = reader.readLine();
					game.handler.player.get(0).setDash(Boolean.parseBoolean(line));

					// Laden der LKs:
					line = reader.readLine();
					game.handler.player.get(0).LK1 = line;

					line = reader.readLine();
					game.handler.player.get(0).LK2 = line;

					reader.close();
					startGame(game, true); // Game wird gestartet

				} catch (Exception e2) { // Falls was schiefl�uft
					System.out.println("FEHLER: " + e2);
				}
			}
		});

		// Falls der exitButton gedr�ckt wird:
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Schlie�t das Spiel
				System.out.println("-> Game closed");
				System.out.println();
				dispose();
				System.exit(0);
			}
		});
	}

	// Methode die das Spiel startet
	public void startGame(Game game, boolean loading) {

		System.out.println("-> Launcher closed");
		System.out.println();

		if (!loading) {
			// Bringt das LK w�hlen in den Vordergrund
			chooseLK.setVisible(true);

			System.out.println("-> LK window opened");
			System.out.println();
		} else { // Falls das Spiel aus der saveFile geladen wird:
			game.setVisible(true);
			game.running = true;
		}

		dispose(); // Launcher wird geschlossen
	}
}
