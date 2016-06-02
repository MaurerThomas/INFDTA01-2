import cluster.Cluster;
import cluster.ClusterCreator;
import data.PointReader;
import point.Point;

import java.io.FileNotFoundException;
import java.util.ArrayList;
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
            clusterCreator.createClusters(100);
            postProcesStep(clusterCreator.getBestNumberOfClusters());
        } catch (FileNotFoundException e) {
            logger.log(Level.SEVERE, "Could not find the file: ", e);
        }
    }


    private void postProcesStep(List<Cluster> clusters) {
        List<Double> totalPurchases = new ArrayList<>();
        for (Cluster cluster: clusters) {
            for (Point point: cluster.getPoints()) {
                List<Double> purchases = point.getPurchases();

                for(int i =0 ; i < purchases.size(); i++){
                    if (totalPurchases.isEmpty()) {
                        totalPurchases = purchases;
                    } else {
                        totalPurchases.set(i, purchases.get(i) + totalPurchases.get(i));
                    }
                }
            }
            ////TODO: Sort totalpurchases.
            ////Print alle purchases die > 3 keer gekocht zijn.
            System.out.println("iiii");
        }



    }


}
