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

import org.apache.commons.math3.fitting.leastsquares.GaussNewtonOptimizer;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import shape.Circle;
//import shape.Point;
import compute.GaussNawtonOptimizer1;

/*
 * 
 * @author Da
 *
 */
public class CirclePanel extends JPanel implements MouseListener, MouseMotionListener {

	private Point initialPoint = null; // The center
	private Circle circle      = null; // The main circle
	private int x2, y2; // Final coords
	private int epsilon = 20;
	private List<shape.Point> points; // Points of grid
	
	private int size;
	
	public CirclePanel(int size1) {
		super();
		
		x2 = -100;
		y2 = -100;
		
		size = size1;
		points = new ArrayList<shape.Point>();
		
		for(int x=0; x<size; x+=40) {
			for(int y=0; y<size; y+=40) {
				points.add(new shape.Point(x, y));
			}
		}
	}


	/**
	 * Compute Distance between two circles (based on their centers) 
	 * @param p1
	 * @param p2
	 * @return
	 */
	private double distance(shape.Point p1, Circle p2) {
		double d = Math.sqrt(Math.pow((p1.getX() - p2.getX()), 2) + Math.pow((p1.getY() - p2.getY()), 2)) ;
		return d;
	}
	
	
	private double distance(shape.Point p1, int x, int y) {
		double d = Math.sqrt(Math.pow((p1.getX() - x), 2) + Math.pow((p1.getY() - y), 2)) ;
		return d;
	}
	
	
	/**
	 * Plot the grid points
	 * @param g
	 */
	private void plotPoints(Graphics g) {	
		for(shape.Point p: points) {	
			double d = 0.0;
			if(circle != null) {
				d = distance(p, circle);
			}
			
			if(p.isSelected()) {
				g.setColor(Color.RED);
			}
			
			else if(circle!=null && d <= circle.getRadius() + epsilon  && d >= circle.getRadius() - epsilon ) {
				g.setColor(Color.BLACK);
				//p.setSelected(true);
			} else {
				g.setColor(Color.WHITE);
				//p.setSelected(false);
			}	
			
			g.fillOval((int) p.getX(), (int) p.getY(), 10, 10);
		}
	}
	
	public shape.Point getNearestPoint(int x, int y){
		for(shape.Point p: points) {
			double d = distance(p, x, y);
			if(d <= epsilon) {
				System.out.println("Found !!!!");
				return p;
			}
		}
		return null;
	}
	
	
	private void plotCircle(double x, double y, double radius) {
		circle = new Circle();
		
		circle.setX(x);
		circle.setY(y);
		circle.setRadius(radius); 
		
		repaint();
	}
	
	public void generateCircle() {
		
		ArrayList<Vector2D> vect = new ArrayList<Vector2D>();
		
		for(shape.Point p: points) {
			if(p.isSelected()) {
				vect.add(new Vector2D(p.getX(), p.getY()));
			}
		}
		
		
		double u[] = GaussNawtonOptimizer1.getCircle(vect);
		
		plotCircle(u[0], u[1], u[2]);
		System.out.println("OUIIIIIII");
		
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
		
		System.out.println("Clicked "+arg0.getX()+" , "+arg0.getY());
		x2 = arg0.getX();
		y2 = arg0.getY();
		
		shape.Point p = getNearestPoint(x2, y2);
		
		if(p != null)
			p.setSelected(!p.isSelected());
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
		int radius = 20;

		plotCircle(mEvt.getX()-radius, mEvt.getY()-radius, 20);
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		x2 = arg0.getX();
		y2 = arg0.getY();
		System.out.println("Released "+x2+" , "+y2);
		circle.setRadius(Math.abs(x2 - circle.getX())); 
		repaint();	
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		plotPoints(g);
		
		if (circle != null) {
			Graphics2D g2 = (Graphics2D) g;
			
			// Plot Circles 
			double r2 = circle.getRadius() + epsilon;
			g2.setColor(new Color(255, 0, 0, 50));
			g2.fillOval((int) (circle.getX() - r2), (int) (circle.getY() - r2), (int) (2*(r2)), (int) (2*(r2)));
			
			g2.setColor(new Color(0, 0, 255, 50));
			g2.fillOval((int) (circle.getX()-circle.getRadius()), (int) (circle.getY()-circle.getRadius()), (int) (2*circle.getRadius()), (int) (2*circle.getRadius()));
			
			double r1 = circle.getRadius() - epsilon;
			g2.setColor(new Color(0, 255, 0, 50));		
			g2.fillOval((int) (circle.getX() - r1), (int) (circle.getY() - r1), (int) (2*(r1)), (int) (2*(r1)));
				
		}
	}
}
