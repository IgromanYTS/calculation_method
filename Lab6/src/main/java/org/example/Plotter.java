package org.example;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;

public class Plotter {
    public static void plot(double[] x, double[] y1, double[] y2, double[] errors) {
        XYSeries exactSeries = new XYSeries("Точне рішення");
        XYSeries approxSeries = new XYSeries("Обчислення");
        XYSeries errorSeries = new XYSeries("Похибка");

        for (int i = 0; i < x.length; i++) {
            exactSeries.add(x[i], y1[i]);
            approxSeries.add(x[i], y2[i]);
            errorSeries.add(x[i], errors[i]);
        }

        XYSeriesCollection dataset1 = new XYSeriesCollection();
        dataset1.addSeries(exactSeries);
        dataset1.addSeries(approxSeries);

        XYSeriesCollection dataset2 = new XYSeriesCollection();
        dataset2.addSeries(errorSeries);

        JFreeChart chart1 = ChartFactory.createXYLineChart(
                "Порівняння рішень",
                "x", "y",
                dataset1,
                PlotOrientation.VERTICAL,
                true, true, false
        );

        JFreeChart chart2 = ChartFactory.createXYLineChart(
                "Графік похибки",
                "x", "e(x)",
                dataset2,
                PlotOrientation.VERTICAL,
                true, true, false
        );

        JFrame frame = new JFrame("Графики");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);
        frame.setLayout(new java.awt.GridLayout(1, 2));
        frame.add(new ChartPanel(chart1));
        frame.add(new ChartPanel(chart2));
        frame.setVisible(true);
    }
}
