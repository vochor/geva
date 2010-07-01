/*
 * Genotype.java
 *
 * Created on March 5, 2007, 7:59 PM
 */

package Individuals;

import java.util.ArrayList;

/**
 * Genotype is an ArryaList<Chromosome>.
 * This is a container for one or more chromosomes.
 * For most usage a single chromosome will be sufficient so the behavior of the 
 * Class will be to provide a wrapper for the chromosome class.
 *
 * @author Blip
 */
public class Genotype extends ArrayList<Chromosome>{
    
    /** Creates a new instance of Genotype */
    public Genotype() {
        super();
        
    }

    @SuppressWarnings({"SameParameterValue"})
    public Genotype(int i) {
        super(i);
    }

    /**
     * Copy constructor
     * @param g genotyp to copy
     */
    public Genotype(Genotype g) {
        super(g);
    }

    @SuppressWarnings({"SameParameterValue"})
    public Genotype(int i, Chromosome chrom) {
        super(i);
        this.add(chrom);
    }
    
}
