package graph;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;

public class LineChart extends ApplicationFrame {
    final XYSeriesCollection dataset = new XYSeriesCollection( );
    public LineChart(String title, String chartTitle) {
        super(title);
        JFreeChart xylineChart = ChartFactory.createXYLineChart(
                chartTitle ,
                "Month Number" ,
                "Sword Sale" ,
                dataset ,
                PlotOrientation.VERTICAL ,
                true , true , false);

        ChartPanel chartPanel = new ChartPanel( xylineChart );
        chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
        setContentPane( chartPanel );
    }
}
