package panel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;
import shape.Rectangle;


public class RectanglePanel extends JPanel implements MouseListener, MouseMotionListener {

	Rectangle rectangle = null;
	int x2, y2;
	Point initialPoint = null;

	public RectanglePanel()
	{
		super();
	}   

	@Override
	public void mouseDragged(MouseEvent mEvt) 
	{
		
		System.out.println("Dragged");
		// use initialPoint, mEvt.getPoint(), 
		// Math.abs(...), Math.min(...), and Math.max(...)
		// to calculate x, y, w, and h

		Point toto = mEvt.getPoint();
		
		
		rectangle.setWidth(Math.abs(mEvt.getX() - rectangle.getX()));
		rectangle.setHeight(Math.abs(mEvt.getY() - rectangle.getX() ));   

		//rectangle = new Rectangle(toto.getX(), toto.getY(), 100, 100);
		repaint();
	}

	@Override
	public void mouseMoved(MouseEvent arg0) 
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) 
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0)
	{
		// TODO Auto-generated method stub

	}



	@Override
	public void mousePressed(MouseEvent mEvt) 
	{

		System.out.println("Pressed");
		initialPoint = mEvt.getPoint();
		
		//repaint();

		Point toto = mEvt.getPoint();
		rectangle = new Rectangle(mEvt.getX(), mEvt.getY(), 10, 10);
		System.out.println("Pressed "+toto.getX()+" , "+toto.getY());
		
		rectangle.setX(mEvt.getX());
		rectangle.setY(mEvt.getY());

		repaint();

	}


	@Override
	public void mouseReleased(MouseEvent arg0)
	{
		
		System.out.println("Released "+arg0.getX()+" , "+arg0.getY());
		x2 = arg0.getX();
		y2 = arg0.getY();

		rectangle.setWidth(Math.abs(x2 - rectangle.getX()));
		rectangle.setHeight(Math.abs(y2 - rectangle.getX()));   

		repaint();
	}



	@Override
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		if (rectangle != null) {
			Graphics2D g2 = (Graphics2D) g;
			g2.setColor(Color.BLUE);
			//g2.fill(rectangle);
			g2.fillRect(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getWidth());
		}
	}

}