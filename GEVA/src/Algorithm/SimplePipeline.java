package Algorithm;

import Operator.Module;

import java.util.ArrayList;
import java.util.Collection;

/**
 * A pipeline containing modules. Extends ArrayList<Module> and implements Pipeline
 * Simple.
 */
public class SimplePipeline extends ArrayList<Module> implements Pipeline {
    
    /**
     * Constructor for a pipeline of set initial size
     * @param size Initial size
     */
    public SimplePipeline(int size) {
        super(size);
    }

    /**
     * Constructor
     */
    public SimplePipeline() {
    }
        
    /**
     * Iterate over the modules in the pipeline
     */
    public void step() {
        for(Module m:this) {
            //long st = System.currentTimeMillis();
            m.perform();
            //st = System.currentTimeMillis() - st;
            //System.out.println(m.getClass().getName()+":");
        }
        //System.out.println("");
    }
    
    /**
     * Add a module to the end of the pipeline
     * @param m Added module
     */
    public void addModule(Module m) {
        this.add(m);
    }

    /**
     * Return element specified by i
     * @param i position in pipeline
     * @return element specified by the position
     */
    public Module getModule(int i) {
        return this.get(i);
    }

    /**
     * Return the entire collection of modules
     * @return the entire collection
     */
    public Collection<Module> getModules() {
        return this;
    }
    
}
