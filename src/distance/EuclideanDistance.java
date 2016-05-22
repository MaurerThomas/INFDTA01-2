package distance;

import point.Point;

import java.util.List;

public class EuclideanDistance {

    public double calculate(Point point, Point centroid) {
        double sum = 0;
        List<Double> customerPoints = point.getCustomerPoints();
        List<Double> centroidCustomerPoints = centroid.getCustomerPoints();

        for (int i = 0; i < customerPoints.size() && i < centroidCustomerPoints.size(); i++) {
            double d = customerPoints.get(i) - centroidCustomerPoints.get(i);
            sum += Math.pow(d, d);
        }
        return Math.sqrt(sum);
    }
}
