package point;

import distance.EuclideanDistance;

import java.util.List;

public class Point {
    private static EuclideanDistance euclideanDistance = new EuclideanDistance();
    private List<Double> purchases;
    private double distance;

    public Point(List<Double> purchases) {
        this.purchases = purchases;
    }

    public static double distance(Point point, Point centroid) {
        return euclideanDistance.calculate(point, centroid);
    }

    public List<Double> getPurchases() {
        return purchases;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

}
