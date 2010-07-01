/*
 * CrossoverModule.java
 *
 * Created on March 12, 2007, 3:16 PM
 *
 */

package Operator;

import Individuals.Individual;
import Util.Random.RandomNumberGenerator;
import Operator.Operations.CrossoverOperation;
import Operator.Operations.Operation;
import java.util.ArrayList;

/**
 * CrossoverModule performs crossover on an entire population. This will typically 
 * be the portion of the main population that has been selected for reproduction. 
 * The specifics of the crossover depend on the particular operation used.  
 * 
 * @author Blip
 */
public class CrossoverModule extends OperatorModule {
    
    private CrossoverOperation op;
    private ArrayList<Individual> alI;

    /** Creates a new instance of CrossoverModule
     * @param m random number generator
     * @param xOver crossover operation
     */
    public CrossoverModule(RandomNumberGenerator m, CrossoverOperation xOver){
        super(m);
        this.op = xOver;
        this.alI = new ArrayList<Individual>(2);
    }

    /**
     * Crossover is performed on in pairs. The neighbouring individuals will be crossed over
     **/
    public void perform() {
        Individual i1;
        Individual i2;
        int length = this.population.size() - this.population.size()%2;
        //System.out.println("xo:"+this.population);
        for(int i=0;i<length;i=i+2) {
            alI.clear();
            i1 = this.population.get(i);
            i2 = this.population.get(i+1);
            alI.add(i1);
            alI.add(i2);
            this.op.doOperation(alI);
        }
        //This handles the case when the selection size is an odd number
        if(this.population.size()%2!=0) {
            int i =this.population.size()-1;
            i1 = this.population.get(i);
            i2 = this.getRandomNotThis(this.population.get(i));
            alI.add(i1);
            alI.add(i2);
            this.op.doOperation(alI);
        }
    }
    
    public Operation getOperation() {
        return this.op;
    }
    
    public void setOperation(Operation op) {
        this.op = (CrossoverOperation) op;
    }

    /**
     * Get a random individual that is not the argument individual
     * @param me Individual not to be returned
     * @return Individual that is not argument individual
     **/
    Individual getRandomNotThis(Individual me) {
        this.population.remove(me);
        Individual notMe;
        notMe = this.population.get(rng.nextInt(this.population.size()));
        this.population.add(me);
        return notMe;
    }
    
}
