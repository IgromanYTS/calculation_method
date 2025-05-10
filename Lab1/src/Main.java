import java.util.HashMap;

class Function {

    static protected final double eps = 1e-10;

    protected static double value(double x) {
        double finalResult = x*x*x*x*x - 3*x*x*x*x + 7*x*x -3;
        return finalResult;
    }
    protected static int count;
    static HashMap<String, Integer> result = new HashMap<>();
}


class Bisection extends Function {

    static void bisection(double a, double b) {
        count = 0;
        if (value(a)*value(b) >= 0) {
            System.out.println("This is function doesn't have solution in this coordinates");
        }
        else {
            while ((b-a) >= eps) {
                count += 1;
                double c = (a+b)/2;
                double resultFuncC = value(c);
                System.out.println("Number of iteration: " + count + " Value c: " + c + " Value of function: " + resultFuncC);
                if (Math.abs(resultFuncC) < eps) {
                    System.out.println();
                    System.out.println("Result c = " + c + " f(c) = " + resultFuncC);
                    result.put("Bisection", count);
                    System.out.println();
                    break;
                } else if (value(a)*resultFuncC < 0) {
                    b = c;
                }
                else {
                    a = c;
                }
            }
        }
    }
}

class Method_Hord extends Function {

    static void method_hord(double a, double b) {
        count = 0;
        double xNext = a;
        do {
            count += 1;
            xNext = a - (value(a)*(b-a))/((value(b)-value(a)));
            if (value(xNext)*value(a) <0 ) {
                a = xNext;
            }
            else {
                b = xNext;
            }
            System.out.println("Number of iteration: " + count + " Value of function: " + value(xNext));
        } while (Math.abs(value(xNext)) > eps);
        System.out.println();
        System.out.println("Result = " + xNext+ " f(xNext) = " + value(xNext));
        result.put("Method_hord", count);
        System.out.println();
    }
}

class Tangents extends Function {
    static private double derative(double x) {
        double result = 5*x*x*x*x - 12*x*x*x + 14*x;
        return result;
    }
    static void tangents(double a, double b) {
        count = 0;
        double x0 = (a+b)/2;
        double xn = value(x0);
        double xn1 = xn - value(xn)/derative(xn);
        while (Math.abs((xn1-xn))> eps) {
            count += 1;
            xn = xn1;
            xn1 = xn - value(xn)/derative(xn);
            System.out.println("Number of iteration: " + count + " , Value of function :" + value(xn1));
        }
        System.out.println();
        System.out.println("Result = " + xn1+ " f(xn1) = " + value(xn1));
        result.put("Tangents", count);
    }
}



public class Main {
    public static void main(String[] args) {
        System.out.println("Method bisection\n");
        Bisection.bisection(-8 , 8);
        System.out.println("Method_hord\n");
        Method_Hord.method_hord(-8, 8);
        System.out.println("Method tangents\n");
        Tangents.tangents(0, 8);
        System.out.println();
        System.out.println("Count of iteration: " + Function.result);

    }
}