
public class Vector2 { // Mathematischer Vektor mit Standard komponentenweiser Multiplikation und
						// Addition ueber den "Reellen" Zahlen

	public float x, y;

	public Vector2(float x, float y) { // 2d Vector klasse
		this.x = x;
		this.y = y;
	}

	public void zero() {
		x = 0;
		y = 0;
	}

	public void norm() {
		x = x / this.getLength();
	}

	public float getLength() { // euklidische Norm
		return (float) Math.sqrt(x * x + y * y);
	}

	public static Vector2 add(Vector2 a, Vector2 b) {
		return new Vector2(a.x + b.x, a.y + b.y);
	}

	public static Vector2 subtract(Vector2 a, Vector2 b) {
		return new Vector2(a.x - b.x, a.y - b.y);
	}

	public static Vector2 getPos(GameObject g) {
		return new Vector2(g.getX(), g.getY());
	}

	public void set(Vector2 a) {
		this.x = a.x;
		this.y = a.y;
	}

	public void set(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public static Vector2 directionalvector(Vector2 v1, Vector2 v2) { // Gibt einen Vektor der Einheitsgroesse in
																		// Richtung von v1 nach v2 zurueck
		Vector2 directionalvector = new Vector2(v1.x - v2.x, v1.y - v2.y);
		directionalvector.norm();
		return directionalvector;
	}

	public void scale(int lambda) { // Komponentenweise Multiplikation mit einem Skalar
		this.x = lambda * x;
		this.y = lambda * y;
	}

	public float times(Vector2 a) { // Standard Kreuzprodukt
		return this.x * a.x + this.y * a.y;
	}
}