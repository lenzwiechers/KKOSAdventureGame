import java.util.LinkedList;

public abstract class generateMap {

	static LinkedList<Wand> waende = new LinkedList<Wand>();
	static LinkedList<Door> tueren = new LinkedList<Door>();
	static LinkedList<GameObject> items = new LinkedList<GameObject>();
	static LinkedList<Enemy> enemies = new LinkedList<Enemy>();

	public static void generate(ObjectHandler handler) {
		generateStartRoom(handler);
		
		generateRoom1(handler);
		
		generateRoom2(handler);
		
		generateRoom3(handler);
		
		generateBossRoom(handler);
		
		

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
		// Startraum Generierung
		
		//Grenzen: 0, -1000, 6000, 1000
		
		waende.add(new Wand(0, 1000, 1000, 1000, handler));
		waende.add(new Wand(1000, 1000, 1000, 1000, handler));
		waende.add(new Wand(-1000, 1000, 1000, 1000, handler));
		waende.add(new Wand(-1000, 0, 1000, 1000, handler));
		waende.add(new Wand(-1000, -1000, 1000, 1000, handler));
		waende.add(new Wand(-1000, -2000, 1000, 1000, handler));
		waende.add(new Wand(0, -2000, 1000, 1000, handler));
		waende.add(new Wand(1000, -2000, 1000, 1000, handler));
		
		for (int i = 0; i < 5; i++) {
			waende.add(new Wand(2000 + 1000*i, 1000, 1000, 1000, handler));
			waende.add(new Wand(2000 + 1000*i, -2000, 1000, 1000, handler));
		}
		
		
		
		waende.add(new Wand(6000, 0, 1000, 1000, handler));
		waende.add(new Wand(6000, -1000, 1000, 1000, handler));
		
		waende.add(new Wand(500, 0, 50, 1000, handler));
		waende.add(new Wand(400, 200, 100, 20, handler));
		waende.add(new Wand(400, 600, 100, 20, handler));
		waende.add(new Wand(0, 400, 100, 20, handler));
		waende.add(new Wand(0, 800, 100, 20, handler));
		waende.add(new Wand(0, 0, 100, 20, handler));
		
		waende.add(new Wand(550, 0, 700, 1000, handler));
		
		waende.add(new Wand(750, -150, 30, 150, handler));
		
		enemies.add(new Enemy("direktorin", 2000, -500, handler));
		enemies.add(new Enemy("gollumneutral", 350, 600, handler));
		enemies.add(new Enemy("chonker", 1000, 0, handler));
		
/*
		waende.add(new Wand(0, -200, 100, 900, handler));
		waende.add(new Wand(0, 700, 1000, 100, handler));
		waende.add(new Wand(1200, 700, 1000, 100, handler));
		waende.add(new Wand(1900, 200, 100, 500, handler));
		waende.add(new Wand(200, 500, 100, 100, handler));
		waende.add(new Wand(400, 400, 400, 100, handler));
		waende.add(new Wand(1000, 0, 100, 600, handler));
		waende.add(new Wand(900, 200, 100, 100, handler));
		waende.add(new Wand(1100, 500, 200, 100, handler));
		waende.add(new Wand(1100, 300, 100, 100, handler));
		waende.add(new Wand(1700, 400, 200, 100, handler));
		waende.add(new Wand(1400, 200, 100, 100, handler));

		enemies.add(new Enemy("gollumneutral", 350, 600, handler));
		enemies.add(new Enemy("chonker", 1000, 0, handler));
		// enemies.add(new Enemy("item_t", 1500, 0, handler));

		items.add(new Item(1800, 200, handler, 1));
		items.add(new Item(1650, 300, handler, 2));

		Door door1 = new Door(800, 600, 100, 100, handler);
		Door door2 = new Door(600, 300, 100, 100, handler);

		tueren.add(door1);
		tueren.add(door2);
		door1.connectExit(door2);
		
		*/
	}
	
	public static void generateRoom1(ObjectHandler handler) {
		// Ebene 1 Generierung
		
		waende.add(new Wand(0, 3000, 100, 1000, handler));
		waende.add(new Wand(0, 4000, 1000, 100, handler));
		
		Door door4 = new Door(200, 3900, 100, 100, handler);
		Door door3 = new Door(1800, 300, 100, 100, handler);
		tueren.add(door3);
		tueren.add(door4);
		door3.connectExit(door4);
		
		
	}
	
	public static void generateRoom2(ObjectHandler handler) {
		// Ebene 2 Generierung
		
		
	}
	
	public static void generateRoom3(ObjectHandler handler) {
		// Ebene 3 Generierung
		
		
	}
	
	public static void generateBossRoom(ObjectHandler handler) {
		// Bossebene Generierung
		
		
	}
}