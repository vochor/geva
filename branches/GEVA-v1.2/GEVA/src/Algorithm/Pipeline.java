package Algorithm;

import Operator.Module;

import java.util.Collection;

/**
 * Interface for a pipline. Pipelines store an ordered list of modules
 * which are to executed in sequence in which they are entered. Step runs
 * the pipeline once, that is it executes all modules in the order that
 * they occur in the pipeline. 
 */
public interface Pipeline {

/**
 * Step through all the modules in the pipeline once
 */
    public void step();

    /**
     * Adds a module to the pipeline
     * @param m Module to add
     */
    public void addModule(Module m);

    /**
     * Gets module number i from the pipeline
     * @param i Number of module to get from the pipeline
     * @return A module from the pipeline
     */
    public Module getModule(int i);

    /**
     * Gets a Collection<Module> from the pipeline
     * @return the collection of modules in the pipeline
     */
    public Collection<Module> getModules();
}