package part3.smoothing;

import java.util.ArrayList;
import java.util.List;

public class DoubleExponentialSmoothing {
    private double doubleSmoothingCoefficient;
    private double trendCoefficient;
    private List<Double> smoothedValues = new ArrayList<>();
    private List<Double> trendValues = new ArrayList<>();
    private List<Double> originalValues = new ArrayList<>();
    private List<Double> forecastValues = new ArrayList<>();
    private double errorMeasure = 0;
    private double[] bestFactors = new double[2];

    public void calculateDES(List<Double> originalValues) {
        this.originalValues = originalValues;
        smoothedValues.clear();
        trendValues.clear();

        for (int i = 1; i < originalValues.size(); i++) {
            double smoothedValue;
            double trendValue;
            if (i == 1) {
                smoothedValues.add(null);
                smoothedValues.add(originalValues.get(i));
                trendValues.add(null);
                trendValues.add(originalValues.get(i) - originalValues.get(i - 1));
            } else {
                smoothedValue = (doubleSmoothingCoefficient * originalValues.get(i)) + (1 - doubleSmoothingCoefficient) * (smoothedValues.get(i - 1) + trendValues.get(i - 1));
                trendValue = (trendCoefficient * (smoothedValue - smoothedValues.get(i - 1))) + ((1 - trendCoefficient) * trendValues.get(i - 1));
                smoothedValues.add(smoothedValue);
                trendValues.add(trendValue);
            }
        }
        calculateForecast();

    }

    public double[] getBestDoubleSmoothingCoefficientFactors(List<Double> originalValues) {
        double bestSmoothingCoefficient = 0;
        double bestTrendCoefficient = 0;
        //TODO: Fix lowestErrorMeasure so that is not a random high number.
        double lowestErrorMeasure = 500000;

        this.originalValues = originalValues;

        for (int i = 1; i < 100; i++) {
            doubleSmoothingCoefficient = i / 100d;

            for (int j = 1; j < 100; j++) {
                trendCoefficient = j / 100d;

                smoothedValues.clear();
                trendValues.clear();

                calculateDES(originalValues);

                if (errorMeasure < lowestErrorMeasure) {
                    lowestErrorMeasure = errorMeasure;

                    bestTrendCoefficient = trendCoefficient;
                    bestSmoothingCoefficient = doubleSmoothingCoefficient;
                }
            }
        }

        bestFactors[0] = bestSmoothingCoefficient;
        bestFactors[1] = bestTrendCoefficient;
        return bestFactors;
    }

    private List<Double> calculateForecast() {
        double forecast;
        forecastValues.clear();
        forecastValues.add(null);
        forecastValues.add(null);

        for (int i = 1; i < originalValues.size()+8; i++) {
            if (i < originalValues.size()) {
                forecast = smoothedValues.get(i) + trendValues.get(i);
            } else {
                forecast = (smoothedValues.get(originalValues.size() - 1)) + (trendValues.get(originalValues.size() - 1) * (i - (originalValues.size() - 1)));
            }
            forecastValues.add(forecast);
        }
        // Dirty fix , fix this if we know the answer to the problem.
        forecastValues.remove(originalValues.size()+1);
        setSumOfSquaredErrors();
        return forecastValues;
    }

    private void setSumOfSquaredErrors() {
        double sum = 0;

        for (int i = 0; i < originalValues.size()-2; i++) {
            double d = forecastValues.get(i+2) - originalValues.get(i+2);
            sum += d * d;
        }

        errorMeasure = Math.sqrt(sum / (originalValues.size() - 2));
    }

    public List<Double> getSmoothedValues() {
        return smoothedValues;
    }

    public void setDoubleSmoothingCoefficient(double doubleSmoothingCoefficient) {
        this.doubleSmoothingCoefficient = doubleSmoothingCoefficient;
    }

    public void setTrendCoefficient(double trendCoefficient) {
        this.trendCoefficient = trendCoefficient;
    }

    public double getBestFactors(int index) {
        return bestFactors[index];
    }

    public List<Double> getForecastValues() {
        return forecastValues;
    }

    public double getErrorMeasure() {
        return errorMeasure;
    }
}
