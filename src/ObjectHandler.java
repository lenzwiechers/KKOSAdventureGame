import java.awt.Component;
import java.util.LinkedList;
import javax.swing.JPanel;

// Klasse die alle Objekte verwaltet
public class ObjectHandler {

	public JPanel panel;
	
	//Listen aller Objekte
	//Verschiedene Listem werden verwendet um Loops für Kollision mit wänden zu verringern

	LinkedList<GameObject> objects = new LinkedList<GameObject>();

	LinkedList<Enemy> enemies = new LinkedList<Enemy>();

	LinkedList<Wand> waende = new LinkedList<Wand>();

	LinkedList<Door> tueren = new LinkedList<Door>();

	LinkedList<Player> player = new LinkedList<Player>();

	LinkedList<Shot> shot = new LinkedList<Shot>();

	LinkedList<Slash> slashes = new LinkedList<Slash>();

	LinkedList<Item> items = new LinkedList<Item>();

	LinkedList<FlyingObject> stuff = new LinkedList<FlyingObject>();

	LinkedList<TutorialLabel> tutorialLabels = new LinkedList<TutorialLabel>();

	public int screenWidth, screenHeight;
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
		//Geht alle Objekte durch
		for (int i = 0; i < objects.size(); i++) {
			obj = objects.get(i);
			//Falls ein Objekt ein Gegner ist:
			if (obj instanceof Enemy) {
				//Gegner werden nur getickt wenn sie in der nähe des Spielers sind
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

			} else {
				//Alle anderen Objekte werden getickt
				obj.tick(dt);
			}
		}
	}

	public void addObject(Object obj) {
		//Fügt das Objekt dem panel hinzu und der Liste aller Objekte
		panel.add((Component) obj);
		objects.add((GameObject) obj);
		//Fügt das Objekt den entsprechenden Listen hinzu
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
		} else if ((obj) instanceof FlyingObject) {
			stuff.add((FlyingObject) obj);
		} else if (obj instanceof TutorialLabel) {
			tutorialLabels.add((TutorialLabel) obj);
		}
	}

	public void removeObject(GameObject obj) {
		//Entfernt das Objekt vom panel un der Liste aller Objekte
		objects.remove(obj);
		panel.remove(obj);
		//Entfernt das Objekt aus den entsprechenden Listen
		if (obj instanceof Wand) {
			waende.remove(obj);
		} else if (obj instanceof Enemy) {
			enemies.remove(obj);
		} else if (obj instanceof Door) {
			tueren.remove(obj);
		} else if (obj instanceof Player) {
			player.remove(obj);
		} else if (obj instanceof Shot) {
			shot.remove(obj);
		} else if (obj instanceof Item) {
			items.remove(obj);
		} else if (obj instanceof Slash) {
			slashes.remove(obj);
		} else if ((obj) instanceof FlyingObject) {
			stuff.remove(obj);
		}
	}
}
