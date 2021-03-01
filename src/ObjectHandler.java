import java.util.LinkedList;

public class ObjectHandler {
	
	private Player player;
	
	private LinkedList<GameObject> objects = new LinkedList<GameObject>();
	
	public ObjectHandler() {
		
		super();
		
	}
	
	public void renderAll() {
		for(int i = 0; i < objects.size(); i++) {
			objects.get(i).render();
		}
	}
	
	public void tick(GameObject obj, long dt) {
		if(obj instanceof Player) {
			player = (Player) obj;
			if(player.left) {
				obj.setPos('x', player.getPos('x') - (int) (player.velX*dt));
			} else if(player.right) {
				player.setPos('x', player.getPos('x') + (int) (player.velX*dt));
			}
		} else {
			
		}
	}
	
	public void addObject(GameObject obj) {
		objects.add(obj);
	}
	
}
