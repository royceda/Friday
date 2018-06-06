package shape;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.color.*;


/**
 * Object Circle
 * @author Da
 *
 */
public class Circle {

	private int x, y, radius;

	public Circle() {}
	public Circle(int x2, int y2) {
		x = x2;
		y = y2;
	}
	
	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}
