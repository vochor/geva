/*
 * IntFitness.java
 *
 * Created on 06 March 2007, 12:33
 *
 */

package Individuals.FitnessPackage;

/**
 * Interface for an Integer fitness
 * @author Conor
 */
public interface IntFitness extends Fitness
{
    public int getDistance(IntFitness f);
    public int getInt();
    public void setInt(int f);
}
