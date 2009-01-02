/*
 * MutationOperator.java
 *
 * Created on March 30, 2007, 5:35 PM
 *
 */

package Operator;

import Operator.Operations.MutationOperation;
import Operator.Operations.Operation;
import Util.Random.RandomNumberGenerator;

import java.util.Iterator;
import Individuals.Individual;

/**
 * MutationOperator has a MutationOperation.
 * @author erikhemberg
 */
public class MutationOperator extends OperatorModule {
    
    protected MutationOperation operation;
    
    /** Creates a new instance of MutationOperator
     * @param rng random number generator
     * @param op opertion
     */
    public MutationOperator(RandomNumberGenerator rng, MutationOperation op) {
        super(rng);
        this.operation = op;
    }
    
    public Operation getOperation() {
        return this.operation;
    }
    
    public void perform() {
        Iterator<Individual> iIt = this.population.iterator();
        while(iIt.hasNext()) {
            operation.doOperation(iIt.next());
        }
    }
    
    public void setOperation(Operation op) {
        this.operation = (MutationOperation)op;
        
    }
    
}
