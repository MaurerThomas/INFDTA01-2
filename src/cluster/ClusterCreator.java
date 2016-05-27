package cluster;

import point.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ClusterCreator {
    private int MAX_CLUSTERS = 4;
    private List<Cluster> clusters;
    private List<Point> allPoints;

    public ClusterCreator(List<Point> allPoints) {
        this.clusters = new ArrayList<>();
        this.allPoints = allPoints;
    }

    public List<Cluster> initRandomCentroids() {
        // Create random centroids, this initializes the process.
        Random randomGenerator = new Random();
        clusters = new ArrayList<>();
        for (int i = 0; i < MAX_CLUSTERS; i++) {
            int index = randomGenerator.nextInt(allPoints.size());
            Cluster cluster = new Cluster(i);
            setCentroidAtIndex(index, cluster);
            clusters.add(cluster);
        }
        assignPointsToCluster();
        return clusters;
    }

    public List<Cluster> createClusters(int iterations) {
        double maximum = Double.MAX_VALUE;
        List<Cluster> bestNumberOfClusters = null;

        for (int i = 0; i < iterations; i++) {
            clusters = initClusters();
            double sumSquaredErrors = getSSE(clusters);
             if (sumSquaredErrors < maximum) {
                 maximum = sumSquaredErrors;
                 bestNumberOfClusters = clusters;
             }
        }
        return bestNumberOfClusters;
        }



    public List<Cluster> initClusters() {
        // Calculate new centroids.
        for (int i = 0; i < MAX_CLUSTERS; i++) {
            Cluster cluster = new Cluster(i);
            List<Double> purchasesMean = calculateMean(clusters.get(i));
            Point centroid = new Point(purchasesMean);
            cluster.setCentroid(centroid);
            clusters.set(i, cluster);
        }
        // Assign allPoints to the new, closer cluster
        assignPointsToCluster();
        return clusters;
    }

    private List<Double> calculateMean(Cluster cluster) {
        List<Double> purchasesMeanTemp = new ArrayList<>();
        List<Point> clusterPoints = cluster.getPoints();
        double numberOfPoints = clusterPoints.size();

        if(purchasesMeanTemp.isEmpty()){
            for(int i = 0; i < 32; i++)
                purchasesMeanTemp.add(0d);
        }

        for (Point point : clusterPoints) {
            List<Double> purchases = point.getPurchases();

            for (int i = 0; i < purchases.size(); i++) {
                purchasesMeanTemp.set(i, purchases.get(i) + purchasesMeanTemp.get(i));
            }
        }

        for (int i = 0; i < purchasesMeanTemp.size(); i++) {
            double purchasesMeanTempVal = purchasesMeanTemp.get(i);
            purchasesMeanTempVal = purchasesMeanTempVal / numberOfPoints;

            purchasesMeanTemp.set(i, purchasesMeanTempVal);
        }

        return purchasesMeanTemp;
    }

    private void assignPointsToCluster() {
        double maximum = Double.MAX_VALUE;
        double min;
        double distance;
        int cluster = 0;
        clearClusters();
        for (Point point : allPoints) {
            min = maximum;
            for (int i = 0; i < MAX_CLUSTERS; i++) {
                Cluster c = clusters.get(i);
                distance = Point.distance(point, c.getCentroid());
                if (distance < min) {
                    point.setDistance(distance);
                    min = distance;
                    cluster = i;
                }
            }
            clusters.get(cluster).addPoint(point);
        }
    }

    private void setCentroidAtIndex(int index, Cluster cluster) {
        cluster.setCentroid(allPoints.get(index));
    }

    private void clearClusters() {
        for(Cluster cluster : clusters) {
            cluster.clearPoints();
        }
    }

    private double getSSE(List<Cluster> clusters) {
        double sum = 0;
        for(Cluster c : clusters) {
            sum += c.calculateSSE(c.getPoints());
        }
        return sum;
    }


}
