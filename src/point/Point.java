package point;

import java.util.List;

public class Point {
    private List<Double> customerPoints;

    public Point(List<Double> customerPoints) {
        this.customerPoints = customerPoints;
    }

    public List<Double> getCustomerPoints() {
        return customerPoints;
    }

}
