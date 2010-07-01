/*
 * Algorithm.java
 *
 * Created on March 27, 2007, 12:57 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package Algorithm;

/**
 * Interface for Algorithms
 * This simple interface defines three methods that an algorithm must implement
 * init() - runs the initialisation pipline(s) of the algorithm.
 * run(int iterations) steps the algorithms loop pipeline(s) a through specified number of iterations
 * step() steps the algorithms loop pipeline through a single iteration
 * @author Blip
 */
public interface Algorithm {
    /**
     * Initializing the algorithm
     */
    public void init();
    /**
     * Run for the specified number of steps
     * @param steps run for steps
     */
    public void run(int steps);
    /**
     * Step the algorithm once
     */
    public void step();
    
}
