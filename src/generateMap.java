import java.util.LinkedList;

import javax.swing.JPanel;

public abstract class generateMap {
	static LinkedList<Wand> waende = new LinkedList<Wand>();
	static LinkedList<Door> tueren = new LinkedList<Door>();
	static LinkedList<Item> items = new LinkedList<Item>();
	static LinkedList<Enemy> enemies = new LinkedList<Enemy>();

	public static void generate(ObjectHandler handler) {
		generateStartRoom(handler);

		for (int i = 0; i < waende.size(); i++) {
			handler.addObject(waende.get(i));
		}
		for (int i = 0; i < tueren.size(); i++) {
			handler.addObject(tueren.get(i));
		}
		for (int i = 0; i < items.size(); i++) {
			handler.addObject(items.get(i));
		}
		for (int i = 0; i < enemies.size(); i++) {
			handler.addObject(enemies.get(i));
		}
	}

	public static void generateStartRoom(ObjectHandler handler) {
		//Startraum Generierung
		waende.add(new Wand(0, 0, 100, 700, handler));
		waende.add(new Wand(0, 700, 1000, 100, handler));
		waende.add(new Wand(1000, 700, 1000, 100, handler));
		waende.add(new Wand(1900, 200, 100, 500, handler));
		waende.add(new Wand(200, 500, 100, 100, handler));
		waende.add(new Wand(400, 400, 400, 100, handler));
		waende.add(new Wand(1000, 0, 100, 600, handler));
		waende.add(new Wand(900, 200, 100, 100, handler));
		waende.add(new Wand(1100, 500, 200, 100, handler));
		waende.add(new Wand(1100, 300, 100, 100, handler));
		waende.add(new Wand(1700, 400, 200, 100, handler));
		waende.add(new Wand(1400, 200, 100, 100, handler));
		
		enemies.add(new Enemy(350, 600, handler));
		enemies.add(new Enemy(1000, 0, handler));
		enemies.add(new Enemy(1500, 0, handler));

		items.add(new Item(1000, -100, handler));

		Door door1 = new Door(800, 600, 100, 100, handler);
		Door door2 = new Door(600, 300, 100, 100, handler);

		tueren.add(door1);
		tueren.add(door2);
		door1.connectExit(door2);

		Door door3 = new Door(1800, 300, 100, 100, handler);
		
		tueren.add(door3);
		
		//Ebene 1 Generierung
		
		
		
		
		
		
		
		
	}
}