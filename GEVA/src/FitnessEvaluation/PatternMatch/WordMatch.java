/*
 * WordMatch.java
 *
 * Created on November 1, 2006, 4:59 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package FitnessEvaluation.PatternMatch;

import Exceptions.BadParameterException;
import Individuals.Individual;
import Individuals.Phenotype;
import java.util.Properties;

import FitnessEvaluation.FitnessFunction;
import Util.Constants;

/**
 * Matches strings
 * @author erikhemberg
 */
public class WordMatch implements FitnessFunction {
    
    private static String s;
    
    /** Creates a new instance of WordMatch */
    public WordMatch() {
	s = "geva";
    }

    /**
     * Creates a new instance of WordMatch
     * @param s String to match
     */
    public WordMatch(String s) {
        WordMatch.s = s;
    }
    public void setProperties(Properties p) {
        String key = Constants.WORDMATCH_WORD;
        String value;
	value = p.getProperty(key,"geva");
	WordMatch.s = value;
    }

    /**
     * Compare a string to the word. Each symbol not matching increases the fitness by 1.
     * Max fitness is max(length of the word, phenotype).
     * @param p Compared phenotype
     * @return Number of missmatches
     */
    public double evaluateString(Phenotype p) {
        String pS = p.getStringNoSpace();
        int minLength = Math.min(pS.length(), s.length());
        double tmpFit = Math.max(pS.length(), s.length());
        for(int i = 0; i<minLength; i++) {
            if(pS.charAt(i) == s.charAt(i)) {
                tmpFit--;
            }
        }
        return tmpFit;
    }

    public void getFitness(Individual i) {
        i.getFitness().setDouble(this.evaluateString(i.getPhenotype()));
    }

    public boolean canCache()
    {   return true;
    }

}
