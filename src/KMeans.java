import cluster.ClusterCreator;
import data.PointReader;
import point.Point;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class KMeans {
    private Logger logger = Logger.getLogger("myLogger");


    public static void main(String[] args) {
        KMeans kMeans = new KMeans();
        kMeans.init();

    }

    private void init() {
        ClusterCreator clusterCreator;
        try {
            // Create Points.
            clusterCreator = new ClusterCreator(new PointReader().readCsv());
            // Set Random Centroids.
            clusterCreator.initRandomCentroids();
            clusterCreator.createClusters(10);
        } catch (FileNotFoundException e) {
            logger.log(Level.SEVERE, "Could not find the file: ", e);
        }
    }



}
