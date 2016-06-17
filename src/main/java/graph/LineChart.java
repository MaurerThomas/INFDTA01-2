package graph;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import part3.Forecasting;
import smoothing.SimpleExponentialSmoothing;

import java.io.FileNotFoundException;
import java.util.List;

import static part3.Forecasting.getSwordSales;

public class LineChart extends ApplicationFrame {

    public LineChart(String title, String chartTitle) throws FileNotFoundException {
        super(title);
        JFreeChart xyLineChart = ChartFactory.createXYLineChart(
                chartTitle,
                "Month Number",
                "Sword Sale",
                dataset(),
                PlotOrientation.VERTICAL ,
                true, true, false);

        ChartPanel chartPanel = new ChartPanel(xyLineChart);
        chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
        setContentPane( chartPanel );
    }

    private XYDataset dataset() throws FileNotFoundException{
        SimpleExponentialSmoothing SES = new SimpleExponentialSmoothing();
        List<Double> swords = Forecasting.getSwordSales();
        List<Double> smoothedValued = SES.calculateSES(swords);

        final XYSeries originalTimeSeries = new XYSeries("Original time series");
        final XYSeries smoothedTimeSeries = new XYSeries("SES");
        for(int i = 0; i < swords.size(); i++){
            originalTimeSeries.add(i, swords.get(i));
            smoothedTimeSeries.add(i, smoothedValued.get(i));
        }

        final XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(originalTimeSeries);
        dataset.addSeries(smoothedTimeSeries);

        return dataset;
    }
}
