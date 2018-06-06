package frame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import panel.CirclePanel;
import panel.RectanglePanel;
import shape.Circle;
import shape.Rectangle;



/**
 * 
 * @author Da
 *
 */
public class RectangleFrame extends JFrame implements ActionListener {

	JPanel buttonPanel;
	JButton saveImage;
	JButton clearImage;
	JCheckBox intersections;
	JCheckBox union;
	JPanel drawingArea;

	/**
	 * Create frame
	 * @param circle
	 */
	public RectangleFrame(boolean circle){		
		super();
		setTitle("Rectangles");
		setSize(600,600);
		setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		buttonPanel = new JPanel();
		buttonPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		this.add(buttonPanel, BorderLayout.SOUTH);

		intersections = new JCheckBox("Draw Intersections");
		buttonPanel.add(intersections);

		union = new JCheckBox("Draw Union");
		buttonPanel.add(union);
		
		if(circle) {
			drawingArea = new CirclePanel();
		} else {
			drawingArea = new RectanglePanel();
		}
		
		drawingArea.setBorder(BorderFactory.createLineBorder(Color.blue));
		this.add(drawingArea, BorderLayout.CENTER);
		drawingArea.setVisible(true);
		drawingArea.addMouseListener((MouseListener) drawingArea);
		drawingArea.addMouseMotionListener((MouseMotionListener) drawingArea);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		// TODO Auto-generated method stub
	}
}





