package Operator;

import Individuals.Individual;
import Individuals.Populations.Population;
import Operator.Operations.EliteReplacementOperation;
import Operator.Operations.Operation;
import Util.Random.RandomNumberGenerator;

import java.util.Collections;
import java.util.List;

/**
 * EliteReplacementOperator has a EliteReplacementOperation.
 * Use this module for Elite replacement
 * @author Blip
 */
public class EliteReplacementOperator extends JoinOperator {
    
    private EliteReplacementOperation replacementOperation;
    
    /**
     * Creates a new instance of EliteReplacementOperator
     * @param rng random number generator
     * @param incPop incomming population
     * @param rO replacement operation
     */
    public EliteReplacementOperator(RandomNumberGenerator rng, Population incPop, EliteReplacementOperation rO){
        super(rng, incPop);
        this.replacementOperation = rO;
    }

    /**
     * Remove worst individuals form the elite population.
     * Add the elites to the original population.
     * Remove the worst individuals from the origninal population.
     **/
    public void perform() {
        //System.out.print("+OE:"+this.incomingPopulation+" => ");
        this.replacementOperation.doOperation(this.incomingPopulation.getAll());
        //System.out.print("+NE:"+this.incomingPopulation+" ");
        //for(int i=0;i<this.incomingPopulation.size();i++) {
            //System.out.println(this.incomingPopulation.get(i).isValid());
        //}
        //System.out.println("-OP:"+this.population);
        trimPopulation(this.population.getAll());
        //System.out.println("-TP:"+this.population);
        this.population.addAll(this.incomingPopulation);
        Collections.sort(this.population.getAll());
        //System.out.println("-NP:"+this.population);
    }
    
    /**
     * Remove the worst individuals from the population.
     * @param operand List of individuals
     **/
    private void trimPopulation(List<Individual> operand) {
        Collections.sort(operand);
        int cnt = operand.size();
        final int cut = operand.size() - this.incomingPopulation.size();
        while(cnt > cut && cnt > 0) {
            cnt--;
            operand.remove(cnt);
        }
    }
    
    public void setOperation(Operation op) {
        this.replacementOperation = (EliteReplacementOperation)op;
    }
    
    public Operation getOperation() {
        return this.replacementOperation;
    }
        
}
