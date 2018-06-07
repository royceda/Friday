package compute;


import java.util.ArrayList;
import java.util.Arrays;

import org.apache.commons.math3.fitting.leastsquares.GaussNewtonOptimizer;
import org.apache.commons.math3.fitting.leastsquares.GaussNewtonOptimizer.Decomposition;
import org.apache.commons.math3.fitting.leastsquares.LeastSquaresBuilder;
import org.apache.commons.math3.fitting.leastsquares.LeastSquaresOptimizer;
import org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem;
import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer;
import org.apache.commons.math3.fitting.leastsquares.MultivariateJacobianFunction;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.linear.SingularValueDecomposition;
import org.apache.commons.math3.optim.SimpleVectorValueChecker;
import org.apache.commons.math3.util.Pair;


public class GaussNawtonOptimizer1 {

	public static double[] getInitialValue(ArrayList<Vector2D> observed) {

		/*final Vector2D[] observedPoints = new Vector2D[] {
				new Vector2D( 1.0,  7.0),
				new Vector2D( 2.0,  6.0),
				new Vector2D( 5.0,  8.0),
				new Vector2D( 7.0, 7.0),
				new Vector2D( 9.0,  5.0),
				new Vector2D( 3.0,  7.0),
		};*/


		// Define B
		RealMatrix B = new Array2DRowRealMatrix(observed.size(), 4);
		for(int i = 0; i < observed.size(); i++) {
			Vector2D p = observed.get(i);
			B.setEntry(i,  0, p.getX()*p.getX() + p.getX()*p.getX());
			B.setEntry(i,  1, p.getX());
			B.setEntry(i,  2, p.getY());
			B.setEntry(i,  3, 1.0);
		}


		// SVD + take the vector associated to smallest singular value of B
		SingularValueDecomposition svd = new SingularValueDecomposition(B);
		/*System.out.println();
		System.out.println(B);
		System.out.println(svd.getS());
		System.out.println("");
		System.out.println(svd.getV().toString());
		System.out.println(svd.getU().toString());*/

		double singularValues[] = svd.getSingularValues();
		
		for(int i = 0; i < singularValues.length; i++) {
			System.out.println(singularValues[i]);
		}

		// Define u = [a, b1, b2, c]
		double a = svd.getVT().getColumn(3)[0];
		double b1 = svd.getVT().getColumn(3)[1];
		double b2 = svd.getVT().getColumn(3)[2];
		double c = svd.getVT().getColumn(3)[3];
		
		System.out.println("a : "+a+", b1 : "+b1+", b2 : "+b2+", c : "+c);

		double z[] = { -b1/(2*a), -b2/(2*a)}; // (5.3794, 7.2532)
		double r = Math.sqrt((b1*b1 + b2*b2)/(4*a*a) - (c/a)); //3.0370

		System.out.println("Center "+z[0]+ " : " + z[1]);
		System.out.println("Radius 1: "+ r);

		double uInit[] = {z[0], z[1], r};

		return uInit;
	}



	public static double[] getCircle(ArrayList<Vector2D> observed) {

		double u[] = getInitialValue(observed) ;
		// the model function components are the distances to current estimated center,
		// they should be as close as possible to the specified radius
		MultivariateJacobianFunction distancesToCurrentCenter = new MultivariateJacobianFunction() {
			public Pair<RealVector, RealMatrix> value(final RealVector point) {
				Vector2D center = new Vector2D(point.getEntry(0), point.getEntry(1)); // u
				RealVector value = new ArrayRealVector(observed.size()); //f(u)
				RealMatrix jacobian = new Array2DRowRealMatrix(observed.size(), 3); //J(u)
	
				//Compute Jacobian
				for (int i = 0; i<observed.size(); i++) {
					Vector2D p = observed.get(i);
					double modelI = Vector2D.distance(p, center);
					value.setEntry(i, modelI);
					// derivative with respect to p0 = x center
					jacobian.setEntry(i, 0, (center.getX() - p.getX()) / modelI);
					// derivative with respect to p1 = y center
					jacobian.setEntry(i, 1, (center.getY() - p.getY()) / modelI);
					// derivative with respect to p2 = r center
					jacobian.setEntry(i, 2, -1);
				}
				return new Pair<RealVector, RealMatrix>(value, jacobian);
			}
		};



		// the target is to have all points at the specified radius from the center
		double[] prescribedDistances = new double[observed.size()];
		Arrays.fill(prescribedDistances, u[2]);

		// least squares problem to solve : modeled radius should be close to target radius
		LeastSquaresProblem problem = new LeastSquaresBuilder().
				start(u).
				checkerPair(new SimpleVectorValueChecker(1e-30, 1e-30)).
				model(distancesToCurrentCenter).
				target(prescribedDistances).
				lazyEvaluation(false).
				maxEvaluations(100000).
				maxIterations(10000).
				build();


		LeastSquaresOptimizer.Optimum optimum = new LevenbergMarquardtOptimizer().optimize(problem);
		Vector2D fittedCenter = new Vector2D(optimum.getPoint().getEntry(0), optimum.getPoint().getEntry(1));
		System.out.println("fitted center: "   + fittedCenter.getX() + " " + fittedCenter.getY());
		System.out.println("radius: "          + optimum.getPoint().getEntry(2));
		System.out.println("normalized cost: " + optimum.getRMS());
		System.out.println("evaluations: "     + optimum.getEvaluations());
		System.out.println("iterations: "      + optimum.getIterations());


		double uFinal[] = {fittedCenter.getX(), fittedCenter.getY(), optimum.getPoint().getEntry(2)};
		return uFinal;
	}
	
	
	public static void main(String args[]) {
		
	
	 Vector2D[] observedPoints = new Vector2D[] {
		      new Vector2D( 1.0,  7.0),
		      new Vector2D( 2.0,  6.0),
		      new Vector2D( 5.0,  8.0),
		      new Vector2D( 7.0,  7.0),
		      new Vector2D( 9.0,  5.0),
		      new Vector2D( 3.0,  5.0)
		  };
		
	 ArrayList<Vector2D> vect = new ArrayList<Vector2D>();
	 vect.add(new Vector2D( 1.0,  7.0));
	 vect.add(new Vector2D( 2.0,  6.0));
	 vect.add(new Vector2D( 5.0,  8.0));
	 vect.add(new Vector2D( 7.0,  7.0));
	 vect.add(new Vector2D( 9.0,  5.0));
	 vect.add(new Vector2D( 3.0,  5.0));
	 
	 getInitialValue(vect);
	 
		
	}
}

