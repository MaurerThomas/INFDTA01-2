package part3;

import com.opencsv.CSVReader;
import part3.graph.LineChart;
import org.jfree.ui.RefineryUtilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Forecasting {

    public static void main(String[] args) throws IOException {
        LineChart chart = new LineChart("Forecasting", "Forecast of swords");
        chart.pack();
        RefineryUtilities.centerFrameOnScreen(chart);
        chart.setVisible(true);
    }

    public static List<Double> getSwordSales() throws IOException {
        List<Double> swordSales = new ArrayList<>();
        Scanner scanner = new Scanner(new File("swordforecasting.csv"));

        while (scanner.hasNextLine()) {
            swordSales.add(scanner.nextDouble());
        }
        scanner.close();

        return swordSales;
    }

    //Method for loading another dataset (forecastingWalmart.csv)
//    public static List<Double> getSwordSales() throws IOException {
//        List<Double> swordSales = new ArrayList<>();
//        CSVReader reader = new CSVReader(new FileReader("forecastingWalmart.csv"), ',', '\'', 1);
//        String [] nextLine;
//
//        while ((nextLine = reader.readNext()) != null) {
//            if(nextLine[0].equals("1") && nextLine[1].equals("2")) {
//                swordSales.add(Double.parseDouble(nextLine[3]));
//            }
//        }
//        reader.close();
//
//        return swordSales;
//    }
}
