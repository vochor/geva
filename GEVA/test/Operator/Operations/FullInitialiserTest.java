/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Operator.Operations;

import Helpers.GrammarCreator;
import Individuals.GEChromosome;
import Individuals.Genotype;
import Mapper.GEGrammar;
import Mapper.Symbol;
import Util.Constants;
import Util.Random.MersenneTwisterFast;
import Util.Random.RandomNumberGenerator;
import Util.Structures.NimbleTree;
import Util.Structures.TreeNode;
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
public class FullInitialiserTest {

    FullInitialiser fi1;
    RandomNumberGenerator rng;
    GEGrammar geg;
    Properties p;

    public FullInitialiserTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        rng = new MersenneTwisterFast();
        rng.setSeed(10);
        p = GrammarCreator.getProperties();   
        p.setProperty(Constants.DERIVATION_TREE, "Mapper.DerivationTree");
        p.setProperty(Constants.MAX_DEPTH, "8");
        geg = new GEGrammar(p);
        fi1 = new FullInitialiser(rng, geg, p);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of grow method, of class FullInitialiser.
     */
    @Test
    public void testGrow() {
        NimbleTree<Symbol> dt = new NimbleTree<Symbol>();
        TreeNode<Symbol> tn = new TreeNode<Symbol>();
        tn.setData(geg.getStartSymbol());
        dt.populateStack();
        dt.setRoot(tn);
        dt.setCurrentNode(dt.getRoot());
        int expResult = dt.getDepth();
        fi1.genotype = new Genotype();
        fi1.chromosome = new GEChromosome(100);
        fi1.chromosome.setMaxChromosomeLength(1000);
        fi1.genotype.add(fi1.chromosome);
        boolean result = fi1.grow(dt);
        assertEquals(true, result);
        assertEquals(true, expResult < dt.getDepth());
    }
}