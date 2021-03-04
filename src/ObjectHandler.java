import java.util.LinkedList;

public class ObjectHandler {

	private Player player;
	private Item item_t;
	private HUD hud;

	LinkedList<GameObject> objects = new LinkedList<GameObject>();

	LinkedList<Wand> wände = new LinkedList<Wand>();

	public ObjectHandler() {

		super();

	}

	public void renderAll() {
		for (int i = 0; i < objects.size(); i++) {
			objects.get(i).render();
		}
	}

	public void tick(long dt) {
		for (int i = 0; i < objects.size(); i++) {
			if (objects.get(i) instanceof Player) {
				objects.get(i).tick(dt);
			}
			if (objects.get(i) instanceof Item) {
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
}
