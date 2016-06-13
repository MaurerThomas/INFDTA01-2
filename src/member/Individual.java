package member;

import evaluation.Fitness;

public class Individual {
    private byte[] genes = new byte[5];
    private int fitness = 0;

    /**
     * Generate random individual.
     */
    void generateRandomIndividual() {
        for (int i = 0; i < size(); i++) {
            byte gene = (byte) Math.round(Math.random());
            genes[i] = gene;
        }
    }

    public byte getGene(int index) {
        return genes[index];
    }

    public void setGene(int index, byte value) {
        genes[index] = value;
        fitness = 0;
    }

    public int getFitness() {
        if (fitness == 0) {
            //fitness = Fitness.getFitness(this);
        }
        return fitness;
    }

    public int size() {
        return genes.length;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < size(); i++) {
            stringBuilder.append(getGene(i));
        }
        return stringBuilder.toString();
    }

}
