/*
 * FastIntegerList.java
 *
 * Created on February 20, 2007, 6:16 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package Util.Structures;

/**
 * Interface for a list structure that stores ints
 * @author Blip
 */
public interface IntList {

    /**
     * Get int at index position
     * @param index position to get element form
     * @return element at index
     */
    public int  get(int index);

    /**
     * Set item at index
     * @param index position to set
     * @param item item to set
     */
    public void set(int index, int item);

    /**
     * Clear structure
     */
    public void clear();

    /**
     * Make an array view
     * @return array view
     */
    public int[] toArray();

    /**
     * Get an iterator
     * @return iterator
     */
    public IntIterator iterator();

    /**
     * Add an int
     * @param item int to add
     * @throws IndexOutOfBoundsException list to small
     */
    public void add(int item) throws IndexOutOfBoundsException;

    /**
     * Size of structure
     * @return size
     */
    public int size();
}
