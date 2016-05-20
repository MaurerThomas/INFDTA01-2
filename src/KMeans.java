import data.PointReader;

import java.io.FileNotFoundException;

public class KMeans {

    public static void main(String[] args) {

        PointReader pointReader = new PointReader();
        try {
            pointReader.readCsv();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("test");
    }



}
