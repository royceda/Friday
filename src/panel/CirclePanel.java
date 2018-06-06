package panel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import shape.Circle;

public class CirclePanel extends JPanel implements MouseListener, MouseMotionListener {

	Circle circle = null;
	int x2, y2, epsilon;
	Point initialPoint = null;

	List<Circle> points;
	
	public CirclePanel() {
		super();
		
		x2 = -100;
		y2 = -100;
		
		points = new ArrayList<Circle>();
		
		for(int x=0; x<800; x+=40) {
			for(int y=0; y<800; y+=40) {
				points.add(new Circle(x, y));
			}
		}
	}

	
	private double distance(Circle p1, Circle p2) {
		double d = Math.sqrt(Math.pow((p1.getX() - p2.getX()), 2) + Math.pow((p1.getY() - p2.getY()), 2)) ;
		//System.out.println("Distance : "+d);
		return d;
	}
	
	
	
	
	private void plotPoints(Graphics g) {
		Circle p1 = new Circle(10, 13);
		Circle p2 = new Circle(25, 30);
		
		System.out.println("D = " + distance(p1, p2));
		
		for(Circle p: points) {
			
			//System.out.println(p.getX()+" , "+p.getY());
			
			double d = 0.0;
			if(circle != null) {
				d = distance(p, circle);
			}
			
			if(circle!=null && d <= circle.getRadius() + epsilon  && d >= circle.getRadius() - epsilon ) {
				g.setColor(Color.BLACK);
				g.fillOval(p.getX(), p.getY(), 10, 10);
			} else {
				g.setColor(Color.WHITE);
				g.fillOval(p.getX(), p.getY(), 10, 10);
			}
			
			
		}
	}
	
	
	
	@Override
	public void mouseDragged(MouseEvent arg0) {

		System.out.println("Dragged "+arg0.getX()+" , "+arg0.getY());
		x2 = arg0.getX();
		y2 = arg0.getY();

		circle.setRadius(Math.abs(x2 - circle.getX())); 
		repaint();
	}


	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent mEvt) {
		System.out.println("Pressed");
		initialPoint = mEvt.getPoint();

		repaint();
		Point toto = mEvt.getPoint();

		circle = new Circle();

	
		System.out.println("Pressed "+toto.getX()+" , "+toto.getY());

		int radius = 10;
		circle.setX(mEvt.getX()-radius);
		circle.setY(mEvt.getY()-radius);

		repaint();
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		System.out.println("Released "+arg0.getX()+" , "+arg0.getY());
		x2 = arg0.getX();
		y2 = arg0.getY();

		circle.setRadius(Math.abs(x2 - circle.getX())); 
		repaint();	
	}

	@Override
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		
		plotPoints(g);
		
		if (circle != null) {
			Graphics2D g2 = (Graphics2D) g;
			
			epsilon = 30;
			
			int r2 = circle.getRadius() + epsilon;
			g2.setColor(new Color(255, 0, 0, 50));
			g2.fillOval(circle.getX() - r2, circle.getY() - r2, 2*(r2), 2*(r2));
			
			g2.setColor(new Color(0, 0, 255, 50));
			g2.fillOval(circle.getX()-circle.getRadius(), circle.getY()-circle.getRadius(), 2*circle.getRadius(), 2*circle.getRadius());
			
			int r1 = circle.getRadius() - epsilon;
			g2.setColor(new Color(0, 255, 0, 50));		
			g2.fillOval(circle.getX() - r1, circle.getY() - r1, 2*(r1), 2*(r1));
				
		}
	}
}
