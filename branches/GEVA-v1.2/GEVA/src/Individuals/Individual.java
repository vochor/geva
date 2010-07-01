/*
 * Individuals.java
 *
 * Created on March 27, 2007, 1:41 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package Individuals;

import Individuals.FitnessPackage.Fitness;
import Mapper.Mapper;

/**
 * This interface defines the methods for individual objects. Individuals 
 *  are intended to be a container class that includes a genotype, a fitness 
 *  object, a phenotype, and a mapper. It doesnt really need to do a lot beyond
 *  implementing a map() method, which predictably enough maps from genotype to
 *  a phenotype. 
 * @author Blip
 */
@SuppressWarnings({"CloneDoesntDeclareCloneNotSupportedException"})
public interface Individual extends Cloneable, Comparable<Individual> {
    
    /**
     * Map the input(Genotype) to output(Phenotype) using a grammar as a map
     * @param map which input to map if there are multiple
     */
    @SuppressWarnings({"SameParameterValue", "UnusedParameters"})
    void map(int map);
    
    //
    // The rest of the methods are fairly obvious get/set patterns 
    //
    /**
     * Get fitness of individuals
     * @return individual fitness
     */
    Fitness getFitness();
    
    /**
     * Get the genotype
     * @return genotype
     */
    Genotype getGenotype();
    
    /**
     * Get the map use to map input and output
     * @return map used
     */
    Mapper getMapper();
    
    /**
     * Get phenotype
     * @return phenotype
     */
    Phenotype getPhenotype();
    
    /**
     * Get a String representation of the output(Phenotype)
     * @param map which output to get if there are multiple
     * @return string of output
     */
    @SuppressWarnings({"UnusedParameters"})
    String getPhenotypeString(int map);
    
    /**
     * Set fitness
     * @param f fitness
     */
    void setFitness(Fitness f);
    
    /**
     * Set genotype
     * @param g genotype
     */
    void setGenotype(Genotype g);
    
    /**
     * Set mapper
     * @param m mapper
     */
    void setMapper(Mapper m);
    
    /**
     * Set phenotype
     * @param p phenotype
     */
    void setPhenotype(Phenotype p);
    
    /**
     * Has the individual been evaluated
     * @return boolean of evaluation status
     */
    public boolean isEvaluated();

    /**
     * Indicate if the individual should be evaluated or not
     * @param b set if individual should be evaluated
     */
    public void setEvaluated(boolean b);

    /**
     * Get the validity of the individual
     * @return validity of the individual
     */
    public boolean isValid();

    /**
     * Set the validity of the individual
     * @param b validity to be set
     */
    public void setValid(boolean b);

    /**
     * Clone the individual
     * @return a clone of the individual
     */
    public Individual clone();
    
    /**
     * Age is how long the individual has existed
     * @param age How long the individual has existed
     **/
    public void setAge(int age);

    /**
     * The age of the individual, counted as how many
     * iterations it has survived.
     * @return number of iterations survived
     */
    public int getAge();
}
