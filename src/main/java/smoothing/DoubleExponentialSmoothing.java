package smoothing;


import java.util.ArrayList;
import java.util.List;

public class DoubleExponentialSmoothing {
    private double doubleSmoothingCoefficient;
    private double trendCoefficient;
    private List<Double> smoothedValues = new ArrayList<>();
    private List<Double> trendValues = new ArrayList<>();
    private List<Double> originalValues = new ArrayList<>();



    private List<Double> forecastValues = new ArrayList<>();

    private double SSE = 0;
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
                smoothedValue = (doubleSmoothingCoefficient * originalValues.get(i)) + (1 - doubleSmoothingCoefficient) * (smoothedValues.get(i-1) + trendValues.get(i-1));
                trendValue = (trendCoefficient * (smoothedValue -  smoothedValues.get(i-1))) + ((1-trendCoefficient)* trendValues.get(i-1));
                smoothedValues.add(smoothedValue);
                trendValues.add(trendValue);
            }
        }
        calculateForecast();

    }

    public double[] getBestDoubleSmoothingCoefficientFactors(List<Double> originalValues) {
        double currentSumOfSquaredErrors;
        double bestSmoothingCoefficient = 0;
        double bestTrendCoefficient = 0;

        this.originalValues = originalValues;

        for (int i = 1; i < 100; i++) {
            doubleSmoothingCoefficient = i / 100d;

            currentSumOfSquaredErrors = SSE;
            smoothedValues.clear();
            trendValues.clear();

            calculateDES(originalValues);

            if (SSE < currentSumOfSquaredErrors) {
                bestSmoothingCoefficient = doubleSmoothingCoefficient;
            }

        }
        for (int i = 1; i < 100; i++) {
            trendCoefficient = i / 100d;
            doubleSmoothingCoefficient = bestSmoothingCoefficient;

            currentSumOfSquaredErrors = SSE;
            smoothedValues.clear();
            trendValues.clear();

            calculateDES(originalValues);

            if (SSE < currentSumOfSquaredErrors) {
                bestTrendCoefficient = trendCoefficient;
            }
        }

        bestFactors[0] = bestSmoothingCoefficient;
        bestFactors[1] = bestTrendCoefficient;
        return bestFactors;
    }

    private List<Double> calculateForecast() {
        double forecast;
        forecastValues.clear();
        for(int i = 1; i < 48; i++) {
            if(i < originalValues.size()){
               forecast = smoothedValues.get(i) + trendValues.get(i);
            }else{
               forecast = (smoothedValues.get(originalValues.size() - 1)) + (trendValues.get(originalValues.size() - 1) * (i- (originalValues.size() -1)));
            }
            forecastValues.add(forecast);
        }
        setSumOfSquaredErrors();
        return forecastValues;
    }

    private void setSumOfSquaredErrors() {
        double sum = 0;

        for (int i = 1; i < originalValues.size(); i++) {
            double d = forecastValues.get(i) - originalValues.get(i);
            sum += d * d;
        }
        SSE = Math.sqrt(sum / (originalValues.size() - 1));
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
}
