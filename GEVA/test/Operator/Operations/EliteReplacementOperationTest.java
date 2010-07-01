/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Operator.Operations;

import Helpers.GrammarCreator;
import Helpers.IndividualMaker;
import Individuals.Individual;
import Util.Constants;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author erikhemberg
 */
public class EliteReplacementOperationTest {

    public EliteReplacementOperationTest() {
    }

    /**
     * Test of setProperties method, of class EliteReplacementOperation.
     */
    @Test
    public void testSetProperties() {
        System.out.println("* EliteReplacementOperation: setProperties");
        Properties p = GrammarCreator.getProperties();
        p.setProperty(Constants.ELITE_SIZE, "1");
        EliteReplacementOperation instance = new EliteReplacementOperation(0);
        instance.setProperties(p);
        assertEquals(instance.getEliteSize(), Integer.parseInt(p.getProperty(Constants.ELITE_SIZE)));

        //ELITE_SIZE -1
        instance = new EliteReplacementOperation(0);
        p.setProperty(Constants.ELITE_SIZE, "-1");
        instance.setProperties(p);
        assertEquals(instance.getEliteSize(), 0);
    }

    /**
     * Test of doOperation method, of class EliteReplacementOperation.
     */
    @Test
    public void testDoOperation_List() {
        System.out.println("* EliteReplacementOperation: doOperation");
        List<Individual> operands = new ArrayList<Individual>();
        operands.add(IndividualMaker.makeIndividual());
        operands.get(0).getFitness().setDouble(2.0);
        operands.get(0).setValid(true);
        operands.get(0).setEvaluated(true);
        operands.add(IndividualMaker.makeIndividual());
        operands.get(1).getFitness().setDouble(1.0);
        operands.get(1).setValid(true);
        operands.get(1).setEvaluated(true);
        Individual ind = operands.get(1);
        EliteReplacementOperation instance = new EliteReplacementOperation(1);
        instance.doOperation(operands);
        assertEquals(instance.getEliteSize(),1);
        assertEquals(operands.get(0).getFitness().getDouble(),1.0,0.001);
        assertSame(ind,operands.get(0));
       }

}