package Operator;

import Individuals.Populations.Population;

/**
 * Interface for modules. The module will recive a population.
 * The module will perform an operation.
 */
public interface Module {

    /**
     * Set the population on which the module will perform its operations
     * @param p population
     */
    public void setPopulation(Population p);

    /**
     * Performs the operation on the population
     */
    public void perform();
}