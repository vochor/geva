            /*
             * FitnessEvaluator.java
             *
             * Created on October 24, 2006, 6:01 PM
             *
             * To change this template, choose Tools | Template Manager
             * and open the template in the editor.
             */

package FitnessEvaluation;

import Individuals.Individual;
import Parameter.ParameterI;

/**
 * A simple interface to be implemented by any fitness evaluator
 * makes making fitness evaluation classes pluggable. If you want to be one
 * all you have to do is implement the single method defined here.
 * @author Blip
 * 
 * 
 */
public interface FitnessFunction extends ParameterI {
    
    /**
     * Creates a new instance of FitnessEvaluator
     * @param i Evaluated individual
     */
    public void getFitness(Individual i);

    /**
     * Return true if it is ok to cache the results of the fitness function
     */
    public boolean canCache();

}
