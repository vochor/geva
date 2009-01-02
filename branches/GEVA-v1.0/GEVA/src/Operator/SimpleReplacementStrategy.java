package Operator;

import Util.Random.RandomNumberGenerator;
import Individuals.Populations.Population;
import Operator.Operations.Operation;
import Operator.Operations.ReplacementOperation;

/**
 * SimpleReplacementStrategy joins two populations. It has a ReplacementOperation
 * The ReplacementOperation is performed on the population before the incoming population is joined
 * @author Blip
 */
public class SimpleReplacementStrategy extends JoinOperator {
    
    protected ReplacementOperation replacementOperation;

    /**
     * Creates a new instance of SimpleReplacementStrategy
     * @param rng random number generator
     * @param incPop incomming population
     * @param rO replacement operation
     */
    public SimpleReplacementStrategy(RandomNumberGenerator rng, Population incPop, ReplacementOperation rO){
	super(rng, incPop);
        this.replacementOperation = rO;
    }

    /**
     * New instance
     */
    public SimpleReplacementStrategy(){
	super();
    }

    /**
     * A ReplacementOperation is performed on the original 
     * population before the incomigPopulation is joined.
     * Competition among the children if Selection Size is larger then replacement size
     */
    public void perform() {
        //System.out.println("ip:"+this.incomingPopulation);
        //System.out.println("op:"+this.population);
        if(this.incomingPopulation.size()>this.replacementOperation.getReplacementSize()) {
            int size = this.incomingPopulation.size()-this.replacementOperation.getReplacementSize();
            this.replacementOperation.doOperation(this.incomingPopulation.getAll(), size);
        }
        //System.out.println("t-ip:"+this.incomingPopulation);
        //Generational. Clear the original population
        if(this.incomingPopulation.size() == this.population.size()) {
            this.population.clear();
        } else {
            this.replacementOperation.doOperation(this.population.getAll());
        }
        //System.out.println("t-p:"+this.population);
        this.population.addAll(this.incomingPopulation);
        //System.out.println("p:"+this.population);
        this.incomingPopulation.clear();
        this.increaseAge(this.population.getAll());
    }

    public void setOperation(Operation op) {
	this.replacementOperation = (ReplacementOperation)op;
    }    

    public Operation getOperation() {
        return this.replacementOperation;
    }
    
}
