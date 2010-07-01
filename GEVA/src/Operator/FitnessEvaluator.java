/*
 * FitnessEvaluator.java
 *
 * Created on April 17, 2007, 11:10 AM
 *
 */

package Operator;

import Operator.Operations.FitnessEvaluationOperation;
import Operator.Operations.Operation;
import Util.Random.RandomNumberGenerator;

/**
 * FitnessEvaluator is a module that is used to evaluate an entire populations fitness.
 * It can host many different types of fitness function
 *
 * @author erikhemberg
 */
public class FitnessEvaluator extends OperatorModule{
    
    private FitnessEvaluationOperation fitnessEvaluationOperation;
    
    /** Creates a new instance of FitnessEvaluator
     * @param rng random number generator
     * @param fEO fitness evaluation operation
     */
    public FitnessEvaluator(RandomNumberGenerator rng, FitnessEvaluationOperation fEO) {
        super(rng);
        this.fitnessEvaluationOperation = fEO;
    }
    
    public Operation getOperation() {
        return  this.fitnessEvaluationOperation;
    }
    
    /**
     * Calls doOperation(List<Individual> operands) in the
     * FitnessEvaluationOperation.
     **/
    public void perform() {
        //System.out.println("pFE:"+this.population);
        this.fitnessEvaluationOperation.doOperation(this.population.getAll());
         //System.out.println("aFE:"+this.population);
    }
    
    public void setOperation(Operation op) {
        this.fitnessEvaluationOperation = (FitnessEvaluationOperation)op;
    }
    
}
