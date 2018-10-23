package stateandbehavior;

public class Location {
	
	private int x;
	private int y;
	
	public Location() {
		this.x = 0;
		this.y = 0;
	}

	public Location(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void up() {
		this.y--;
	}
	
	public void down() {
		this.y++;
	}
	
	public void left() {
		this.x--;
	}
	
	public void right() {
		this.x++;
	}
	
	public int getY() {
		return this.y;
	}
	
	public int getX() {
		return this.x;
	}
	
	public String toString() {
		return ("x: " + this.x + " y: " + this.y);
	}
	
	
}
