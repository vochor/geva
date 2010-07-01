/*
 * DoubleFitness.java
 *
 * Created on 06 March 2007, 12:33
 *
 */

package Individuals.FitnessPackage;

/**
 * Interface for DoubleFitness
 * @author Conor
 */
public interface DoubleFitness extends Fitness
{
    /**
     * Get the distance
     * @param f incoming fitness
     * @return distance measure
     */
    public double getDistance(DoubleFitness f);
    
}
