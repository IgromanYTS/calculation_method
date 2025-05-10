import java.util.ArrayList;
import java.util.Arrays;

class SquaredMethod {

    static private double sum(int i, int j, double[][] matrix) {
        double finalResult = 0;
        for (int k=0; k<j; ++k) {
            finalResult += matrix[i][k]*matrix[j][k];
        }
        return finalResult;
    }

    static private double sumSquared(int i, double[][] matrix) {
        double finalResult = 0;
        for (int k=0; k<i; ++k) {
            finalResult += matrix[i][k] * matrix[i][k];
        }
        return finalResult;
    }

    static double [][] createMatrix(double [][] matrix) {
        double [][] resultMatrix = new double[matrix.length][matrix.length];
        for (int i=0; i<matrix.length; ++i) {
            for (int j=0; j< matrix.length; ++j) {
                System.out.println(Arrays.deepToString(resultMatrix));

//                if (i == 0 && j == 0) {
//                    resultMatrix[0][0] = Math.sqrt(matrix[0][0]);
//                }
//                else if (i == 0 && j > 0) {
//                    resultMatrix[0][j] = matrix[0][j]/resultMatrix[0][0];
//                }
//                else if (i == j) {
//                    resultMatrix[i][i] = Math.sqrt(( matrix[i][i] - sumSquared(i, resultMatrix) ));
//                } else if (i>j) {
//                    resultMatrix[i][j] = (matrix[i][j]-sum(i,j, resultMatrix))/resultMatrix[j][j];
//                } else if (j>i) {
//                    resultMatrix[i][j] = 0;
//                }

//variant 1
//                if (j>i) {
//                    resultMatrix[i][j] = 0;
//                } else if (i == 0 && j == 0) {
//                    resultMatrix[0][0] = Math.sqrt(matrix[0][0]);
//                } else if (i == 0 && j > 0) {
//                    resultMatrix[0][j] = matrix[0][j]/resultMatrix[0][0];
//                } else if (i>j) {
//                    resultMatrix[i][j] = (matrix[i][j]-sum(i,j, resultMatrix))/resultMatrix[j][j];
//                } else if (i>0 && i<= (matrix.length-1)) {
//                    resultMatrix[i][i] = Math.sqrt(( matrix[i][i] - sumSquared(i, resultMatrix) ));
//                }


//                if (i == j) {
//
//                    resultMatrix[i][i] = Math.sqrt(matrix[i][i] - sumSquared(i, resultMatrix));
//                } else {
//                    resultMatrix[i][j] = (matrix[i][j] - sum(i, j, resultMatrix)) / resultMatrix[j][j];
//                }

                if (i == 0 && j == 0) {
                    resultMatrix[0][0] = Math.sqrt(matrix[0][0]);
                }
                else if (i == j) {
                    resultMatrix[i][i] = Math.sqrt(( matrix[i][i] - sumSquared(i, resultMatrix) ));
                } else if (i>j) {
                    resultMatrix[i][j] = (matrix[i][j]-sum(i,j, resultMatrix))/resultMatrix[j][j];
                } else if (j>i) {
                    resultMatrix[i][j] = 0;
                }


            }
        }
        //System.out.println(resultMatrix);
        return resultMatrix;
    }

    static double[][] transpose(double[][] matrix) {
        int n = matrix.length;

        double[][] transposed = new double[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                transposed[j][i] = matrix[i][j];
            }
        }

        return transposed;
    }

    static double sum(int i, double [][] matrix, double [] vectorY) {
        double result = 0;
        for (int k=0; k<i; ++k) {
            result += matrix[i][k]*vectorY[k];
        }
        return result;
    }

    static double sum2(int i, int n, double [] vectorX, double [][] tMatrix) {
        double result = 0;
        for (int k = i+1; k<n; ++k) {
            result += tMatrix[i][k]*vectorX[k];
        }
        return result;
    }

    static double [] reversStroke(double [] vectorB, double[][] matrix, double[][] matrixT) {
        int n = matrix.length;
        double [] vectorY = new double[vectorB.length];
        double [] vectorX = new double[n];
        for (int i = 0; i < n; ++i) {
            if (i == 0) {
                vectorY[0] = vectorB[0]/matrix[0][0];
            }
            else {
                vectorY[i] = (vectorB[i] - sum(i, matrix, vectorY))/matrix[i][i];
            }
            //if (i == n-1) {
//                vectorX[n-1] = vectorY[n-1]/matrixT[n-1][n-1];
//            } else if (i<n-1) {
//                vectorX[i] = (vectorY[i])/matrixT[i][i];
//            }
        }
        System.out.println("Вектор Y:");
        System.out.println(Arrays.toString(vectorY));
        for (int i = n - 1; i >= 0; --i) {
            if (i == n - 1) {
                vectorX[n - 1] = vectorY[n - 1] / matrixT[n - 1][n - 1];
            } else {
                vectorX[i] = (vectorY[i] - sum2(i, n, vectorX, matrixT)) / matrixT[i][i];
            }
        }
        return vectorX;
    }

}



public class Main {

    static double[][] multiply(double[][] A, double[][] B) {
        int n = A.length;
        double[][] result = new double[n][n];

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                for (int k = 0; k < n; ++k) {
                    result[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("Початкова система: ");
        double [][] matrix = {{1.00, 0.42, 0.54, 0.66}, {0.42, 1.00, 0.32, 0.44}, {0.54, 0.32, 1.00, 0.22}, {0.66, 0.44, 0.22, 1.00}};
        System.out.println(Arrays.deepToString(matrix));
        System.out.println("\nПочатковий вектор В: ");
        double [] vectorB = {0.3, 0.5, 0.7, 0.9};
        System.out.println(Arrays.toString(vectorB));
//        System.out.println(matrix.length);
//        System.out.println(matrix[0].length);

        System.out.println("\nСтворюємо матрицю Т \n\nКроки:");

        double[][] matrix1 = SquaredMethod.createMatrix(matrix);
        System.out.println("\nУтворена матриця: \n" + Arrays.deepToString(matrix1));
        double[][] transposedMatrix = SquaredMethod.transpose(matrix1);
        System.out.println("Утворенна транспонована матриця: \n"+Arrays.deepToString(transposedMatrix));
        double[] vectorX = SquaredMethod.reversStroke(vectorB, matrix1, transposedMatrix);
        System.out.println("Утворенний вектор Х:\n"+ Arrays.toString(vectorX));
        System.out.println();
        System.out.println("Перевірка правильності обрахунку матриць: " + Arrays.deepToString(multiply(matrix1, transposedMatrix)));


        double[] AX = new double[vectorB.length];
        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix.length; ++j) {
                AX[i] += matrix[i][j] * vectorX[j];
            }
        }

        System.out.println();
        System.out.println("A * X (перевірка):");
        System.out.println(Arrays.toString(AX));

    }
    }





