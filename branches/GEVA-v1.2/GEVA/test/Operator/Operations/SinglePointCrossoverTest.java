/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Operator.Operations;

import Individuals.GEChromosome;
import Individuals.GEIndividual;
import Individuals.Individual;
import Util.Constants;
import Helpers.IndividualMaker;
import Helpers.JUnitHelper;
import Util.Random.MersenneTwisterFast;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author erikhemberg
 */
public class SinglePointCrossoverTest {

    public SinglePointCrossoverTest() {
    }

    @Before
    public void setUp() {
    }

    /**
     * Test of setProperties method, of class SinglePointCrossover.
     */
    @Test
    public void testSetProperties() {
        System.out.println("*SinglePointCrossoverTest: setProperties");

        //Default
        Properties p = new Properties();
        SinglePointCrossover instance = new SinglePointCrossover(new MersenneTwisterFast(0), p);
        instance.setProperties(p);
        assertEquals(false, instance.fixedCrossoverPoint);
        assertEquals(false, instance.codonsUsedSensitive);

        //CodonsUsed, fixedCrossoverPoint true
        p.setProperty(Constants.FIXED_POINT_CROSSOVER,"true");
        p.setProperty(Constants.CODONS_USED_SENSITIVE,"true");
        instance.setProperties(p);
        assertEquals(true, instance.fixedCrossoverPoint);
        assertEquals(true, instance.codonsUsedSensitive);
    }

    /**
     * Test of doOperation method, of class SinglePointCrossover.
     */
    @Test
    public void testDoOperation_List() {
        System.out.println("*SinglePointCrossoverTest: doOperation");

        //CodonsUsed false
        List<Individual> operands = new ArrayList<Individual>();
        for (int i = 0; i < 2; i++) {
            GEIndividual individual = IndividualMaker.makeIndividual();
            ((GEChromosome) individual.getGenotype().get(0)).setMaxChromosomeLength(20);
            operands.add(individual);
        }
        int[] ia = {1,1,1,1,1,1,1,1,1,1};
        int[] ia2 = {1,1,1,1,0,0,0,0,0,0,0,0,0};
        int[] ia3 = {0,1,1,1,1,1,1};
        ((GEChromosome)operands.get(0).getGenotype().get(0)).setAll(ia);
        SinglePointCrossover instance = new SinglePointCrossover(new MersenneTwisterFast(1), 1.0);
        instance.setFixedCrossoverPoint(false);
        instance.codonsUsedSensitive = false;
        instance.doOperation(operands);
        JUnitHelper.checkArrays(ia2, ((GEChromosome)operands.get(0).getGenotype().get(0)).toArray());
        JUnitHelper.checkArrays(ia3, ((GEChromosome) operands.get(1).getGenotype().get(0)).toArray());

//CodonsUsed true (Using chromsome from above
        //Not good for testing CodonsUsed FIXME
        instance.codonsUsedSensitive = true;
        ((GEIndividual)operands.get(0)).setPreviouslyUsedCodons(4);
        ((GEIndividual)operands.get(1)).setPreviouslyUsedCodons(6);
        int[] ia4 = ia3;
        int[] ia5 = ia2;
        instance.doOperation(operands);
        JUnitHelper.checkArrays(ia4, ((GEChromosome)operands.get(0).getGenotype().get(0)).toArray());
        JUnitHelper.checkArrays(ia5, ((GEChromosome) operands.get(1).getGenotype().get(0)).toArray());
    }

    /**
     * Test of makeNewChromosome method, of class SinglePointCrossover.
     */
    @Test
    public void testMakeNewChromosome() {
        System.out.println("*SinglePointCrossoverTest: makeNewChromosome");
        MersenneTwisterFast mft = new MersenneTwisterFast(0);
        
        //Variable point xo
        GEChromosome c1 = new GEChromosome(6);
        GEChromosome c2 = new GEChromosome(6);
        for (int i = 0; i < 6; i++) {
            c1.add(1);
            c2.add(2);
        }
        int[] ia = {1,1,1,1,2,2,2,2,2};
        int[] ia2 = {2,1,1};

        SinglePointCrossover instance = new SinglePointCrossover(mft, 1.0);
        instance.setFixedCrossoverPoint(false);
        instance.makeNewChromosome(c1, c2, c1.size(), c2.size());
        JUnitHelper.checkArrays(ia, c1.toArray());
        JUnitHelper.checkArrays(ia2, c2.toArray());

        //Fixed point xo
        instance.setFixedCrossoverPoint(true);
        c1.clear();
        c2.clear();
        for(int i = 0; i < 6; i++) {
            c1.add(1);
            c2.add(2);
        }
        c1.add(1);
        int[] ia3 = {2,2,1,1,1,1,1};
        int[] ia4 = {1,1,2,2,2,2};
        instance.makeNewChromosome(c1, c2, c1.size(), c2.size());
        JUnitHelper.checkArrays(ia3, c1.toArray());
        JUnitHelper.checkArrays(ia4, c2.toArray());
    }
    
}