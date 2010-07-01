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
import java.util.ArrayList;
import java.util.Properties;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jbyrne
 */
public class NodalMutationTest {

    Properties p;
    Properties p2;

    public NodalMutationTest() {
        p = GrammarCreator.getProperties();
        p2 = GrammarCreator.getProperties();
	p.setProperty(Constants.MAX_WRAPS,"0");
        p.setProperty(Constants.DERIVATION_TREE,"Mapper.ContextualDerivationTree");
	p2.setProperty(Constants.MAX_WRAPS,"0");
        p2.setProperty(Constants.DERIVATION_TREE,"Mapper.ContextualDerivationTree");
        String file_name = GrammarCreator.getGrammarFile("test_gec.bnf");
        p2.setProperty(Constants.GRAMMAR_FILE, file_name);
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of doOperation method, of class NodalMutation.
     * create an instance, mutate it, see if its okay
     */
    @Test
    public void testDoOperation_Individual() {
        System.out.println("Nodal muation doOperation");
        //Integer.MAX_VALUE
        GEIndividual operand = IndividualMaker.makeIndividual(p);
        int[] chromosome = {0,1,2};
        int[] expected = {0,1,111352301};
        GEChromosome geChromosome = (GEChromosome)operand.getGenotype().get(0);
        geChromosome.setAll(chromosome);

        NodalMutation instance = new NodalMutation(0.5, new MersenneTwisterFast(2));
        instance.doOperation(operand);
        JUnitHelper.checkArrays(expected, geChromosome.data);
        GEIndividualTest.testInvalidated(operand);
        
        //test to make sure its invalidated
        geChromosome = null;
        try {
            instance.doOperation(operand);
        } catch(NullPointerException e) {
            assertTrue(true);
        }
        GEIndividualTest.testInvalidated(operand);
    }

    /**
     * Test of doOperation method, of class NodalMutation.
     * create an instance, mutate it, see if its okay
     */
    @Test
    public void testDoOperation_codonList() {
        System.out.println("Nodal mutation codonlist");
        //Integer.MAX_VALUE
        GEIndividual operand = IndividualMaker.makeIndividual(p);
        int[] chromosome = {1,1,2,1,1,2,4,6,7,8,9,9,9,0,5,4,3};
        GEChromosome geChromosome = (GEChromosome)operand.getGenotype().get(0);
        geChromosome.setAll(chromosome);
        ContextualDerivationTree tree = (ContextualDerivationTree) GenotypeHelper.buildDerivationTree(operand);
        System.out.println(tree.toString());
        ArrayList<Integer> expected = tree.getNodeCodonList();
        NodalMutation instance = new NodalMutation(0.5, new MersenneTwisterFast(2));
        instance.doOperation(operand);


        tree = (ContextualDerivationTree) GenotypeHelper.buildDerivationTree(operand);
        ArrayList<Integer> result = tree.getNodeCodonList();;
        System.out.println("expected"+expected.toString());
        System.out.println("result"+result.toString());
        System.out.println(tree.toString());
        JUnitHelper.checkArrays(expected, result);
        GEIndividualTest.testInvalidated(operand);
    }



        //this tests that it will mutate gecodonvalues
        @Test
	public void testDoOperation_GECodonValue() {
	    GEIndividual operand = IndividualMaker.makeIndividual(p2);
	    int[] chromosome = {1,2,1,1,2,2,0,0};
	    int[] expected = {1,2,1937831252,1,2,1748719057,0,0,};
	    GEChromosome geChromosome = (GEChromosome)operand.getGenotype().get(0);
	    geChromosome.setAll(chromosome);

        System.out.println("Operand:"+operand);
	    ContextualDerivationTree tree = (ContextualDerivationTree) GenotypeHelper.buildDerivationTree(operand);
	    System.out.println("BEFORE "+operand.getGenotype());
	    //FIXME Erik Commenting out string because it threw null pointer and I did not know why. And it did not seem to matter to the test what was printed??
	    //System.out.println(tree.toString());

	    NodalMutation instance = new NodalMutation(1.0, new MersenneTwisterFast(0));
	    instance.doOperation(operand);

	    tree = (ContextualDerivationTree) GenotypeHelper.buildDerivationTree(operand);
	    System.out.println("AFTER "+operand.getGenotype());
	    //	    System.out.println(tree.toString());

	    //JUnitHelper.checkArrays(expected, geChromosome.data);
	    //GEIndividualTest.testInvalidated(operand);

        }

}