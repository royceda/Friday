import java.awt.Graphics;
import java.awt.Color;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import javax.swing.JFrame;


public class PlotPoints extends JPanel {    
	double[] x;
	double[] y;

	public void paintComponent (Graphics g)     
	{
		super.paintComponent(g);
		Graphics2D g2d  = (Graphics2D) g;
		g2d.setColor(Color.BLACK);
		
		for (int i=0; i<x.length; i++){
			g2d.fillOval((int)this.x[i],(int)this.y[i], 10, 10);            
		}
	}

	public void drawPoints(double[]xs, double[]ys){
		JFrame frame = new JFrame("Points");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.x=xs.clone();
		this.y=ys.clone();
		frame.add(new PlotPoints());
		frame.setSize(500, 500);//laptop display size
		frame.setVisible(true);
	}
}