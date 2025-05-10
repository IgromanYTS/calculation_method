import java.util.Arrays;



public class test {
    public static void main(String[] args) {
        double [][] arrayA = {{2, 0.42, 0.54, 0.66}, {0.42, 2, 0.32, 0.44}, {0.54, 0.32, 2, 0.22}, {0.66, 0.44, 0.22, 2}};
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