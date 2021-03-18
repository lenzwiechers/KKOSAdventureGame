import java.util.LinkedList;

public abstract class generateMap {

	static LinkedList<Wand> waende = new LinkedList<Wand>();
	static LinkedList<Door> tueren = new LinkedList<Door>();
	static LinkedList<Item> items = new LinkedList<Item>();
	static LinkedList<Enemy> enemies = new LinkedList<Enemy>();

	public static void generate(ObjectHandler handler) {
		generateStartRoom(handler);

		generateRoom1(handler);

		generateRoom2(handler);

		generateRoom3(handler);

		generateBossRoom(handler);

		// generateTestRoom(handler);

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

	public static void generateTestRoom(ObjectHandler handler) {
		Door doorDebug1 = new Door(250, 4900, 100, 100, handler);
		Door doorDebug2 = new Door(100, -2000, 100, 100, handler);
		doorDebug1.connectExit(doorDebug2);
		doorDebug2.connectExit(doorDebug1);

		tueren.add(doorDebug2);
		tueren.add(doorDebug1);

		waende.add(new Wand(0, -2000, 1000, 100, handler));
		waende.add(new Wand(1000, -2000, 1000, 100, handler));
		waende.add(new Wand(-100, -3000, 100, 1000, handler));
		waende.add(new Wand(0, -3100, 1000, 100, handler));
		waende.add(new Wand(1000, -3100, 1000, 100, handler));
		waende.add(new Wand(2000, -3000, 100, 1000, handler));
	}

	public static void generateStartRoom(ObjectHandler handler) {
		// Startraum Generierung

		// Grenzen: 0, -1000, 6000, 1000

		// Umriss

		waende.add(new Wand(0, 1000, 1000, 1000, handler));
		waende.add(new Wand(1000, 1000, 1000, 1000, handler));
		waende.add(new Wand(-1000, 1000, 1000, 1000, handler));
		waende.add(new Wand(-1000, 0, 1000, 1000, handler));
		waende.add(new Wand(-1000, -1000, 1000, 1000, handler));
		waende.add(new Wand(-1000, -2000, 1000, 1000, handler));
		waende.add(new Wand(0, -2000, 1000, 1000, handler));
		waende.add(new Wand(1000, -2000, 1000, 1000, handler));

		for (int i = 0; i < 5; i++) {
			waende.add(new Wand(2000 + 1000 * i, 1000, 1000, 1000, handler));
			waende.add(new Wand(2000 + 1000 * i, -2000, 1000, 1000, handler));
		}

		waende.add(new Wand(6000, 0, 1000, 1000, handler));
		waende.add(new Wand(6000, -1000, 1000, 1000, handler));

		// Umriss Ende

		// Innen

		waende.add(new Wand(500, 0, 50, 1000, handler));
		waende.add(new Wand(300, 200, 200, 20, handler));
		waende.add(new Wand(300, 600, 200, 20, handler));
		waende.add(new Wand(0, 400, 200, 20, handler));
		waende.add(new Wand(0, 800, 200, 20, handler));
		waende.add(new Wand(0, 0, 200, 20, handler));

		waende.add(new Wand(550, 0, 700, 1000, handler));

		waende.add(new Wand(750, -150, 30, 150, handler));

		enemies.add(new Enemy("gollumneutral", 800, -100, handler));

		waende.add(new Wand(1450, 500, 500, 20, handler));
		waende.add(new Wand(1250, 700, 100, 200, handler));
		waende.add(new Wand(1250, 900, 300, 100, handler));

		enemies.add(new Enemy("gollumneutral", 1900, 840, handler));
		enemies.add(new Enemy("chonker", 2150, 800, handler));

		waende.add(new Wand(2150, 600, 100, 20, handler));
		waende.add(new Wand(2350, 500, 300, 20, handler));

		waende.add(new Wand(2800, 300, 1000, 700, handler));
		waende.add(new Wand(3300, -1000, 20, 1000, handler));
		waende.add(new Wand(3300, 0, 20, 300, handler));

		Door door1 = new Door(3150, 200, 100, 100, handler);
		Door door2 = new Door(3370, 200, 100, 100, handler);
		door1.connectExit(door2);
		door2.connectExit(door1);
		tueren.add(door2);
		tueren.add(door1);

		waende.add(new Wand(4150, 300, 200, 20, handler));

		Door door3 = new Door(3850, 900, 100, 100, handler);
		tueren.add(door3);

		door3.connectExit(door2);

		waende.add(new Wand(4700, 200, 600, 20, handler));

		enemies.add(new Enemy("chonker", 4800, 100, handler));
		enemies.add(new Enemy("chonker", 5100, 50, handler));
		enemies.add(new Enemy("gollumneutral", 4800, 900, handler));
		enemies.add(new Enemy("gollumneutral", 5200, 900, handler));

		waende.add(new Wand(5500, -500, 200, 1000, handler));
		waende.add(new Wand(5500, 500, 200, 310, handler));

		waende.add(new Wand(5000, 500, 50, 500, handler));

		// Innen Ende

		// Secret

		waende.add(new Wand(5700, 800, 40, 10, handler));
		waende.add(new Wand(5960, 600, 40, 10, handler));
		waende.add(new Wand(5700, 400, 40, 10, handler));
		waende.add(new Wand(5960, 200, 40, 10, handler));
		waende.add(new Wand(5700, 0, 40, 10, handler));
		waende.add(new Wand(5960, -200, 40, 10, handler));
		waende.add(new Wand(5700, -400, 40, 10, handler));

		waende.add(new Wand(4800, -600, 500, 20, handler));
		waende.add(new Wand(4100, -700, 500, 20, handler));
		waende.add(new Wand(3300, 0, 500, 50, handler));

		items.add(new Item(3385, -30, handler, 0));
		items.add(new Item(3485, -30, handler, 0));
		items.add(new Item(3585, -30, handler, 0));

		// Secret Ende

		// Rewardraum

		waende.add(new Wand(7000, 500, 1000, 500, handler));
		waende.add(new Wand(9000, 500, 1000, 500, handler));
		waende.add(new Wand(7000, -500, 1000, 1000, handler));
		waende.add(new Wand(8000, -500, 1000, 1000, handler));
		waende.add(new Wand(8000, 1000, 1000, 1000, handler));
		waende.add(new Wand(9000, -500, 1000, 1000, handler));
		waende.add(new Wand(7000, 1000, 1000, 1000, handler));
		waende.add(new Wand(9000, 1000, 1000, 1000, handler));

		waende.add(new Wand(8350, 980, 300, 20, handler));

		items.add(new Item(8493, 930, handler, 2));

		Door door6 = new Door(5850, 900, 100, 100, handler);
		Door door7 = new Door(8050, 900, 100, 100, handler);
		door6.connectExit(door7);
		tueren.add(door7);
		tueren.add(door6);

		// Rewardraum Ende
	}

	public static void generateRoom1(ObjectHandler handler) {
		// Ebene 1 Generierung

		// Grenzen: 0, 3000, 6000, 5000

		// Debug

		items.add(new Item(200, 4900, handler, 1));

		// Debug Ende

		// Verbindung

		Door door4 = new Door(8850, 900, 100, 100, handler);
		Door door5 = new Door(50, 4900, 100, 100, handler);
		door4.connectExit(door5);
		door5.connectExit(door4);
		tueren.add(door5);
		tueren.add(door4);

		// Verbindung Ende

		// Umriss

		waende.add(new Wand(0, 5000, 1000, 1000, handler));
		waende.add(new Wand(1000, 5000, 1000, 1000, handler));
		waende.add(new Wand(-1000, 5000, 1000, 1000, handler));
		waende.add(new Wand(-1000, 4000, 1000, 1000, handler));
		waende.add(new Wand(-1000, 3000, 1000, 1000, handler));
		waende.add(new Wand(-1000, 2000, 1000, 1000, handler));
		waende.add(new Wand(0, 2000, 1000, 1000, handler));
		waende.add(new Wand(1000, 2000, 1000, 1000, handler));

		for (int i = 0; i < 5; i++) {
			waende.add(new Wand(2000 + 1000 * i, 5000, 1000, 1000, handler));
			waende.add(new Wand(2000 + 1000 * i, 2000, 1000, 1000, handler));
		}

		waende.add(new Wand(6000, 4000, 1000, 1000, handler));
		waende.add(new Wand(6000, 3000, 1000, 1000, handler));

		// Umriss Ende

		// Innen

		waende.add(new Wand(0, 4830, 800, 20, handler));
		waende.add(new Wand(780, 4700, 20, 130, handler));
		waende.add(new Wand(1000, 4000, 100, 1000, handler));
		waende.add(new Wand(1000, 3700, 100, 300, handler));
		waende.add(new Wand(950, 4900, 50, 100, handler));

		enemies.add(new Enemy("chonker", 780, 4860, handler));

		enemies.add(new Enemy("gollumneutral", 50, 4600, handler));
		enemies.add(new Enemy("gollumneutral", 150, 4600, handler));
		enemies.add(new Enemy("gollumneutral", 250, 4600, handler));
		enemies.add(new Enemy("gollumneutral", 350, 4600, handler));
		enemies.add(new Enemy("gollumneutral", 450, 4600, handler));
		enemies.add(new Enemy("gollumneutral", 550, 4600, handler));

		waende.add(new Wand(600, 4550, 100, 20, handler));
		waende.add(new Wand(400, 4450, 100, 20, handler));
		waende.add(new Wand(200, 4350, 100, 20, handler));
		waende.add(new Wand(0, 4250, 100, 20, handler));
		waende.add(new Wand(100, 4050, 100, 20, handler));

		waende.add(new Wand(0, 3850, 100, 20, handler));
		waende.add(new Wand(200, 3700, 900, 20, handler));

		waende.add(new Wand(800, 4330, 200, 20, handler));

		items.add(new Item(950, 4300, handler, 0));

		waende.add(new Wand(1300, 3900, 100, 20, handler));

		waende.add(new Wand(1300, 4200, 37, 10, handler));
		waende.add(new Wand(1300, 4199, 1, 1, handler));
		waende.add(new Wand(1336, 4199, 1, 1, handler));
		enemies.add(new Enemy("gollumneutral", 1301, 4165, handler));

		waende.add(new Wand(1300, 4600, 37, 10, handler));
		waende.add(new Wand(1300, 4599, 1, 1, handler));
		waende.add(new Wand(1336, 4599, 1, 1, handler));
		enemies.add(new Enemy("gollumneutral", 1301, 4565, handler));

		waende.add(new Wand(1700, 4600, 100, 20, handler));
		waende.add(new Wand(1600, 4800, 100, 20, handler));
		waende.add(new Wand(2000, 4500, 100, 20, handler));

		waende.add(new Wand(2300, 4600, 37, 10, handler));
		waende.add(new Wand(2300, 4599, 1, 1, handler));
		waende.add(new Wand(2336, 4599, 1, 1, handler));
		enemies.add(new Enemy("gollumneutral", 2301, 4565, handler));

		waende.add(new Wand(2200, 4300, 100, 20, handler));

		waende.add(new Wand(2500, 4100, 100, 20, handler));

		waende.add(new Wand(2800, 4200, 37, 10, handler));
		waende.add(new Wand(2800, 4199, 1, 1, handler));
		waende.add(new Wand(2836, 4199, 1, 1, handler));
		enemies.add(new Enemy("gollumneutral", 2801, 4165, handler));

		waende.add(new Wand(2800, 3900, 100, 20, handler));

		waende.add(new Wand(3100, 3700, 100, 20, handler));

		waende.add(new Wand(3300, 4200, 37, 10, handler));
		waende.add(new Wand(3300, 4199, 1, 1, handler));
		waende.add(new Wand(3336, 4199, 1, 1, handler));
		enemies.add(new Enemy("gollumneutral", 3301, 4165, handler));

		waende.add(new Wand(3300, 4400, 37, 10, handler));
		waende.add(new Wand(3300, 4399, 1, 1, handler));
		waende.add(new Wand(3336, 4399, 1, 1, handler));
		enemies.add(new Enemy("gollumneutral", 3301, 4365, handler));

		waende.add(new Wand(3600, 4500, 300, 20, handler));

		waende.add(new Wand(4000, 4300, 100, 20, handler));
		waende.add(new Wand(4300, 4200, 400, 800, handler));

		waende.add(new Wand(5800, 4700, 200, 300, handler));
		waende.add(new Wand(5500, 4850, 200, 20, handler));

		enemies.add(new Enemy("chonker", 4720, 4800, handler));
		enemies.add(new Enemy("chonker", 4770, 4800, handler));
		enemies.add(new Enemy("chonker", 4840, 4800, handler));

		// Innen Ende

		// Rewardraum

		waende.add(new Wand(7000, 4500, 1000, 500, handler));
		waende.add(new Wand(9000, 4500, 1000, 500, handler));
		waende.add(new Wand(7000, 3500, 1000, 1000, handler));
		waende.add(new Wand(8000, 3500, 1000, 1000, handler));
		waende.add(new Wand(8000, 5000, 1000, 1000, handler));
		waende.add(new Wand(9000, 3500, 1000, 1000, handler));
		waende.add(new Wand(7000, 5000, 1000, 1000, handler));
		waende.add(new Wand(9000, 5000, 1000, 1000, handler));

		waende.add(new Wand(8350, 4980, 300, 20, handler));

		items.add(new Item(8493, 4930, handler, 1));

		Door door8 = new Door(5850, 4600, 100, 100, handler);
		Door door9 = new Door(8050, 4900, 100, 100, handler);
		door8.connectExit(door9);
		tueren.add(door8);
		tueren.add(door9);

		// Rewardraum Ende
	}

	public static void generateRoom2(ObjectHandler handler) {
		// Ebene 2 Generierung

		// Grenze: 0, 7000, 6000, 9000

		// Verbindung

		Door door10 = new Door(8850, 4900, 100, 100, handler);
		Door door11 = new Door(50, 8900, 100, 100, handler);
		door10.connectExit(door11);
		door11.connectExit(door10);
		tueren.add(door10);
		tueren.add(door11);

		// Verbindung Ende

		// Umriss

		waende.add(new Wand(0, 9000, 1000, 1000, handler));
		waende.add(new Wand(1000, 9000, 1000, 1000, handler));
		waende.add(new Wand(-1000, 9000, 1000, 1000, handler));
		waende.add(new Wand(-1000, 8000, 1000, 1000, handler));
		waende.add(new Wand(-1000, 7000, 1000, 1000, handler));
		waende.add(new Wand(-1000, 6000, 1000, 1000, handler));
		waende.add(new Wand(0, 6000, 1000, 1000, handler));
		waende.add(new Wand(1000, 6000, 1000, 1000, handler));

		for (int i = 0; i < 5; i++) {
			waende.add(new Wand(2000 + 1000 * i, 9000, 1000, 1000, handler));
			waende.add(new Wand(2000 + 1000 * i, 6000, 1000, 1000, handler));
		}

		waende.add(new Wand(6000, 8000, 1000, 1000, handler));
		waende.add(new Wand(6000, 7000, 1000, 1000, handler));

		// Umriss Ende

		// Innen

		// Innen Ende

		// Rewardraum

		waende.add(new Wand(7000, 8500, 1000, 500, handler));
		waende.add(new Wand(9000, 8500, 1000, 500, handler));
		waende.add(new Wand(7000, 7500, 1000, 1000, handler));
		waende.add(new Wand(8000, 7500, 1000, 1000, handler));
		waende.add(new Wand(8000, 9000, 1000, 1000, handler));
		waende.add(new Wand(9000, 7500, 1000, 1000, handler));
		waende.add(new Wand(7000, 9000, 1000, 1000, handler));
		waende.add(new Wand(9000, 9000, 1000, 1000, handler));

		waende.add(new Wand(8350, 8980, 300, 20, handler));

		items.add(new Item(8493, 8930, handler, 1));

		Door door12 = new Door(5850, 8900, 100, 100, handler);
		Door door13 = new Door(8050, 8900, 100, 100, handler);
		door12.connectExit(door13);
		tueren.add(door12);
		tueren.add(door13);

		// Rewardraum Ende
	}

	public static void generateRoom3(ObjectHandler handler) {
		// Ebene 3 Generierung

		// Grenze: 0, 11000, 6000, 13000

		// Verbindung

		Door door14 = new Door(8850, 8900, 100, 100, handler);
		Door door15 = new Door(50, 12900, 100, 100, handler);
		door14.connectExit(door15);
		door15.connectExit(door14);
		tueren.add(door14);
		tueren.add(door15);

		// Verbindung Ende

		// Umriss

		waende.add(new Wand(0, 13000, 1000, 1000, handler));
		waende.add(new Wand(1000, 13000, 1000, 1000, handler));
		waende.add(new Wand(-1000, 13000, 1000, 1000, handler));
		waende.add(new Wand(-1000, 12000, 1000, 1000, handler));
		waende.add(new Wand(-1000, 11000, 1000, 1000, handler));
		waende.add(new Wand(-1000, 10000, 1000, 1000, handler));
		waende.add(new Wand(0, 10000, 1000, 1000, handler));
		waende.add(new Wand(1000, 10000, 1000, 1000, handler));

		for (int i = 0; i < 5; i++) {
			waende.add(new Wand(2000 + 1000 * i, 13000, 1000, 1000, handler));
			waende.add(new Wand(2000 + 1000 * i, 10000, 1000, 1000, handler));
		}

		waende.add(new Wand(6000, 12000, 1000, 1000, handler));
		waende.add(new Wand(6000, 11000, 1000, 1000, handler));

		// Umriss Ende

		// Innen
		
		

		// Innen Ende
	}

	public static void generateBossRoom(ObjectHandler handler) {
		// Bossebene Generierung
		
		// Grenzen: 0, 16020, 1720, 17000
		
		// Verbindung
		
		Door door18 = new Door(5850, 12900, 100, 100, handler);
		Door door19 = new Door(50, 16900, 100, 100, handler);
		door18.connectExit(door19);
		tueren.add(door18);
		tueren.add(door19);
		
		// Verbindung Ende

		// Umriss
		
		waende.add(new Wand(0, 17000, 1000, 1000, handler));
		waende.add(new Wand(1000, 17000, 720, 1000, handler));
		waende.add(new Wand(-1000, 17000, 1000, 1000, handler));
		waende.add(new Wand(-1000, 16020, 1000, 980, handler));
		waende.add(new Wand(-1000, 15020, 1000, 1000, handler));
		waende.add(new Wand(0, 15020, 1000, 1000, handler));
		waende.add(new Wand(1000, 15020, 720, 1000, handler));
		waende.add(new Wand(1720, 15020, 1000, 1000, handler));
		waende.add(new Wand(1720, 16020, 1000, 1000, handler));
		waende.add(new Wand(1720, 17000, 1000, 1000, handler));
		
		// Umriss Ende
		
		// Innen
		
		waende.add(new Wand(0, 16780, 100, 20, handler));
		
		waende.add(new Wand(0, 16220, 50, 20, handler));
		items.add(new Item(10, 16170, handler, 0));
		
		waende.add(new Wand(0, 16420, 50, 20, handler));
		items.add(new Item(10, 16370, handler, 0)); 
		
		waende.add(new Wand(200, 16600, 100, 20, handler));
		
		waende.add(new Wand(750, 16700, 200, 20, handler));
		waende.add(new Wand(550, 16850, 50, 20, handler));
		waende.add(new Wand(1050, 16500, 10, 100, handler));
		
		// Innen Ende
	}
}