/*
 * Stochastic.java
 *
 * Created on March 5, 2007, 2:01 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package Util.Random;

/**
 * Interface to be used to declare that a class is in some way stochastic and therefore needs a 
 * Random number generator
 * @author Blip
 */
public interface Stochastic {

    /**
     * Set the number generator
     * @param m number generator
     */
    public void setRNG(RandomNumberGenerator m);

    /**
     * Get the number generator
     * @return number generator
     */
    public RandomNumberGenerator getRNG();
    
}
