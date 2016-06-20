package part2;

import part2.geneticalgorithm.Algorithm;
import part2.member.Population;

public class GeneticA {

    public static void main(String[] args) {
        double crossoverRate = 0.90;
        double mutationRate = 0.05;
        boolean elitism = true;
        int iterations = 50;

        Population population = new Population(20, true);
        Algorithm algorithm = new Algorithm(crossoverRate, mutationRate, elitism, iterations);
        Population evolvedPopulation = algorithm.evolvePopulation(population);
        postProcess(evolvedPopulation);
    }

    private static void postProcess(Population evolvedPopulation) {
        double averageFitness = 0;

        for (int i = 0; i < evolvedPopulation.getPopulationSize(); i++) {
            averageFitness += evolvedPopulation.getIndividual(i).getFitness();
        }
        averageFitness /= evolvedPopulation.getPopulationSize();

        //TODO: Average fitness moet dichtbij de best fitness liggen.
        //TODO: Andere selectiemethode gebruiken.

        System.out.println("Average fitness: " + averageFitness);
        System.out.println("Best fitness: " + evolvedPopulation.getFittestIndividual().getFitness());
        System.out.println("Best individual: " + evolvedPopulation.getFittestIndividual());

    }
}
