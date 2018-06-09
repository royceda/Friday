package compute;


import java.util.ArrayList;

import org.apache.commons.math3.linear.QRDecomposition;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;

import org.apache.commons.math3.linear.SingularValueDecomposition;

public class EllipticRegression {


	private static double[] getInitialValue(ArrayList<Vector2D> observed) {
		// TODO Auto-generated method stub
		return null;
	}

	public static double[] getEllipse(ArrayList<Vector2D> observed) {
		double u[] = getInitialValue(observed) ;

		RealMatrix S = new Array2DRowRealMatrix(observed.size(), 6);
		for(int i = 0; i < observed.size(); i++) {
			Vector2D p = observed.get(i);

			S.setEntry(i,  0, p.getX());
			S.setEntry(i,  1, p.getY());
			S.setEntry(i,  2, 1.0);
			S.setEntry(i,  3, p.getX()*p.getX());
			S.setEntry(i,  4, Math.sqrt(2)*p.getX()*p.getY() );
			S.setEntry(i,  5, p.getY()*p.getY());
		}



		QRDecomposition decomp = new QRDecomposition(S);
		System.out.println(decomp.getR());
		
		//extract R22
		RealMatrix R22 = decomp.getR().getSubMatrix(observed.size()-3, observed.size()-1, 3, 5);
		System.out.println(R22);

		
		SingularValueDecomposition svd = new SingularValueDecomposition(R22);
		
		double uFinal[] = {0, 0, 0};
		return uFinal;
	}


	public static void main(String ars[]) {

		ArrayList<Vector2D> observedPoints = new ArrayList<Vector2D>();

		observedPoints.add(new Vector2D( 1.0,  7.0));
		observedPoints.add(new Vector2D( 2.0,  6.0));
		observedPoints.add(new Vector2D( 5.0,  8.0));
		observedPoints.add(new Vector2D( 7.0, 7.0));
		observedPoints.add(new Vector2D( 9.0,  5.0));
		observedPoints.add(new Vector2D( 3.0,  7.0));

		getEllipse(observedPoints);

	}

}


/*


Array2DRowRealMatrix{
	
	{-13.0,-13.3846153846,-2.0769230769,-94.8461538462,-113.3546563071,-88.9230769231},
	{0.0,-9.6359779476,-1.2662112241,23.6072862743,-12.2065868163,-71.7933167067},
	{0.0,0.0,0.2882701313,6.6036850946,-16.6921870121,-12.4601669346},
	{0.0,0.0,0.0,9.396514169,-4.6019156503,0.3316724012},
	{0.0,0.0,0.0,0.0,4.6812815432,-0.9638977477},
	{0.0,0.0,0.0,0.0,0.0,1.4529973499}}


{
{9.396514169,-4.6019156503,0.3316724012},
{0.0,4.6812815432,-0.9638977477},
{0.0,0.0,1.4529973499}}
*/

