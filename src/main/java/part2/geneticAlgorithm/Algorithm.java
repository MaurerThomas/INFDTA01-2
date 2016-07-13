package part2.geneticalgorithm;

import part2.member.Individual;
import part2.member.Population;

import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

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
        //TODO: Fix this: Elitism is NOT used. The evolved population should only get better.
        this.elitism = elitism;
        this.iterations = iterations;
    }

    public Population evolvePopulation(Population population) {
        // This is the evolved population.
        Population evolvedPopulation = new Population(population.getPopulationSize(), false);
        int elitismOffset = 0;
        for (int p = 0; p < iterations; p++) {
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
                //Individual individual1 = rouletteWheelSelection(population);
                //Individual individual2 = rouletteWheelSelection(population);

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
//
//    private Individual rankSelection(Population population) {
//
//        Map<Integer, Individual> rankingMap = new TreeMap();
//        for (int i = 0; i < population.getPopulationSize(); i++) {
//            rankingMap.put(i, population.getIndividual(i));
//        }
//
//
//    }

    private Individual rouletteWheelSelection(Population population) {
        double totalFitness = 0;
        Random random = new Random();
        double randomDouble = random.nextDouble();
        //Invert the lowest fitness, so we can use this to add this value to all negative fitnesses.
        double lowestFitness = Math.abs(population.getLeastFitIndividual().getFitness())+1;
        double sum = 0;

        //Set the total fitness
        for (int i = 0; i < population.getPopulationSize(); i++) {
           totalFitness += (population.getIndividual(i).getFitness() + lowestFitness);
        }
        //Get a random slice from the total pie
        double desiredSlice = randomDouble * totalFitness;

        for (int i = 0; i < population.getPopulationSize(); i++) {
            //Add the fitness of the individual to the sum.
            //If this is lower, we are NOT yet at the desired point.
            if(sum < desiredSlice){
                sum+= (population.getIndividual(i).getFitness() + lowestFitness);

                if(sum >= desiredSlice){
                    return population.getIndividual(i);
                }
            }
        }
        return null;
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