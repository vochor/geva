
/*
 * IntFlipMutation.java
 *
 * Created on 15 March 2007, 20:05
 *
 */

package Operator.Operations;

import Individuals.GEChromosome;
import Individuals.GEIndividual;
import Individuals.Individual;
import Util.Random.RandomNumberGenerator;

import java.util.List;
import java.util.Properties;

/**
 * IntFlipMutation does integer mutation
 * @author Conor
 */
public class IntFlipMutation extends MutationOperation {
    
    /** Creates a new instance of IntFlipMutation
     * @param prob mutation probability
     * @param rng random number generator
     */
    public IntFlipMutation(double prob,RandomNumberGenerator rng) {
        super(prob, rng);
    }

    /**
     * New instance
     * @param rng random number generator
     * @param p properties
     */
    public IntFlipMutation(RandomNumberGenerator rng, Properties p) {
        super(rng, p);
    }
    
    public void doOperation(List<Individual> operands) {
        for (Individual operand : operands) {
            this.doOperation(operand);

        }
    }
    
    /**
     * Calls doMutation(GEIndividual c) and then calls Individual.invalidate()
     * @param operand operand to operate on
     */
    public void doOperation(final Individual operand) {
        doMutation((GEChromosome)operand.getGenotype().get(0));
        ((GEIndividual)operand).invalidate();
    }
    
    /**
     * According to this.probability a codon in the chromosome is  
     * replaced with a new randomly chosen integer
     * @param c input to mutate
     */
    private void doMutation(final GEChromosome c) {
        for(int i=0;i<c.getLength();i++) {
            if(this.rng.nextBoolean(this.probability)) {
                final int nextInt = Math.abs(rng.nextInt());
                c.set(i, nextInt);
            }
        }
    }
    
    
}
