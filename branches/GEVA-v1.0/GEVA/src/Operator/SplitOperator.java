package Operator;

import Individuals.Populations.Population;
import Operator.Operations.Operation;
import Util.Random.RandomNumberGenerator;

import java.util.Properties;

/**
 * Abstract class for splitting a population.
 * Splits the popluation to destinationPopulation
 * used for eg. selectionScheme
 */
public abstract class SplitOperator extends SourceModule {

    protected Population destinationPopulation;
    protected Operation operation;

    /**
     * New instance
     * @param rng random number generator
     * @param size size
     * @param op operation
     */
    public SplitOperator(RandomNumberGenerator rng, int size, Operation op){
        super(rng, size);
        this.operation = op;
    }

    /**
     * New instance
     * @param rng random number generator
     * @param op operation
     * @param p properties
     */
    public SplitOperator(RandomNumberGenerator rng, Operation op, Properties p){
        super(rng, p);
        this.operation = op;
    }

    public abstract void perform();
    public abstract void setOperation(Operation op);
    public abstract Population getPopulation();
    public abstract Operation getOperation(); 

}