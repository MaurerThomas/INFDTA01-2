package part2.member;

import part2.evaluation.Fitness;

public class Individual {
    private int fitness = 0;
    private byte gene;

    public Individual() {
        gene = setGenes();
    }

    private byte setGenes() {
        byte b = 0;
        for (int i = 0; i < 5; i++) {
            b |= getRandomBit(i);
        }
        return b;
    }

    public byte getGene() {
        return gene;
    }

    public void setGene(byte genes) {
        gene = genes;
    }

    private int getRandomBit(int b) {
        if (Math.random() < 0.5) {
            return 1 << b;
        }
        return 0;
    }

    public int getFitness() {
        if (fitness == 0) {
            fitness = Fitness.calculateFitness(this);
        }
        return fitness;
    }

    @Override
    public String toString() {
        return String.valueOf(gene);
    }
}
