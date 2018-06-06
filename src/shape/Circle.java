package shape;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.color.*;



public class Circle extends JPanel {

	private int x, y, radius;

	public Circle() {
		
	}
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

	public void paint(Graphics g) {
		setSize(800, 800);

		//g.drawOval(100, 100, 50, 500);
		g.setColor(Color.BLACK);


		for(int x=0; x<800; x+=40) {
			for(int y=0; y<800; y+=40) {
				g.fillOval(x, y, 10, 10);
			}
		}

		g.setColor(Color.BLUE);
		g.drawRect(150, 30, 50, 10);

	}

}
