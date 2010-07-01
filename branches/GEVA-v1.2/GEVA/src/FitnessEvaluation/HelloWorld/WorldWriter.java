package FitnessEvaluation.HelloWorld;

import java.util.HashMap;

/**
 * Class to be extended by HelloWorldBSF
 * Contains methods for appending,
 * printing and calculating the fitness compared to
 * "geva"
 */
public class WorldWriter {

    @SuppressWarnings({"StringBufferField"})
    private final StringBuffer world;
    public static String s = "geva";

    /**
     * Create an instance
     */
    public WorldWriter(){
        this.world = new StringBuffer();
    }

    /**
     * Append a char to the string
     * @param c char to append
     */
    public void writeChar(char c) {
        this.world.append(c);
    }

    public void writeChar(String c) {
        this.world.append(c);
    }

    /**
     * Append a char to the string from a hashmap
     * @param c structure containg a char
     */
    public void writeChar(HashMap c) {
        this.world.append(c.get("c"));
    }

    /**
     * Print the string
     * @return the word contained in the string
     */
    private String printWorld() {
        return world.toString();
    }

    /**
     * Compare a string. Each symbol not matching increases the fitness by 1.
     * The length difference between the string and the sought string is added to the fitness
     * @return Number of missmatches
     */
    public double calculateFitness() {
        String pS = printWorld();
        double tmpFit = 0;
        int minLength;
        if(pS.length()<s.length()) {
            minLength =  pS.length();
        } else {
            minLength = s.length();
        }
        for(int i = 0; i<minLength; i++) {
            if(pS.charAt(i) != s.charAt(i)) {
                tmpFit++;
            }
        }
        tmpFit += (Math.abs(s.length()-pS.length()));
        //System.out.println(s + " vs " + pS + " = " + tmpFit);
        return tmpFit;
    }

}
