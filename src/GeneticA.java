import geneticAlgorithm.Algorithm;
import member.Population;

public class GeneticA {

    public static void main(String[] args) {
        double crossoverRate = 0.90;
        double mutationRate = 0.05;
        boolean elitism = true;
        int iterations = 50;

        Population population = new Population(50, true);
        Algorithm algorithm = new Algorithm(crossoverRate, mutationRate, elitism, iterations);
        Population evovledPopulation = algorithm.evolvePopulation(population);
        postProcess(evovledPopulation);
    }

    private static void postProcess(Population evolvedPopulation) {
        double averageFitness = 0;

        for (int i = 0; i < evolvedPopulation.getPopulationSize(); i++) {
            averageFitness += evolvedPopulation.getIndividual(i).getFitness();
        }
        averageFitness /= evolvedPopulation.getPopulationSize();

        System.out.println("Average fitness: " + averageFitness);
        System.out.println("Best fitness: " + evolvedPopulation.getFittestIndividual().getFitness());
        System.out.println("Best individual: " + evolvedPopulation.getFittestIndividual());

    }
}
