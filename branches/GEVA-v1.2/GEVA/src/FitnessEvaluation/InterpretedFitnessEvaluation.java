/*
 * InterpretedFitnessEvaluation.java
 *
 * Created on May 29, 2007, 12:10 PM
 *
 */

package FitnessEvaluation;

import Individuals.FitnessPackage.BasicFitness;
import Individuals.Phenotype;
import Individuals.Individual;

/**
 * Abstract class for interpreting generated code, instead of compiling
 * @author erikhemberg
 */
public abstract class InterpretedFitnessEvaluation implements FitnessFunction{
    
    /** Creates a new instance of InterpretedFitnessEvaluation */
    public InterpretedFitnessEvaluation() {
    }

    /**
     * Run the phenotype and return the fitness
     * @param p input
     * @return fitness of input
     */
    public abstract double runFile(Phenotype p);

    /**
     * Evaluate an individual and set the fitness
     * @param i input to evaluate
     */
    public void getFitness(Individual i) {
        if(i.isValid()) {
            i.getFitness().setDouble(runFile(i.getPhenotype()));
        } else {
            i.getFitness().setDouble(((BasicFitness)i.getFitness()).getDefaultFitness());
        }
    }
    
    public boolean canCache()
    {   return true;
    }

}
