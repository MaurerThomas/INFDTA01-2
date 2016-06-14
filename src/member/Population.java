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
                newIndividual.getFitness();
                individuals[i] = newIndividual;
            }
        }
    }

    public Individual getIndividual(int index) {
        return individuals[index];
    }

    public void setIndividual(int index, Individual individual){
        individuals[index] = individual;
    }

    public int getPopulationSize(){
        return individuals.length;
    }

    public Individual getFittestIndividual() {
        Individual fittestIndividual = individuals[0];
        for (Individual individual: individuals) {
            if(individual.getFitness() > fittestIndividual.getFitness()){
                fittestIndividual = individual;
            }
        }
        return fittestIndividual;
    }
}
