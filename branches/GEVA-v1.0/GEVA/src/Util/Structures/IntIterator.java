/*
 * IntIterator.java
 *
 * Created on February 20, 2007, 6:33 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package Util.Structures;

/**
 * Interface for an iterator objects that uses int primitaves instead of using 
 * the boxed Integer vesion. 
 * @author Blip
 */
public interface IntIterator {

    /**
     * Get next int
     * @return next int
     */
    public int next();

    /**
     * Check if there are more elements
     * @return if there are more elements
     */
    public boolean hasNext();
    
    
}
