import java.awt.Color;
import java.util.LinkedList;

import javax.swing.JLabel;

public abstract class generateMap {

	static LinkedList<Wand> waende = new LinkedList<Wand>();
	static LinkedList<Door> tueren = new LinkedList<Door>();
	static LinkedList<Item> items = new LinkedList<Item>();
	static LinkedList<Enemy> enemies = new LinkedList<Enemy>();
	static LinkedList<JLabel> tutorialLabels = new LinkedList<JLabel>();

	public static void generate(ObjectHandler handler) {
		generateStartRoom(handler);

		generateRoom1(handler);

		generateRoom2(handler);

		generateRoom3(handler);

		generateBossRoom(handler);

		generateTestRoom(handler);

		for (int i = 0; i < tutorialLabels.size(); i++) {
			handler.addObject(tutorialLabels.get(i));
		}
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

		enemies.add(new Enemy("direktorin", 500, -2000, handler));
	}

	public static void generateStartRoom(ObjectHandler handler) {
		// Startraum Generierung

		// Grenzen: 0, -1000, 6000, 1000

		tutorialLabels.add(new TutorialLabel(600, 600, 400, 400,
				"<html><body>HELLO! MOVE YOUR PLAYER WITH THE 'A', AND 'D' KEYS, AND JUMP WITH SPACE!</body></html>",
				Color.WHITE));

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
		items.add(new Item(3485, -30, handler, 1));
		items.add(new Item(3585, -30, handler, 2));
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

		waende.add(new Wand(1300, 4400, 37, 10, handler));
		waende.add(new Wand(1300, 4399, 1, 1, handler));
		waende.add(new Wand(1336, 4399, 1, 1, handler));
		enemies.add(new Enemy("gollumneutral", 1301, 4365, handler));

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

		waende.add(new Wand(2600, 4400, 37, 10, handler));
		waende.add(new Wand(2600, 4399, 1, 1, handler));
		waende.add(new Wand(2636, 4399, 1, 1, handler));
		enemies.add(new Enemy("gollumneutral", 2601, 4365, handler));

		waende.add(new Wand(2500, 4100, 100, 20, handler));

		waende.add(new Wand(2800, 4200, 37, 10, handler));
		waende.add(new Wand(2800, 4199, 1, 1, handler));
		waende.add(new Wand(2836, 4199, 1, 1, handler));
		enemies.add(new Enemy("gollumneutral", 2801, 4165, handler));

		waende.add(new Wand(2800, 3900, 100, 20, handler));

		waende.add(new Wand(3100, 4000, 37, 10, handler));
		waende.add(new Wand(3100, 3999, 1, 1, handler));
		waende.add(new Wand(3136, 3999, 1, 1, handler));
		enemies.add(new Enemy("gollumneutral", 3101, 3965, handler));

		waende.add(new Wand(3100, 3700, 100, 20, handler));

		waende.add(new Wand(3300, 4200, 37, 10, handler));
		waende.add(new Wand(3300, 4199, 1, 1, handler));
		waende.add(new Wand(3336, 4199, 1, 1, handler));
		enemies.add(new Enemy("gollumneutral", 3301, 4165, handler));

		waende.add(new Wand(4100, 4500, 37, 10, handler));
		waende.add(new Wand(4100, 4499, 1, 1, handler));
		waende.add(new Wand(4136, 4499, 1, 1, handler));
		enemies.add(new Enemy("gollumneutral", 4101, 4465, handler));

		waende.add(new Wand(3300, 4400, 37, 10, handler));
		waende.add(new Wand(3300, 4399, 1, 1, handler));
		waende.add(new Wand(3336, 4399, 1, 1, handler));
		enemies.add(new Enemy("gollumneutral", 3301, 4365, handler));

		waende.add(new Wand(3300, 4600, 37, 10, handler));
		waende.add(new Wand(3300, 4599, 1, 1, handler));
		waende.add(new Wand(3336, 4599, 1, 1, handler));
		enemies.add(new Enemy("gollumneutral", 3301, 4565, handler));

		waende.add(new Wand(3600, 4500, 300, 20, handler));

		waende.add(new Wand(4000, 4300, 100, 20, handler));
		waende.add(new Wand(4300, 4200, 400, 800, handler));

		/*
		 * waende.add(new Wand(2000, 4480, 100, 20, handler));
		 * 
		 * enemies.add(new Enemy("gollumneutral", 2010, 4400, handler)); enemies.add(new
		 * Enemy("gollumneutral", 2020, 4400, handler)); enemies.add(new
		 * Enemy("gollumneutral", 2030, 4400, handler)); enemies.add(new
		 * Enemy("gollumneutral", 2040, 4400, handler)); enemies.add(new
		 * Enemy("gollumneutral", 2050, 4400, handler)); enemies.add(new
		 * Enemy("gollumneutral", 2060, 4400, handler)); enemies.add(new
		 * Enemy("gollumneutral", 2070, 4400, handler)); enemies.add(new
		 * Enemy("gollumneutral", 2080, 4400, handler)); enemies.add(new
		 * Enemy("gollumneutral", 2090, 4400, handler)); enemies.add(new
		 * Enemy("gollumneutral", 2000, 4400, handler));
		 * 
		 * waende.add(new Wand(3000, 4480, 100, 20, handler));
		 * 
		 * enemies.add(new Enemy("gollumneutral", 3010, 4400, handler)); enemies.add(new
		 * Enemy("gollumneutral", 3020, 4400, handler)); enemies.add(new
		 * Enemy("gollumneutral", 3030, 4400, handler)); enemies.add(new
		 * Enemy("gollumneutral", 3040, 4400, handler)); enemies.add(new
		 * Enemy("gollumneutral", 3050, 4400, handler)); enemies.add(new
		 * Enemy("gollumneutral", 3060, 4400, handler)); enemies.add(new
		 * Enemy("gollumneutral", 3070, 4400, handler)); enemies.add(new
		 * Enemy("gollumneutral", 3080, 4400, handler)); enemies.add(new
		 * Enemy("gollumneutral", 3090, 4400, handler)); enemies.add(new
		 * Enemy("gollumneutral", 3000, 4400, handler));
		 * 
		 * 
		 * waende.add(new Wand(4000, 4480, 100, 20, handler));
		 * 
		 * enemies.add(new Enemy("gollumneutral", 4010, 4400, handler)); enemies.add(new
		 * Enemy("gollumneutral", 4020, 4400, handler)); enemies.add(new
		 * Enemy("gollumneutral", 4030, 4400, handler)); enemies.add(new
		 * Enemy("gollumneutral", 4040, 4400, handler)); enemies.add(new
		 * Enemy("gollumneutral", 4050, 4400, handler)); enemies.add(new
		 * Enemy("gollumneutral", 4060, 4400, handler)); enemies.add(new
		 * Enemy("gollumneutral", 4070, 4400, handler)); enemies.add(new
		 * Enemy("gollumneutral", 4080, 4400, handler)); enemies.add(new
		 * Enemy("gollumneutral", 4090, 4400, handler)); enemies.add(new
		 * Enemy("gollumneutral", 4000, 4400, handler));
		 * 
		 * 
		 * waende.add(new Wand(5000, 4480, 100, 20, handler));
		 * 
		 * enemies.add(new Enemy("gollumneutral", 5010, 4400, handler)); enemies.add(new
		 * Enemy("gollumneutral", 5020, 4400, handler)); enemies.add(new
		 * Enemy("gollumneutral", 5030, 4400, handler)); enemies.add(new
		 * Enemy("gollumneutral", 5040, 4400, handler)); enemies.add(new
		 * Enemy("gollumneutral", 5050, 4400, handler)); enemies.add(new
		 * Enemy("gollumneutral", 5060, 4400, handler)); enemies.add(new
		 * Enemy("gollumneutral", 5070, 4400, handler)); enemies.add(new
		 * Enemy("gollumneutral", 5080, 4400, handler)); enemies.add(new
		 * Enemy("gollumneutral", 5090, 4400, handler)); enemies.add(new
		 * Enemy("gollumneutral", 5000, 4400, handler));
		 */
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