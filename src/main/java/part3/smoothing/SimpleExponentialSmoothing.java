package part3.smoothing;

import java.util.ArrayList;
import java.util.List;

public class SimpleExponentialSmoothing {
    private double smoothingCoefficient;
    private List<Double> originalValues;
    private List<Double> smoothedValues = new ArrayList<>();
    private double errorMeasure = 0;

    public List<Double> calculateSES(List<Double> originalValues) {
        //Starting part1.point
        double startingPoint = getStartingPoint(originalValues);
        smoothedValues.add(startingPoint);

        for (int i = 0; i < originalValues.size(); i++) {
            double smoothedValue;
            smoothedValue = (smoothingCoefficient * originalValues.get(i)) + ((1 - smoothingCoefficient) * smoothedValues.get((i)));
            smoothedValues.add(smoothedValue);
        }

        double forecastedValue = (smoothingCoefficient * originalValues.get(originalValues.size() - 1)) + ((1 - smoothingCoefficient) * smoothedValues.get(originalValues.size() - 1));

        for (int i = 36; i < 47; i++) {
            smoothedValues.add(forecastedValue);
        }

        errorMeasure = getSumOfSquaredErrors();

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
            currentSumOfSquaredErrors = errorMeasure;
            smoothedValues.clear();

            calculateSES(originalValues);

            if (errorMeasure < currentSumOfSquaredErrors) {
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

    public double getSmoothingCoefficient() {
        return smoothingCoefficient;
    }

    public void setSmoothingCoefficient(double smoothingCoefficient) {
        this.smoothingCoefficient = smoothingCoefficient;
    }

    public double getErrorMeasure() {
        return errorMeasure;
    }

}

