package shape;


public class Rectangle{
	private int x;
	private int y;
	private int width;
	private int height;
	
	public Rectangle(int x1, int y1, int width1, int height1) {
		x = x1;
		y = y1;
		width = width1;
		height = height1;
	}
	
	public void setX(int x2) {
		x = x2;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public void setWidth(int d) {
		width = d;
	}
	
	
	public void setHeight(int d) {
		height = d;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getWidth() {
		return width;
	}
}
