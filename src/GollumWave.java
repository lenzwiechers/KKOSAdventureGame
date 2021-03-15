
public class GollumWave extends GameObject {

	private static final long serialVersionUID = -3240563349601296826L;
	
	private boolean right = true;
	
	private ObjectHandler handler;

	public GollumWave(ObjectHandler handler, int posX, int posY, boolean right) {
		super("gollumWave", handler);
		
		if(!right) {
			changeName("gollumWave"); // sp�ter mal die invertete Version hier rein
			this.right = false;
		}
		velX = 0.0000004f;
		
		this.posX = posX;
		this.posY = posY;
		
		this.width = 30;
		this.height = 30;
		
		this.handler = handler;
	}
	
	public void tick(long dt) {
		
		if(right) {
			posX += velX * dt;
		} else {
			posX -= velX * dt;
		}
		
		if(wallCollision()) {
			handler.removeObject(this);
		}
		
	}

}
