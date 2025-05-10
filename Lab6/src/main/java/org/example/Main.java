package org.example;

class RungeKunga {

    static double x;

    private double [] xs, ys, exact, error;

    private static double h = 0.1;

    private static double y1 = 0;
    private static double y2 = Math.cos(0);

    private static double F(double x) {
        return -Math.sin(x) - (1 - x*x) * Math.cos(x);
    }

    private static double dydx(double x, double y) {
        return (1-x*x)*y + F(x);
    }

    private int n;

    RungeKunga(int n) {
        this.n = n;
        xs = new double[n+1];
        ys = new double[n+1];
        exact = new double[n+1];
        error = new double[n+1];
        loopKunga();
    }





    private void loopKunga() {

//        double [] y10 = new double[n];
//        double [] y20 = new double[n];
//
//        y10[0] = 1/Math.sqrt(2);
//        y20[0] = 0;



        x = y1;
        double y = y2;

        double k1, k2, k3, k4, m1, m2, m3, m4;

        for (int i = 0; i <= 3; ++i) {

            xs[i] = x;
            ys[i] = y;
            exact[i] = Math.cos(x);
            error[i] = Math.abs(y - exact[i]);

            k1 = h * dydx(x, y);
            k2 = h * dydx(x+h/2, y+k1/2);
            k3 = h * dydx(x+h/2, y+k2/2);
            k4 = h * dydx(x+h, y+k3);

            y += (k1+2*k2+2*k3+k4)/6;
            x += h;

            System.out.println("Runge: " + x + " " + y + " " + Math.cos(x) + " " + Math.abs(y - Math.cos(x)));
        }

        for (int i=4; i<=n; ++i) {

            xs[i] = x;

            ys[i] = ys[i-1] + h * (55*dydx(xs[i-1], ys[i-1]) - 59*dydx(xs[i-2], ys[i-2]) + 37*dydx(xs[i-3], ys[i-3]) - 9*dydx(xs[i-4], ys[i-4]))/24;

            x += h;

            exact[i] = Math.cos(x);
            error[i] = Math.abs(ys[i] - exact[i]);

            System.out.println("Adams: " + x + " " + ys[i] + " " + Math.cos(x) + " " + Math.abs(ys[i] - Math.cos(x)) );
        }
    }

    public void plot() {
        Plotter.plot(xs, ys, exact, error);
    }

}

public class Main {
    public static void main(String[] args) {
        RungeKunga rungeKunga = new RungeKunga(10);
        rungeKunga.plot();
    }
}