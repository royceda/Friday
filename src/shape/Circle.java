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

	protected double x, y, radius;

	public Circle() {}
	public Circle(double x2, double y2) {
		x = x2;
		y = y2;
	}
	
	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public double getX() {
		return x;
	}

	public void setX(double x2) {
		this.x = x2;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
}
