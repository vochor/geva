package Individuals;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author erikhemberg
 */
public class GenotypeTest {

    public GenotypeTest() {
    }

    /**
     * Testing consturctor in Genotype(int i, Chromsome chrom)
     */
    @Test
    public void testGenotype_i_chrom() {
        System.out.println("*GenotypeTest: Genotpye(int i, Chromsome chrom)");
        Chromosome chromsome = new GEChromosome();
        Genotype genotype = new Genotype(1, chromsome);
        assertEquals(chromsome, genotype.get(0));
        assertSame(chromsome, genotype.get(0));
        assertEquals(genotype.size(), 1);
    }
}