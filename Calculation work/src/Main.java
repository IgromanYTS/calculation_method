import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVWriter;

class Libman {

    private static class LibmanPrivate {

    static final int L1 = 1;
    static final int L2 = 1;

    static final int Nx = 10;
    static final int Ny = 10;

    static double hx = (double) L1 / ( Nx - 1);
    static double hy = (double) L2 / (Ny - 1);

    double a = 0.3, b = 0.3, c = 1.1, d = 1.5, f = 0.9;

    static final int N = Nx * Ny;

    static double [][] A = new double[N][N];

    static double [] vecB = new double[N];

    static int index(int i, int j) {
        return i + j * Nx;
    }

    static double firstEquation(double y) {
        double result = 0.3 * y + 1.1;
        return result;
    }

    static double secondEquation(double y) {
        double result = 0.3 * L1 * L1 - 0.3 * y * y + 0.3 * L1 * y + 1.7 * L1 + 1.8 * y + 2.0;
        return result;
    }

    static double thirdEquation(double x) {
        double result = 0.3 * x * x + 0.8 * x - 0.6;
        return result;
    }

    static  double forthEquation(double x) {
        double result = 0.3 * x * x - 0.3 * L2 * L2 + 0.3 * x * L2 + 1.1 * x + 1.5 * L2 + 0.9;
        return result;
    }

    static double exSolution(double x, double y) {
        double result = 0.3 * (x*x - y*y) + 0.3 * x * y + 1.1 * x + 1.5 * y + 0.9;
        return result;
    }

    static void libman() {
        double x, y;
        for (int j=0; j<Ny; ++j) {
            for (int i=0; i<Nx; ++i) {
                int k = index(i, j);

                x = i * hx;
                y = j * hy;

                if (i == 0) {
                    A[k][k] =  -1 / hx;
                    A[k][index(i+1, j)] = 1 / hx;
                    vecB[k] = firstEquation(y);
                }
                else if (i == Nx - 1) {
                    A[k][k] = 1 + 1 / hx;
                    A[k][index(i-1, j)] = -1 / hx;
                    vecB[k] = secondEquation(y);
                }
                else if (j == 0) {
                    A[k][k] = 1 + 1 / hy;
                    A[k][index(i, j+1)] = -1 / hx;
                    vecB[k] = thirdEquation(x);
                } else if (j == Ny-1) {
                    A[k][k] = 1;
                    vecB[k] = forthEquation(x);
                }
                else {
                    A[k][k] = -2 / (hx*hx) - 2 / (hy*hy);
                    A[k][index(i+1, j)] = 1 / (hx*hx);
                    A[k][index(i-1, j)] = 1 / (hx*hx);
                    A[k][index(i, j+1)] = 1 / (hy*hy);
                    A[k][index(i, j-1)] = 1 / (hy*hy);
                    vecB[k] = 0;
                }
            }
        }
        //System.out.println(Arrays.deepToString(A));
    }
    }

    static void solveLibman(double eps, int maxIter) {
        LibmanPrivate.libman();
        double [] u = new double[LibmanPrivate.N];
        double [] uPrev = new double[LibmanPrivate.N];

        for (int iter = 0; iter<maxIter; ++iter) {
            System.arraycopy(u, 0, uPrev, 0 , LibmanPrivate.N);

            for (int i=0; i<LibmanPrivate.N; ++i) {
                double sum = LibmanPrivate.vecB[i];

                for (int j=0; j<LibmanPrivate.N; ++j) {
                    if (j != i){
                        sum -= LibmanPrivate.A[i][j] * uPrev[j];
                    }
                }
                if (Math.abs(LibmanPrivate.A[i][i]) > 1e-12) {
                    u[i] = sum / LibmanPrivate.A[i][i];
                }
            }

            double maxDiff = 0;
            for (int i=0; i<LibmanPrivate.N; ++i) {
                maxDiff = Math.max(maxDiff, Math.abs(u[i] - uPrev[i]));
            }
            if (maxDiff < eps) {
                System.out.println("Iter: " + iter);
                break;
            }
        }

        System.out.println("Result:");
        for (int j=0; j<LibmanPrivate.Ny; ++j) {
            for (int i=0; i< LibmanPrivate.Nx; ++i) {
                System.out.printf("%.4f\t", u[LibmanPrivate.index(i,j)]);
            }
            System.out.println();
        }

        double maxError = 0;
        for (int j=0; j<LibmanPrivate.Ny; ++j) {
            for (int i=0; i<LibmanPrivate.Nx; ++i) {
                double x = i * LibmanPrivate.hx;
                double y = j * LibmanPrivate.hy;
                double exact = LibmanPrivate.exSolution(x, y);
                double approx = u[LibmanPrivate.index(i, j)];
                double error = Math.abs(exact - approx);
                maxError = Math.max(maxError, error);
            }
        }

        System.out.println("\nMax error = " + maxError);

        SaveToCSV.addDataToCSV(u);
//        System.out.println(u.length);
//        System.out.println(Arrays.toString(u));



    }

    static class SaveToCSV {
        private static final String CSV_FILE_PATH = "./result(20x20).csv";

        static void addDataToCSV(double [] array) {
            String output = CSV_FILE_PATH;
            File file = new File(output);

            try {
                FileWriter outputfile = new FileWriter(file);
                CSVWriter writer = new CSVWriter(outputfile, ';', CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_QUOTE_CHARACTER, CSVWriter.DEFAULT_LINE_END);

                List<String[]> data = new ArrayList<>();
                int countOfRow = LibmanPrivate.Nx;
                for (int i=0; i<countOfRow; ++i) {
                    ArrayList<String> arrayList = new ArrayList<>();
                    for (int j=0; j<LibmanPrivate.Nx; ++j) {
                        arrayList.add(String.valueOf(array[10*i+j]));
                    }
                    String [] rowData = arrayList.toArray(new String[0]);
                    data.add(rowData);
                }
                writer.writeAll(data);
                writer.close();


            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}



public class Main {
    public static void main(String[] args) {
        Libman.solveLibman(1e-6, 10000);
    }
}