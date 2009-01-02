package Operator;

import Individuals.Populations.Population;

/**
 * Interface for modules that can create a population.
 * Initialisers should Implement this class.
 */
public interface Creator {
    /**
     * Returns a reference to the population
     * @return population
     */
    public Population getPopulation();

    /**
     *Sets the size of the desired population
     * @param i size of population
     */
    public void setSize(int i);
    
    /**
     * Initialise the module;
     */
    public void init();
}