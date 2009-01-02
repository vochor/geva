/*
 * Collector.java
 *
 * Created on April 26, 2007, 1:46 PM
 *
 */

package Operator;

import Individuals.Populations.Population;
import Operator.Operations.Operation;
import Operator.Operations.OutputI;

/**
 * Collector is used as a base class for collecting data from a 
 * population, e.g for collecting statistics. This class should not
 * be used to derive a class that alters a population. 
 * @author erikhemberg
 */
public class Collector implements Operator{
    
    private Operation operation;
    private Population population;
    
    /** Creates a new instance of Collector
     * @param op operation
     */
    public Collector(Operation op) {
        this.operation = op;
    }
    
    public Operation getOperation() {
        return this.operation;
    }
    
    public void perform() {
        this.operation.doOperation(this.population.getAll());
    }
    
    public void setOperation(Operation op) {
        this.operation = op;
    }
    
    public void setPopulation(Population p) {
        this.population = p;
    }

    /**
     * Call the print(list<Individual> operands, boolean b)
     * in the operation.
     * @param toFile if output is written to file
     **/
    public void print(boolean toFile) {
        ((OutputI)this.operation).print(this.population.getAll(), toFile);
    }

}
