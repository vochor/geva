package Operator;

import Util.Random.RandomNumberGenerator;
import Individuals.Populations.Population;
import Operator.Operations.Operation;
import Operator.Operations.ReplacementOperation;

/**
 * MeritReplacementStrategy joins to populations. It has a ReplacementOperation
 * The population is joined before the ReplacementOperation is performed
 * @author Blip
 */
public class MeritReplacementStrategy extends JoinOperator{
    
    protected ReplacementOperation replacementOperation;
    
    /**
     * Creates a new instance of MeritReplacementStrategy
     * @param rng random number generator
     * @param incPop incoming population
     * @param rO replacement operation
     */
    public MeritReplacementStrategy(RandomNumberGenerator rng, Population incPop, ReplacementOperation rO){
	super(rng, incPop);
        this.replacementOperation = rO;
        
    }

    /**
     * New instance
     */
    public MeritReplacementStrategy() {
        super();
    }
    
        /**
     * A ReplacementOperation is performed after the 
     * populations are joined.
     * Competition among the children if Selection Size is larger then replacement size
     * When replacement is done the age of the population is increased by one
     */
    public void perform() {
        final int populationSize = this.population.size();
        //System.out.println("ops:"+this.population.size()+" "+this.population);
        if(this.incomingPopulation.size()>this.replacementOperation.getReplacementSize()) {
            int size = this.incomingPopulation.size()-this.replacementOperation.getReplacementSize();
            this.replacementOperation.doOperation(this.incomingPopulation.getAll(), size);
        }
         //System.out.println("ips:"+this.incomingPopulation.size()+" "+this.incomingPopulation);
        this.population.addAll(this.incomingPopulation);
         //System.out.println("t-ps:"+this.population.size()+" "+this.population);
        
         
        this.replacementOperation.doOperation(this.population.getAll(), (this.population.size() - populationSize));
        this.incomingPopulation.clear();
         //System.out.println("nps:"+this.population.size()+" "+this.population);
        super.increaseAge(this.population.getAll());
    }


    public void setOperation(Operation op) {
        this.replacementOperation = (ReplacementOperation)op;
    }    

    public Operation getOperation() {
        return this.replacementOperation;
    }
    
}
