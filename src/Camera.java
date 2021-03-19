
public class Camera {

	static ObjectHandler handler;
	static Player player;

	public static int xPos;
	public static int yPos;

	public static int frameXSize;
	public static int frameYSize;

	static GameObject object;

	public Camera(ObjectHandler handler, Player player, int frameXSize, int frameYSize) {

		Camera.handler = handler;
		Camera.player = player;

		Camera.frameXSize = frameXSize;
		Camera.frameYSize = frameYSize;

	}

	public boolean isInScreen(GameObject obj) {
		//Checkt ob ein Objekt sich in den Bounds des Bildschirms befindet

		if (obj.getPos('x') + obj.getSize('x') + 50 >= xPos && obj.getPos('x') <= xPos + frameXSize + 50
				&& obj.getPos('y') + obj.getSize('y') + 50 >= yPos && obj.getPos('y') <= yPos + frameYSize + 50) {
			return true;
		}

		return false;
	}

	public void tick(boolean bossroom) {

		if (!bossroom) { // Falls der Player sich nicht im Bossraum befindet (Dort wird es anders gerendert)
			xPos = player.getPos('x') - (frameXSize / 2) + (player.getSize('x') / 2);
			yPos = player.getPos('y') - (frameYSize / 2) + (player.getSize('y') / 2);
			
			handler.player.get(0).setBounds(frameXSize / 2 - player.getSize('x') / 2,
					frameYSize / 2 - player.getSize('y') / 2, player.getSize('x'), player.getSize('y'));
		} else {
			xPos = -500;
			yPos = 15720;
			
			handler.player.get(0).setBounds(handler.player.get(0).getPos('x') - xPos,
					handler.player.get(0).getPos('y') - yPos, handler.player.get(0).getSize('x'),
					handler.player.get(0).getSize('y'));
		}
		for (int i = 0; i < handler.objects.size(); i++) {
			//Alle objekte die sich innerhalb des Bildschirms befinden, werden gerendert
			object = handler.objects.get(i);
			if (!(object instanceof Player) && isInScreen(object)) {
				object.setBounds(object.getPos('x') - xPos, object.getPos('y') - yPos, object.getSize('x'),
						object.getSize('y'));
			}
		}
		
	}

	public static void renderAll() {
		//Rendert alle Objekte
		//Wird verwendet um Objekte, welche sich nun nicht mehr im screen befinden, zu rendern, um sie vom Bildschirm zu lösen weil sie nicht entfernt werden
		xPos = player.getPos('x') - (frameXSize / 2) + (player.getSize('x') / 2);
		yPos = player.getPos('y') - (frameYSize / 2) + (player.getSize('y') / 2);
		for (int i = 0; i < handler.objects.size(); i++) {
			object = handler.objects.get(i);
			if (!(object instanceof Player)) {
				object.setBounds(object.getPos('x') - xPos, object.getPos('y') - yPos, object.getSize('x'),
						object.getSize('y'));
			}
		}
	}
}
