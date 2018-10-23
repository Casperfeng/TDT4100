package stateandbehavior;

public class Rectangle {
	private int minX;
	private int minY;
	private int maxX;
	private int maxY;
	int width;
	int height;
	int x;
	int y;
	
	
	
	public Rectangle() {
		this.minX = 0;
		this.minY = 0;
		this.maxX = 0;
		this.maxY = 0;
	}
	
	public int getMinX() {
		return this.minX;
	}
	
	public int getMinY() {
		return this.minY;
	}
	
	public int getMaxX() {
		return this.maxX;
	}
	
	public int getMaxY() {
		return this.maxY;
	}
	
	public int getWidth() {
		return Math.abs((Integer)(this.minX - this.maxX));
	}
	
	public int getHeight() {
		return Math.abs((Integer)(this.minY - this.maxY));
	}
	
	public boolean isEmpty() {
		if (getHeight()!=0 && getWidth()!=0) {
			return false;
		} 
		else {
			return true;
		}	
	}
	
	public boolean contains(int x, int y) {
		if (x<this.minX || x>this.maxX || y<this.minY || y>this.maxY) {
			return false;
		} 
		else {
			return true;	
		}
	}
	
	public boolean contains(Rectangle rect) {
		if (this.minX>rect.minX || this.maxX<rect.maxX || this.minY>rect.minY || this.maxY<rect.maxY) {
			return false;
		}
		else if (rect.isEmpty()) {
			return false;
		}
		else {
			return true;	
		}
		
	}
	
	public boolean add(int x, int y) {
			boolean change = true;
			if (this.minX>x) {
				this.minX=x;
			}
			if (this.maxX<x) {
				this.maxX = x;
			}
			if (this.minY>y) {
				this.minY = y;
			}
			if (this.maxY<y) {
				this.maxY = y;
			}
			else {
				return false;
			}
			return change;

	}
	
	public boolean add(Rectangle rect) {
		if (contains(rect) && !(isEmpty())) {
			return false;
		} else {
			add(rect.minX,rect.minY);
			add(rect.maxX,rect.maxY);
			return true;
		}
		
	}
	
	
	
	





}
