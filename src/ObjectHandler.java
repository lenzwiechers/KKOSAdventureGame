import java.util.LinkedList;

public class ObjectHandler {

	LinkedList<GameObject> objects = new LinkedList<GameObject>();
	
	LinkedList<Enemy> enemies = new LinkedList<Enemy>();

	LinkedList<Wand> waende = new LinkedList<Wand>();

	public ObjectHandler() {
		
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
		objects.add(obj);
		if (obj instanceof Wand) {
			waende.add((Wand) obj);
		} else if (obj instanceof Enemy) {
			enemies.add((Enemy) obj);
		}
	}
	
	public void removeObject(GameObject obj) {
		objects.remove(obj);
	}
}
