package flow;

public class Individual {
    private String binaryString;

    public Individual() {
    }

    // Generate random individual
    public void generateIndividual(int input) {
       binaryString = Integer.toBinaryString(input);
       while (binaryString.length() < 5) {
           binaryString = "0" + binaryString;
       }
    }
}
