import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Function {

    static protected final double eps = 1e-6;

    protected static double value(double x) {
        double finalResult = x*x*x*x - 21.86*x*x*x + 168.634*x*x -548.502*x+636.954;
        return finalResult;
    }
    protected static int count;
    static HashMap<String, Integer> result = new HashMap<>();
}


class Bisection extends Function {
    static List<Double> findRoots(double start, double end, double step) {
        List<Double> roots = new ArrayList<>();
        double a = start;

        while (a < end) {
            double b = a + step;
            if (value(a) * value(b) < 0) {
                roots.add(bisection(a, b));
            }
            a = b;
        }
        return roots;
    }

    static double bisection(double a, double b) {
        int count = 0;
        double c = a;

        while ((b-a) >= eps) {
            count++;
            c = (a+b)/2;

            if (Math.abs(value(c)) < eps) {
                break;
            } else if (value(a)*value(c) < 0) {
                b = c;
            } else {
                a = c;
            }
        }
        System.out.printf("Found root: %.6f after %d iterations%n", c, count);
        return c;
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println("Finding all roots");
        List<Double> roots = Bisection.findRoots(2, 10, 0.5);
        System.out.println("All roots: " + roots);
    }
}