package point;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thomas on 15-5-2016.
 */
public class Point {
    private ArrayList<Integer> customerPoints;

    public Point(ArrayList<Integer> customerPoints) {
        this.customerPoints = customerPoints;
    }

    public List<Integer> getCustomerPoints() {
        return customerPoints;
    }


}
