package cluster;

import distance.SquaredEuclidianDistance;
import point.Point;

import java.util.ArrayList;
import java.util.List;

public class Cluster {
    private SquaredEuclidianDistance squaredEuclidianDistance = new SquaredEuclidianDistance();
    private List<Point> points;
    private Point centroid;
    private int id;

    public Cluster(int id) {
        this.points = new ArrayList<>();
        this.centroid = null;
        this.id = id;
    }

    public void addPoint(Point point) {
        points.add(point);
    }

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }

    public Point getCentroid() {
        return centroid;
    }

    public void setCentroid(Point centroid) {
        this.centroid = centroid;
    }

    public void clearPoints() {
        this.points.clear();
    }

    public double calculateSSE() {
        double sum = 0;

        for(Point point : points) {
            sum += squaredEuclidianDistance.calculate(point, centroid);
        }
        return sum;
    }
}
