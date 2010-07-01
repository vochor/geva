/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Operator.Operations;

import Helpers.GrammarCreator;
import Helpers.IndividualMaker;
import Individuals.Individual;
import Util.Constants;
import Helpers.JUnitHelper;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author erikhemberg
 */
public class ReplacementOperationTest {

    List<Individual> operands;

    public ReplacementOperationTest() {
    }

    @Before
    public void setUp() {
        operands = new ArrayList<Individual>();
        for (int i = 10; i > 0; i--) {
            operands.add(IndividualMaker.makeIndividual());
            operands.get(10-i).getFitness().setDouble(i);
        }
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of setProperties method, of class ReplacementOperation.
     */
    @Test
    public void testSetProperties() {
        System.out.println("* ReplacementOperation: setProperties");
//STEADY_STATE
        Properties p = GrammarCreator.getProperties();
        p.setProperty(Constants.POPULATION_SIZE, "10");
        p.setProperty(Constants.REPLACEMENT_TYPE, Constants.STEADY_STATE);
        ReplacementOperation instance = new ReplacementOperation(0);
        instance.setProperties(p);
        assertEquals(instance.replacementSize, 1);

        //GENERATIONAL
        p = GrammarCreator.getProperties();
        p.setProperty(Constants.POPULATION_SIZE, "10");
        p.setProperty(Constants.REPLACEMENT_TYPE, Constants.GENERATIONAL);
        instance = new ReplacementOperation(0);
        instance.setProperties(p);
        assertEquals(instance.replacementSize, Integer.parseInt(p.getProperty(Constants.POPULATION_SIZE)));

        //GENERATION GAP = 0.5
        p = GrammarCreator.getProperties();
        p.setProperty(Constants.POPULATION_SIZE, "10");
        p.setProperty(Constants.GENERATION_GAP, "0.5");
        p.remove(Constants.REPLACEMENT_TYPE);
        instance = new ReplacementOperation(0);
        instance.setProperties(p);
        assertEquals(instance.replacementSize, 5);

         //GENERATION GAP = -0.5
        p = GrammarCreator.getProperties();
        p.setProperty(Constants.POPULATION_SIZE, "10");
        p.setProperty(Constants.GENERATION_GAP, "-0.5");
        p.remove(Constants.REPLACEMENT_TYPE);
        instance = new ReplacementOperation(0);
        instance.setProperties(p);
        assertEquals(instance.replacementSize, 10);
    }

    /**
     * Test of doOperation method, of class ReplacementOperation.
     */
    @Test
    public void testDoOperation_List() {
        System.out.println("* ReplacementOperation: doOperation");
        ReplacementOperation instance = new ReplacementOperation(2);
        List<Individual> reference = operands;
        instance.doOperation(operands);
        assertEquals(operands.size(), 8);
        assertSame(operands,reference);
        List<Double> dL = new ArrayList<Double>();
        List<Double> fL = new ArrayList<Double>();
        for (int i = operands.size(); i > 0; i--) {
            dL.add(new Double(i));
            fL.add(operands.get(operands.size()-i).getFitness().getDouble());
        }
        JUnitHelper.checkList(fL, dL);
    }

    /**
     * Test of doOperation method, of class ReplacementOperation.
     */
    @Test
    public void testDoOperation_List_int() {
        System.out.println("* ReplacementOperation: doOperation");
        ReplacementOperation instance = new ReplacementOperation(2);
        List<Individual> reference = operands;
        instance.doOperation(operands,4);
        assertEquals(operands.size(), 6);
        assertSame(operands,reference);
        List<Double> dL = new ArrayList<Double>();
        List<Double> fL = new ArrayList<Double>();
        for (int i = operands.size(); i > 0; i--) {
            dL.add(new Double(i));
            fL.add(operands.get(operands.size()-i).getFitness().getDouble());
        }
        JUnitHelper.checkList(fL, dL);
    }
    
}