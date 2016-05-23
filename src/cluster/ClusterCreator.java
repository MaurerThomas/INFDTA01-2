package cluster;

import point.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ClusterCreator {
    private int MAX_CLUSTERS = 4;
    private List<Cluster> clusters;
    private List<Point> points;

    public ClusterCreator(List<Point> points) {
        this.clusters = new ArrayList<>();
        this.points = points;
    }

    public void addCluster(Cluster cluster) {
        clusters.add(cluster);
    }

    public List<Cluster> getClusters() {
        return clusters;
    }

    public void setClusters(List<Cluster> clusters) {
        this.clusters = clusters;
    }

    public List<Cluster> initClusters() {
        Random randomGenerator = new Random();
        clusters = new ArrayList<>();
        for (int i = 0; i < MAX_CLUSTERS; i++) {
            int index = randomGenerator.nextInt(points.size());
            Cluster cluster = new Cluster(i);
            setCentroid(index, cluster);
            clusters.add(cluster);
        }
        assignPointsToCluster();
        return clusters;
    }

    private void setCentroid(int index, Cluster cluster) {
        cluster.setCentroid(points.get(index));
    }

    private void assignPointsToCluster() {
        double maximum = Double.MAX_VALUE;
        double min;
        double distance;
        int cluster = 0;

        for (Point point : points) {
            min = maximum;
            for (int i = 0; i < MAX_CLUSTERS; i++) {
                Cluster c = clusters.get(i);
                distance = Point.distance(point, c.getCentroid());
                if (distance < min) {
                    point.setDistance(distance);
                    min = distance;
                    cluster = i;
                }
                c.calculateSSE();
            }
            clusters.get(cluster).addPoint(point);
        }
    }

     public List<Cluster> createClusters(int iterations) {
         double maximum = Double.MAX_VALUE;
         List<Cluster> bestNumberOfClusters = null;

         for(int i =0; i < iterations;i++) {
             clusters = initClusters();
             //double sumSquaredErrors = getSSE();
//             if (sumSquaredErrors < maximum) {
//                 maximum = sumSquaredErrors;
//                 bestNumberOfClusters = clusters;
//             }
         }
        return bestNumberOfClusters;
     }
}
