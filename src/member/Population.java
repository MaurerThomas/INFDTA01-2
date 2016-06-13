package member;

public class Population {

    private Individual[] individuals;

    /**
     * Generate a new population
     * @param populationSize The population size
     * @param initialize Should we initialize, true or false
     */
    public Population(int populationSize, boolean initialize) {

        individuals = new Individual[populationSize];

        /** If we are not evolving we can initialize */
        if (initialize) {
            /** Create the individuals */
            for (int i = 0; i < individuals.length; i++) {
                Individual newIndividual = new Individual();
                newIndividual.generateRandomIndividual();
                individuals[i] = newIndividual;
            }

        }
    }

    public Individual getIndividual(int index) {
        return individuals[index];
    }
}
