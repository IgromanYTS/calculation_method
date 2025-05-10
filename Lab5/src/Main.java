import java.util.ArrayList;
import java.util.Arrays;

class Newton {
    static final double [] array = {-Math.PI/2, -Math.PI/8, Math.PI/4, 5*Math.PI/8, Math.PI};
    private static double function(double x) {
        double y = (x*x)*Math.cos(x);
        return y;
    }

    static double newton(double [] array1, double x) {
        int length = array1.length;
        double [] value = new double[length];

        for (int i=0; i<length; ++i) {
            value[i] = function(array1[i]);
        }

        double [][] delta = new double[length][length];
        for (int i=0; i<length;++i) {
            delta[0][i] = value[i];
        }

        for (int i=1; i<length; ++i) {
            for (int j=0; j<length-i; ++j) {
                delta[i][j] = delta[i - 1][j + 1] - delta[i - 1][j];
            }
        }
        double h = array1[1] - array1[0];
        double q = (x - array1[0])/h;

        double S = delta[0][0];
        double q_pr = 1;
        double fact = 1;

        for (int i=1; i<length; ++i) {
            q_pr *= (q-i+1);
            fact *= i;
            S += (q_pr * delta[i][0]) / fact;
        }
        return S;
    }

}

public class Main {
    public static void main(String[] args) {

        double x = 0.5;
        System.out.println(Newton.newton(Newton.array, x));

    }
}