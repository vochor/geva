/*
 * CrossoverOperation.java
 *
 * Created on 14 March 2007, 22:45
 *
 */

package Operator.Operations;

import Exceptions.BadParameterException;
import Individuals.Individual;
import Util.Random.RandomNumberGenerator;
import Util.Random.Stochastic;
import Util.Constants;

import java.util.List;
import java.util.Properties;

/**
 * Abstract class for CrossoverOperation
 * @author Conor
 */
public abstract class CrossoverOperation implements Operation, Stochastic {
    
    protected double probability;
    protected RandomNumberGenerator rand;
    
    public abstract void doOperation(List<Individual> operands);
    
    /** Creates a new instance of CrossoverOperation
     * @param prob crossover probability
     * @param rng random number generator
     */
    public CrossoverOperation(double prob, RandomNumberGenerator rng) {
        this.probability = prob;
        this.rand = rng;
    }

    /**
     * New instance
     * @param rng random number generator
     * @param p properties
     */
    public CrossoverOperation(RandomNumberGenerator rng, Properties p) {
        this.setProperties(p);
        this.rand = rng;
    }

    /**
     * Set properties
     *
     * @param p object containing properties
     */
    public void setProperties(Properties p) {
        double value = 0.9;
        try {
            String key = Constants.CROSSOVER_PROBABILITY;
            if(p.getProperty(key)!=null) {
                value = Double.parseDouble(p.getProperty(key));
                if(value < 0.0 || value > 1.0) {
                    throw new BadParameterException(key);
                }
            }
        } catch(Exception e) {
            value = 0.9;
            System.out.println(e+" using default: "+value);
        }
        this.probability = value;
    }

    public void setRNG(RandomNumberGenerator m) {
        this.rand = m;
    }
    
    public RandomNumberGenerator getRNG() {
        return this.rand;
    }
    
}
