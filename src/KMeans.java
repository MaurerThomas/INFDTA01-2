import cluster.ClusterCreator;
import data.PointReader;
import point.Point;

import java.io.FileNotFoundException;
import java.util.List;

public class KMeans {


    public static void main(String[] args) {
        initClusters();

        System.out.println("test");
    }

    private static void initClusters() {
        PointReader pointReader = new PointReader();
        ClusterCreator clusterCreator;
        List<Point> points;

        try {
            points = pointReader.readCsv();
            clusterCreator = new ClusterCreator(points);
            clusterCreator.initRandomClusters();
            clusterCreator.createClusters(5);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("kaas");

    }

}
