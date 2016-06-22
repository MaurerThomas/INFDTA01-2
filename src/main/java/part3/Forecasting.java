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
//        List<String[]> lines = new ArrayList<>();
//
//        for (int i = 0; i <= 10245; i++) {
//            String line = scanner.nextLine();
//            if (line != null && !line.isEmpty()) {
//                lines.add(line.split(","));
//            }
//        }

        while (scanner.hasNextLine()) {
            swordSales.add(scanner.nextDouble());
        }
        scanner.close();

//        CSVReader reader = new CSVReader(new FileReader("forecastingWalmart.csv"), ',', '\'', 1);
//        String [] nextLine;
//        while ((nextLine = reader.readNext()) != null) {
//            // nextLine[] is an array of values from the line
//            if(nextLine[0].equals("1")) {
//                //System.out.println(nextLine[3]);
//                swordSales.add(Double.parseDouble(nextLine[3]));
//            }
//        }
        return swordSales;
    }
}
