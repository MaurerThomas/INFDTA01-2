package cluster;

import point.Point;

import java.util.ArrayList;
import java.util.List;

public class Cluster {
    public List<Point> points;
    public Point centroid;
    public int id;

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

}
