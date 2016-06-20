package distance;

import point.Point;

import java.util.List;

public class EuclideanDistance {

    public double calculate(Point point, Point centroid) {
        double sum = 0;
        List<Double> purchases = point.getPurchases();
        List<Double> centroidPurchases = centroid.getPurchases();

        for (int i = 0; i < purchases.size() && i < centroidPurchases.size(); i++) {
            double d = purchases.get(i) - centroidPurchases.get(i);
            sum += d * d;
        }
        return Math.sqrt(sum);
    }
}
