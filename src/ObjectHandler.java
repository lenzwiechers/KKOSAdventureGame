import java.util.LinkedList;

public class ObjectHandler {

	LinkedList<GameObject> objects = new LinkedList<GameObject>();

	LinkedList<Wand> wände = new LinkedList<Wand>();

	public ObjectHandler() {
		
	}

	public void tick(long dt) {
		for (int i = 0; i < objects.size(); i++) {
			if (objects.get(i) instanceof Player) {
				objects.get(i).tick(dt);
			} else if (objects.get(i) instanceof Item) {
				objects.get(i).tick(dt);
			}
		}
	}

	public void addObject(GameObject obj) {
		objects.add(obj);
		if (obj instanceof Wand) {
			wände.add((Wand) obj);
		}
	}
	
	public void removeObject(GameObject obj) {
		objects.remove(obj);
	}
}
