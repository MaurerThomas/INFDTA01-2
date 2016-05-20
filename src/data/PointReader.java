package data;


import point.Point;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;



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


    public List<List<String>> read() throws FileNotFoundException {

        try (BufferedReader reader = new BufferedReader(source)) {

            return reader.lines()
                    .map(line -> Arrays.asList(line.split(",")))
                    .collect(Collectors.toList());

        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }



    private void parseLines(List<String[]> lines) {
        for(int i = 0; i < lines.get(0).length; i++) {
            ArrayList<Integer> values = new ArrayList<>(lines.size());

            for(int j = 0; j < lines.size(); j++) {
                values.add(Integer.parseInt(lines.get(j)[i]));
            }
            points.add(new Point(values));
        }
    }


}
