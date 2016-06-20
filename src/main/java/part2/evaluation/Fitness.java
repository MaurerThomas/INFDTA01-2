package part2.evaluation;

import part2.member.Individual;

public class Fitness {

    /**
     * Calculates the fitness of a individual based on a problem.
     * f(x) = -x² + 7x
     *
     * @param individual The individual to calculate its fitness.
     * @return The calculated fitness of a indivudual
     */
    public static int calculateFitness(Individual individual) {
        byte b = individual.getGene();
        int x = b & 0xff;
        return x * (7 - x);
    }
}
