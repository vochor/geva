/*
 * BadParameterException.java
 *
 * Created on 12 October 2006, 15:26
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package Exceptions;

/**
 * Thrown when the input parameters are exceptional
 * @author EHemberg
 */
public class BadParameterException extends Exception {
    
    /** Creates a new instance of BadParameterException */
    public BadParameterException() {
        System.out.println("Bad parameter.");
    }
    
    /**
     * Creates a new instance of BadParameterException
     * @param key parameter
     */
    public BadParameterException(String key) {
        System.out.println("Bad parameter for "+key);
    }
}
