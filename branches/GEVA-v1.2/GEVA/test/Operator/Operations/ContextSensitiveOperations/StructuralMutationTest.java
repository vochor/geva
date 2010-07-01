/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Operator.Operations.ContextSensitiveOperations;

import Helpers.GrammarCreator;
import Helpers.IndividualMaker;
import Helpers.JUnitHelper;
import Individuals.GEChromosome;
import Individuals.GEIndividual;
import Individuals.GEIndividualTest;
import Mapper.ContextualDerivationTree;
import Util.Constants;
import Util.GenotypeHelper;
import Util.Random.MersenneTwisterFast;
import java.util.Properties;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jbyrne
 */
public class StructuralMutationTest {

    Properties p;

    public StructuralMutationTest() {
        p = GrammarCreator.getProperties();
	p.setProperty(Constants.MAX_WRAPS,"0");
        p.setProperty(Constants.DERIVATION_TREE,"Mapper.ContextualDerivationTree");
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }


    /**
     * Test of doOperation method, of class StructuralMutation.
     */
    @Test
    public void testDoOperation_Individual() {
        System.out.println("Structural muation doOperation");
        //Integer.MAX_VALUE
        GEIndividual operand = IndividualMaker.makeIndividual(p);
        int[] chromosome = {0,1,2,1,2,Integer.MIN_VALUE,Integer.MAX_VALUE,0,2,1,1,1};
        int[] expected = {111352301,1,2,1,2,-2147483648,2147483647,0,2,1,1,1};
        GEChromosome geChromosome = (GEChromosome)operand.getGenotype().get(0);
        geChromosome.setAll(chromosome);

        StructuralMutation instance = new StructuralMutation(0.5, new MersenneTwisterFast(2));
        instance.doOperation(operand);

        JUnitHelper.checkArrays(expected, geChromosome.data);
        GEIndividualTest.testInvalidated(operand);

        //Null
        operand = IndividualMaker.makeIndividual(p);
        geChromosome = null;
        try {
            instance.doOperation(operand);
        } catch(NullPointerException e) {
            assertTrue(true);
        }
        GEIndividualTest.testInvalidated(operand);
    }
}