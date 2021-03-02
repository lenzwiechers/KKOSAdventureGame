import java.util.LinkedList;

public class ObjectHandler {
	
	private Player player;
	
	LinkedList<GameObject> objects = new LinkedList<GameObject>();
	
	LinkedList<Wand> wände = new LinkedList<Wand>();
	
	public ObjectHandler() {
		
		super();
		
	}
	
	public void renderAll() {
		for(int i = 0; i < objects.size(); i++) {
			objects.get(i).render();
		}
	}
	
	public void tick() {
		for(int i = 0; i < objects.size(); i++) {
			objects.get(i).tick();
		}
	}
	
	public void tick(GameObject obj, long dt) {
		/*
		 * if(obj instanceof Player) {
		 
			player = (Player) obj;
			if(player.left) {
				player.setPos('x', player.getPos('x') - (int) (player.velX*dt));
			} else if(player.right) {
				player.setPos('x', player.getPos('x') + (int) (player.velX*dt));
			}
			if(player.down) {
				player.setPos('y', player.getPos('y') + (int) (player.velY*dt));
			}
			
			// System.out.println(player.velY*dt);
			player.tick();
		} else {
			
		}
		*/
	}
	
	public void addObject(GameObject obj) {
		objects.add(obj);
		if(obj instanceof Wand) {
			wände.add((Wand) obj);
		}
	}
}
