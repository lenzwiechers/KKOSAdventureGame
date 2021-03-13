import java.util.LinkedList;

import javax.swing.JPanel;

public class ObjectHandler {
	
	public JPanel panel;

	LinkedList<GameObject> objects = new LinkedList<GameObject>();
	
	LinkedList<Enemy> enemies = new LinkedList<Enemy>();

	LinkedList<Wand> waende = new LinkedList<Wand>();
	
	LinkedList<Door> tueren = new LinkedList<Door>();
	
	LinkedList<Player> player = new LinkedList<Player>();

	public ObjectHandler(JPanel panel) {
		this.panel = panel;
	}

	public void tick(long dt) {
		for (int i = 0; i < objects.size(); i++) {
			if (objects.get(i) instanceof Player) {
				objects.get(i).tick(dt);
			} else if (objects.get(i) instanceof Item) {
				objects.get(i).tick(dt);
			} else if(objects.get(i) instanceof Enemy) {
				objects.get(i).tick(dt);
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
		}else if (obj instanceof Door) {
			tueren.add((Door)obj);
		}else if (obj instanceof Player) {
			player.add((Player)obj);
		}
	}
	
	public void removeObject(GameObject obj) {
		objects.remove(obj);
		panel.remove(obj);
	}
}
