/*
 * MutationOperation.java
 *
 * Created on March 15, 2007, 4:38 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package Operator.Operations;

import Exceptions.BadParameterException;
import Util.Random.Stochastic;
import Util.Random.RandomNumberGenerator;
import Util.Constants;
import FitnessEvaluation.FitnessFunction;
 
import java.util.List;
import Individuals.Individual;
import java.util.Properties;

/**
 * Abstract class for mutaion operation.
 * @author Blip
 */
public abstract class MutationOperation implements Stochastic,Operation {
    
    protected double probability;
    protected double noOfMutationCalls;
    protected RandomNumberGenerator rng;
    protected FitnessFunction fitnessFunction;

    /** Creates a new instance of MutationOperation
     * @param prob mutation probability
     * @param rng random number generator
     */
    public MutationOperation(double prob,RandomNumberGenerator rng) {
        this.probability = prob;
        this.rng = rng;
        this.noOfMutationCalls=0;
    }
    
    /** Creates a new instance of MutationOperation
     * @param rng random number generator
     * @param p properties
     */
    public MutationOperation(RandomNumberGenerator rng, Properties p) {
        this.setProperties(p);
        this.rng = rng;
    }

    /**
     * Set properties
     *
     * @param p object containing properties
     */
    public void setProperties(Properties p) {
        double value  ;
        try {
            String key = Constants.MUTATION_PROBABILITY;
            value = Double.parseDouble(p.getProperty(key));
            if(value < 0.0 || value > 1.0) {
                throw new BadParameterException(key);
            }
        } catch(Exception e) {
            value = 0.01;
            System.out.println(e+" using default: "+value);
        }
        this.probability = value;
        
        int popSize;
        try 
        {
            String key = p.getProperty(Constants.POPULATION_SIZE); 
            popSize = Integer.parseInt(key);
        }
        catch(NumberFormatException e) { popSize = 0; }
        
        int generations ;
        try 
        {
            String key =  p.getProperty(Constants.GENERATION);
            generations = Integer.parseInt(key);   
        }  
        catch(NumberFormatException e) { generations = 0; } 
        this.noOfMutationCalls = generations * popSize;
    }

    public abstract void doOperation(Individual operand);
    
    public abstract void doOperation(List<Individual> operand);
    
    public void setFitnessFunction(FitnessFunction ff)
    {
        this.fitnessFunction = ff;
    }
    public void setRNG(RandomNumberGenerator m) {
        this.rng = m;
    }
    
    public RandomNumberGenerator getRNG() {
        return this.rng;
    }
    
}
