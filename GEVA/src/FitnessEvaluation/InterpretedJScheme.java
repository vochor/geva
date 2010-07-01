/*
 * InterpretedJScheme.java
 *
 * Created on May 30, 2007, 4:51 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package FitnessEvaluation;

import jscheme.JScheme;

/**
 * Interpret code using JScheme
 * @author Blip
 */
public abstract class InterpretedJScheme extends InterpretedFitnessEvaluation{
    
    protected final JScheme js;
    /** Creates a new instance of InterpretedJScheme */
    public InterpretedJScheme() {
        js = new JScheme();
    }
    
}
