/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Operator;

import Helpers.IndividualMaker;
import Individuals.Individual;
import Individuals.Populations.Population;
import Individuals.Populations.SimplePopulation;
import Operator.Operations.SinglePointCrossover;
import Util.Random.MersenneTwisterFast;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author erikhemberg
 */
public class CrossoverModuleTest {

    MersenneTwisterFast rng = new MersenneTwisterFast(0);
    Population operands;

    public CrossoverModuleTest() {
    }

    @Before
    public void setUp() {
        List<Individual> iL = new ArrayList<Individual>();
        for (int i = 3; i > 0; i--) {
            iL.add(IndividualMaker.makeIndividual());
            iL.get(3-i).getFitness().setDouble(i);
            iL.get(3-i).setValid(true);
        }
        operands = new SimplePopulation();
        operands.addAll(iL);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of perform method, of class CrossoverModule.
     */
    @Test
    public void testPerform() {
        System.out.println("* CrossoverModule: perform");
        CrossoverModule instance = new CrossoverModule(rng, new SinglePointCrossover(rng, 0.99));
        instance.setPopulation(operands);
        instance.perform();
    }

    /**
     * Test of getRandomNotThis method, of class CrossoverModule.
     */
    @Test
    @SuppressWarnings("empty-statement")
    public void testGetRandomNotThis() {
        System.out.println("* CrossoverModule: getRandomNotThis");
        Individual me = operands.get(0);
        operands.remove(operands.get(1));
        CrossoverModule instance = new CrossoverModule(rng, new SinglePointCrossover(rng, 0.9));
        instance.setPopulation(operands);
        Individual expResult = operands.get(1);
        Individual result = instance.getRandomNotThis(me);
        assertEquals(expResult, result);
    }

}