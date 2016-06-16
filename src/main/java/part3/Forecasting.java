package part3;

import graph.LineChart;
import org.jfree.ui.RefineryUtilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Forecasting {

    public static void main(String[] args) throws FileNotFoundException {
        LineChart chart = new LineChart("Sword Forecasting", "Forecast of swords");
        chart.pack();
        RefineryUtilities.centerFrameOnScreen( chart );
        chart.setVisible( true );
        getSwordSales();
    }


    private static List<Double> getSwordSales() throws FileNotFoundException {
        List<Double> swordSales = new ArrayList<>();
        Scanner scanner = new Scanner(new File("swordforecasting.csv"));
        while(scanner.hasNextLine()) {
            swordSales.add(scanner.nextDouble());
        }
        scanner.close();
        return swordSales;
    }
}
