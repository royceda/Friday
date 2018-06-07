package shape;

public class Point extends Circle {
	private boolean selected;
	
	public Point() {
		super();
		this.setRadius(10);
		setSelected(false);
	}
	
	public Point(int x2, int y2) {
		super(x2, y2);
		setSelected(false);
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
}
