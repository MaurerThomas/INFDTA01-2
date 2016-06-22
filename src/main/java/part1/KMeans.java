package part1;

import part1.cluster.Cluster;
import part1.cluster.ClusterCreator;
import data.PointReader;
import part1.point.Point;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class KMeans {
    private Logger logger = Logger.getLogger("myLogger");


    public static void main(String[] args) {
        KMeans kMeans = new KMeans();
        kMeans.init();

    }

    /**
     * Returns a sorted TreeMap from highest to lowest.
     * Source: http://stackoverflow.com/a/2581754
     *
     * @param map The TreeMap.
     * @param <K> TreeMap Key.
     * @param <V> TreeMap Value.
     * @return A sorted TreeMap.
     */
    private static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        List<Map.Entry<K, V>> list =
                new LinkedList<>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
            @Override
            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

    private void init() {
        ClusterCreator clusterCreator;
        try {
            // Create Points.
            clusterCreator = new ClusterCreator(new PointReader().readCsv());
            // Set Random Centroids.
            clusterCreator.initRandomCentroids();
            clusterCreator.createClusters(200);
            postProcesStep(clusterCreator);
        } catch (FileNotFoundException e) {
            logger.log(Level.SEVERE, "Could not find the file: ", e);
        }
    }

    private void postProcesStep(ClusterCreator clusterCreator) {
        Map<Integer, Double> totalPurchasesMap = new TreeMap();
        List<Cluster> clusters = clusterCreator.getBestNumberOfClusters();

        System.out.println("Best SSE: " + clusterCreator.getBestSSE());

        for (Cluster cluster : clusters) {
            for (Point point : cluster.getPoints()) {
                List<Double> purchases = point.getPurchases();

                for (int i = 0; i < purchases.size(); i++) {
                    if (totalPurchasesMap.containsKey(i)) {
                        totalPurchasesMap.put(i, (purchases.get(i) + totalPurchasesMap.get(i)));
                    } else {
                        totalPurchasesMap.put(i, purchases.get(i));
                    }
                }
            }

            Map<Integer, Double> temp = sortByValue(totalPurchasesMap);

            System.out.println("\n" + "Cluster: " + cluster.getId() + "\n");
            for (Map.Entry<Integer, Double> entry : temp.entrySet()) {
                int sale = entry.getKey();
                double purchase = entry.getValue();

                if (purchase > 3) {
                    System.out.println("OFFER " + (sale + 1) + " bought " + purchase + " times");
                }
            }

        }

    }
}
