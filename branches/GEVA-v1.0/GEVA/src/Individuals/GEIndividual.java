/*
 * GEIndividual.java
 *
 * Created on February 23, 2007, 1:54 PM
 *
 */

package Individuals;

import Individuals.FitnessPackage.BasicFitness;
import Individuals.FitnessPackage.Fitness;
import Mapper.GEGrammar;
import Mapper.Mapper;

/**
 * GEIndividual. Has a genotype, Phenotype and a GEGrammar.
 * Has fields for validity and mapped status.
 * Only has one chromosome and grammar
 * @author Blip
 */
@SuppressWarnings({"CloneDoesntCallSuperClone"})
public class GEIndividual extends AbstractIndividual {
    
    private Genotype            genotype;
    private Phenotype           phenotype;
    private GEGrammar           grammar;
    private boolean             mapped;
    private boolean             valid;
    private int                 usedCodons;
    private int                 usedWraps;
    
    /** Creates a new instance of GEIndividual
     *
     */
    public GEIndividual() {
        super();
        this.mapped =  false;
        this.valid = false;
        this.usedCodons = -1;
        this.usedWraps = -1;
    }

    /**
     * Create new GEindividual instance
     * @param g mapper(grammar)
     * @param p output(phenotype)
     * @param gen input(genotype)
     * @param f fitness
     */
    public GEIndividual(GEGrammar g, Phenotype p, Genotype gen, Fitness f) {
        this.grammar = g;
        this.phenotype = p;
        this.genotype = gen;
        this.fitness = f;
        this.evaluated = false;
        this.mapped = false;
        this.valid = false;
        this.usedCodons = -1;
        this.usedWraps = -1;
        //Setting genotype and phenotype
        this.grammar.setPhenotype(this.phenotype);
        this.grammar.setGenotype(this.genotype.get(0));
        this.fitness.setIndividual(this);
        this.age = 0;
    }
    
    /**
     * Copy constructor
     * @param i individual to copy
     */
    private GEIndividual(GEIndividual i) {
        super(i);
        this.grammar = new GEGrammar(i.grammar);
        this.phenotype = new Phenotype(i.phenotype);
        this.genotype = new Genotype(1);
        this.genotype.add(new GEChromosome((GEChromosome)i.getGenotype().get(0)));
        this.fitness = new BasicFitness(i.fitness.getDouble(), this);
        this.grammar.setGenotype(this.genotype.get(0));
        this.grammar.setPhenotype(this.phenotype);

    }

    /**
     * Invalidate the individual.
     */
    public void invalidate() {
        this.usedCodons = -1;
        this.usedWraps = -1;
        this.valid = false;
        this.mapped = false;
        this.evaluated = false;// All new individuals must be evaluated seperaately
        this.age = 1;
        //this.fitness.setDefault();
    }

    /**
     * Is the individual mapped
     * @return mapped
     */
    public boolean isMapped() {
        return mapped;
    }

    /**
     * Set the mapped status of the individual
     * @param mapped status of the individuals mapping
     */
    public void setMapped(boolean mapped) {
        this.mapped = mapped;
    }

    public boolean isValid() {
        return this.valid;
    }

    public void setValid(boolean b) {
        this.valid = b;
    }

    /**
     * Set how many codons were used
     * @param usedCodons number of codons used
     */
    public void setUsedCodons(int usedCodons) {
        this.usedCodons = usedCodons;
    }

    /**
     * Set how many wraps were used
     * @param usedWraps number of wraps used
     */
    public void setUsedWraps(int usedWraps) {
        this.usedWraps = usedWraps;
    }
    
    /**
     * Setting these both here so as to negate the need for multiple
     * copies of the Mapper.
     * @param map index to use
     */
    public void map(int map) {
        if(!this.mapped) {
            //Clear the phenotype
            this.phenotype.clear();
            this.valid = this.grammar.genotype2Phenotype(true);
            this.mapped = true;
            this.usedCodons = this.grammar.getUsedCodons();
            this.usedWraps = this.grammar.getUsedWraps();
        }
    }

    /**
     * @param map phenotype to chose
     * @return output string
     */
    public String getPhenotypeString(int map) {
        return this.phenotype.getString();
    }
    
    public Genotype getGenotype() {
        return this.genotype;
    }
    
    public Mapper getMapper() {
        return this.grammar;
    }
    
    public void setMapper(Mapper m) {
        this.grammar = (GEGrammar)m;
    }

    /**
     * Invalidates the individual because a change has been made to the genotype.
     * Sets the new genotype in the indivudual as well as in the mapper
     * @param g genotype
     */
    public void setGenotype(Genotype g) {
        this.invalidate();
        this.genotype = g;
        this.getMapper().setGenotype(this.genotype.get(0));
    }
    
    public void setPhenotype(Phenotype p) {
        this.phenotype = p;
    }
    
    public Phenotype getPhenotype() {
        return this.phenotype;
    }

    /**
     * Clone this individual, invialidate and return the clone
     * @return Individual cloned and invalidated individual
     */
    public Individual clone() {
        GEIndividual ind = new GEIndividual(this);
        ind.invalidate();
        return ind;
    }
    
    public String toString() {
        return this.phenotype.getString();
    }

    /**
     * Get number of codons used for mapping
     * @return codons used for mapping
     */
    public int getUsedCodons() {
        return usedCodons;
    }

    /**
     * Get number of wraps used for mapping
     * @return wraps used
     */
    public int getUsedWraps() {
        return usedWraps;
    }

}
