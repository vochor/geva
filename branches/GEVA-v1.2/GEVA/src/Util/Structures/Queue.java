package Util.Structures;

import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * A FIFO queue implementation containting a linked list
 */
public class Queue<T> {
    
    private LinkedList<T> items;

    /**
     * Create queue
     */
    public Queue() {
        this.items = new LinkedList<T>();
    }

    /**
     * add element to end of items
     * @param element element to add
     * @return added element
     */
    @SuppressWarnings({"UnusedReturnValue"})
    public T enqueue(T element) {
        items.add(element);
        return element;
    }

    /**
     * Remove first element.
     * @throws NoSuchElementException if size is 0
     * @return first element
     */
    public T dequeue() {
        if (items.size()== 0)
            throw new NoSuchElementException() ;
        return items.removeFirst();
    }

    /**
     * Check if queue is empty
     * @return true if it is empty
     */
    @SuppressWarnings({"BooleanMethodIsAlwaysInverted"})
    public boolean isEmpty() {
        return items.isEmpty();
    }


}