package data;

import point.Point;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PointReader {
    private List<Point> points;

   public List<Point> readCsv() throws FileNotFoundException {
       Scanner scanner = new Scanner(new File("WineData.csv"));
       while (scanner.hasNext()) {
           points = new ArrayList<>();
           List<String[]> lines = new ArrayList<>();

           while(scanner.hasNextLine()) {
               String line = scanner.nextLine();
               if(line != null && !line.isEmpty()) {
                   lines.add(line.split(","));
               }
           }
           parseLines(lines);
       }
       scanner.close();
       return points;
   }

    private void parseLines(List<String[]> lines) {
        for(int i = 0; i < lines.get(0).length; i++) {
            ArrayList<Double> values = new ArrayList<>(lines.size());
            for(int j = 0; j < lines.size(); j++) {
                values.add(Double.parseDouble(lines.get(j)[i]));
            }
            points.add(new Point(values));
        }
    }

}
