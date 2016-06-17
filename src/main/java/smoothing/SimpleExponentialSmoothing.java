package smoothing;

import java.util.ArrayList;
import java.util.List;

public class SimpleExponentialSmoothing {
    private double smoothingCoefficient = 0.5;
    private List<Double> originalValues;
    private List<Double> smoothedValues = new ArrayList<>();

    public List<Double> calculateSES(List<Double> originalValues) {
        //S_T
        this.originalValues = originalValues;

        //Starting point
        double startingPoint = getStartingPoint(originalValues);
        smoothedValues.add(startingPoint);

        for (int i = 0; i < originalValues.size(); i++) {
            double smoothedValue;
            smoothedValue = (smoothingCoefficient * originalValues.get(i)) + ((1 - smoothingCoefficient) * smoothedValues.get((i)));
            smoothedValues.add(smoothedValue);
        }

        double SSE = getSumOfSquaredErrors();

        return smoothedValues;
    }

    private double getStartingPoint(List<Double> originalValues) {
        double startingPoint = 0;

        for (int i = 0; i < 12; i++) {
            startingPoint += originalValues.get(i);
        }
        startingPoint /= 12;

        return startingPoint;
    }

    private double getSumOfSquaredErrors(){
        double sum = 0;

        for(int i = 0;i < originalValues.size(); i++){
            double d = smoothedValues.get(i) - originalValues.get(i);
            sum += d*d;
        }

        return Math.sqrt(sum / (originalValues.size()-1));
    }
}
