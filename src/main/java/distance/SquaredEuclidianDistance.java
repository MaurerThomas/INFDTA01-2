package distance;

import point.Point;

import java.util.List;

public class SquaredEuclidianDistance {

    public double calculate(Point point, Point centroid) {
        double sum = 0;
        List<Double> purchases = point.getPurchases();
        List<Double> centroidCustomerPoints = centroid.getPurchases();
        for (int i = 0; i < purchases.size() && i < centroidCustomerPoints.size(); i++) {
            double d = purchases.get(i) - centroidCustomerPoints.get(i);
            sum += d * d;
        }
        return sum;
    }
}
