package compute;



import org.apache.commons.math3.fitting.leastsquares.GaussNewtonOptimizer;
import org.apache.commons.math3.fitting.leastsquares.LeastSquaresOptimizer;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.LUDecomposition;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.QRDecomposition;
import org.apache.commons.math3.linear.RealMatrix;

import org.apache.commons.math3.linear.SingularValueDecomposition;
import org.apache.commons.math3.optim.SimpleVectorValueChecker;

public class Regression {

	public Regression() {

	}


	public static void test() {
		double matrixData[][] = { 
				{ 2,  1, 1,  4,  Math.sqrt(2)*2, 1},
				{ 4,  0, 1, 16,               0, 0},
				{-3,  3, 1,  9, -Math.sqrt(2)*9, 9},
				{-2, -2, 1,  4,  Math.sqrt(2)*4, 4}};

		RealMatrix m = MatrixUtils.createRealMatrix(matrixData);

		double[][] matrixData2 = { {1d,2d}, {2d,5d}, {1d, 7d}};
		RealMatrix n = new Array2DRowRealMatrix(matrixData2);


		// Now multiply m by n
		//RealMatrix p = m.multiply(n);
		//System.out.println(p.getRowDimension());    // 2
		//System.out.println(p.getColumnDimension()); // 2

		// Invert p, using LU decomposition
		//RealMatrix pInverse = new LUDecomposition(p).getSolver().getInverse();	

		SingularValueDecomposition s = new SingularValueDecomposition(m);

		QRDecomposition q = new QRDecomposition(m);

		System.out.println(s.getS().toString());
		System.out.println(q.getR().toString());


		double r22Data[][] = {
				{-6.4408732704,-5.662208585,5.7445626465},
				{7.1156271198,-11.7980192013,4.58835266},
				{-14.5818062098,-0.7482791121,-6.4949227152},
				{8.0158549056,-5.4941983251,1.3277796469}};

		RealMatrix r22 = MatrixUtils.createRealMatrix(r22Data);
		SingularValueDecomposition s22 = new SingularValueDecomposition(r22);


		System.out.println(s22.getS().toString());

		double u1[] = {0, 0};

		double a[] = {1, 7};
		double b[] = {2, 6};
		double c[] = {5, 8};
		double d[] = {9, 5};
		double e[] = {3, 7};

		double x[][] = new double[2][5];

		double jData[][] =  new double[3][5];


		// Jacobian
		for(int i =0; i<2; i++) {
			double di = Math.sqrt(Math.pow((u1[0] - x[i][0]), 2) + Math.pow((u1[1] - x[i][1]), 2));
			for(int j=0; j<5; j++) {
				if(j < 4) 
					jData[i][j] = (u1[i] - x[i][j]) ;
				else
					jData[i][j] = -1;
			}
		}

		RealMatrix jacobian = MatrixUtils.createRealMatrix(jData);
		System.out.println(jacobian);
		
		

		GaussNewtonOptimizer optimizer = new GaussNewtonOptimizer().withDecomposition(GaussNewtonOptimizer.Decomposition.QR);
               
		



		double jacobianData[][] = {
				{-6.4408732704,-5.662208585,5.7445626465},
				{7.1156271198,-11.7980192013,4.58835266},
				{-14.5818062098,-0.7482791121,-6.4949227152},
				{8.0158549056,-5.4941983251,1.3277796469}};



	}


	public static void main(String args[]) {
		test();
	}



}
