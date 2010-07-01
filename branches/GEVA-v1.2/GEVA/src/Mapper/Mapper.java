/*
 * Mapper.java
 *
 * Created on 09 October 2006, 11:40
 *
 */

package Mapper;

/**
 * Interface for mapping input (genotype) to output (phenotype).
 * @author EHemberg
 */
public interface Mapper{
    
    /**
     * Maps from a input(genotype) to an output (phenotype)
     * @return Boolean denoting a successful (True) or failed (false) mapping.
     */
    public abstract boolean genotype2Phenotype();

    /**
     * Not yet implemented!
     * @return Boolean denoting a successful (True) or failed (false) mapping.
     */
    @SuppressWarnings({"SameReturnValue"})
    public abstract boolean phenotype2Genotype();

    /**
     * Clears the mapper by dereferencing the genotype and phenotype. 
     * After this operation a mapper should be able to perform another 
     * mapping. The mapper may also need to reinitialse its internal state:
     * eg clear derivation tree clear string buffers if they are used.
     */
    public abstract void clear();

    /**
     * Returns the mappers output (phenotype)
     * @return output of the mapping
     */
    public abstract Object getPhenotype();
    /**
     * Returns the mappers input (genotype)
     * @return input of the mapper
     */
    public abstract Object getGenotype();

    /**
     * Sets the output(phenotype)
     * @param p output to set
     */
    public abstract void setPhenotype(Object p);// It's hard to avoid casting here

    /**
     * Sets the input (genotype)
     * @param g input to set
     */
    public abstract void setGenotype(Object g); // so lets pass objects about

}
