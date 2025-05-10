import java.util.Arrays;

class Simple_Iteration {

    private static double eps = 1e-4;

    static double [][] createMatrixC(double [][] array) {
        int length = array.length;
        double [][] result = new double[length][length];
        for (int i=0; i<length; ++i) {
            for (int j=0; j<length; ++j) {
                if (i==j) {
                    result[i][j] = 0;
                }
                else {
                    result[i][j] = - ((array[i][j])/array[i][i]);
                }
            }
        }
        return result;
    }

    static double [] createVectorD(double [][] arrayA, double [] arrayB) {
        int length = arrayB.length;
        double [] result = new double[length];
        for (int i = 0; i<length; ++i) {
            result[i] = arrayB[i]/arrayA[i][i];
        }
        return result;
    }

    private static double sumC(double [][] array, int i, int length) {
        double sum = 0.0;
        for (int j=0; j<length; ++j) {
            sum += Math.abs(array[i][j]);
        }
        return sum;
    }

    private static double sumR(double [][] array, int i, int length) {
        double sum = 0.0;
        for (int j=0; j<length; ++j) {
            sum += Math.abs(array[j][i]);
        }
        return sum;
    }

    static double findQ(double [][] array) {
        int length = array.length;
        double column = 0.0;
        double row= 0.0;

        for (int i=0; i<length; ++i) {
           double sum = sumC(array, i, length);
           if (sum>column) {
               column = sum;
           }
        }

        for (int i=0; i<length; ++i) {
            double sum = sumR(array, i, length);
            if (sum>column) {
                row = sum;
            }
        }
        return Math.max(row, column);
    }

    private static double minus(double [] vector_2, double [] vector_1) {
        int length = vector_1.length;
        double [] result = new double[length];
        for (int i=0; i<length; ++i) {
            result[i] = Math.abs(vector_2[i] - vector_1[i]);
        }

        double max = 0.0;
        for (double elem: result) {
            if (elem > max) {
                max = elem;
            }
        }
        return max;
    }

    static double [] iteration_process(double [][] array, double q, double [] d) {
        int length = array.length;
        double [] vectorX_1 = Arrays.copyOf(d, length);
        double [] vectorX_2 = new double[length];
        //double [] copy;
        double error;
        do {
            //copy = Arrays.copyOf(vectorX_1, length);
            for (int i=0; i<length; ++i) {
                double sum = 0.0;
                for (int k=0; k<length; ++k) {
                    sum += array[i][k] * vectorX_1[k];
                }
                vectorX_2[i] = sum + d[i];
            }
            System.out.println(Arrays.toString(vectorX_2));

            error = ( (1/(1-q)) * (minus(vectorX_2, vectorX_1)) );

            vectorX_1 = Arrays.copyOf(vectorX_2, length);
        } while ( error > eps);
        return vectorX_2;
    }

}


public class Main {
    public static void main(String[] args) {
        //double [][] arrayA = {{0.73, 0.26, 0.04, 0.55}, {0.055, 0.87, 0.3, 0.165}, {0.518, -0.028, 0.88, 0.154}, {0.149, 0.208, 0.192, 0.615}};
        double [][] arrayA = {{0.73, 0.055, 0.518, 0.149}, {0.26, 0.87, -0.028, 0.258}, {0.04, 0.3, 0.88, 0.192}, {0.55, 0.165, 0.154, 0.615}};
        double [] vectorB = {0.3, 0.5, 0.7, 0.9};
        double [][] matrixC = Simple_Iteration.createMatrixC(arrayA);
        System.out.println("Matrix C: \n" + Arrays.deepToString(matrixC) + "\n");
        double [] vectorD = Simple_Iteration.createVectorD(arrayA, vectorB);
        System.out.println("Vector D: \n" +Arrays.toString(vectorD) + "\n");
        double q = Simple_Iteration.findQ(matrixC);
        System.out.println("Q: \n" + q + "\n");
        System.out.println("Iteration:");
        double [] result = Simple_Iteration.iteration_process(matrixC, q, vectorD);
        System.out.println("\nResult: \n" + Arrays.toString(result) + "\n");

        double[] res = new double[4];
        for (int i = 0; i < 4; i++) {
            double sum = 0.0;
            for (int j = 0; j < 4; j++) {
                sum += arrayA[i][j] * result[j];
            }
            res[i] = sum - vectorB[i];
        }

        System.out.println("Check (Ax - b): \n" + Arrays.toString(res)); /// correct :)

        System.out.println("Correct answer with our eps :)");
    }
}