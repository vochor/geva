/*
 * Population.java
 *
 * Created on 07 March 2007, 11:35
 *
 */

package Individuals.Populations;

import Individuals.*;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Interface for a population. The population contains the individuals
 * @author Conor
 */
public interface Population {

    /**
     * Sort the individuals in the population
     */
    @SuppressWarnings({"EmptyMethod"})
    public void sort();

    /**
     * Get an iterator over the population
     * @return iterator over the Individuals
     */
    public Iterator<Individual> iterator();

    /**
     * The number of individuals in the population
     * @return number of individuals
     */
    public int size();

    /**
     * Add a collection of individuals to the population
     * @param immigrants collection of individuals
     */
    public void addAll(Collection<Individual> immigrants);

    /**
     * Add an entire population to the population
     * @param pop population to add
     */
    public void addAll(Population pop);

    /**
     * Get a list of all the individuals
     * @return list view of the population
     */
    public List<Individual> getAll();

    /**
     * Check if the individual is contained in the population
     * @param individual individual to compare
     * @return boolean value if the individual exists in the population
     */
    @SuppressWarnings({"BooleanMethodIsAlwaysInverted"})
    public boolean contains(Individual individual);

    /**
     * Add an individual to the population
     * @param i individual to add
     */
    public void add(Individual i);

    /**
     * Get an individual from the specified index
     * @param index which individual to return
     * @return individual at index
     */
    public Individual get(int index);

    /**
     * Clear the population of all individuals
     */
    public void clear();

    /**
     * Remove individual from population
     * @param ind individual to remove
     */
    public void remove(Individual ind);
}
