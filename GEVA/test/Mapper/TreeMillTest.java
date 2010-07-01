/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Mapper;

import Individuals.GEChromosome;
import Individuals.GEIndividual;
import Util.Constants;
import Helpers.GrammarCreator;
import Helpers.IndividualMaker;
import java.util.Properties;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jbyrne
 */
public class TreeMillTest {

    Properties p;
    GEGrammar rgeg;
    GEGrammar pgeg;
    GEGrammar pageg;
    GEGrammar cgeg;
    GEChromosome gen;


    public TreeMillTest() {
    }

    @Before
    public void setUp() {
        gen = GrammarCreator.getGEChromosome();
        p = GrammarCreator.getProperties();
	p.setProperty(Constants.MAX_WRAPS,"0");
        p.setProperty(Constants.DERIVATION_TREE,"Mapper.ContextualDerivationTree");
        cgeg = new GEGrammar(p);
        cgeg.setGenotype(gen);
        p.setProperty(Constants.DERIVATION_TREE,"Mapper.DerivationTree");
        pageg = new GEGrammar(p);
        pageg.setGenotype(gen);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getDerivationTree method, of class TreeMill.
     */
    @Test
    public void testGetDerivationTree() {

        System.out.println("testing ContextualDerivationTree");
        DerivationTree result = TreeMill.getDerivationTree(cgeg);
        assertEquals(true,result instanceof ContextualDerivationTree);

        System.out.println("testing parent");
        result = TreeMill.getDerivationTree(cgeg);
        assertEquals(true,result instanceof DerivationTree);

        System.out.println("testing false");
	result = TreeMill.getDerivationTree(pageg);
        assertEquals(false,result instanceof ContextualDerivationTree);

        //Null
        System.out.println("testing null");
        GEIndividual gei = IndividualMaker.makeIndividual();
        ((GEGrammar)gei.getMapper()).setDerivationTreeType(null);
        result = null;
        try {
            result = TreeMill.getDerivationTree((GEGrammar)gei.getMapper());
        } catch(NullPointerException e) {
            assertTrue(true);
        }
        assertEquals(false,result instanceof DerivationTree);

    }

}