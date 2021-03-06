
public class Camera {

	ObjectHandler handler;
	Player player;

	private int xPos, yPos = 0;

	private int frameXSize = 1300;
	private int frameYSize = 700;

	GameObject object;

	public Camera(ObjectHandler handler, Player player) {

		this.handler = handler;
		this.player = player;

	}

	public boolean isInScreen(GameObject obj) {
		return false;
	}

	public void tick() {
		
		xPos = player.getPos('x') - (frameXSize / 2) + (player.getSize('x') / 2);
		yPos = player.getPos('y') - (frameYSize / 2) + (player.getSize('y') / 2);
		
		for(int i = 0; i < handler.objects.size(); i++) {
			if(!(handler.objects.get(i) instanceof Player)) {
				object = handler.objects.get(i);
				object.setBounds(object.getPos('x') - xPos, object.getPos('y') - yPos, object.getSize('x'), object.getSize('y'));
				System.out.println(player.getPos('x'));
			}
		}
		
	}
}
