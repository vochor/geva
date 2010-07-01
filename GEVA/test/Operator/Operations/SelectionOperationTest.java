/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Operator.Operations;

import Helpers.GrammarCreator;
import Util.Constants;
import java.util.Properties;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author erikhemberg
 */
public class SelectionOperationTest {

    public SelectionOperationTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of setProperties method, of class SelectionOperation.
     */
    @Test
    public void testSetProperties() {
        System.out.println("* SelectionOperation: setProperties");
//STEADY_STATE
        Properties p = GrammarCreator.getProperties();
        SelectionOperation instance = new SelectionOperationMock();
        p.setProperty(Constants.REPLACEMENT_TYPE, Constants.STEADY_STATE);
        p.setProperty(Constants.POPULATION_SIZE, "10");
        instance.setProperties(p);
        assertEquals(instance.size,2);

        //GENERATIONAL
        p = GrammarCreator.getProperties();
        instance = new SelectionOperationMock();
        p.setProperty(Constants.REPLACEMENT_TYPE, Constants.GENERATIONAL);
        p.setProperty(Constants.POPULATION_SIZE, "10");
        instance.setProperties(p);
        assertEquals(instance.size,10);

        //SELECTINO_SIZE = 0.5
        p = GrammarCreator.getProperties();
        instance = new SelectionOperationMock();
        p.setProperty(Constants.SELECTION_SIZE, "0.5");
        p.setProperty(Constants.POPULATION_SIZE, "10");
        p.remove(Constants.REPLACEMENT_TYPE);
        instance.setProperties(p);
        assertEquals(instance.size,5);

            //SELECTINO_SIZE = -0.5
        p = GrammarCreator.getProperties();
        instance = new SelectionOperationMock();
        p.setProperty(Constants.SELECTION_SIZE, "-0.5");
        p.setProperty(Constants.POPULATION_SIZE, "10");
        p.remove(Constants.REPLACEMENT_TYPE);
        try {
            instance.setProperties(p);
        } catch(IllegalArgumentException e) {
            assertTrue(true);
        }
        assertEquals(instance.size,0);
}

}