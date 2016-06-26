package data;

import part1.point.Point;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PointReader {
    private List<Point> points;

    public List<Point> readCsv() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("a2.csv"));
        while (scanner.hasNext()) {
            points = new ArrayList<>();
            List<String[]> lines = new ArrayList<>();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line != null && !line.isEmpty()) {
                    lines.add(line.split(","));
                }
            }

            parseLines(lines);
        }
        scanner.close();
        return points;
    }

    private void parseLines(List<String[]> lines) {
        for (String[] vals : lines) {
            List<Double> values = new ArrayList<>(lines.size());

            for (int i = 0; i < vals.length; i++) {
                double value = Double.parseDouble(vals[i]);
                values.add(value);
            }

            points.add(new Point(values));
        }
    }

}
