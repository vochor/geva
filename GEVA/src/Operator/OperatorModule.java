/*
 * OperatorModule.java
 *
 * Created on 14 March 2007, 15:40
 *
 */

package Operator;

import Individuals.Populations.Population;
import Util.Random.RandomNumberGenerator;
import Util.Random.Stochastic;

/**
 * Abstract class for Modules with operations.
 * The OperatorModule performes its operation oon a population.
 * @author Conor
 */
public abstract class OperatorModule implements Operator, Stochastic {
    
    protected RandomNumberGenerator rng;
    protected Population population;
    
    /** Creates a new instance of OperatorModule
     * @param rng random number generator
     */
    public OperatorModule(RandomNumberGenerator rng) {
        this.rng = rng;
    }
    
    /** Creates a new instance of OperatorModule */
    public OperatorModule() {
    }
    
    public abstract void perform();
    
    /**
     * Set the RandomNumberGenerator
     * @param m RandomNumberGenerator
     **/
    public void setRNG(RandomNumberGenerator m) {
        this.rng = m;
    }
    
    /**
     * Get the randomnumbergenerator
     * @return RandomNumberGenerator
     **/
    public RandomNumberGenerator getRNG() {
        return this.rng;
    }
    
    /**
     * Set the population that the module will operate on
     * @param p Population
     **/
    public void setPopulation(Population p) {
        this.population = p;
    }
    
}
