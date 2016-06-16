package point;

import distance.EuclideanDistance;

import java.util.List;

public class Point {
    private List<Double> purchases;
    private static EuclideanDistance euclideanDistance = new EuclideanDistance();
    private double distance;

    public Point(List<Double> purchases) {
        this.purchases = purchases;
    }

    public List<Double> getPurchases() {
        return purchases;
    }

    public static double distance(Point point, Point centroid) {
        return euclideanDistance.calculate(point, centroid);
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

}
