/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package FitnessEvaluation.MultiSquares;

import Individuals.GEIndividual;
import Mapper.Symbol;
import Util.Constants;
import Util.Enums;
import Helpers.IndividualMaker;
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
public class PictureCopyTest {

    GEIndividual gei;

    public PictureCopyTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {

    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        gei = IndividualMaker.makeIndividual();
        PictureCopy instance = new PictureCopy();
        assertEquals(true,instance !=null);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of setProperties method, of class PictureCopy.
     */
    @Test
    public void testSetProperties() {
        System.out.println("setProperties");
        Properties p = new Properties();
        p.setProperty(Constants.TARGET_PHENOTYPE,"UpDwn_( -20 ) symX_( 100 ) sqr");
        PictureCopy instance = new PictureCopy();
        instance.setProperties(p);
        assertEquals(true,instance.targetFile==null);


        p.setProperty(Constants.TARGET_IMAGE,"/Users/doesntExist");
        try{
            instance.setProperties(p);
        } catch(Exception e) {
            assertTrue(true);
        }
         assertEquals(true,instance.targetFile==null);
      
    }

    /**
     * Test of canCache method, of class PictureCopy.
     */
    @Test
    public void testCanCache() {
        System.out.println("canCache");
        PictureCopy instance = new PictureCopy();
        boolean expResult = true;
        boolean result = instance.canCache();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFitness method, of class PictureCopy.
     */
    @Test
    public void testGetFitness() {
        System.out.println("getFitness");
        String target = "[ UpDwn_( -20 ) symX_( 100 ) tri ] gro_( 2 ) gro_( 2 ) UpDwn_( -20 ) UpDwn_( -20 ) LftRght_( -20 ) sqr";
        String input = "UpDwn_( -20 ) symX_( 100 ) sqr";

        Properties p = new Properties();
        p.setProperty(Constants.TARGET_PHENOTYPE,target);
        PictureCopy instance = new PictureCopy(p);
        instance.init();

        gei.getPhenotype().clear();
        gei.getPhenotype().add(new Symbol("UpDwn_( -20 )", Enums.SymbolType.TSymbol));
        gei.getPhenotype().add(new Symbol("symX_( 100 )", Enums.SymbolType.TSymbol));
        gei.getPhenotype().add(new Symbol("sqr", Enums.SymbolType.TSymbol));
        System.out.println(gei.getPhenotype().getString());
        instance.getFitness(gei);
        assertEquals(true,gei.getFitness().getDouble()==1655);
    }

}