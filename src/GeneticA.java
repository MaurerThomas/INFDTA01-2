import geneticAlgorithm.Algorithm;
import member.Population;

public class GeneticA {

    public static void main(String[] args) {
        Population population = new Population(50, true);
        Algorithm algorithm = new Algorithm();
        algorithm.evolvePopulation(population);
    }
}
