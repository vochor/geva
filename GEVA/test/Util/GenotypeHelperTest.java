/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Util;

import Helpers.GrammarCreator;
import Helpers.IndividualMaker;
import Individuals.GEIndividual;
import Mapper.ContextualDerivationTree;
import Mapper.DerivationTree;
import java.util.Properties;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jbyrne
 */
public class GenotypeHelperTest {

    GEIndividual gei;
    GEIndividual gei2;
    public GenotypeHelperTest() {
    }

    @Before
    public void setUp() {
        Properties p = GrammarCreator.getProperties();
	p.setProperty(Constants.MAX_WRAPS,"0");
        p.setProperty(Constants.DERIVATION_TREE,"Mapper.ContextualDerivationTree");
        gei = IndividualMaker.makeIndividual(p);
        p.setProperty(Constants.DERIVATION_TREE,"Mapper.DerivationTree");
        gei2 = IndividualMaker.makeIndividual(p);
    }

    @After
    public void tearDown() {
    }


    /**
     * Test of buildDerivationTree method, of class GenotypeHelper.
     */
    @Test
    public void testBuildDerivationTree_Individual() {
        System.out.println("buildDerivationTree");
        DerivationTree result = GenotypeHelper.buildDerivationTree(gei);
        assertEquals(true, result instanceof ContextualDerivationTree);
        result = GenotypeHelper.buildDerivationTree(gei2);
        assertEquals(true, result instanceof DerivationTree);
    }



}