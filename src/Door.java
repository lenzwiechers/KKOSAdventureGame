

public class Door extends GameObject{
	public int x, y;
	public int width, height;
	
	
	public Door(ObjectHandler handler) {
		super("Door", handler);
	}
	
	
	public Door(int x, int y, int width, int height, ObjectHandler handler) {
		super("Door", handler);
		
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		
	}
}
