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
            cluster.setCentroid(points.get(index));
            clusters.add(cluster);
        }
        return clusters;
    }
}
