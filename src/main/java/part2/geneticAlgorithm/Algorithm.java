package part2.geneticalgorithm;

import part2.member.Individual;
import part2.member.Population;

import java.util.Random;

public class Algorithm {
    private static final int TOURNAMENT_SIZE = 5;
    private static final int NUMBER_OF_BITS = 5;
    private double crossoverRate;
    private double mutationRate;
    private boolean elitism;
    private int iterations;

    public Algorithm(double crossoverRate, double mutationRate, boolean elitism, int iterations) {
        this.crossoverRate = crossoverRate;
        this.mutationRate = mutationRate;
        this.elitism = elitism;
        this.iterations = iterations;
    }

    public Population evolvePopulation(Population population) {
        // This is the evolved population.
        Population evolvedPopulation = new Population(population.getPopulationSize(), false);
        for (int p = 0; p < iterations; p++) {
            int elitismOffset = 0;

            // We don't want to lose our best individual so we set offset to 1.
            // This will ensure that we do not override the individual.
            if (elitism) {
                elitismOffset = 1;
                evolvedPopulation.setIndividual(0, population.getFittestIndividual());
            }

            // Create new individuals with uniform uniformCrossover and tournament selection.
            for (int i = elitismOffset; i < population.getPopulationSize(); i++) {
                Individual individual1 = tournamentSelection(population);
                Individual individual2 = tournamentSelection(population);
                Individual newIndividual = uniformCrossover(individual1, individual2);
                evolvedPopulation.setIndividual(i, newIndividual);
            }

            // Mutate the individuals in our evolved population.
            for (int i = elitismOffset; i < evolvedPopulation.getPopulationSize(); i++) {
                mutate(evolvedPopulation.getIndividual(i));
            }
        }
        return evolvedPopulation;
    }

    private Individual tournamentSelection(Population population) {
        Population tournament = new Population(5, false);
        Random random = new Random();

        for (int i = 0; i < TOURNAMENT_SIZE; i++) {
            int randomIndex = random.nextInt(population.getPopulationSize());
            tournament.setIndividual(i, population.getIndividual(randomIndex));
        }
        return tournament.getFittestIndividual();
    }

    private Individual uniformCrossover(Individual parentOne, Individual parentTwo) {
        Individual offSpring = new Individual();
        byte parentOneGene = parentOne.getGene();
        byte parentTwoGene = parentTwo.getGene();
        byte f = 0;

        // Loop through genes
        for (int i = 0; i < NUMBER_OF_BITS; i++) {
            if (Math.random() < crossoverRate) {
                // Get a random bit from parentOne
                f |= (parentOneGene & (1 << i));
            } else {
                // Get a random bit from parentTwo
                f |= (parentTwoGene & (1 << i));
            }
        }
        offSpring.setGene(f);
        return offSpring;
    }

    private void mutate(Individual individual) {
        byte individualGene = individual.getGene();
        for (int i = 0; i < NUMBER_OF_BITS; i++) {
            if (Math.random() < mutationRate) {
                individualGene ^= (1 << i);
            }
            individual.setGene(individualGene);
        }
    }
}