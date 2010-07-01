/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Operator.Operations;

import Individuals.GEChromosome;
import Individuals.GEIndividual;
import Individuals.GEIndividualTest;
import Helpers.IndividualMaker;
import Helpers.JUnitHelper;
import Util.Random.MersenneTwisterFast;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author erikhemberg
 */
public class IntFlipMutationTest {

    public IntFlipMutationTest() {
    }

    /**
     * Test of doOperation method, of class IntFlipMutation.
     */
    @Test
    public void testDoOperation_Individual() {
    System.out.println("*IntFlipMutationTest: doOperation");
        //Integer.MAX_VALUE
        GEIndividual operand = IndividualMaker.makeIndividual();
        int[] chromosome = {0,2,Integer.MIN_VALUE,Integer.MAX_VALUE,0,2};
        int[] expected = {0, 2, Integer.MIN_VALUE, Integer.MAX_VALUE,1520873195,1277901399};
        GEChromosome geChromosome = (GEChromosome)operand.getGenotype().get(0);
        geChromosome.setAll(chromosome);
        IntFlipMutation instance = new IntFlipMutation(0.5, new MersenneTwisterFast(0));
        instance.doOperation(operand);
        JUnitHelper.checkArrays(expected, geChromosome.data);
        GEIndividualTest.testInvalidated(operand);

        //Null
        operand = IndividualMaker.makeIndividual();
        geChromosome = null;
        try {
            instance.doOperation(operand);
        } catch(NullPointerException e) {
            assertTrue(true);
        }
        GEIndividualTest.testInvalidated(operand);
    }

}