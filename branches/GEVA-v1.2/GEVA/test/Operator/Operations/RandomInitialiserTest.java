package Operator.Operations;

import Individuals.Individual;
import Mapper.GEGrammar;
import Util.Constants;
import Helpers.GrammarCreator;
import Util.Random.MersenneTwisterFast;
import Util.Random.RandomNumberGenerator;
import java.util.Properties;
import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import static org.junit.Assert.*;

/**
 *
 * @author jbyrne
 */
public class RandomInitialiserTest {

    RandomInitialiser ri1;
    RandomInitialiser ri2;
    RandomNumberGenerator rng;
    GEGrammar cgeg;
    GEGrammar geg;
    Properties p;

    public RandomInitialiserTest() {
    }

    @Before
    public void setUp() {
        rng = new MersenneTwisterFast();
        p = GrammarCreator.getProperties();
	p.setProperty(Constants.MAX_WRAPS,"0");
        p.setProperty(Constants.DERIVATION_TREE, "Mapper.ContextualDerivationTree");
        cgeg = new GEGrammar(p);
        ri1 = new RandomInitialiser(rng, cgeg, p);
        p.setProperty(Constants.DERIVATION_TREE, "Mapper.DerivationTree");
        geg = new GEGrammar(p);
        ri2 = new RandomInitialiser(rng, geg, p);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getRNG method, of class RandomInitialiser.
     */
    @Test
    public void testGetRNG() {
        System.out.println("getRNG");
        RandomNumberGenerator result = ri1.getRNG();
        assertEquals(true, result instanceof MersenneTwisterFast);
    }

    /**
     * Test of createIndividual method, of class RandomInitialiser.
     */
    @Test
    public void testCreateIndividual() {
        System.out.println("createIndividual");

        Individual result = ri1.createIndividual();
        GEGrammar grammar = (GEGrammar) result.getMapper();
        System.out.println("the grammar is:" + grammar.getDerivationString());
        assertEquals(true, grammar.getDerivationString().equals("Mapper.ContextualDerivationTree"));

        result = ri2.createIndividual();
        grammar = (GEGrammar) result.getMapper();
        System.out.println("the grammar is:" + grammar.getDerivationString());
        assertEquals(true, grammar.getDerivationString().equals("Mapper.DerivationTree"));

    }
}