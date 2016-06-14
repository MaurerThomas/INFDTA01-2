package geneticAlgorithm;

import member.Individual;
import member.Population;

import java.util.Random;

public class Algorithm {
//    private double crossoverRate;
//    private double mutationRate;
//    private boolean elitism;
//    private Population population;
//
//    public Algorithm(double crossoverRate, double mutationRate, boolean elitism, Population population) {
//        this.crossoverRate = crossoverRate;
//        this.mutationRate = mutationRate;
//        this.elitism = elitism;
//        this.population = population;
//    }

    public void evolvePopulation(Population population) {
        Individual individual1 = tournamentSelection(population);
        Individual individual2 = tournamentSelection(population);
    }

    private Individual tournamentSelection(Population population) {
        Population tournament = new Population(5, false);
        Random random = new Random();

        for (int i=0; i < 5; i++) {
            int randomIndex = random.nextInt(population.getPopulationSize()) ;
            tournament.setIndividual(i, population.getIndividual(randomIndex));
           }
        return tournament.getFittestIndividual();
    }

}
