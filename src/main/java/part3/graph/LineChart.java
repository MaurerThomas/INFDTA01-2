package part3.graph;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYTextAnnotation;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import part3.Forecasting;
import part3.smoothing.DoubleExponentialSmoothing;
import part3.smoothing.SimpleExponentialSmoothing;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class LineChart extends ApplicationFrame {
    private SimpleExponentialSmoothing simpleExponentialSmoothing = new SimpleExponentialSmoothing();
    private DoubleExponentialSmoothing doubleExponentialSmoothing = new DoubleExponentialSmoothing();

    public LineChart(String title, String chartTitle) throws IOException {
        super(title);
        JFreeChart xyLineChart = ChartFactory.createXYLineChart(
                chartTitle,
                "Week Number",
                "WallMart sales",
                dataset(),
                PlotOrientation.VERTICAL,
                true, true, false);

        ChartPanel chartPanel = new ChartPanel(xyLineChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));

        XYTextAnnotation bestSimpleExponentialSmoothingLabel = new XYTextAnnotation("SES Coefficient: " + simpleExponentialSmoothing.getSmoothingCoefficient(), 8, 390);
        System.out.println("SES Coefficient: " + simpleExponentialSmoothing.getSmoothingCoefficient());
        XYTextAnnotation bestSimpleExponentialSmoothingErrorMeasureLabel = new XYTextAnnotation("SES Error Measure: " + simpleExponentialSmoothing.getErrorMeasure(), 24, 390);
        System.out.println("SES Error Measure: " + simpleExponentialSmoothing.getErrorMeasure());

        XYTextAnnotation bestDoubleExponentialSmoothingLabel = new XYTextAnnotation("DES Coefficient: " + doubleExponentialSmoothing.getBestFactors(0), 15, 67500);
        System.out.println("DES Coefficient: " + doubleExponentialSmoothing.getBestFactors(0));
        XYTextAnnotation bestDoubleExponentialSmoothingErrorMeasureLabel = new XYTextAnnotation("DES Error Measure: " + doubleExponentialSmoothing.getErrorMeasure(), 24, 62500);
        System.out.println("DES Error Measure: " + doubleExponentialSmoothing.getErrorMeasure());
        System.out.println("DES Trend: " + doubleExponentialSmoothing.getBestFactors(1));
        XYTextAnnotation bestDoubleExponentialTrendSmoothingLabel = new XYTextAnnotation("DES Trend: " + doubleExponentialSmoothing.getBestFactors(1), 15, 65000);

        xyLineChart.getXYPlot().addAnnotation(bestSimpleExponentialSmoothingLabel);
        xyLineChart.getXYPlot().addAnnotation(bestSimpleExponentialSmoothingErrorMeasureLabel);

        xyLineChart.getXYPlot().addAnnotation(bestDoubleExponentialSmoothingLabel);
        xyLineChart.getXYPlot().addAnnotation(bestDoubleExponentialTrendSmoothingLabel);
        xyLineChart.getXYPlot().addAnnotation(bestDoubleExponentialSmoothingErrorMeasureLabel);

        setContentPane(chartPanel);
    }

    private XYDataset dataset() throws IOException {
        List<Double> swords = Forecasting.getSwordSales();

        // Simple Smoothing
        double bestSmoothingCoefficient = simpleExponentialSmoothing.getBestSmoothingCoefficient(swords);
        simpleExponentialSmoothing.setSmoothingCoefficient(bestSmoothingCoefficient);
        List<Double> smoothedValues = simpleExponentialSmoothing.calculateSES(swords);

        // Double Smoothing
        doubleExponentialSmoothing.getBestDoubleSmoothingCoefficientFactors(swords);
        double bestDoubleSmoothingCoefficient = doubleExponentialSmoothing.getBestFactors(0);
        double bestTrendCoefficient = doubleExponentialSmoothing.getBestFactors(1);
        doubleExponentialSmoothing.setTrendCoefficient(bestTrendCoefficient);
        doubleExponentialSmoothing.setDoubleSmoothingCoefficient(bestDoubleSmoothingCoefficient);
        doubleExponentialSmoothing.calculateDES(swords);

        List<Double> doubleSmoothedValues = doubleExponentialSmoothing.getForecastValues();

        final XYSeries originalTimeSeries = new XYSeries("Original time series");
        final XYSeries smoothedTimeSeries = new XYSeries("SES");
        final XYSeries doubleSmoothedTimeSeries = new XYSeries("DES");

        for (int i = 0; i < swords.size(); i++) {
            originalTimeSeries.add(i, swords.get(i));
        }

        for (int i = 0; i < doubleSmoothedValues.size(); i++) {
            doubleSmoothedTimeSeries.add(i, doubleSmoothedValues.get(i));
            smoothedTimeSeries.add(i, smoothedValues.get(i));
        }

        final XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(originalTimeSeries);
        //dataset.addSeries(smoothedTimeSeries);
        dataset.addSeries(doubleSmoothedTimeSeries);

        return dataset;
    }
}
