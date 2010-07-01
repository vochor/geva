/*
 * InitializationException.java
 *
 * Created on 12 October 2006, 15:26
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package Exceptions;

/**
 * Thrown when the initialization of an Individual is exceptional
 * @author EHemberg
 */
public class InitializationException extends Exception {
    
    /** Creates a new instance of InitializationException */
    public InitializationException() {
        System.out.println("InitializationException");
    }
    
    /**
     * Constructor
     * @param s Message
     */
    public InitializationException(String s) {
        System.out.println("InitializationException:"+s);
    }
    
}
