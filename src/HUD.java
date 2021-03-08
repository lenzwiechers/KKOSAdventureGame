import java.awt.Color;
import java.awt.Graphics;

public class HUD {

	public static float HEALTH = 100; // Leben
	private ObjectHandler handler;
	private float greenValue = 255;
	private float lengthOfHealth;
	private int score = 0;
	private int level = 1; // Gruenwert, score, level
	private Player player;

	public HUD(Player player) {
		this.player = player;
	}

	public void tick() {
		HEALTH = Game.clamp(HEALTH, 0, 100);
		greenValue = Game.clamp(greenValue, 0, 255); // Gruenwert und Leben werden nur in ihren Grenzen bleiben
		greenValue = HEALTH * 2;
		lengthOfHealth = Game.clamp(lengthOfHealth, 0, 200);
		lengthOfHealth = HEALTH * 2;
		// score++;
	}

	public void render(Graphics g) {
		g.setColor(Color.gray);
		g.fillRect(35, 35, 200, 32);
		g.setColor(new Color(175, (int) greenValue, 0)); // das Darstellen von Leben und Level
		g.fillRect(35, 35, (int) lengthOfHealth, 32);
		g.setColor(Color.white);
		g.drawRect(35, 35, 200, 32);
		// g.drawString("Score: "+score, 15, 64);
		g.drawString("Level: " + level, 35, 80);
		g.drawString("x: " + player.getPos('x'), 35, 110);
		g.drawString("y: " + player.getPos('y'), 35, 130);
	}

	public void drawPause() {
		int width = Game.screenWidth;
		int height = Game.screenHeight;
	}

	public void drawMenu(Game game) {
		HEALTH = 100;
		int width = Game.screenWidth;
		int height = Game.screenHeight;
	}

	public void score(int score) {
		this.score = score;
	}

	public int getScore() {
		return score; // Get und Setmethoden fuer Level und Score
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
}