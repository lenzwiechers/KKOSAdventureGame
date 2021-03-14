import java.util.LinkedList;

import javax.swing.JPanel;

public class ObjectHandler {

	public JPanel panel;

	LinkedList<GameObject> objects = new LinkedList<GameObject>();

	LinkedList<Enemy> enemies = new LinkedList<Enemy>();

	LinkedList<Wand> waende = new LinkedList<Wand>();

	LinkedList<Door> tueren = new LinkedList<Door>();

	LinkedList<Player> player = new LinkedList<Player>();

	LinkedList<Shot> shot = new LinkedList<Shot>();

	LinkedList<Slash> slashes = new LinkedList<Slash>();

	LinkedList<Item> items = new LinkedList<Item>();

	private int screenWidth, screenHeight;
	private GameObject obj;
	private Enemy enemy;

	public ObjectHandler() {

	}

	public ObjectHandler(JPanel panel) {
		this.panel = panel;
	}

	public ObjectHandler(JPanel panel, int screenWidth, int screenHeight) {

		this.panel = panel;

		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
	}

	public void tick(long dt) {
		for (int i = 0; i < objects.size(); i++) {
			obj = objects.get(i);
			if (obj instanceof Player) {
				obj.tick(dt);
			} else if (obj instanceof Item) {
				obj.tick(dt);
			} else if (obj instanceof Shot) {
				obj.tick(dt);
			} else if (obj instanceof Slash) {
				obj.tick(dt);
			} else if (obj instanceof Enemy) {
				if (player.get(0).getPos('x') + player.get(0).getSize('x') - (1.5 * screenWidth) <= obj.getPos('x')
						&& player.get(0).getPos('x') + player.get(0).getSize('x') + (1.5 * screenWidth) >= obj
								.getPos('x')
						&& player.get(0).getPos('y') + player.get(0).getSize('y') - (1.5 * screenHeight) <= obj
								.getPos('y')
						&& player.get(0).getPos('y') + player.get(0).getSize('y') + (1.5 * screenHeight) >= obj
								.getPos('y')) {
					enemy = (Enemy) obj;
					enemy.tick(dt);
					enemy.isInScreen = true;
				} else {
					enemy = (Enemy) obj;
					enemy.isInScreen = false;
				}

			}
		}
	}

	public void addObject(GameObject obj) {
		panel.add(obj);
		objects.add(obj);
		if (obj instanceof Wand) {
			waende.add((Wand) obj);
		} else if (obj instanceof Enemy) {
			enemies.add((Enemy) obj);
		} else if (obj instanceof Door) {
			tueren.add((Door) obj);
		} else if (obj instanceof Player) {
			player.add((Player) obj);
		} else if (obj instanceof Shot) {
			shot.add((Shot) obj);
		} else if (obj instanceof Item) {
			items.add((Item) obj);
		} else if (obj instanceof Slash) {
			slashes.add((Slash) obj);
		}
	}

	public void removeObject(GameObject obj) {
		objects.remove(obj);
		panel.remove(obj);
	}
}
