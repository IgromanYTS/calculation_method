import java.io.FileWriter;
import java.io.IOException;

public class NewtonError {

    static final double[] x = {
            -Math.PI / 2, -Math.PI / 8, Math.PI / 4, 5 * Math.PI / 8, Math.PI
    };
    static final int n = x.length;

    static double f(double x) {
        return x * x * Math.cos(x);
    }

    static double[][] dividedDifferences(double[] x) {
        double[][] dd = new double[n][n];
        for (int i = 0; i < n; i++) {
            dd[i][0] = f(x[i]);
        }
        for (int j = 1; j < n; j++) {
            for (int i = 0; i < n - j; i++) {
                dd[i][j] = (dd[i + 1][j - 1] - dd[i][j - 1]) / (x[i + j] - x[i]);
            }
        }
        return dd;
    }

    static double newtonPoly(double x0, double[] x, double[][] dd) {
        double result = dd[0][0];
        double term = 1.0;
        for (int i = 1; i < n; i++) {
            term *= (x0 - x[i - 1]);
            result += dd[0][i] * term;
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        double[][] dd = dividedDifferences(x);

        FileWriter writer = new FileWriter("error_data.csv");
        writer.write("x,error\n");

        for (double xi = -Math.PI / 2; xi <= Math.PI; xi += 0.01) {
            double fx = f(xi);
            double px = newtonPoly(xi, x, dd);
            double error = Math.abs(fx - px);
            writer.write(xi + "," + error + "\n");
        }

        writer.close();
        System.out.println("Error data saved to error_data.csv");
    }
}