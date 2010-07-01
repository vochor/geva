/*
 * AbstractIndividual.java
 *
 * Created on October 25, 2006, 3:38 PM
 *
 */

package Individuals;

import Individuals.FitnessPackage.Fitness;
import Mapper.Mapper;

/**
 * An Abstract individual. An abstract individual class implements a
 * couple of the get/set patterns and adds a fitness data member.
 * @author Blip
 */
@SuppressWarnings({"CloneDoesntDeclareCloneNotSupportedException"})
public abstract class AbstractIndividual implements Individual {
    
    Fitness fitness;
    boolean evaluated;
    int age;

    /**
     * Copy constructor
     * @param copy individual to be copied
     */
    @SuppressWarnings({"UnusedDeclaration", "UnusedParameters"})
    AbstractIndividual(AbstractIndividual copy) {
    }

    AbstractIndividual() {
        
    }

    public abstract void map(int map);

    public abstract String getPhenotypeString(int map);

    public abstract Mapper getMapper();

    public abstract Genotype getGenotype();

    public abstract void setMapper(Mapper m);

    public abstract void setGenotype(Genotype g);

    public abstract void setPhenotype(Phenotype p);

    public abstract Phenotype getPhenotype();

    public abstract Individual clone();

    /**
     * Compare the indivdual
     * @param o individual to compare to
     * @return -1,0,-1
     */
    public int compareTo(Individual o) {
        return this.fitness.compareTo(o.getFitness());
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Fitness getFitness(){
        return this.fitness;
    }

    public void setFitness(Fitness f){
        this.fitness = f;
        f.setIndividual(this);
    }

    public boolean isEvaluated() {
        return this.evaluated;
    }

    public void setEvaluated(boolean b) {
        this.evaluated = b;
    }
    
    
}
