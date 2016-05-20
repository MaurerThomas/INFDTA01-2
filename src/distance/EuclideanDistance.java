package distance;

import point.Point;

import java.util.List;

public class EuclideanDistance {

    public double getDistance(Point point, Point centroid) {
        double sum = 0;
        List<Double> points = point.getCustomerPoints();
        List<Double> centroidPoints = centroid.getCustomerPoints();

        for (int i = 0; i < points.size() && i < centroidPoints.size(); i++) {
            double d = points.get(i) - centroidPoints.get(i);
            sum += Math.pow(d, d);
        }

        return Math.sqrt(sum);
    }
}
