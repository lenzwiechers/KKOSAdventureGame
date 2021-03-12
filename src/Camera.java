
public class Camera {

	static ObjectHandler handler;
	static Player player;

	private static int xPos;
	private static int yPos = 0;

	private static int frameXSize;
	private static int frameYSize;

	static GameObject object;

	public Camera(ObjectHandler handler, Player player, int frameXSize, int frameYSize) {

		this.handler = handler;
		this.player = player;

		this.frameXSize = frameXSize;
		this.frameYSize = frameYSize;

	}

	public boolean isInScreen(GameObject obj) {

		if (obj.getPos('x') + obj.getSize('x') + 50 >= xPos && obj.getPos('x') <= xPos + frameXSize + 50
				&& obj.getPos('y') + obj.getSize('y') + 50 >= yPos && obj.getPos('y') <= yPos + frameYSize + 50) {
			return true;
		}

		return false;
	}

	public void tick() {

		xPos = player.getPos('x') - (frameXSize / 2) + (player.getSize('x') / 2);
		yPos = player.getPos('y') - (frameYSize / 2) + (player.getSize('y') / 2);

		for (int i = 0; i < handler.objects.size(); i++) {
			object = handler.objects.get(i);
			if (!(object instanceof Player) && isInScreen(object)) {
				object.setBounds(object.getPos('x') - xPos, object.getPos('y') - yPos, object.getSize('x'),
						object.getSize('y'));
			}
		}
	}
	
	public static void renderAll() {
		xPos = player.getPos('x') - (frameXSize / 2) + (player.getSize('x') / 2);
		yPos = player.getPos('y') - (frameYSize / 2) + (player.getSize('y') / 2);
		for(int i = 0; i < handler.objects.size(); i++) {
			object = handler.objects.get(i);
			if(!(object instanceof Player)) {
				object.setBounds(object.getPos('x') - xPos, object.getPos('y') - yPos, object.getSize('x'), object.getSize('y'));
			}
		}
	}
}
