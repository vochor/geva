/*
 * SourceModule.java
 *
 * Created on 07 March 2007, 23:25
 *
 */

package Operator;

import Individuals.Populations.Population;
import Parameter.ParameterI;
import Util.Random.RandomNumberGenerator;

import java.util.Properties;

/**
 * Abstract class used for a source module.
 * Contains the size of the source.
 * @author Conor
 */
public abstract class SourceModule extends OperatorModule implements ParameterI {
    
    protected int size;
    
    /** Creates a new instance of SourceModule
     * @param rng random number generator
     * @param size size of source
     */
    public SourceModule(RandomNumberGenerator rng, int size) {
        super(rng);
        this.size = size;
    }
    
    /** Creates a new instance of SourceModule */
    public SourceModule() {
        super();
    }
    
    /** Creates a new instance of SourceModule
     * @param rng random number generator
     * @param p properies
     */
    public SourceModule(RandomNumberGenerator rng, Properties p) {
        super(rng);
        this.setProperties(p);
    }
    
    public abstract Population getPopulation();

    /**
     * Set the size
     * @param size The integer size
     **/
    public void setSize(int size) {
        this.size = size;
    }
    
}
