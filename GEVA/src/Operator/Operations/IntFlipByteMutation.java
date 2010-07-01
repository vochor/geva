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
 * IntFlipMutation does integer mutation. This method
 * changes the integer into a byte before mutation so as to show the 
 * effects of locality on the mutation operation
 * @author Conor
 */
public class IntFlipByteMutation extends MutationOperation {
    
    /** Creates a new instance of IntFlipMutation
     * @param prob mutation probability
     * @param rng random number generator
     */
    public IntFlipByteMutation(double prob,RandomNumberGenerator rng) {
        super(prob, rng);
    }

    /**
     * New instance
     * @param rng random number generator
     * @param p properties
     */
    public IntFlipByteMutation(RandomNumberGenerator rng, Properties p) {
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
    public void doOperation(Individual operand) {
        doMutation((GEChromosome)operand.getGenotype().get(0));
        ((GEIndividual)operand).invalidate();
    }
    
    /**
     * According to this.probability a codon in the chromosome is  
     * replaced with a new randomly chosen integer
     * @param c input to mutate
     */
    private void doMutation(GEChromosome c) 
    {
        for(int i=0;i<c.getLength();i++) 
        {         
        int mut = 0;

        for(int ii=0; ii<c.getCodonSize(); ii++) {
            //this is where the integer is turned into a byte array
            for(int j=0; j<Byte.SIZE; j++) 
            {
                if(this.rng.nextBoolean(this.probability)) {
                mut = mut + (int)Math.pow(2,j*(ii+1));              
                }
            }
        }
        c.set(i,c.get(i)^mut);
        }       
    }
}