package Operator.Operations;

import Exceptions.BadParameterException;
import Individuals.Individual;
import Individuals.Populations.Population;
import Individuals.Populations.SimplePopulation;
import Util.Constants;

import java.util.List;
import java.util.Properties;

/**
 * Abstract SelectionOperation class. Has a selectedPopulation and the size of the selection
 */
public abstract class SelectionOperation implements Operation {
    
    protected Population selectedPopulation;
    protected int size;

    /**
     * New instance
     * @param size size of selection
     */
    public SelectionOperation(int size) {
        this.selectedPopulation = new SimplePopulation(size);
        this.size = size;
    }

    /**
     * New instance
     * @param p properties
     */
    public SelectionOperation(Properties p) {
        this.setProperties(p);
    }

    /**
     * New instance
     */
    public SelectionOperation() {
    }

    /**
     * Set properties
     *
     * @param p object containing properties
     */
    public void setProperties(Properties p) {
        double valueD = 1.0;
        String key;
        int valueI = Integer.parseInt(Constants.DEFAULT_POPULATION_SIZE);
        key = Constants.POPULATION_SIZE;
	valueI = Integer.parseInt(p.getProperty(key, String.valueOf(valueI)));
        key = Constants.REPLACEMENT_TYPE;
        if(p.getProperty(key)!=null) {
            if(p.getProperty(key).equals(Constants.STEADY_STATE)) {
                valueD = 2.0/valueI;
            } else {
                if(p.getProperty(key).equals(Constants.GENERATIONAL)) {
                    valueD = 1.0;
                }
            }
        } else {
	    key = Constants.SELECTION_SIZE;
	    valueD = Double.parseDouble(p.getProperty(key,"1.0"));
	}
        this.size = (int)(valueD*valueI);
        this.selectedPopulation = new SimplePopulation(this.size);                                                                                       
    }
    
    @SuppressWarnings({"EmptyMethod"})
    public abstract void doOperation(Individual operand);
    public abstract void doOperation(List<Individual> operands);
    
    /**
     * Returns the selected population.
     * @return Selected population
     **/
    public Population getSelectedPopulation() {
        return selectedPopulation;
    }
    
    /**
     * Size of the population to be selecetd
     * @return selected population size
     **/
    public int getSize() {
        return size;
    }
}