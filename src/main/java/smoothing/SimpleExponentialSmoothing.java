package smoothing;

import java.util.ArrayList;
import java.util.List;

public class SimpleExponentialSmoothing {
    private double smoothingCoefficient;
    private List<Double> originalValues;
    private List<Double> smoothedValues = new ArrayList<>();
    private double SSE = 0;

    public List<Double> calculateSES(List<Double> originalValues) {
        //Starting point
        double startingPoint = getStartingPoint(originalValues);
        smoothedValues.add(startingPoint);


        for (int i = 0; i < originalValues.size(); i++) {
            double smoothedValue;
            smoothedValue = (smoothingCoefficient * originalValues.get(i)) + ((1 - smoothingCoefficient) * smoothedValues.get((i)));
            smoothedValues.add(smoothedValue);
        }

        SSE = getSumOfSquaredErrors();

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

    public double getBestSmoothingCoefficient(List<Double> originalValues) {
        double currentSumOfSquaredErrors;
        double bestSmoothingCoefficient = 0;
        this.originalValues = originalValues;

        for (int i = 1; i < 100; i++) {
            smoothingCoefficient = i / 100d;
            currentSumOfSquaredErrors = SSE;
            smoothedValues.clear();

            calculateSES(originalValues);

            if (SSE < currentSumOfSquaredErrors) {
                bestSmoothingCoefficient = smoothingCoefficient;
            }
        }
        return bestSmoothingCoefficient;
    }

    private double getSumOfSquaredErrors() {
        double sum = 0;

        for (int i = 0; i < originalValues.size(); i++) {
            double d = smoothedValues.get(i) - originalValues.get(i);
            sum += d * d;
        }

        return Math.sqrt(sum / (originalValues.size() - 1));
    }

    public void setSmoothingCoefficient(double smoothingCoefficient) {
        this.smoothingCoefficient = smoothingCoefficient;
    }

    public double getSmoothingCoefficient() {
        return smoothingCoefficient;
    }

    public double getSSE() {
        return SSE;
    }

}

