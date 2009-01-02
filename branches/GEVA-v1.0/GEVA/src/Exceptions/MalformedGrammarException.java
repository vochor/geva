/*
 * MalformedGrammarException.java
 *
 * Created as ThisGrammarBeBadException.java on 12 October 2006, 15:26
 * Renamed to MalformedGrammarException.java 19 November 2008
 *
 */

package Exceptions;

/**
 * Thrown if the Grammar file fails to parse.
 * @author EHemberg
 */
public class MalformedGrammarException extends Exception {
    
    /** Creates a new instance of MalformedGrammarException */
    public MalformedGrammarException() {
        System.out.println("Malformed Grammar.");
    }
   
    /**
     * Creates a new instance of MalformedGrammarException
     * @param s parameter
     */
    public MalformedGrammarException(String s) {
        System.out.println("Malformed Grammar: " + s);
    }
    
}
